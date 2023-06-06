package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Player;
import data.Team;
import utils.Pair;

/**
 * The {@code LogicsRigoriImpl} class implements the {@code LogicsRigori} interface
 * and defines the logic for penalty shoot-outs.
 */
public class LogicsRigoriImpl implements LogicsRigori {
    private Team s1;
    private Team s2;
    private List<Player> titolari1;
    private List<Player> titolari2;
    private int gol1;
    private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private boolean continua;
    private Map<Integer, Pair<Player, String>> map1;
    private Map<Integer, Pair<Player, String>> map2;
    private ArrayList<Map<Integer, Pair<Player, String>>> list;
    private static final double MIN_MOD_RATING = 0.8;
    private static final double ADD_MOD_RATING = 0.4;
    private static final double COST_MADE_PEN = 0.75; //costante rigori fatti nei shootout

    /**
     * Constructs a new {@code LogicsRigoriImpl} object with the specified teams.
     *
     * @param s1 the first team
     * @param s2 the second team
     */
    public LogicsRigoriImpl(final Team s1, final Team s2) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.titolari1 = s1.getStarting();
        this.titolari2 = s2.getStarting();
        this.gol1 = 0;
        this.gol2 = 0;
        this.tiri1 = 0;
        this.tiri2 = 0;
        this.totTiri = 10;
        this.continua = true;
        this.list = new ArrayList<>();
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
    }

    @Override
    public ArrayList<Map<Integer, Pair<Player, String>>> compute() {
        while (continua) {
            if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2)
                        || ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2
                		|| (((totTiri / 2) - tiri2) + gol2) < gol1))) {
                continua = false;
            } else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                if ((tiri1 + tiri2) % 2 == 0) {
                    if (titolari1.isEmpty()) {
                        titolari1 = s1.getStarting();
                    }
                    Player tiratore = titolari1.get(titolari1.size() - 1);
                    titolari1.remove(tiratore);
                    String res = rigore(tiratore, s2);
                    map1.put(tiri1, new Pair<>(tiratore, res));
                    if (res.equals("Gol")) {
                        gol1++;
                    }
                    tiri1++;
                } else {
                    if (titolari2.isEmpty()) {
                        titolari2 = s2.getStarting();
                    }
                    Player tiratore = titolari2.get(titolari2.size() - 1);
                    titolari2.remove(tiratore);
                    String res = rigore(tiratore, s1);
                    map2.put(tiri2, new Pair<>(tiratore, res));
                    if (res.equals("Gol")) {
                        gol2++;
                    }
                    tiri2++;
                }
            }
        }
        titolari1 = s1.getStarting();
        titolari2 = s2.getStarting();
        list.add(map1);
        list.add(map2);
        return list;
    }

    /**
     * Simulates a penalty kick by a player against a goalkeeper.
     *
     * @param tiratore the player taking the penalty kick
     * @param dif the defending team (goalkeeper's team)
     * @return the result of the penalty kick ("Gol" for a goal, "Sbagliato" for a miss)
     */
    private String rigore(final Player tiratore, final Team dif) {
        double tirRating = tiratore.getRating().getY().getX() * (MIN_MOD_RATING + new Random().nextDouble() * ADD_MOD_RATING);
        double porRating = dif.getStartingKeeper().getRating().getY().getZ()
        		* (MIN_MOD_RATING + new Random().nextDouble() * ADD_MOD_RATING);
        double modPorRating = porRating * COST_MADE_PEN;
        if (tirRating > modPorRating) {
            return "Gol";
        } else {
            return "Sbagliato";
        }
    }

    @Override
    public int getGol1() {
        return gol1;
    }

    @Override
    public int getGol2() {
        return gol2;
    }

    @Override
    public Team getWinner() {
        if (gol1 > gol2) {
            return s1;
        } else {
            return s2;
        }
    }
}
