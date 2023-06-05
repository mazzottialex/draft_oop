package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;
import data.Squadra;

/**
 * The {@code LogicsPartita} interface defines methods related to the logic of a match.
 */
public interface LogicsPartita {
	
	/**
     * Calculates the scorers for a given time period in the match.
     *
     * @param tempo the time period in the match.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    void scorers(int tempo) throws FileNotFoundException, ClassNotFoundException, IOException;
    
    /**
     * Returns a list of minute numbers corresponding to the goals scored by the specified team.
     *
     * @param s the team.
     * @return a list of minute numbers of goals.
     */
    List<Integer> getMinGol(Squadra s);
    
    /**
     * Adds a scorer to the specified team and returns the player who scored.
     *
     * @param s the team.
     * @return the player who scored.
     */
    Calciatore addScorer(Squadra s);
    
    /**
     * Applies sanctions or penalties during the match.
     */
    void sanctions();
}
