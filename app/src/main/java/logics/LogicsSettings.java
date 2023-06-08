package logics;

import java.util.List;

import data.Player;

/**
 *The LogicsSettings interface represents the operations related to settings in a team management system.
 */
public interface LogicsSettings {

     /**
     * Retrieves the logo.
     * @return the logo
     */
    String getLogo();

    /**
     * Sets the logo.
     * @param logo the logo to set
     */
    void setLogo(String logo);

    /**
     * Retrieves the teamName.
     * @return the teamName
     */
    String getTeamName();

    /**
     * Sets the team name.
     * @param teamName the name to set
     */
    void setTeamName(String teamName);

    /**
     * Retrieves the list of players.
     * @return the List of players
     */
    List<Player> getLi();

    /**
     * Retrieves the list of logo.
     * @return the List of logo
     */
    List<String> getLiLogo();
}
