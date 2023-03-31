package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class ManageDataImpl implements ManageData{

	private List<Calciatore> li;
	
	public ManageDataImpl() throws FileNotFoundException, IOException {
		Scraping sc = new ScrapingImpl();
		li=sc.getLista();
	}
	
	public List<Calciatore> getCalciatoreBySquadra(String squadra) {
		return li.stream()
				.filter(c->c.getSquadra().equals(squadra))
				.toList();
	}

	public Optional<Calciatore> getCalciatoreByName(String name) {
		return li.stream()
				.filter(c->c.getNominativo().equals(name))
				.findFirst();
	}

	//RUOLI: P, D, C, A
	public List<Calciatore> getListaByRuolo(String ruolo) {
		return li.stream()
				.filter(c->c.getRuolo().equals(ruolo))
				.toList();
	}

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



}
