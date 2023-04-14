package rating;

import data.Calciatore;
import utils.Pair;
import utils.Triple;

public interface CalcoloRating {
	public Pair<Integer, Triple<Integer, Integer, Integer>> getRating(Calciatore calc);
	public Pair<Integer, Integer> getRatingCartellino(Calciatore calc);
}
