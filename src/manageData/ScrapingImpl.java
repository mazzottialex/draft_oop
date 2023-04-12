package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Pair;

public class ScrapingImpl implements Scraping{
	private List<Calciatore> li=new ArrayList<>();
	private final int nThread;
	private final String url="https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";

	public ScrapingImpl(int nThread) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.nThread=nThread;
		ManageThreads(); //restituisce nella li tutti i calciatori
	}
	
	public ScrapingImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		this.nThread=7;
		//ManageThreads(); //restituisce nella li tutti i calciatori
	}
	
	private void ManageThreads() {
		List<Pair<RunnableScraping, Thread>> liThr=new ArrayList<>();
		
		for(int i=0; i<nThread;i++) {
			RunnableScraping runnable=new RunnableScraping(i, nThread);
			Thread thr=new Thread(runnable);
			liThr.add(new Pair<>(runnable,thr));
			thr.start();
		}
		
		liThr.forEach(el-> {
			try {
				el.getY().join();
				li.addAll(el.getX().getLi());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	public List<String> getStagioni(){
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");

		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver();
		driver.get(url);

		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;

		js.executeScript("document.querySelector('[data-id=\"selectPickerSeasons\"]').click()");
		
		List<String> li=new ArrayList<>();
		driver.findElement(By.tagName("ul")).findElements(By.tagName("li"))
											.stream()
											.map(el->el.getText())
											.forEach(el->li.add(el));
		driver.quit();
		return li;
	}
	public List<Calciatore> getLista() {
		return li;
	}
}
