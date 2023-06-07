package rating;
import static java.util.stream.Collectors.toList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import utils.Pair;
import utils.Triple;

/**
 * Implementation of the AnalysisRating interface for calculating player ratings
 */
public class AnalysisRatingImpl implements AnalysisRating {
    private final Function fun = new Function();
    private final ExtractData ex;
    private final List<Player> li;
    
    /**
     * Constructs an AnalysisRatingImpl object with the given player list
     *
     * @param li The list of players
     * @throws FileNotFoundException If the file is not found
     * @throws ClassNotFoundException If the class is not found
     * @throws IOException If an I/O error occurs
     */
    public AnalysisRatingImpl(final List<Player> li) {
        ex = new ExtractDataImpl(li);
        this.li = li;
    }
    
    @Override
	public List<Player> updateRating() {
        return li.stream()
            .map(c-> {
                c.setRating(this.getRating(c));
                c.setCardRating(this.getRatingCard(c));
                return c;
            })
            .collect(toList());
    }
    
    /**
     * Calculates the overall rating and detailed rating components for the given player
     *
     * @param p The player for which to calculate the rating
     * @return A Pair object containing the overall rating and a Triple object with the detailed rating components
     */
    private Pair<Integer, Triple<Integer, Integer, Integer>> getRating(final Player p) {
    	final String ruolo = p.getPos();
        //per rating A
    	final int ratGol = fun.logarithmic(p.getGoals(), 
        		ex.getTopByAttribute(c -> c.getGoals()), 50, 100);
    	final int ratTiri = fun.logarithmic(p.getShots(),
        		ex.getTopByAttribute(c -> c.getShots()), 50, 100);
    	final int ratDribl = fun.logarithmic(p.getDribbles(),
        		ex.getTopByAttribute(c -> c.getDribbles()), 50, 90);
    	final int ratAss = fun.logarithmic(p.getAssists(),
        		ex.getTopByAttribute(c -> c.getAssists()), 50, 90);
    	final int ratPassChiave = fun.logarithmic(p.getKeyPasses(),
        		ex.getTopByAttribute(c -> c.getKeyPasses()), 50, 100);
    	final int ratA = (ratAss * 2 + ratDribl + ratGol * 5 + ratPassChiave + ratTiri * 2) / 11;
        //per rating D
    	final int ratPassaggi = fun.logarithmic(p.getPasses(),
        		ex.getTopByAttribute(c -> c.getPasses()), 50, 100);
    	final int ratRubati = fun.logarithmic(p.getBallsRecovered(),
        		ex.getTopByAttribute(c -> c.getBallsRecovered()), 50, 95);
    	final int ratTackle = fun.logarithmic(p.getTacklesWon(),
        		ex.getTopByAttribute(c -> c.getTacklesWon()), 50, 90);
    	final int ratCS = fun.logarithmic(p.getCleanSheets(),
        		ex.getTopByAttribute(c -> c.getCleanSheets()), 50, 82);
    	int ratD = (ratPassaggi + ratRubati + ratTackle + ratCS) / 4;
        //per rating C
    	int ratC = (ratA + ratD) / 2;
        //per rating totale
        int rat = 50;
        //per specifico ruolo
        if (ruolo.equals("A"))
            rat = (int) Math.ceil(0.9 * ratA + 0.1 * ratD);
        else if (ruolo.equals("C")) {
            ratC *= 1.05;
            rat = (int) Math.ceil(0.51 * ratA + 0.51 * ratD);
        } else if (ruolo.equals("D"))
            rat = (int) Math.ceil(0.1 * ratA + 0.9 * ratD);
        else if (ruolo.equals("P")) {
        	final int ratParate = fun.logarithmic(p.getSaves(), 
            		ex.getTopByAttribute(c -> c.getSaves()), 50, 92);
            ratD = (ratParate + ratCS) / 2;
            rat = ratD;
        }
        //varia in base al minutaggio (influsce del 20%)
        final int ratMin = fun.logarithmic(p.getMinutes(),
        		ex.getTopByAttribute(c -> c.getMinutes()), 10, 50, 100);
        rat = (int)(0.8 * rat + 0.2 * ratMin);
        return new Pair<Integer, Triple<Integer, Integer, Integer>> (rat, new Triple<>(ratA, ratC, ratD));
    }
    
    /**
     * Calculates the card rating for the given player
     *
     * @param p The player for which to calculate the card rating
     * @return A Pair object containing the card rating
     */
    private Pair<Integer, Integer> getRatingCard(Player p) {
        int ratAmm = 0;
        int ratEsp = 0;
        if (p.getMinutes() != 0) {
            ratAmm = p.getYellowCards() * 9000 / p.getMinutes();
            ratAmm = ratAmm < 100 ? ratAmm : 100;
        }
        if (p.getMinutes() != 0) {
            ratEsp = p.getRedCards() * 9000 / p.getMinutes();
            ratEsp = ratEsp < 100 ? ratEsp : 100;
        }
        ratAmm = fun.logarithmic(ratAmm, 100, 50, 90); //per percentuale commentare queste due righe
        ratEsp = fun.logarithmic(ratEsp, 100, 50, 90);
        return new Pair<Integer, Integer>(ratAmm, ratEsp);
    }
}
