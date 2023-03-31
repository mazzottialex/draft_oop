package manageData;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScrapingImpl implements Scraping{
	private String url="https://www.gazzetta.it/calcio/fantanews/statistiche/serie-a-2022-23/";
	private Document doc;	//html del sito
	private List<Calciatore> li;

	public ScrapingImpl() throws FileNotFoundException, IOException {
		li=new ArrayList<>();
		
		try {
			doc=Jsoup.connect(url).get();
			SaveHTML(doc);
		} catch (IOException e) {
			doc=LoadHTML();			
		}
		
		CreateList();
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
