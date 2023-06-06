package manageData;

import java.util.List;
import data.Player;

/**
 * Interface for managing player data
 */
public interface ManageData {
    /**
     * Retrieves the list of players
     *
     * @return The list of players
     */
    public List<Player> getLi();

    /**
     * Loads the data of players
     * @throws Exception 
     */
    public void loadData() throws Exception;

    /**
     * Downloads the data of players
     *
     * @return True if the data download is successful, false otherwise
     */
    public Boolean downloadData();
}
