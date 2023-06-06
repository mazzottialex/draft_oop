package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import data.Player;
import data.Team;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

/**
 * The {@code LogicsPartitaImpl} class implements the {@code LogicsPartita} interface and provides the logic for a match.
 */
public class LogicsPartitaImpl implements LogicsPartita {
    private Team s1;
    private Team s2;
    private List<Integer> list1;
    private List<Integer> list2;
    private static final int REG = 0;
    private static final int SUPPL = 90;
    private SimulatingMatch sim;
    private static final int MINUTES_REG = 90;
    private static final int MINUTES_SUPPL = 120;
    private static final double OWNGOAL_RATE = 2.904040404040404;

    /**
     * Constructs a new instance of {@code LogicsPartitaImpl} with the given teams.
     *
     * @param s1 the first team
     * @param s2 the second team
     * @throws FileNotFoundException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if an I/O error occurs
     */
    public LogicsPartitaImpl(final Team s1, final Team s2) throws FileNotFoundException, ClassNotFoundException, IOException {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.sim = new SimulatingMatchImpl(s1, s2);
    }

    @Override
    public void scorers(final int tempo) throws FileNotFoundException, ClassNotFoundException, IOException {
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
    public List<Integer> getMinGol(final Team s) {
        if (s == s1) {
            return list1;
        } else if (s == s2) {
            return list2;
        }
        return null;
    }

    /**
     * Checks if any element from the first list is contained in the second list.
     *
     * @param l1 the first list
     * @param l2 the second list
     * @param <T> the type of elements in the lists
     * @return {@code true} if any element from the first list is contained in the second list, {@code false} otherwise
     */
    public static <T> boolean containsAny(final List<T> l1, final List<T> l2) {
        for (T elem: l1) {
            if (l2.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a list of goal minutes based on the number of goals and the time period.
     *
     * @param g the number of goals
     * @param tempo the time period
     * @return a list of goal minutes
     */
    public List<Integer> getNumGol(final int g, final int tempo) {
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
    public Player addScorer(final Team s) {
        double totGol = 0;
        List<Player> titolari = s.getStarting();
        List<Double> golList = new ArrayList<>();

        for (Player calciatore: titolari) {
            double gol = calciatore.getGoals();
            golList.add(gol);
            totGol += gol;
        }

        double autogol = (totGol * OWNGOAL_RATE) / 100;
        double random = new Random().nextDouble() * (totGol + autogol);

        double cumulativeProbability = 0.0;
        for (int i = 0; i < titolari.size(); i++) {
            cumulativeProbability += golList.get(i);
            if (random <= cumulativeProbability) {
                return titolari.get(i);
            }
        }
        return getAutogol(s);
    }

    /**
     * Generates an own goal scorer from the opposing team.
     *
     * @param s the team
     * @return the scorer
     */
    public Player getAutogol(final Team s) {
        Team sq;
        if (s == s1) {
            sq = s2;
        } else {
            sq = s1;
        }
        int random = new Random().nextInt(sq.getStarting().size());
        return sq.getStarting().get(random);
    }

    @Override
    public void sanctions() {
        // TODO Auto-generated method stub
    }
}
