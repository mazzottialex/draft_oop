package manageData;

import utils.Pair;
import utils.Triple;

public interface CalcoloRating {
	public Pair<Integer, Triple<Integer, Integer, Integer>> getRating(Calciatore calc);
}
