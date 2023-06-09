package model.simulating;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.data.Player;
import model.data.Team;
import model.managedata.ExtractData;
import model.managedata.ExtractDataImpl;

/**
 * Implementation of the {@code SimulatingFunctions} interface that provides
 * simulation functions for fantasy football.
 */
public final class SimulatingFunctionsImpl implements SimulatingFunctions, Serializable {

    private static final long serialVersionUID = -1388612538618774091L;
    private final Random r = new Random();
    private static final double OWNGOAL_RATE = 2.904_040_404_040_404; // percentuale di autogol su gol
    private static final double PENALTY_RATE = 0.2875; // rigori per partita
    private static final double MISSED_PENALTIES_RATE = 22.826_086_956_521_74; // percentuale rigori sbagliati
    private static final double COST_VOTE_P = 0.074_683_544_303_797_5; // costante calcolo voto portiere
    private static final double COST_VOTE_D = 0.072_151_898_734_177_2; // costante calcolo voto difensore
    private static final double COST_VOTE_C = 0.075_308_641_975_308_6; // costante calcolo voto centrocampista
    private static final double COST_VOTE_A = 0.072_941_176_470_588_2; // costante calcolo voto attaccante
    private static final int THRESHOLD_GOAL_1 = 51;
    private static final int THRESHOLD_GOAL_2 = 77;
    private static final int THRESHOLD_GOAL_3 = 90;
    private static final int THRESHOLD_GOAL_4 = 96;
    private static final int THRESHOLD_GOAL_5 = 99;
    private static final int GOAL_6 = 6;
    private static final int GOAL_5 = 5;
    private static final double THRESHOLD_3_DIF = 6.5;
    private static final double THRESHOLD_4_5_DIF = 6;
    private static final double BONUS_4_DIF = 1;
    private static final double BONUS_5_DIF = 2;
    private static final double SUB_VOTE = 5;
    private static final double MOD_VOTE_DIF_D = 3;
    private static final double MOD_VOTE_DIF_C = 1;
    private static final double MOD_VOTE_DIF_A = 0.5;
    private static final double MOD_VOTE_OFF_D = 0.5;
    private static final double MOD_VOTE_OFF_C = 4;
    private static final double MOD_VOTE_OFF_A = 2.5;
    private static final double MINUTES = 90;
    private static final double MOD_MAX = 1.2;
    private static final double MOD_MIN = 0.8;
    private static final double DEF_5 = 5;
    private static final Map<String, Double> ROLE_COEFFICIENT_MAP = new HashMap<>();

    static {
        ROLE_COEFFICIENT_MAP.put("P", COST_VOTE_P);
        ROLE_COEFFICIENT_MAP.put("D", COST_VOTE_D);
        ROLE_COEFFICIENT_MAP.put("C", COST_VOTE_C);
        ROLE_COEFFICIENT_MAP.put("A", COST_VOTE_A);
    }

    /**
     * Generates a random number within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random number between min and max.
     */
    public double prob(final double min, final double max) {
        return min + (max - min) * r.nextDouble();
    }

    /**
     * Calculates the fantasy rating for a player.
     *
     * @param p The player.
     * @return The fantasy rating for the player.
     */
    public double getFantasyPlayerRating(final Player p) {
        final double k = ROLE_COEFFICIENT_MAP.getOrDefault(p.getPos(), 0.0);
        return k * p.getRating().getX() * prob(MOD_MIN, MOD_MAX);
    }

    @Override
    public Map<Player, Double> getFantasyRantings(final List<Player> starters) {
        final Map<Player, Double> map = new LinkedHashMap<>();
        for (final Player p: starters) {
            map.put(p, getFantasyPlayerRating(p));
        }
        return map;
    }

    @Override
    public int getFantasyConcededGoals(final Team t) throws FileNotFoundException, ClassNotFoundException, IOException {
        final ExtractData ed = new ExtractDataImpl(t.getStarting());
        final Player gk = ed.getListByPos("P").get(0);
        final double probCleanSheet = (double) gk.getCleanSheets() / (gk.getMatchesPlayed() == 0 ? 1 : gk.getMatchesPlayed());
        if (prob(0, 1) <= probCleanSheet) {
            return 0;
        } else {
            int goals = 1;
            final int p = (int) (1 + (prob(0, 1) * 100));
            if (p > THRESHOLD_GOAL_1 && p <= THRESHOLD_GOAL_2) {
                goals = 2;
            } else if (p > THRESHOLD_GOAL_2 && p <= THRESHOLD_GOAL_3) {
                goals = 3;
            } else if (p > THRESHOLD_GOAL_3 && p <= THRESHOLD_GOAL_4) {
                goals = 4;
            } else if (p > THRESHOLD_GOAL_4 && p <= THRESHOLD_GOAL_5) {
                goals = GOAL_5;
            } else if (p > THRESHOLD_GOAL_5) {
                goals = GOAL_6;
            }
            return goals;
        }
    }

