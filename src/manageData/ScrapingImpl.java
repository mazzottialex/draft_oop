package manageData;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrapingImpl implements Scraping{
	private String url="https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
	private Document doc;	//html del sito
	private List<Calciatore> li;

	public ScrapingImpl() throws FileNotFoundException, IOException {
		li=new ArrayList<>();
		
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
				
		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes");
		        
		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;
		        
		//Attende che la pagina carichi la tabella
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
				
		Integer last= Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());
		
		for (Integer i=1;i<last+1;i++) {
			js.executeScript("document.querySelector('[data-num=\""+i.toString()+"\"]').click()");
			
			//System.out.println(driver.findElement(By.tagName("tbody")).getText());
			 	for(int j=1;j<16;j++) {
				List<WebElement> riga=driver.findElements(By.tagName("tr")).get(j).findElements(By.tagName("td"));		
				li.add(new Calciatore(
						Integer.parseInt(riga.get(0).getText()), //id 
						riga.get(1).getText(),	//nome
						riga.get(2).getText().substring(0, 1),	//ruolo
						riga.get(3).getText(),	//squadra
						Integer.parseInt(riga.get(7).getText()),	//pg
						Integer.parseInt(riga.get(9).getText()),	//minuti
						Integer.parseInt(riga.get(10).getText()),	//gol
						Integer.parseInt(riga.get(11).getText()),	//tiri
						Integer.parseInt(riga.get(14).getText()),	//dribling
						Integer.parseInt(riga.get(15).getText()),	//assist
						Integer.parseInt(riga.get(16).getText()),	//passaggi
						Integer.parseInt(riga.get(17).getText()),	//passaggiChiave
						Integer.parseInt(riga.get(20).getText()),	//ammonizioni
						Integer.parseInt(riga.get(21).getText()),	//espulsioni
						Integer.parseInt(riga.get(22).getText()),	//rubati
						Integer.parseInt(riga.get(23).getText()),	//tackle
						Integer.parseInt(riga.get(24).getText()),	//cleanSheet
						Integer.parseInt(riga.get(25).getText())	//parate
						));
				System.out.print(j);
				//System.out.println(driver.findElements(By.tagName("tr")).get(j).findElements(By.tagName("td")).get(0).getText());
			}
		}	
		
		driver.quit();
		
		/*
		
		try {
			doc=Jsoup.connect(url).get();
			SaveHTML(doc);
		} catch (IOException e) {
			doc=LoadHTML();			
		}
		
		CreateList();*/
	}
	
	private void SaveHTML(Document doc) throws IOException {
		try(final OutputStream file = new FileOutputStream("res/backup.txt");
			final OutputStream bstream = new BufferedOutputStream(file);
			final DataOutputStream dstream=new DataOutputStream(file);
				){
			dstream.writeBytes(doc.html());
		}
		
	}
	
	private Document LoadHTML() throws FileNotFoundException, IOException {
		File file=new File("res/backup.txt");
		return Jsoup.parse(file);
	}

	

	public List<Calciatore> getLista() {
		return li;
	}
	
	public Document getDoc() {
		return doc;
	}

	private void CreateList() {
		int count=0;
		for(Element e : doc.select("tr")) {
			if(e.select("td.field-giocatore").text()!="")
			{
				count++;
				
			}
		}
	}
	
	private int ParseInt(String s) {
		int ret;
		try {
			ret=Integer.parseInt(s);
		}
		catch(Exception e) {
			ret=0;
		}
		return ret;
	}
	
	private float ParseFloat(String s) {
		float ret;
		try {
			ret=Float.parseFloat(s);
		}
		catch(Exception e) {
			ret=(float) 0.0;
		}
		return ret;
	}
}
