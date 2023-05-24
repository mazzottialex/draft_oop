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
	private final static int SUPPL = 90;
	private final static int GOL_SUPPL = 3;
	
	private SimulatingMatch sim;
	
	private static final int MINUTES_REG = 90;
	private final static int MINUTES_SUPPL = 120;
	
	private static final double OWNGOAL_RATE = 2.904040404040404;
	
	public LogicsPartitaImpl(SquadraAvversaria s1, SquadraAvversaria s2) throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.gol1 = 0;
		this.gol2 = 0;
		this.sim = new SimulatingMatchImpl(s1, s2);
	}
	
	@Override
	public void scorers(int tempo) throws FileNotFoundException, ClassNotFoundException, IOException {
		do {
			list1 = getNumGol(sim.risultato().get(s1.getNomeSquadra()), tempo);
			list2 = getNumGol(sim.risultato().get(s2.getNomeSquadra()), tempo);
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
            if (l2.contains(elem)) {
                return true;
            }
        }
        return false;
    }
	
	public List<Integer> getNumGol(int g, int tempo) {
		List<Integer> list = new ArrayList<>();
		Random r = new Random();
		int min;
		int gol = g;
		int random = MINUTES_REG;
		if (tempo == SUPPL) {
			gol = g / GOL_SUPPL;
			random = MINUTES_SUPPL - MINUTES_REG;
		}
		for (int i = 0; i < gol; i++) {
			do {
				min = r.nextInt(random) + 1;
				if (tempo == SUPPL) {
					min += MINUTES_REG;
				}
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
	public Calciatore addScorer(SquadraAvversaria s) {
		// TODO Auto-generated method stub
		int g1 = s.getTitolari().get(1).getGol();
		int g2 = s.getTitolari().get(2).getGol();
		int g3 = s.getTitolari().get(3).getGol();
		int g4 = s.getTitolari().get(4).getGol();
		int g5 = s.getTitolari().get(5).getGol();
		int g6 = s.getTitolari().get(6).getGol();
		int g7 = s.getTitolari().get(7).getGol();
		int g8 = s.getTitolari().get(8).getGol();
		int g9 = s.getTitolari().get(9).getGol();
		int g10 = s.getTitolari().get(10).getGol();
		int totGol = 0;
		for (Calciatore calciatore : s.getTitolari()) {
		    int r = calciatore.getRating().getX();
		    totGol += r;
		}
		int tot = totGol + (int) ((totGol / 100) + OWNGOAL_RATE);
		int random = new Random().nextInt(tot);
		if (random <= g1) {
		    return s.getTitolari().get(1);
		} else if (random > g1 && random <= g2) {
		    return s.getTitolari().get(2);
		} else if (random > g1 + g2 && random <= g3) {
		    return s.getTitolari().get(3);
		} else if (random > g1 + g2 + g3 && random <= g4) {
		    return s.getTitolari().get(4);
		} else if (random > g1 + g2 + g3 + g4 && random <= g5) {
		    return s.getTitolari().get(5);
		} else if (random > g1 + g2 + g3 + g4 + g5 && random <= g6) {
		    return s.getTitolari().get(6);
		} else if (random > g1 + g2 + g3 + g4 + g5 + g6 && random <= g7) {
		    return s.getTitolari().get(7);
		} else if (random > g1 + g2 + g3 + g4 + g5 + g6 + g7 && random <= g8) {
		    return s.getTitolari().get(8);
		} else if (random > g1 + g2 + g3 + g4 + g5 + g6 + g7 + g8 && random <= g9) {
		    return s.getTitolari().get(9);
		} else if (random > g1 + g2 + g3 + g4 + g5 + g6 + g7 + g8 + g9 && random <= g10) {
		    return s.getTitolari().get(10);
		} else {
		    return getAutogol(s);
		}
	}
	
	public Calciatore getAutogol(SquadraAvversaria s) {
		SquadraAvversaria sq;
		if (s == s1) {
			sq = s2;
		} else {
			sq = s1;
		}
		int random = new Random().nextInt(sq.getTitolari().size()) + 1;
		return sq.getTitolari().get(random);
	}

	@Override
	public void sanctions() {
		// TODO Auto-generated method stub
		
	}
}
