package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.data.Player;
import model.data.Team;
import model.data.TeamUser;
import utils.Pair;

/**
 * The {@code LogicsShootoutImpl} class implements the {@code LogicsShootout} interface
 * and defines the logic for penalty shoot-outs.
 */
public final class LogicsShootoutImpl implements LogicsShootout, Serializable {
    private static final long serialVersionUID = -5675711964921406918L;
    private Team t1;
    private Team t2;
    private List<Player> starters1;
    private List<Player> starters2;
    private int goals1;
    private int goals2;
    private int shoots1;
    private int shoots2;
    private int totShoots;
    private boolean goAhead;
    private Map<Integer, Pair<Player, String>> map1;
    private Map<Integer, Pair<Player, String>> map2;
    private ArrayList<Map<Integer, Pair<Player, String>>> list;
    private static final double MINIMUM_MODIFIER_RATING = 0.8;
    private static final double ADD_MODIFIER_RATING = 0.4;
    private static final double CONVERSION_RATE_SHOOTOUT = 0.75; //costante rigori fatti nei shootout
    private static final int DEFAULT = 77;

    /**
     * Constructs a new {@code LogicsShootoutImpl} object with the specified teams.
     *
     * @param t1 the first team
     * @param t2 the second team
     */
    public LogicsShootoutImpl(final Team t1, final Team t2) {
        super();
        this.t1 = new TeamUser(t1.getTeamName(), t1.getLogo(), t1.getModule(), t1.getStarting(), t1.getSubstitution());
        this.t2 = new TeamUser(t2.getTeamName(), t2.getLogo(), t2.getModule(), t2.getStarting(), t2.getSubstitution());
        starters1 = t1.getStarting();
        starters2 = t2.getStarting();
        goals1 = 0;
        goals2 = 0;
        shoots1 = 0;
        shoots2 = 0;
        totShoots = 10;
        goAhead = true;
        list = new ArrayList<>();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    @Override
    public ArrayList<Map<Integer, Pair<Player, String>>> compute() {
        while (goAhead) {
            if ((((shoots1 + shoots2) >= totShoots) && shoots1 == shoots2 && goals1 != goals2)
                        || ((shoots1 + shoots2) < totShoots && ((((totShoots / 2) - shoots1) + goals1) < goals2
                        || (((totShoots / 2) - shoots2) + goals2) < goals1))) {
                goAhead = false;
            } else if ((shoots1 + shoots2) < totShoots || shoots1 != shoots2 || (shoots1 == shoots2 && goals1 == goals2)) {
                if ((shoots1 + shoots2) % 2 == 0) {
                    if (starters1.isEmpty()) {
                        starters1 = t1.getStarting();
                    }
                    Player p = starters1.get(starters1.size() - 1);
                    starters1.remove(p);
                    String res = rigore(p, t2);
                    map1.put(shoots1, new Pair<>(p, res));
                    if (res.equals("Gol")) {
                        goals1++;
                    }
                    shoots1++;
                } else {
                    if (starters2.isEmpty()) {
                        starters2 = t2.getStarting();
                    }
                    Player p = starters2.get(starters2.size() - 1);
                    starters2.remove(p);
                    String res = rigore(p, t1);
                    map2.put(shoots2, new Pair<>(p, res));
                    if (res.equals("Gol")) {
                        goals2++;
                    }
                    shoots2++;
                }
            }
        }
        starters1 = t1.getStarting();
        starters2 = t2.getStarting();
        list.add(map1);
        list.add(map2);
        return new ArrayList<>(list);
    }

    /**
     * Simulates a penalty kick by a player against a goalkeeper.
     *
     * @param shooter the player taking the penalty kick
     * @param oppositeTeam the defending team (goalkeeper's team)
     * @return the result of the penalty kick ("Gol" for a goal, "Sbagliato" for a miss)
     */
    private String rigore(final Player shooter, final Team oppositeTeam) {
        double shooterRating = shooter.getRating().getY().getX()
                * (MINIMUM_MODIFIER_RATING + new Random().nextDouble() * ADD_MODIFIER_RATING);
        double gkRating = (oppositeTeam.getStartingKeeper() != null ? oppositeTeam.getStartingKeeper().getRating().getY().getZ()
                : DEFAULT) * (MINIMUM_MODIFIER_RATING + new Random().nextDouble() * ADD_MODIFIER_RATING);
        double modGkRating = gkRating * CONVERSION_RATE_SHOOTOUT;
        if (shooterRating > modGkRating) {
            return "Gol";
        } else {
            return "Sbagliato";
        }
    }

    @Override
    public int getGoals1() {
        return goals1;
    }

    @Override
    public int getGoals2() {
        return goals2;
    }

    @Override
    public Team getWinner() {
    	Team copiat1= new TeamUser(t1.getTeamName(), t1.getLogo(), t1.getModule(), t1.getStarting(), t1.getSubstitution());
    	Team copiat2= new TeamUser(t2.getTeamName(), t2.getLogo(), t2.getModule(), t2.getStarting(), t2.getSubstitution());
    	if (goals1 > goals2) {
            return copiat1;
        } else {
            return copiat2;
        }
    }
}
