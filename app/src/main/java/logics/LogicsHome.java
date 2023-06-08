package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;

/**
 * The LogicsHome interface provides methods for managing football seasons and retrieving player information.
 */
public interface LogicsHome {
     /**
     * Returns the current season.
     *
     * @return the current season
     */
    String getSeason();
    /**
     * Returns the online status.
     *
     * @return the online status
     */
    Boolean getOnline();
    /**
     * Sets the current season.
     *
     * @param season the season to set
     */
    void setSeason(String season);

    /**
     * Returns a list of available seasons.
     *
     * @return a list of available seasons
     */
    List<String> getLiSeasons();

    /**
     * Loads the data for the specified season.
     *
     * @param season the season to load
     * @return true if the data is successfully loaded, false otherwise
     */
    Boolean loadStagione(String season);

    /**
     * Downloads the data for the specified season.
     *
     * @param season the season to download
     * @return true if the data is successfully downloaded, false otherwise
     */
    Boolean downloadSeason(String season);

    /**
     * Retrieves a list of players for the current season.
     *
     * @return a list of players
     * @throws FileNotFoundException if the data file is not found
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     * @throws IOException if an I/O error occurs during reading the data
     */
    List<Player> getLi() throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Check if browser Chrome or/and Firefox are installed on file system.
     * 
     * @return True if Chrome or/and Firefox are correctly installed, False otherwise
     */
    Boolean checkBrowser();
}
