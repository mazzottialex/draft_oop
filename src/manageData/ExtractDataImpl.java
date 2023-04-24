package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import data.Calciatore;
import data.Modulo;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;

public class ExtractDataImpl implements ExtractData{

	private List<Calciatore> li;
	private CalcoloRating r = new CalcoloRatingImpl(li);
	
	public ExtractDataImpl(List<Calciatore> li) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.li=li;
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
	
	public int getTopByAttribute(Function<Calciatore, Integer> attr) {
		return li.
				stream()
				.map(c->attr.apply(c))
				.max((c1,c2)-> c1-c2)
				.orElse(0);
	}
	
	//per ammonizioni e espulsioni
	public int getTopByAttribute(Function<Calciatore, Integer> f1, Function<Calciatore, Integer> f2) {
		return li.
				stream()
				.map(c->{
					if(f1.apply(c)!=0 && f2.apply(c)>100) {
						//System.out.println(f2.apply(c)/f1.apply(c));
						return f2.apply(c)/f1.apply(c);
					}
					else
						return 10000;})
				.max((c1,c2)-> c1-c2)
				.orElse(0);
	}
	
	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> lista = getCalciatoreBySquadra(squadra).stream()
				.filter(c -> c.getRuolo().equals(ruolo))
				.sorted((c1, c2) -> r.getRating(c2).getX() - r.getRating(c1).getX())
				.toList();
		List<Calciatore> listaTitolari = new ArrayList<>();
		int n = 0;
		switch (ruolo) {
		case "P":
			n = 1;
			listaTitolari = lista.stream()
					.limit(n)
					.toList();
			break;
		case "D":
			n = modulo.getNumDif();
			listaTitolari = lista.stream()
					.limit(n)
					.toList();
			break;
		case "C":
			n = modulo.getNumCen();
			listaTitolari = lista.stream()
					.limit(n)
					.toList();
			break;
		case "A":
			n = modulo.getNumAtt();
			listaTitolari = lista.stream()
					.limit(n)
					.toList();
			break;
		default:
			break;
		}
		return listaTitolari;
	}
	
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo) {
		List<Calciatore> lista = getCalciatoreBySquadra(squadra).stream()
				.filter(c -> c.getRuolo().equals(ruolo))
				.sorted((c1, c2) -> r.getRating(c2).getX() - r.getRating(c1).getX())
				.toList();
		List<Calciatore> listaRiserve = new ArrayList<>();
		int n = 2;
		int m = 0;
		switch (ruolo) {
		case "P":
			m = 1;
			n = 1;
			listaRiserve = lista.stream()
					.skip(m)
					.limit(n)
					.toList();
			break;
		case "D":
			m = modulo.getNumDif();
			listaRiserve = lista.stream()
					.skip(m)
					.limit(n)
					.toList();
			break;
		case "C":
			m = modulo.getNumCen();
			listaRiserve = lista.stream()
					.skip(m)
					.limit(n)
					.toList();
			break;
		case "A":
			m = modulo.getNumAtt();
			listaRiserve = lista.stream()
					.skip(m)
					.limit(n)
					.toList();
			break;
		default:
			break;
		}
		return listaRiserve;
	}

}
