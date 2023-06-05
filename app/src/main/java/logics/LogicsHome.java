package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;

/**
 * The LogicsHome interface provides methods for managing football seasons and retrieving player information
 */
public interface LogicsHome {
	/**
     * Sets the current season
     *
     * @param stagione the season to set
     */
	public void setSeason(String season);
	
	/**
     * Returns a list of available seasons
     *
     * @return a list of available seasons
     */
	public List<String> getSeason();
	
	/**
     * Loads the data for the specified season
     *
     * @param stagione the season to load
     * @return true if the data is successfully loaded, false otherwise
     */
	public Boolean loadStagione(String season);
	
	/**
     * Downloads the data for the specified season
     *
     * @param season the season to download
     * @return true if the data is successfully downloaded, false otherwise
     */
	public Boolean downloadSeason(String season);
	
	/**
     * Retrieves a list of players for the current season
     *
     * @return a list of players
     * @throws FileNotFoundException if the data file is not found
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     * @throws IOException if an I/O error occurs during reading the data
     */
	public List<Calciatore> getLi() throws FileNotFoundException, ClassNotFoundException, IOException;
}
