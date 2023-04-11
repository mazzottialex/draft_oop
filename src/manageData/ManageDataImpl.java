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
		Scraping scr=new ScrapingImpl();
		li=scr.getLista();
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

	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> listaSquadra = getCalciatoreBySquadra(squadra);
		List<Calciatore> lista = new ArrayList<>();
		int n = 0;
		Predicate<Calciatore> noP = c -> !c.getRuolo().equals("P");
		Predicate<Calciatore> noD = c -> !c.getRuolo().equals("D");
		Predicate<Calciatore> noC = c -> !c.getRuolo().equals("C");
		Predicate<Calciatore> noA = c -> !c.getRuolo().equals("A");
		Predicate<Calciatore> noTC = c -> !c.getRuolo().equals("T (C)");
		Predicate<Calciatore> noTA = c -> !c.getRuolo().equals("T (A)");
		switch (ruolo) {
		case "P":
			n = 1;
			lista = listaSquadra.stream()
					.filter(c->c.getRuolo().equals(ruolo))
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.limit(n)
					.toList();
			break;
		case "D":
			n = modulo.getNumDif();
			lista = listaSquadra.stream()
					.filter(c->c.getRuolo().equals(ruolo))
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.limit(n)
					.toList();
			break;
		case "C":
			n = modulo.getNumCen();
			lista = listaSquadra.stream()
					.filter(noP).filter(noD).filter(noA).filter(noTA)
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.limit(n)
					.toList();
			break;
		case "A":
			n = modulo.getNumAtt();
			lista = listaSquadra.stream()
					.filter(noP).filter(noD).filter(noC).filter(noTC)
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.limit(n)
					.toList();
			break;
		default:
			break;
		}
		return lista;
	}

	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> listaSquadra = getCalciatoreBySquadra(squadra);
		List<Calciatore> lista = new ArrayList<>();
		int n = 0;
		int m = 0;
		Predicate<Calciatore> noP = c -> !c.getRuolo().equals("P");
		Predicate<Calciatore> noD = c -> !c.getRuolo().equals("D");
		Predicate<Calciatore> noC = c -> !c.getRuolo().equals("C");
		Predicate<Calciatore> noA = c -> !c.getRuolo().equals("A");
		Predicate<Calciatore> noTC = c -> !c.getRuolo().equals("T (C)");
		Predicate<Calciatore> noTA = c -> !c.getRuolo().equals("T (A)");
		switch (ruolo) {
		case "P":
			n = 1;
			m = 1;
			lista = listaSquadra.stream()
					.filter(c->c.getRuolo().equals(ruolo))
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.skip(n)
					.limit(m)
					.toList();
			break;
		case "D":
			n = modulo.getNumDif();
			m = 2;
			lista = listaSquadra.stream()
					.filter(c->c.getRuolo().equals(ruolo))
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.skip(n)
					.limit(m)
					.toList();
			break;
		case "C":
			n = modulo.getNumCen();
			m = 2;
			lista = listaSquadra.stream()
					.filter(noP).filter(noD).filter(noA).filter(noTA)
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.skip(n)
					.limit(m)
					.toList();
			break;
		case "A":
			n = modulo.getNumAtt();
			m = 2;
			lista = listaSquadra.stream()
					.filter(noP).filter(noD).filter(noC).filter(noTC)
					.sorted(Comparator.comparingDouble(Calciatore::getMv).reversed())
					.skip(n)
					.limit(m)
					.toList();
			break;

		default:
			break;
		}
		return lista;
	}



=======
>>>>>>> TestSelenium
*/
}
