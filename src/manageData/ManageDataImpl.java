package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
<<<<<<< HEAD
import java.util.Comparator;
import java.util.HashSet;
=======
import java.util.ArrayList;
>>>>>>> TestSelenium
import java.util.List;

public class ManageDataImpl implements ManageData{
	private List<Calciatore> li;
	private LogicsFile logFile;
	
	public ManageDataImpl() {
		li=new ArrayList<>();
		logFile=new LogicsFileImpl();
	}
	
	public List<Calciatore> getLi() {
		return li;
	}
	
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		li=logFile.LoadData();
	}
	public void UploadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		Scraping scr=new ScrapingImpl(7);
		li=scr.getLista();
		logFile.SaveData(li);
	}
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

	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> listaSquadra = getCalciatoreBySquadra(squadra);
		int n = 0;
		switch (ruolo) {
		case "P":
			n = 1;
			break;
		case "D":
			n = modulo.getNumDif();
			break;
		case "C":
			n = modulo.getNumCen();
			break;
		case "A":
			n = modulo.getNumAtt();
			break;

		default:
			break;
		}
		return listaSquadra.stream()
				.filter(c->c.getRuolo().equals(ruolo))
				.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
				.limit(n)
				.toList();
	}
	
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> listaSquadra = getCalciatoreBySquadra(squadra);
		List<Calciatore> listaTitolari = getTitolariBySquadraByRuolo(squadra, ruolo, modulo);
		listaSquadra.removeAll(listaTitolari);
		int n = 0;
		if (ruolo == "P") {
			n = 1;
		} else {
			n = 2;
		}
		return listaSquadra.stream()
				.filter(c->c.getRuolo().equals(ruolo))
				.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
				.limit(n)
				.toList();
	}



=======
>>>>>>> TestSelenium
}
