package model.scraping;
import java.util.List;

import model.data.Player;

/**
 * An interface for scraping data.
 */
public interface Scraping {
    /**
     * Returns the list of players.
     *
     * @return The list of players
     */
    List<Player> getLiPlayer();
    /**
     * Returns the list of seasons.
     *
     * @return The list of seasons
     */
    List<String> getLiSeason();

    /**
     * Reads the table for the specified season and updates the player list.
     *
     * @param season The season of the table to read
     * @param nThread number of thread
     * @return True if the table was successfully read, False otherwise
     */
    Boolean readTable(String season, int nThread);

    /**
     * Reads the table for the default season and updates the player list.
     *
     * @return True if the table was successfully read, False otherwise
     */
    Boolean readTable();

    /**
     * Reads the list of seasons.
     *
     * @return True if the seasons were successfully read, False otherwise
     */
    Boolean readSeason();
    /**
     * Check if browser Chrome or/and Firefox are installed on file system.
     * 
     * @return True if Chrome or/and Firefox are correctly installed, False otherwise
     */
    Boolean checkBrowsers();

}
