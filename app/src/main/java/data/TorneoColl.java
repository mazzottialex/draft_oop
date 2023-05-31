package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TorneoColl {
	//private List<List<Optional<Squadra>>> tabellone;
	private List<Squadra> quarti;
	private List<Squadra> semifinali=new ArrayList<>();
	private List<Squadra> finale=new ArrayList<>();
	//private List<Squadra> li;
	
	public TorneoColl(List<Squadra> li) {
		//tabellone=new ArrayList<>();
		this.quarti=li;
		//tabellone.addAll(quarti);
	}
	
	public void addSemi(Squadra s) {
		semifinali.add(s);
	}
	
	public void addFinale(Squadra s) {
		finale.add(s);
	}

	public List<Squadra> getSemi() {
		return semifinali;
	}

}