    @Override
    public int getFantasyOwngoals(final Team t) throws FileNotFoundException, ClassNotFoundException, IOException {
        int og = 0;
        for (int i = 0; i < getFantasyConcededGoals(t); i++) {
            if (prob(0, 100) <= OWNGOAL_RATE) {
                og++;
            }
        }
        return og;
    }

    @Override
    public int getFantasySavedPenalties(final Team t)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        final int penalties = getPenalties();
        int savedPenalties = 0;
        final double prob = prob(0, 1);
        for (int i = 0; i < penalties; i++) {
            if (prob <= MISSED_PENALTIES_RATE) {
                savedPenalties++;
            }
        }
        return savedPenalties;
    }

    private int getPenalties() {
        int penalties = 0;
        final double prob = prob(0, 1);
        if (prob <= Math.pow(PENALTY_RATE, 3)) {
            penalties = 3;
        } else if (prob > Math.pow(PENALTY_RATE, 3)
            && prob <= Math.pow(PENALTY_RATE, 2) + Math.pow(PENALTY_RATE, 3)) {
            penalties = 2;
        } else if (prob > Math.pow(PENALTY_RATE, 2) + Math.pow(PENALTY_RATE, 3)
            && prob <= PENALTY_RATE + Math.pow(PENALTY_RATE, 2) + Math.pow(PENALTY_RATE, 3)) {
            penalties = 1;
        }
        return penalties;
    }

    @Override
    public double getLockdownDefenseRating(final Team t, final Map<Player, Double> defendersRatings) {
        final ExtractData ed = new ExtractDataImpl(t.getStarting());
        final List<Player> defenders = ed.getListByPos("D");
        double count = 0;
        double totRatings = 0;
        if (defenders.size() >= 3) {
            final double threshold = defenders.size() == 3 ? THRESHOLD_3_DIF : THRESHOLD_4_5_DIF;
            for (final Player c : defenders) {
                if (defendersRatings.get(c) >= threshold) {
                    totRatings += defendersRatings.get(c);
                } else {
                    totRatings = 0;
                    break;
                }
            }
            if (totRatings != 0) {
                if (defenders.size() == 3) {
                    count = totRatings - (defenders.size() * THRESHOLD_3_DIF);
                } else if (defenders.size() == 4) {
                    count = BONUS_4_DIF + totRatings - (defenders.size() * THRESHOLD_4_5_DIF);
                } else if (defenders.size() == DEF_5) {
                    count = BONUS_5_DIF + totRatings - (defenders.size() * THRESHOLD_4_5_DIF);
                }
            }
        }
        return count;
    }

    @Override
    public Map<String, Double> modifiedFantasyRatings(final Team t, final Map<Player, Double> mapRatings) {
        final Map<String, Double> mapModifiedRatings = new HashMap<>();
        for (final Player p : t.getStarting()) {
            final String role = p.getPos();
            final double rating = mapRatings.get(p) - SUB_VOTE;
            if (!mapModifiedRatings.containsKey(role)) {
                mapModifiedRatings.put(role, rating);
            } else {
                final double old = mapModifiedRatings.get(role);
                mapModifiedRatings.put(role, old + rating);
            }
        }
        return mapModifiedRatings;
    }


    @Override
    public double getFantasyDefensiveRating(final Team t, final Map<String, Double> v)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        return MOD_VOTE_DIF_D * (v.get("P") + v.get("D")) + MOD_VOTE_DIF_C * v.get("D")
            + MOD_VOTE_DIF_A * v.get("A");
    }

    @Override
    public double getFantasyOffensiveRating(final Team t, final Map<String, Double> v)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        return MOD_VOTE_OFF_D * v.get("D") + MOD_VOTE_OFF_C * v.get("D") + MOD_VOTE_OFF_A * v.get("A");
    }

    @Override
    public int getFantasyScoredGoals(final Team t) throws FileNotFoundException, ClassNotFoundException, IOException {
        int goal = 0;
        for (final Player p: t.getStarting()) {
            final double probGoal = p.getGoals() / (p.getMinutes() / MINUTES);
            final double prob = prob(0, 1);
            if (prob <= Math.pow(probGoal, 3)) {
                goal += 3;
            } else if (prob > Math.pow(probGoal, 3) && prob <= Math.pow(probGoal, 2) + Math.pow(probGoal, 3)) {
                goal += 2;
            } else if (prob > Math.pow(probGoal, 2) + Math.pow(probGoal, 3)
                && prob <= probGoal + Math.pow(probGoal, 2) + Math.pow(probGoal, 3)) {
                goal += 1;
            }
        }
        return goal;
    }

    @Override
    public int getDeltaScoredSavedPenalties(final Team t)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        final int penalties = getPenalties();
        int missedPenalties = 0;
        final double prob = prob(0, 1);
        for (int i = 0; i < penalties; i++) {
            if (prob <= MISSED_PENALTIES_RATE) {
                missedPenalties++;
            }
        }
        return (penalties - missedPenalties) - missedPenalties;
    }
}
