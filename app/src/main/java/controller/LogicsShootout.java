package controller;

import java.util.ArrayList;
import java.util.Map;

import model.data.Player;
import model.data.Team;
import utils.Pair;

/**
 * The {@code LogicsShootout} interface defines the logic for penalty shoot-outs.
 */
public interface LogicsShootout {

    /**
     * Computes the penalty shoot-out and returns the list of penalty results.
     *
     * @return the list of penalty results
     */
    ArrayList<Map<Integer, Pair<Player, String>>> compute();

    /**
     * Returns the number of goals scored by team 1 in the penalty shoot-out.
     *
     * @return the number of goals scored by team 1
     */
    int getGoals1();

    /**
     * Returns the number of goals scored by team 2 in the penalty shoot-out.
     *
     * @return the number of goals scored by team 2
     */
    int getGoals2();

    /**
     * Returns the winner of the penalty shoot-out.
     *
     * @return the winning team
     */
    Team getWinner();
}
