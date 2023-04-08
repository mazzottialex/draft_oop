package manageData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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

	public ScrapingImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		li=new ArrayList<>();
		
		int nThread=9;
		
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
		
		SaveHTML();
		
		li=LoadHTML();
	}
	
	private void SaveHTML() throws IOException {
		try(final OutputStream file = new FileOutputStream("res/backup.txt");
			final OutputStream bstream = new BufferedOutputStream(file);
			final ObjectOutputStream ostream=new ObjectOutputStream(file);
				){
			ostream.writeObject(li);
			ostream.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Calciatore> LoadHTML() throws IOException, ClassNotFoundException {
		List<Calciatore> c;
		try(final InputStream file = new FileInputStream("res/backup.txt");
			final InputStream bstream = new BufferedInputStream(file);
			final ObjectInputStream ostream=new ObjectInputStream(file);
					){
				c=(List<Calciatore>)ostream.readObject();
				ostream.close();
			}
		return c;
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
