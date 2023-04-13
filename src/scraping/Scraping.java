package scraping;

import java.util.List;
import org.jsoup.nodes.Document;

import data.Calciatore;

public interface Scraping{
	public List<Calciatore> getLista();
	public List<Calciatore> getLista(String stagione);
	public List<String> getStagioni();
	
}
