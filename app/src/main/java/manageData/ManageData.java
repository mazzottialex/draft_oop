package manageData;

import java.util.List;
import data.Player;

/**
 * Interface {@code ManageData} for managing player data.
 */
public interface ManageData {
    /**
     * Retrieves the list of players.
     *
     * @return The list of players
     */
    List<Player> getLi();

    /**
     * Loads the data of players.
     * @throws Exception 
     */
    void loadData() throws Exception;

    /**
     * Downloads the data of players.
     *
     * @return True if the data download is successful, false otherwise
     */
    Boolean downloadData();
}
