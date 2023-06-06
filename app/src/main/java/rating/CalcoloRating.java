package rating;
import java.util.List;
import data.Player;
import utils.Pair;
import utils.Triple;
public interface CalcoloRating {
    public Pair<Integer, Triple<Integer, Integer, Integer>> getRating(Player calc);
    public Pair<Integer, Integer> getRatingCard(Player calc);
    public List<Player> updateRating();
    public List<Player> getLi();
}
