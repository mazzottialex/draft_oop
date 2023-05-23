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
	private List<Integer> list1;
	private List<Integer> list2;
	
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
	public void scorers() throws FileNotFoundException, ClassNotFoundException, IOException {
		do {
			list1 = getNumGol(sim.risultato().get(s1.getNomeSquadra()));
			list2 = getNumGol(sim.risultato().get(s2.getNomeSquadra()));
		} while (containsAny(list1, list2));
	}
	
	@Override
	public List<Integer> getMinGol(SquadraAvversaria s) {
		if (s == s1) {
			return list1;
		} else if (s == s2){
			return list2;
		}
		return null;
	}

	public static <T> boolean containsAny(List<T> l1, List<T> l2) {
        for (T elem : l1) {
            if (l2.contains(l1)) {
                return true;
            }
        }
        return false;
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
	
//	@Override
//	public List<Integer> getMinGol1() {
//		return list1;
//	}
//	
//	@Override
//	public List<Integer> getMinGol2() {
//		return list2;
//	}

	@Override
	public void addScorer(SquadraAvversaria s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sanctions() {
		// TODO Auto-generated method stub
		
	}
}
