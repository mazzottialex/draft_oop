package model.managedata;

import java.io.IOException;
import java.util.List;

import model.data.Player;
import model.data.Team;

/**
 * Interface {@code LogicsFile} for logics of File.
 */
public interface LogicsFile {

    /**
     * Loads the player data from a file for the specified season.
     *
     * @param season The season for which to load the player data
     * @return The list of Player objects loaded from the file
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    List<Player> loadData(String season) throws IOException, ClassNotFoundException;

    /**
     * Saves the player data to a file for the specified season.
     *
     * @param li     The list of Player objects to save
     * @param season The season for which to save the player data
     * @return true if the data is saved successfully, false otherwise
     */
    Boolean saveData(List<Player> li, String season);

    /**
     * Loads the list of seasons from a file.
     *
     * @return The list of seasons loaded from the file
     */
    List<String> loadSeason();

    /**
     * Saves the list of seasons to a file.
     *
     * @param li The list of seasons to save
     * @return true if the seasons are saved successfully, false otherwise
     */
    Boolean saveSeason(List<String> li);

    /**
     * Loads the history of teams from a file.
     *
     * @return The list of Team objects loaded from the file
     */
    List<Team> loadHistory();

    /**
     * Saves a team's history to a file.
     *
     * @param s The Team object representing the team's history to save
     * @return true if the team's history is saved successfully, false otherwise
     */
    Boolean saveHistory(Team s);
}
