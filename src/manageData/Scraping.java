package manageData;

import java.util.List;
import org.jsoup.nodes.Document;

public interface Scraping{
	public List<Calciatore> getLista();
	public List<Calciatore> getLista(String stagione);
	public List<String> getStagioni();
	
}
