package scraping;

import java.util.List;
import org.jsoup.nodes.Document;

import data.Calciatore;

public interface Scraping{
	//prima leggere con readTable poi getLista
	public List<Calciatore> getLista();
	public Boolean ReadTable(String stagione);
	public Boolean ReadTable();
	public List<String> getStagioni();
	
}
