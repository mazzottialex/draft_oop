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
	
	public List<Calciatore> getLista(String stagione) {
		this.ReadTable(stagione);
		return li;
	}
	
	public List<Calciatore> getLista() {
		String defaultStagione="2022-2023";
		return this.getLista(defaultStagione);
	}
	
	private Boolean ReadTable(String stagione) {
		
		List<Pair<RunnableScraping, Thread>> liThr=new ArrayList<>();
		
		for(int i=0; i<nThread;i++) {
			RunnableScraping runnable=new RunnableScraping(i, nThread, stagione);
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
		for (Pair<RunnableScraping, Thread> el: liThr) {
			//el.getY().join();
			if(!el.getX().check()) {
				System.out.print("err");
				return false;
			}		
			li.addAll(el.getX().getLi());
		}
		return true;
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
