package controller;

import java.net.URL;
import java.util.List;

import model.data.Player;

/**
 *The LogicsSettings interface represents the operations related to settings in a team management system.
 */
public interface LogicsSettings {

     /**
     * Retrieves the logo.
     * @return the logo
     */
    URL getLogo();

    /**
     * Sets the logo.
     * @param url the logo to set
     */
    void setLogo(URL url);

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
