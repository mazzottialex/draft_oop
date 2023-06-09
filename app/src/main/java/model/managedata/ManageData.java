package model.managedata;

import java.io.IOException;
import java.util.List;

import model.data.Player;

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
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws Exception 
     */
    void loadData() throws ClassNotFoundException, IOException;

    /**
     * Downloads the data of players.
     * @param nThread number of thread
     *
     * @return True if the data download is successful, false otherwise
     */
    Boolean downloadData(int nThread);
}
