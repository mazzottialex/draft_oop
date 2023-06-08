package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import data.Player;
import data.Team;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

/**
 * The {@code LogicsMatchImpl} class implements the {@code LogicsMatch} interface and provides the logic for a match.
 */
public final class LogicsMatchImpl implements LogicsMatch, Serializable {
    private static final long serialVersionUID = -180921977203150416L;
    private Team t1;
    private Team t2;
    private List<Integer> list1;
    private List<Integer> list2;
    private static final int REG = 0;
    private static final int EXTRA = 90;
    private SimulatingMatch sim;
    private static final int MINUTES_REG = 90;
    private static final int MINUTES_EXTRA = 120;
    private static final double OWNGOAL_RATE = 2.904040404040404;

    /**
     * Constructs a new instance of {@code LogicsMatchImpl} with the given teams.
     *
     * @param t1 the first team
     * @param t2 the second team
     * @throws FileNotFoundException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if an I/O error occurs
     */
    public LogicsMatchImpl(final Team t1, final Team t2) throws FileNotFoundException, ClassNotFoundException, IOException {
        super();
        this.t1 = t1;
        this.t2 = t2;
        this.sim = new SimulatingMatchImpl(t1, t2);
    }

    @Override
    public void scorers(final int time) throws FileNotFoundException, ClassNotFoundException, IOException {
        if (time == REG) {
            do {
                list1 = getGoalsNum(sim.result().get(t1), time);
                list2 = getGoalsNum(sim.result().get(t2), time);
            } while (containsAny(list1, list2));
        } else if (time == EXTRA) {
            do {
                list1 = getGoalsNum(sim.resultExtra().get(t1), time);
                list2 = getGoalsNum(sim.resultExtra().get(t2), time);
            } while (containsAny(list1, list2));
        } else {
            do {
                list1 = getGoalsNum(sim.resultSub(time).get(t1), time);
                list2 = getGoalsNum(sim.resultSub(time).get(t2), time);
            } while (containsAny(list1, list2));
        }
    }

    @Override
    public List<Integer> getGoalsMinutes(final Team t) {
        if (t == t1) {
            return list1;
        } else if (t == t2) {
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
     * @param time the time period
     * @return a list of goal minutes
     */
    public List<Integer> getGoalsNum(final int g, final int time) {
        List<Integer> list = new ArrayList<>();
        int goal = g;
        int random = MINUTES_REG;
        if (time == EXTRA) {
            goal = ((MINUTES_EXTRA - MINUTES_REG) * g) / MINUTES_REG;
            random = MINUTES_EXTRA - MINUTES_REG;
        } else if (time < MINUTES_REG) {
            goal = ((MINUTES_REG - time) * g) / MINUTES_REG;
            random = MINUTES_REG - time;
        } else if (time > MINUTES_REG) {
            goal = ((MINUTES_EXTRA - time) * g) / MINUTES_REG;
            random = MINUTES_EXTRA - time;
        }
        for (int i = 0; i < goal; i++) {
            int min;
            do {
                min = new Random().nextInt(random) + 1 + time;
            } while (list.contains(min));
            list.add(min);
        }
        Collections.sort(list);
        return list;
    }


    @Override
    public Player addScorer(final Team t) {
        double totGoals = 0;
        List<Player> starters = t.getStarting();
        List<Double> goalList = new ArrayList<>();
        for (Player p: starters) {
            double goals = p.getGoals();
            goalList.add(goals);
            totGoals += goals;
        }
        double owngoals = (totGoals * OWNGOAL_RATE) / 100;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < starters.size(); i++) {
            cumulativeProbability += goalList.get(i);
            if (new Random().nextDouble() * (totGoals + owngoals) <= cumulativeProbability) {
                return starters.get(i);
            }
        }
        return getAutogol(t);
    }

    /**
     * Generates an own goal scorer from the opposing team.
     *
     * @param team the team
     * @return the scorer
     */
    public Player getAutogol(final Team team) {
        Team t;
        if (team == t1) {
            t = t2;
        } else {
            t = t1;
        }
        return t.getStarting().get(new Random().nextInt(t.getStarting().size()));
    }
}
