package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class ExtractDataImpl implements ExtractData{

	private List<Calciatore> li;
	
	public ExtractDataImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		ManageData md=new ManageDataImpl();
		md.LoadData();
		li=md.getLi();
	}
	
	public List<Calciatore> getCalciatoreBySquadra(String squadra) {
		return li.stream()
				.filter(c->c.getSquadra().equals(squadra))
				.toList();
	}

	@Override
	public List<Calciatore> getLi() {
		return li;
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
	
	public int getTopGol() {
		return li.stream()
				.map(c->c.getGol())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopTiri() {
		return li.stream()
				.map(c->c.getTiri())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopDribling() {
		return li.stream()
				.map(c->c.getDribling())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopAssist() {
		return li.stream()
				.map(c->c.getAssist())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopPassChiave() {
		return li.stream()
				.map(c->c.getPassaggiChiave())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopPassaggi() {
		return li.stream()
				.map(c->c.getPassaggi())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}

	public int getTopRubati() {
		return li.stream()
				.map(c->c.getRubati())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopTackle() {
		return li.stream()
				.map(c->c.getTackle())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopCleanSheet() {
		return li.stream()
				.map(c->c.getCleanSheet())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopMinuti() {
		return li.stream()
				.map(c->c.getMinuti())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getTopParate() {
		return li.stream()
				.map(c->c.getMinuti())
				.max((c1,c2)->c1-c2)
				.orElse(0);
	}
	
	public int getCountPortieri() {
		return (int) li.stream()
				.filter(c->c.getRuolo().equals(new String("P")))
				.count();
	}
}
