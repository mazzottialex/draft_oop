package logics;

import java.util.ArrayList;
import java.util.Map;

import data.Player;
import data.Team;
import utils.Pair;

/**
 * The {@code LogicsRigori} interface defines the logic for penalty shoot-outs.
 */
public interface LogicsRigori {
	
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
    int getGol1();
    
    /**
     * Returns the number of goals scored by team 2 in the penalty shoot-out.
     *
     * @return the number of goals scored by team 2
     */
    int getGol2();
    
    /**
     * Returns the winner of the penalty shoot-out.
     *
     * @return the winning team
     */
    Team getWinner();
}
