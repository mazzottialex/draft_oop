package manageData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {
	private String url="https://www.gazzetta.it/calcio/fantanews/statistiche/serie-a-2022-23/";
	private Document doc;
	private Elements el;
	private List<String> calciatori;
	
	public Scraping() {
		
		calciatori=new ArrayList<>();
		
		try {
			doc=Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		
		el=doc.select("td.field-giocatore");
		
		for(Element e : el) {
			calciatori.add(e.selectFirst("a").text());
		}
	}
	
	public List<String> getCalciatori() {
		return calciatori;
	}

	public void setCalciatori(List<String> calciatori) {
		this.calciatori = calciatori;
	}

	public Document getDoc() {
		return doc;
	}


	public Elements getEl() {
		return el;
	}

}
