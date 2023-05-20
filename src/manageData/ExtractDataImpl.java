package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.function.Function;

import data.Calciatore;
import data.Modulo;

public class ExtractDataImpl implements ExtractData{

	private List<Calciatore> li;
	
	public ExtractDataImpl(List<Calciatore> li) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.li = li;
	}
	
	@Override
	public List<Calciatore> getCalciatoreBySquadra(String squadra) {
		return li.stream()
				.filter(c -> c.getSquadra().equals(squadra))
				.toList();
	}

	@Override
	public List<Calciatore> getLi() {
		return li;
	}
	
	@Override
	public Optional<Calciatore> getCalciatoreByName(String name) {
		return li.stream()
				.filter(c -> c.getNominativo().equals(name))
				.findFirst();
	}

	//RUOLI: P, D, C, A
	@Override
	public List<Calciatore> getListaByRuolo(String ruolo) {
		return li.stream()
				.filter(c -> c.getRuolo().equals(ruolo))
				.toList();
	}
	
	@Override
	public List<Calciatore> getRandomByRuolo(String ruolo, int n) {
		List<Calciatore> listaRuolo = getListaByRuolo(ruolo);
		Random rnd = new Random();
		Set<Integer> posizioni = new HashSet<>();
		for(int i=0; i<n; i++) {
			int num;
			do {
				num=rnd.nextInt(listaRuolo.size());
			}while(posizioni.contains(num));
			posizioni.add(num);
		}
		
		return posizioni.stream()
				.map(p-> listaRuolo.get(p))
				.toList();
	}
	
	@Override
	public int getTopByAttribute(Function<Calciatore, Integer> attr) {
		return li.
				stream()
				.map(c -> attr.apply(c))
				.max((c1, c2) -> c1 - c2)
				.orElse(0);
	}
	
	//per ammonizioni e espulsioni
	@Override
	public int getTopByAttribute(Function<Calciatore, Integer> f1, Function<Calciatore, Integer> f2) {
		return li.
				stream()
				.map(c -> {
					if(f1.apply(c)!=0 && f2.apply(c)>100) {
						return f2.apply(c) / f1.apply(c);
					}
					else
						return 100;})
				.max((c1, c2) -> c1 - c2)
				.orElse(0);
	}
	
	@Override
	public List<Calciatore> getListOrdered(Function<Calciatore, Integer> attr){
		return li.stream()
				.sorted((c1,c2) -> attr.apply(c1)-attr.apply(c2) )
				.toList();
	}

	
	@Override
	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		int n = 1;
		switch (ruolo) {
		case "D":
			n = modulo.getNumDif();
			break;
		case "C":
			n = modulo.getNumCen();
			break;
		case "A":
			n = modulo.getNumAtt();
			break;
		default:	//compreso il case "P" perché per il ruolo portiere n=1
			break;
		}
		List<Calciatore> lista = getCalciatoreBySquadra(squadra).stream()
				.filter(c -> c.getRuolo().equals(ruolo))
				.sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
				.limit(n)
				.toList();
		return lista;
	}
	
	@Override
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		int n = 2;
		int m = 1;
		switch (ruolo) {
		case "P":
			n = 1;
			break;
		case "D":
			m = modulo.getNumDif();
			break;
		case "C":
			m = modulo.getNumCen();
			break;
		case "A":
			m = modulo.getNumAtt();
			break;
		default:
			break;
		}
		List<Calciatore> lista = getCalciatoreBySquadra(squadra).stream()
				.filter(c -> c.getRuolo().equals(ruolo))
				.sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
				.skip(m)
				.limit(n)
				.toList();
		return lista;
	}

	@Override
	public List<Calciatore> getTitolari(String squadra, Modulo modulo) {
		List<Calciatore> lt = new ArrayList<>();
		lt.addAll(getTitolariBySquadraByRuolo(squadra, "P", modulo));
		lt.addAll(getTitolariBySquadraByRuolo(squadra, "D", modulo));
		lt.addAll(getTitolariBySquadraByRuolo(squadra, "C", modulo));
		lt.addAll(getTitolariBySquadraByRuolo(squadra, "A", modulo));
		return lt;
	}

	@Override
	public List<Calciatore> getRiserve(String squadra, Modulo modulo) {
		List<Calciatore> lr = new ArrayList<>();
		lr.addAll(getRiserveBySquadraByRuolo(squadra, "P", modulo));
		lr.addAll(getRiserveBySquadraByRuolo(squadra, "D", modulo));
		lr.addAll(getRiserveBySquadraByRuolo(squadra, "C", modulo));
		lr.addAll(getRiserveBySquadraByRuolo(squadra, "A", modulo));
		return lr;
	}
	
	public List<String> getNomeCalciatori(String squadra) {
		return getCalciatoreBySquadra(squadra)
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
	
	public List<String> getNomeTitolari(String squadra, Modulo modulo) {
		return getTitolari(squadra, modulo)
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
	
	public List<String> getNomeRiserve(String squadra, Modulo modulo) {
		return getRiserve(squadra, modulo)
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
	
	// ruolo, nome, rating
	public List<?> tsr(String squadra, Modulo modulo) {
		return getTitolari(squadra, modulo)
				.stream()
				.map(c -> c.toVector())
				.toList();
	}
	
	public List<Calciatore> getRandom(int nA, int nC, int nD, int nP){
		List<Calciatore> li=new ArrayList<>();
		li.addAll(getRandomByRuolo("A", nA));
		li.addAll(getRandomByRuolo("C", nC));
		li.addAll(getRandomByRuolo("D", nD));
		li.addAll(getRandomByRuolo("P", nP));
		Calciatore c;
		for(int i=0;i<5;i++) {
			do {
				c=getRandomByRuolo("P", 1).get(0);
			}while(li.contains(c));
			li.add(c);
		}
		for(int i=0;i<10;i++) {
			do {
				c=getRandomByRuolo("D", 2).get(0);
			}while(li.contains(c));
			li.add(c);
		}
		for(int i=0;i<10;i++) {
			do {
				c=getRandomByRuolo("C", 2).get(0);
			}while(li.contains(c));
			li.add(c);
		}
		for(int i=0;i<10;i++) {
			do {
				c=getRandomByRuolo("A", 2).get(0);
			}while(li.contains(c));
			li.add(c);
		}
		return li;
	}
}
