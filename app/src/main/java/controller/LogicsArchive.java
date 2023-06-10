package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.data.Player;
/**
 * This interface represents an archive of logic operations on player data.
 */
public interface LogicsArchive {
    /**
     * Orders the list of players according to a specific criterion.
     *
     * @param players the list of players to be ordered
     * @return the ordered list of players
     * @throws FileNotFoundException if a file is not found
     * @throws ClassNotFoundException if a class is not found
     * @throws IOException if an I/O error occurs
     */
    List<Player> liOrdered(List<Player> players)
            throws FileNotFoundException, ClassNotFoundException, IOException;

    /**
     * Gets the online status.
     *
     * @return true if online, false if offline
     */
    Boolean getOnline();

    /**
     * Gets the current season.
     *
     * @return the current season
     */
    String getSeason();
}

