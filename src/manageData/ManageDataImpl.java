package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import data.Calciatore;
import scraping.Scraping;
import scraping.ScrapingImpl;

public class ManageDataImpl implements ManageData{
	private List<Calciatore> li;
	private LogicsFile logFile;
	private String stagione;
	
	public ManageDataImpl(String stagione) {
		li=new ArrayList<>();
		logFile=new LogicsFileImpl(stagione);
		this.stagione=stagione;
	}
	
	public List<Calciatore> getLi() {
		return li;
	}
	
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		li=logFile.LoadData();
	}
	public void DownloadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		Scraping scr=new ScrapingImpl();
		li=scr.getLista(this.stagione);
		logFile.SaveData(li);
	}
	/*
<<<<<<< HEAD

	public List<Calciatore> getRandomByRuolo(String ruolo, int n) {
		List<Calciatore> listaRuolo=getListaByRuolo(ruolo);
		Random rnd=new Random();
		Set<Integer> posizioni=new HashSet<>();
		for(int i=0;i<n;i++) {
			posizioni.add(rnd.nextInt(listaRuolo.size()));
		}
		return listaRuolo.stream()
				.filter(c->posizioni.contains(c.getId()))
				.toList();
	}

=======
>>>>>>> TestSelenium
*/
}
