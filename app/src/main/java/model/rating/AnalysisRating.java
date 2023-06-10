package model.rating;
import java.util.List;

import model.data.Player;

/**
 * Interface for calculating player ratings.
 */
public interface AnalysisRating {
    /**
     * Updates the ratings for all players.
     *
     * @return The updated list of players.
     */
    List<Player> updateRating();
}
