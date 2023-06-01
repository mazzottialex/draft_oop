package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TorneoColl {
	private List<List<Squadra>> tabellone;
	private List<Squadra> quarti;
	private List<Squadra> semifinali=new ArrayList<>();
	private List<Squadra> finale=new ArrayList<>();
	//private List<Squadra> li;
	
	public TorneoColl(List<Squadra> li) {
		tabellone=new ArrayList<>();
		this.quarti=li;
		tabellone.add(quarti);
	}
	
	public void addSemi(Squadra s) {
		semifinali.add(s);
		tabellone.add(semifinali);
	}
	
	public void addFinale(Squadra s) {
		finale.add(s);
		tabellone.add(finale);
	}

	public List<List<Squadra>> getTabellone() {
		return tabellone;
	}

}
