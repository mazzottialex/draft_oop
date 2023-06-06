package data;

import java.io.Serializable;
import java.util.List;

/**
 * The interface represents a team in a football (soccer) match.
 */
public interface Team extends Serializable{

    /**
     * Returns a list of the starting players for the team.
     *
     * @return a list of Player objects representing the starting players.
     */
    List<Player> getStarting();

    /**
     * Returns a list of the reserve players for the team.
     *
     * @return a list of Player objects representing the reserve players.
     */
    List<Player> getSubstitution();

    /**
     * Returns the name of the team.
     *
     * @return a string representing the name of the team.
     */
    String getTeamName();

    /**
     * Returns the emblem of the team.
     *
     * @return a string representing the emblem of the team.
     */
    String getLogo();

    /**
     * Returns the team's evaluation.
     *
     * @return an integer representing the team's evaluation.
     */
    int getRating();

    /**
     * Returns the formation (modulo) of the team.
     *
     * @return a Modulo object representing the formation of the team.
     */
    Module getModule();

    /**
     * Returns the ID of the team.
     *
     * @return an integer representing the ID of the team.
     */
    int getId();

    /**
     * Returns a list of all the players in the team.
     *
     * @return a list of Player objects representing all the players in the team.
     */
    List<Player> getLiPlayers();

    /**
     * Returns the player with the specified ID.
     *
     * @param id the ID of the player to retrieve.
     * @return the Player object with the specified ID.
     */
    Player getPlayerById(int id);

    /**
     * Returns the starting goalkeeper for the team.
     *
     * @return the Player object representing the starting goalkeeper.
     */
    Player getStartingKeeper();

    /**
     * Sets the list of starting players for the team.
     *
     * @param liStarting a list of Player objects representing the starting players.
     */
    void setStarting(List<Player> liStarting);

    /**
     * Sets the list of reserve players for the team.
     *
     * @param liSubstitution a list of Player objects representing the reserve players.
     */
    void setSubstitution(List<Player> liSubstitution);

    /**
     * Returns a list of the starting players in descending order.
     *
     * @return a list of Player objects representing the starting players in descending order.
     */
    List<Player> getStartingDesc();
}
