package manageData;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

import utils.Pair;

public class ScrapingImpl implements Scraping{
	private String url="https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
	private Document doc;	//html del sito
	private List<Calciatore> li;

	public ScrapingImpl() throws FileNotFoundException, IOException {
		li=new ArrayList<>();
		
		int nThread=7;
		
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
		
		
		
		/*
		
		try (Scanner sc = new Scanner(new File("res/backup.txt"))) {
			sc.useDelimiter("\\s+");
			while(sc.hasNext()) {
				li.add(new Calciatore(
						Integer.parseInt(sc.next()), //id 
						"",	//nome
						sc.next().substring(0, 1),	//ruolo
						sc.next(),	//squadra
						Integer.parseInt(sc.next()),	//pg
						Integer.parseInt(sc.next()),	//minuti
						Integer.parseInt(sc.next()),	//gol
						Integer.parseInt(sc.next()),	//tiri
						Integer.parseInt(sc.next()),	//dribling
						Integer.parseInt(sc.next()),	//assist
						Integer.parseInt(sc.next()),	//passaggi
						Integer.parseInt(sc.next()),	//passaggiChiave
						Integer.parseInt(sc.next()),	//ammonizioni
						Integer.parseInt(sc.next()),	//espulsioni
						Integer.parseInt(sc.next()),	//rubati
						Integer.parseInt(sc.next()),	//tackle
						Integer.parseInt(sc.next()),	//cleanSheet
						Integer.parseInt(sc.next())	//parate
						));
				System.out.print(sc.next());
			}
			sc.close();
		}
		
		
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
