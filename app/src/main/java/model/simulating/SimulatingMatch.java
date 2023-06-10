package model.simulating;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import model.data.Team;

/**
 * Implementation of the {@code SimulatingMatch} interface that simulates a match between two teams.
 */
public interface SimulatingMatch {

    /**
     * Simulates the match and returns the result as a map of teams to their scores.
     *
     * @param minute the minute from which to simulate the match
     * @return a map of teams to their scores
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    Map<Team, Integer> result(int minute)
            throws FileNotFoundException, ClassNotFoundException, IOException;
}
