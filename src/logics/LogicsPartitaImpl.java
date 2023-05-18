package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.SquadraAvversaria;
import gui.Partita;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

public class LogicsPartitaImpl implements LogicsPartita{
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	private int gol1;
	private int gol2;
	
	private SimulatingMatch sim;
	
	private static final int MINUTES = 90;
	
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
		/*
//		int prevGol1 = (int) gol1;
//		int prevGol2 = (int) gol2;
		this.gol1 += sim.risultato().get(s1.getNomeSquadra()) / MINUTES;
		this.gol2 += sim.risultato().get(s2.getNomeSquadra()) / MINUTES;
//		ris.put(s1.getNomeSquadra(), (int) gol1 > prevGol1 ? (int) gol1 : prevGol1);
//		ris.put(s2.getNomeSquadra(), (int) gol2 > prevGol2 ? (int) gol2 : prevGol2);
		*/
		
		List<Integer> list1 = getNumGol(sim.risultato().get(s1));
		List<Integer> list2 = getNumGol(sim.risultato().get(s2));
	}
	
	public List<Integer> getNumGol(int gol) {
		List<Integer> list = new ArrayList<>();
		Random r = new Random();
		int min = 0;
		for (int i = 0; i < gol; i++) {
			do {
				min = r.nextInt(MINUTES) + 1;
			} while (list.contains(min));
			list.add(min);
		}
		Collections.sort(list);
		return list;
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
