package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Team;

/**
 * Interface {@code SimulatingFunctions} for simulating functions related to fantasy football teams.
 */
public interface SimulatingFunctions {

	/**
     * Retrieves the number of fantasy goals conceded by the specified team.
     *
     * @param t the team
     * @return the number of fantasy goals conceded
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    int getFantasyConcededGoals(Team t)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the number of fantasy own goals scored by the specified team.
     *
     * @param t the team
     * @return the number of fantasy own goals
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    int getFantasyOwngoals(Team t)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the number of fantasy saved penalties by the specified team.
     *
     * @param t the team
     * @return the number of fantasy saved penalties
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    int getFantasySavedPenalties(Team t)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the difference between the number of scored and saved fantasy penalties by the specified team.
     *
     * @param t the team
     * @return the difference between scored goals and saved penalties
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    int getDeltaScoredSavedPenalties(Team t)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the fantasy defensive rating of the specified team based on the provided values.
     *
     * @param t the team
     * @param v the values for calculating the rating
     * @return the fantasy defensive rating
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    double getFantasyDefensiveRating(Team t, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the fantasy offensive rating of the specified team based on the provided values.
     *
     * @param t the team
     * @param v the values for calculating the rating
     * @return the fantasy offensive rating
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    double getFantasyOffensiveRating(Team t, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Retrieves the number of fantasy goals scored by the specified team.
     *
     * @param t the team
     * @return the number of fantasy goals scored
     * @throws FileNotFoundException    if the file is not found
     * @throws ClassNotFoundException   if the class is not found
     * @throws IOException              if an I/O error occurs
     */
    int getFantasyScoredGoals(Team t)
    		throws FileNotFoundException, ClassNotFoundException, IOException;
}
