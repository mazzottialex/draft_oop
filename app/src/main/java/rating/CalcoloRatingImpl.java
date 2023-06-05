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
public class CalcoloRatingImpl implements CalcoloRating {
    private final Funzioni fun = new Funzioni();
    private final ExtractData ex;
    private final List <Player> li;
    public CalcoloRatingImpl(final List<Player> li) 
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        ex = new ExtractDataImpl(li);
        this.li = li;
    }
    @Override
    public Pair<Integer, Triple<Integer, Integer, Integer>> getRating(final Player calc) {
    	final String ruolo = calc.getPos();
        //per rating A
    	final int ratGol = fun.Logaritmica(calc.getGoals(), 
        		ex.getTopByAttribute(c -> c.getGoals()), 50, 100);
    	final int ratTiri = fun.Logaritmica(calc.getShots(),
        		ex.getTopByAttribute(c -> c.getShots()), 50, 100);
    	final int ratDribl = fun.Logaritmica(calc.getDribbles(),
        		ex.getTopByAttribute(c -> c.getDribbles()), 50, 90);
    	final int ratAss = fun.Logaritmica(calc.getAssists(),
        		ex.getTopByAttribute(c -> c.getAssists()), 50, 90);
    	final int ratPassChiave = fun.Logaritmica(calc.getKeyPasses(),
        		ex.getTopByAttribute(c -> c.getKeyPasses()), 50, 100);
    	final int ratA = (ratAss * 2 + ratDribl + ratGol * 5 + ratPassChiave + ratTiri * 2) / 11;
        //per rating D
    	final int ratPassaggi = fun.Logaritmica(calc.getPasses(),
        		ex.getTopByAttribute(c -> c.getPasses()), 50, 100);
    	final int ratRubati = fun.Logaritmica(calc.getBallsRecovered(),
        		ex.getTopByAttribute(c -> c.getBallsRecovered()), 50, 95);
    	final int ratTackle = fun.Logaritmica(calc.getTacklesWon(),
        		ex.getTopByAttribute(c -> c.getTacklesWon()), 50, 90);
    	final int ratCS = fun.Logaritmica(calc.getCleanSheets(),
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
        	final int ratParate = fun.Logaritmica(calc.getSaves(), 
            		ex.getTopByAttribute(c -> c.getSaves()), 50, 92);
            ratD = (ratParate + ratCS) / 2;
            rat = ratD;
        }
        //varia in base al minutaggio (influsce del 20%)
        final int ratMin = fun.Logaritmica(calc.getMinutes(),
        		ex.getTopByAttribute(c -> c.getMinutes()), 10, 50, 100);
        rat = (int)(0.8 * rat + 0.2 * ratMin);
        return new Pair<Integer, Triple<Integer, Integer, Integer>> (rat, new Triple<>(ratA, ratC, ratD));
    }
    public Pair<Integer, Integer> getRatingCard(Player calc) {
        int ratAmm = 0;
        int ratEsp = 0;
        if (calc.getMinutes() != 0) {
            ratAmm = calc.getYellowCards() * 9000 / calc.getMinutes();
            ratAmm = ratAmm < 100 ? ratAmm : 100;
        }
        if (calc.getMinutes() != 0) {
            ratEsp = calc.getRedCards() * 9000 / calc.getMinutes();
            ratEsp = ratEsp < 100 ? ratEsp : 100;
        }
        ratAmm = fun.Logaritmica(ratAmm, 100, 50, 90); //per percentuale commentare queste due righe
        ratEsp = fun.Logaritmica(ratEsp, 100, 50, 90);
        return new Pair<Integer, Integer>(ratAmm, ratEsp);
    }

    public List<Player> updateRating() {
        return li.stream()
            .map(c-> {
                c.setRating(this.getRating(c));c.setCardRating(this.getRatingCard(c));
                return c;
            })
            .collect(toList());
    }

    public List<Player> getLi() {
        return li;
    }
}
