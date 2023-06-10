package model.simulating;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.data.Player;
import model.data.Team;

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

    /**
     * Calculates the fantasy ratings for a list of players (starters).
     *
     * @param starting The list of players.
     * @return A map containing each player and their corresponding fantasy rating.
     */
    Map<Player, Double> getFantasyRantings(List<Player> starting);

    /**
     * Calculates the "lockdown defense" rating for a team based on the ratings of its defenders.
     *
     * @param t1                the team for which to calculate the defense rating
     * @param ratings1 a map containing the defenders and their corresponding fantasy ratings
     * @return the lockdown defense rating for the team
     */
    double getLockdownDefenseRating(Team t1, Map<Player, Double> ratings1);

    /**
     * Modifies the fantasy ratings of players in a team based on certain criteria.
     *
     * @param t2          the team for which to modify the ratings
     * @param ratings2 a map containing the players and their corresponding fantasy ratings
     * @return a map of role to modified ratings for the team
     */
    Map<String, Double> modifiedFantasyRatings(Team t2, Map<Player, Double> ratings2);
}
