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
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes");
		        
		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;
		        
		//Attende che la pagina carichi la tabella
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
				
		Integer last= Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());

		List<WebElement> liWeb=new ArrayList<>();
		for (Integer i=1;i<last+1;i++) {
			js.executeScript("document.querySelector('[data-num=\""+i.toString()+"\"]').click()");	
			for(int j=1;j<16;j++)
			//liWeb=driver.findElements(By.tagName("tr"));
			//liWeb.addAll(driver.findElements(By.tagName("tr")));
				System.out.println(driver.findElements(By.tagName("tr")).get(j).getText());
			//System.out.print(liWeb.get(j).getTagName());
		}	
		
		
		//System.out.print(liWeb.size());
		//System.out.print(liWeb.get(2).getTagName());
		//li.add(new Calciatore(liWeb.get(0).findElement(By.tagName("tr")).getText(), url, url, url, 0, 0, 0, 0, 0))
		
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
				li.add(new Calciatore(
						count,
						e.select("td.field-giocatore").text(),
						e.select("td.field-ruolo").text(),
						e.select("td.field-sqd").text(),
						ParseInt(e.select("td.field-pg").text()),
						ParseInt(e.select("td.field-g").text()),
						ParseInt(e.select("td.field-am").text()),
						ParseInt(e.select("td.field-es").text()),
						ParseFloat(e.select("td.field-mv").text())));
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
