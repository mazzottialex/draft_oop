package rating;
import java.util.List;
import data.Player;

/**
 * Interface for calculating player ratings
 */
public interface AnalysisRating {
	
    /**
     * Updates the ratings for all players.
     *
     * @return The updated list of players.
     */
    public List<Player> updateRating();
}
