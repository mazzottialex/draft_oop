package logics;

import java.util.Map;

import data.SquadraAvversaria;
import gui.Partita;

public class LogicsPartitaImpl implements LogicsPartita{
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	private double gol1;
	private double gol2;
	
	public LogicsPartitaImpl(SquadraAvversaria s1, SquadraAvversaria s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.gol1 = 0;
		this.gol2 = 0;
	}
	
	public Map<String, Double> makeResult(Map<String, Double> risultato) {
		// TODO
//		gol1 = risultato.get(s1);
		return risultato;
	}
	
	public void addGol() {
		//TODO
	}
}
