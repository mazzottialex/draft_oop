package model.rating;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import model.data.Player;
import model.data.managedata.ExtractData;
import model.data.managedata.ExtractDataImpl;
import utils.Pair;
import utils.Triple;

/**
 * Implementation of the AnalysisRating interface for calculating player ratings.
 */
public final class AnalysisRatingImpl implements AnalysisRating {
    private final Function fun = new Function();
    private final ExtractData ex;
    private final List<Player> li;
    private static final int MAX = 100;
    private static final int MIN = 50;
    private static final int MAX_DRIBLING = 90;
    private static final int MAX_ASSIST = 90;
    private static final int MAX_TACKLE = 90;
    private static final int MAX_STEAL = 95;
    private static final int MAX_CLEANSHEET = 82;
    private static final int MAX_SAVED = 92;
    private static final double COST_MAX = 0.9;
    private static final double COST_MIN = 0.1;
    private static final double COST_MED = 0.51;
    private static final double COST_MIDF = 1.05;
    private static final int COST_CARDS = 9000;
    private static final int CARDS = 90;
    private static final double COST_RAT = 0.8;
    private static final double COST_RAT_MIN = 0.2;

    /**
     * Constructs an AnalysisRatingImpl object with the given player list.
     *
     * @param li The list of players
     * @throws FileNotFoundException If the file is not found
     * @throws ClassNotFoundException If the class is not found
     * @throws IOException If an I/O error occurs
     */
    public AnalysisRatingImpl(final List<Player> li) {
        ex = new ExtractDataImpl(li);
        this.li = new ArrayList<>(li);
    }

    @Override
    public List<Player> updateRating() {
        return li.stream()
                .map(c -> {
                    c.setRating(this.getRating(c));
                    c.setCardRating(this.getRatingCard(c));
                    return c;
                })
                .collect(toList());
    }

    /**
     * Calculates the overall rating and detailed rating components for the given player.
     *
     * @param p The player for which to calculate the rating
     * @return A Pair object containing the overall rating and a Triple object with the detailed rating components
     */
    private Pair<Integer, Triple<Integer, Integer, Integer>> getRating(final Player p) {
        final String ruolo = p.getPos();
        //per rating A
        final int ratGol = fun.logarithmic(p.getGoals(),
                ex.getTopByAttribute(c -> c.getGoals()), MIN, MAX);
        final int ratTiri = fun.logarithmic(p.getShots(),
                ex.getTopByAttribute(c -> c.getShots()), MIN, MAX);
        final int ratDribl = fun.logarithmic(p.getDribbles(),
                ex.getTopByAttribute(c -> c.getDribbles()), MIN, MAX_DRIBLING);
        final int ratAss = fun.logarithmic(p.getAssists(),
                ex.getTopByAttribute(c -> c.getAssists()), MIN, MAX_ASSIST);
        final int ratPassChiave = fun.logarithmic(p.getKeyPasses(),
                ex.getTopByAttribute(c -> c.getKeyPasses()), MIN, MAX);
        final int ratA = (ratAss * 2 + ratDribl + ratGol * 5 + ratPassChiave + ratTiri * 2) / 11;
        //per rating D
        final int ratPassaggi = fun.logarithmic(p.getPasses(),
                ex.getTopByAttribute(c -> c.getPasses()), MIN, MAX);
        final int ratRubati = fun.logarithmic(p.getBallsRecovered(),
                ex.getTopByAttribute(c -> c.getBallsRecovered()), MIN, MAX_STEAL);
        final int ratTackle = fun.logarithmic(p.getTacklesWon(),
                ex.getTopByAttribute(c -> c.getTacklesWon()), MIN, MAX_TACKLE);
        final int ratCS = fun.logarithmic(p.getCleanSheets(),
                ex.getTopByAttribute(c -> c.getCleanSheets()), MIN, MAX_CLEANSHEET);
        int ratD = (ratPassaggi + ratRubati + ratTackle + ratCS) / 4;
        //per rating C
        int ratC = (ratA + ratD) / 2;
        //per rating totale
        int rat = MIN;
        //per specifico ruolo
        if (ruolo.equals("A")) {
            rat = (int) Math.ceil(COST_MAX * ratA + COST_MIN * ratD);
        } else if (ruolo.equals("C")) {
            ratC *= COST_MIDF;
            rat = (int) Math.ceil(COST_MED * ratA + COST_MED * ratD);
        } else if (ruolo.equals("D")) {
            rat = (int) Math.ceil(COST_MIN * ratA + COST_MAX * ratD);
        } else if (ruolo.equals("P")) {
            final int ratParate = fun.logarithmic(p.getSaves(),
                    ex.getTopByAttribute(c -> c.getSaves()), MIN, MAX_SAVED);
            ratD = (ratParate + ratCS) / 2;
            rat = ratD;
        }
        //varia in base al minutaggio (influsce del 20%)
        final int ratMin = fun.logarithmic(p.getMinutes(),
                ex.getTopByAttribute(c -> c.getMinutes()), 10, MIN, MAX);
        rat = (int) (COST_RAT * rat + COST_RAT_MIN * ratMin);
        return new Pair<Integer, Triple<Integer, Integer, Integer>>(rat, new Triple<>(ratA, ratC, ratD));
    }

    /**
     * Calculates the card rating for the given player.
     *
     * @param p The player for which to calculate the card rating
     * @return A Pair object containing the card rating
     */
    private Pair<Integer, Integer> getRatingCard(final Player p) {
        int ratAmm = 0;
        int ratEsp = 0;
        if (p.getMinutes() != 0) {
            ratAmm = p.getYellowCards() * COST_CARDS / p.getMinutes();
            ratAmm = ratAmm < MAX ? ratAmm : MAX;
        }
        if (p.getMinutes() != 0) {
            ratEsp = p.getRedCards() * COST_CARDS / p.getMinutes();
            ratEsp = ratEsp < MAX ? ratEsp : MAX;
        }
        ratAmm = fun.logarithmic(ratAmm, MAX, MIN, CARDS); //per percentuale commentare queste due righe
        ratEsp = fun.logarithmic(ratEsp, MAX, MIN, CARDS);
        return new Pair<Integer, Integer>(ratAmm, ratEsp);
    }
}
