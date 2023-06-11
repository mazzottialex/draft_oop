package model.simulating;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.data.Player;
import model.data.Team;
import model.managedata.ExtractDataImpl;

/**
 * Implementation of the {@code SimulatingMatch} interface that simulates a match between two teams.
 */
public final class SimulatingMatchImpl implements SimulatingMatch, Serializable {
    private static final long serialVersionUID = -7975364128825077937L;
    final private Team t1;
    final private Team t2;
    final private int concededGoals1;
    final private int concededGoals2;
    final private int owngoals1;
    final private int owngoals2;
    final private int savedPenalties1;
    final private int savedPenalties2;
    final private double lockdownDefense1;
    final private double lockdownDefense2;
    final private double defensiveRatings1;
    final private double defensiveRatings2;
    final private double offensiveRatings1;
    final private double offensiveRatings2;
    final private int scoredGoals1;
    final private int scoredGoals2;
    final private int scoredPenalties1;
    final private int scoredPenalties2;
    private static final double COST_SUB_DEF = 14; // da aumentare a 15
    private static final double COST_SUB_OFF = 5; // da aumentare a 7
    private static final double COST_DIV_DEF_OFF_CR = 5;
    private static final int START = 0;
    private static final int END_REG = 90;
    private static final int END_EXTRA = 120;

    /**
     * Constructs a new {@code SimulatingMatchImpl} object with the specified teams.
     *
     * @param t1 the first team
     * @param t2 the second team
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    @SuppressFBWarnings("EI2")
    public SimulatingMatchImpl(final Team t1, final Team t2)
    throws FileNotFoundException, ClassNotFoundException, IOException {
    	final SimulatingFunctions sf = new SimulatingFunctionsImpl();
        this.t1 = t1;
        this.t2 = t2;
        final Map<Player, Double> ratings1 = sf.getFantasyRantings(this.t1.getStarting());
        final Map<Player, Double> ratings2 = sf.getFantasyRantings(this.t2.getStarting());
        concededGoals1 = sf.getFantasyConcededGoals(this.t1);
        concededGoals2 = sf.getFantasyConcededGoals(this.t2);
        owngoals1 = sf.getFantasyOwngoals(this.t1);
        owngoals2 = sf.getFantasyOwngoals(this.t2);
        savedPenalties1 = sf.getFantasySavedPenalties(this.t1);
        savedPenalties2 = sf.getFantasySavedPenalties(this.t2);
        lockdownDefense1 = sf.getLockdownDefenseRating(this.t1, ratings1);
        lockdownDefense2 = sf.getLockdownDefenseRating(this.t2, ratings2);
        final Map<String, Double> modifiedRatings1 = sf.modifiedFantasyRatings(this.t1, ratings1);
        final Map<String, Double> modifiedRatings2 = sf.modifiedFantasyRatings(this.t2, ratings2);
        defensiveRatings1 = sf.getFantasyDefensiveRating(t1, modifiedRatings1);
        defensiveRatings2 = sf.getFantasyDefensiveRating(t2, modifiedRatings2);
        offensiveRatings1 = sf.getFantasyOffensiveRating(t1, modifiedRatings1);
        offensiveRatings2 = sf.getFantasyOffensiveRating(t2, modifiedRatings2);
        scoredGoals1 = sf.getFantasyScoredGoals(t1);
        scoredGoals2 = sf.getFantasyScoredGoals(t2);
        scoredPenalties1 = sf.getDeltaScoredSavedPenalties(t1);
        scoredPenalties2 = sf.getDeltaScoredSavedPenalties(t2);
    }

    /**
     * Calculates the defensive performance of a team.
     *
     * @param team the team for which to calculate the defensive performance
     * @return the defensive performance value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    private double defensivePerformance(final Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        double dp = 0;
        if (team.equals(t1)) {
            dp = (defensiveRatings1 + new ExtractDataImpl(t1.getStarting()).getListByPos("D").size() + lockdownDefense1
                    - 2 * concededGoals1 - 2 * owngoals1 + 3 * savedPenalties1 - COST_SUB_DEF) / COST_DIV_DEF_OFF_CR;
        } else if (team.equals(t2)) {
            dp = (defensiveRatings2 + new ExtractDataImpl(t2.getStarting()).getListByPos("D").size() + lockdownDefense2
                    - 2 * concededGoals2 - 2 * owngoals2 + 3 * savedPenalties2 - COST_SUB_DEF) / COST_DIV_DEF_OFF_CR;
        }
        return dp;
    }

    /**
     * Calculates the scoring ability of a team.
     *
     * @param team the team for which to calculate the scoring ability
     * @return the scoring ability value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    private double scoringAbility(final Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        double sa = 0;
        if (team.equals(t1)) {
            sa = scoredGoals1 + owngoals2 + scoredPenalties1;
        } else if (team.equals(t2)) {
            sa = scoredGoals2 + owngoals1 + scoredPenalties2;
        }
        return sa;
    }

    /**
     * Calculates the offensive performance of a team.
     *
     * @param team the team for which to calculate the offensive performance
     * @return the offensive performance value
     * @throws FileNotFoundException    if the data file is not found
     * @throws ClassNotFoundException   if the class is not found during deserialization
     * @throws IOException              if an I/O error occurs during data extraction
     */
    private double offensivePerformance(final Team team)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        double op = 0;
        if (team.equals(t1)) {
            op = (offensiveRatings1 + (scoringAbility(team) / 2) - COST_SUB_OFF) / COST_DIV_DEF_OFF_CR;
        } else if (team.equals(t2)) {
            op = (offensiveRatings2 + (scoringAbility(team) / 2) - COST_SUB_OFF) / COST_DIV_DEF_OFF_CR;
        }
        return op;
    }

    @Override
    public Map<Team, Integer> result(final int minute)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        final int team1 = (int) Math.round(Math.min(scoringAbility(t1),
            offensivePerformance(t1) - defensivePerformance(t2)));
        final int team2 = (int) Math.round(Math.min(scoringAbility(t2),
            offensivePerformance(t2) - defensivePerformance(t1)));
        final Map<Team, Integer> map = new HashMap<>();
        if (minute == START) {
            map.put(this.t1, team1 >= 0 ? team1 : 0);
            map.put(this.t2, team2 >= 0 ? team2 : 0);
            return map;
        } else {
            if (minute < END_REG) {
                map.put(t1, (int) (((double) team1 >= 0 ? team1 : 0) * (END_REG - minute) / END_REG));
                map.put(t2, (int) (((double) team2 >= 0 ? team2 : 0) * (END_REG - minute) / END_REG));
            } else {
                map.put(t1, (int) (((double) team1 >= 0 ? team1 : 0) * (END_EXTRA - minute) / END_REG));
                map.put(t2, (int) (((double) team2 >= 0 ? team2 : 0) * (END_EXTRA - minute) / END_REG));
            }
            return map;
        }
    }
}
