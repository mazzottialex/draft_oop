package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TorneoColl {
	private List<List<Squadra>> tabellone;
	private List<Squadra> lastLi;
	public TorneoColl(List<Squadra> li) {
		tabellone=new ArrayList<>();
		tabellone.add(li);
		lastLi=li;
	}
	
	

	public void addLi(List<Squadra> li) {
		tabellone.add(li);
		lastLi=li;
	}
	
	public List<Squadra> getLastLi(){
		return lastLi;
	}

	public List<List<Squadra>> getTabellone() {
		return tabellone;
	}
	
	@Override
	public String toString() {
		return "TorneoColl [tabellone=" + tabellone + "]";
	}

}
