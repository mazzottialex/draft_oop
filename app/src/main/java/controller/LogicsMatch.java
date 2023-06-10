package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.data.Player;
import model.data.Team;

/**
 * The {@code LogicsMatch} interface defines methods related to the logic of a match.
 */
public interface LogicsMatch {

    /**
     * Calculates the scorers for a given time period in the match.
     *
     * @param time the time period in the match.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    void scorers(int time) throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Returns a list of minute numbers corresponding to the goals scored by the specified team.
     *
     * @param t the team.
     * @return a list of minute numbers of goals.
     */
    List<Integer> getGoalsMinutes(Team t);

    /**
     * Adds a scorer to the specified team and returns the player who scored.
     *
     * @param s the team.
     * @return the player who scored.
     */
    Player addScorer(Team s);
}
