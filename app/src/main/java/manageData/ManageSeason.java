package manageData;
import java.util.List;

/**
 * Interface for managing seasons
 */
public interface ManageSeason {
	
	/**
	 * Retrieves the list of seasons
	 *
	 * @return The list of seasons
	 */
	public List<String> getSeason();
	
	/**
	 * Updates the list of seasons
	 */
	public void updateSeason();
	
	/**
	 * Checks if updateSeason is successful
	 *
	 * @return True if updateSeason is successful, false otherwise
	 */
    public Boolean check();
}
