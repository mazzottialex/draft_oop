package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Team;

/**
 * Implementation of the {@code SimulatingMatch} interface that simulates a match between two teams.
 */
public interface SimulatingMatch {

    /**
     * Calculates the defensive performance of a team.
     *
     * @param team the team for which to calculate the defensive performance
     * @return the defensive performance value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    double defensivePerformance(Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Calculates the scoring ability of a team.
     *
     * @param team the team for which to calculate the scoring ability
     * @return the scoring ability value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    double scoringAbility(Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Calculates the offensive performance of a team.
     *
     * @param team the team for which to calculate the offensive performance
     * @return the offensive performance value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    double offensivePerformance(Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Simulates the match and returns the result as a map of teams to their scores.
     *
     * @return a map of teams to their scores
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    Map<Team, Integer> result()
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Simulates the match and returns the result of the extra-time as a map of teams to their scores.
     *
     * @return a map of teams to their scores
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    Map<Team, Integer> resultExtra()
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Simulates the match from a given minute and returns the result as a map of teams to their scores.
     *
     * @param minute the minute from which to simulate the match
     * @return a map of teams to their scores
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    Map<Team, Integer> resultSub(int minute)
            throws FileNotFoundException, ClassNotFoundException, IOException;
}
