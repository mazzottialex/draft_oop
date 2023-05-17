package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Calciatore;
import data.SquadraAvversaria;
import gui.Partita;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

public class LogicsPartitaImpl implements LogicsPartita{
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	private double gol1;
	private double gol2;
	
	private SimulatingMatch sim;
	
	private static final double MINUTES = 90;
	
	public LogicsPartitaImpl(SquadraAvversaria s1, SquadraAvversaria s2) throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.gol1 = 0;
		this.gol2 = 0;
		this.sim = new SimulatingMatchImpl(s1, s2);
	}
	
	@Override
	public void computeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
//		int prevGol1 = (int) gol1;
//		int prevGol2 = (int) gol2;
		this.gol1 += sim.risultato().get(s1.getNomeSquadra()) / MINUTES;
		this.gol2 += sim.risultato().get(s2.getNomeSquadra()) / MINUTES;
//		ris.put(s1.getNomeSquadra(), (int) gol1 > prevGol1 ? (int) gol1 : prevGol1);
//		ris.put(s2.getNomeSquadra(), (int) gol2 > prevGol2 ? (int) gol2 : prevGol2);
	}
	
	@Override
	public int getGol1() {
		return (int) gol1;
	}
	
	@Override
	public int getGol2() {
		return (int) gol2;
	}

	@Override
	public void addScorer(SquadraAvversaria s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sanctions() {
		// TODO Auto-generated method stub
		
	}
}
