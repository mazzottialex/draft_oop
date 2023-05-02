package scraping;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import data.Calciatore;
import utils.Pair;

public class ScrapingImpl implements Scraping{
	private List<Calciatore> li=new ArrayList<>();
	private String url="https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
	private int nThread;
	
	public ScrapingImpl(){
		this.nThread=7; //default
	}
	
	public ScrapingImpl(int nThread){
		this.nThread=nThread;
	}
	
	public List<Calciatore> getLista() {
		return li;
	}
	
	public Boolean ReadTable(String stagione) {
		List<Pair<RunnableScrapingData, Thread>> liThr=new ArrayList<>();
		
		for(int i=0; i<nThread;i++) {
			RunnableScrapingData runnable=new RunnableScrapingData(i, nThread, stagione);
			Thread thr=new Thread(runnable);
			liThr.add(new Pair<>(runnable,thr));
			thr.start();
		}
		
		
		for (Pair<RunnableScrapingData, Thread> el: liThr) {
			try {
				el.getY().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!el.getX().check()) {
				return false;
			}		
			li.addAll(el.getX().getLi());
		}
		return true;
	}

	public Boolean ReadTable() {
		String defaultStagione="2022-2023";
		return this.ReadTable(defaultStagione);
	}
	
	public List<String> getStagioni(){
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");

		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);

		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;

		js.executeScript("document.querySelector('[data-id=\"selectPickerSeasons\"]').click()");
		
		List<String> li=new ArrayList<>();
		driver.findElement(By.tagName("ul")).findElements(By.tagName("li"))
											.stream()
											.map(el->el.getText())
											.map(str->str.replace("/", "-"))
											.forEach(el->li.add(el));
		driver.quit();
		return li;
	}
	
	
}
