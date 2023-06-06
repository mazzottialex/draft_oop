package scraping;
import java.util.List;
import data.Player;

/**
 * An interface for scraping data
 */
public interface Scraping {
	
	/**
     * Returns the list of players
     *
     * @return The list of players
     */
    public List<Player> getLiPlayer();
    
    /**
     * Returns the list of seasons
     *
     * @return The list of seasons
     */
    public List<String> getLiSeason();
    
    /**
     * Reads the table for the specified season and updates the player list
     *
     * @param season The season of the table to read
     * @return True if the table was successfully read, False otherwise
     */
    public Boolean readTable(String season);
    
    /**
     * Reads the table for the default season and updates the player list
     *
     * @return True if the table was successfully read, False otherwise
     */
    public Boolean readTable();
    
    /**
     * Reads the list of seasons
     *
     * @return True if the seasons were successfully read, False otherwise
     */
    public Boolean readSeason();
}
