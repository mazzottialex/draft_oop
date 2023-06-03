package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import data.Calciatore;
import data.Squadra;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

public class LogicsPartitaImpl implements LogicsPartita {
    private Squadra s1;
    private Squadra s2;
    private List<Integer> list1;
    private List<Integer> list2;
    private final static int REG = 0;
    private final static int SUPPL = 90;

    private SimulatingMatch sim;

    private static final int MINUTES_REG = 90;
    private final static int MINUTES_SUPPL = 120;

    private static final double OWNGOAL_RATE = 2.904040404040404;

    public LogicsPartitaImpl(Squadra s1, Squadra s2) throws FileNotFoundException, ClassNotFoundException, IOException {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.sim = new SimulatingMatchImpl(s1, s2);
    }

    @Override
    public void scorers(int tempo) throws FileNotFoundException, ClassNotFoundException, IOException {
        if (tempo == REG) {
            do {
                list1 = getNumGol(sim.risultato().get(s1), tempo);
                list2 = getNumGol(sim.risultato().get(s2), tempo);
            } while (containsAny(list1, list2));
        } else if (tempo == SUPPL) {
            do {
                list1 = getNumGol(sim.risultatoSuppl().get(s1), tempo);
                list2 = getNumGol(sim.risultatoSuppl().get(s2), tempo);
            } while (containsAny(list1, list2));
        } else {
            do {
                list1 = getNumGol(sim.risultatoSub(tempo).get(s1), tempo);
                list2 = getNumGol(sim.risultatoSub(tempo).get(s2), tempo);
            } while (containsAny(list1, list2));
        }
    }

    @Override
    public List<Integer> getMinGol(Squadra s) {
        if (s == s1) {
            return list1;
        } else if (s == s2) {
            return list2;
        }
        return null;
    }

    public static <T> boolean containsAny(List<T> l1, List<T> l2) {
        for (T elem: l1) {
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
            gol = ((MINUTES_SUPPL - MINUTES_REG) * g) / MINUTES_REG;
            random = MINUTES_SUPPL - MINUTES_REG;
        } else if (tempo != SUPPL && tempo != REG) {
            if (tempo < MINUTES_REG) {
                gol = ((MINUTES_REG - tempo) * g) / MINUTES_REG;
                random = MINUTES_REG - tempo;
            } else {
                gol = ((MINUTES_SUPPL - tempo) * g) / MINUTES_REG;
                random = MINUTES_SUPPL - tempo;
            }
        }
        for (int i = 0; i < gol; i++) {
            do {
                min = r.nextInt(random) + 1;
                min += tempo;
            } while (list.contains(min));
            list.add(min);
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public Calciatore addScorer(Squadra s) {
        double totGol = 0;
        List<Calciatore> titolari = s.getTitolari();
        List<Double> golList = new ArrayList<>();

        for (Calciatore calciatore: titolari) {
            double gol = calciatore.getGol();
            golList.add(gol);
            totGol += gol;
        }

        double autogol = (totGol * OWNGOAL_RATE) / 100;
        double random = new Random().nextDouble(totGol + autogol);

        double cumulativeProbability = 0.0;
        for (int i = 0; i < titolari.size(); i++) {
            cumulativeProbability += golList.get(i);
            if (random <= cumulativeProbability) {
                return titolari.get(i);
            }
        }
        return getAutogol(s);
    }

    public Calciatore getAutogol(Squadra s) {
        Squadra sq;
        if (s == s1) {
            sq = s2;
        } else {
            sq = s1;
        }
        int random = new Random().nextInt(sq.getTitolari().size());
        return sq.getTitolari().get(random);
    }

    @Override
    public void sanctions() {
        // TODO Auto-generated method stub
    }
}