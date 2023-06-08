package managedata;
import java.util.List;

/**
 * Interface {@code ManageSeason} for managing seasons.
 */
public interface ManageSeason {

    /**
     * Retrieves the list of seasons.
     *
     * @return The list of seasons
     */
    List<String> getSeason();

    /**
     * Updates the list of seasons.
     */
    void updateSeason();

    /**
     * Checks if updateSeason is successful.
     *
     * @return True if updateSeason is successful, false otherwise
     */
    Boolean check();
}
