package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.Squadra;
import utils.Pair;

public class LogicsRigoriImpl implements LogicsRigori {
    private Squadra s1;
    private Squadra s2;
    private List < Calciatore > titolari1;
    private List < Calciatore > titolari2;
    private int gol1;
    private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private boolean continua;
    private Map<Integer, Pair<Calciatore, String>> map1;
    private Map<Integer, Pair<Calciatore, String>> map2;
    private ArrayList<Map<Integer, Pair<Calciatore, String>>> list;

    public LogicsRigoriImpl(Squadra s1, Squadra s2) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.titolari1 = s1.getTitolari();
        this.titolari2 = s2.getTitolari();
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
    public ArrayList<Map<Integer, Pair<Calciatore, String>>> compute() {
        while (continua) {
            if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2) ||
                ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
                continua = false;
            } else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                if ((tiri1 + tiri2) % 2 == 0) {
                    if (titolari1.isEmpty()) {
                        titolari1 = s1.getTitolari();
                    }
                    Calciatore tiratore = titolari1.get(titolari1.size() - 1);
                    titolari1.remove(tiratore);
                    String res = rigore(tiratore, s2);
                    map1.put(tiri1, new Pair < > (tiratore, res));
                    if (res.equals("Gol")) {
                        gol1++;
                    }
                    tiri1++;
                } else {
                    if (titolari2.isEmpty()) {
                        titolari2 = s2.getTitolari();
                    }
                    Calciatore tiratore = titolari2.get(titolari2.size() - 1);
                    titolari2.remove(tiratore);
                    String res = rigore(tiratore, s1);
                    map2.put(tiri2, new Pair < > (tiratore, res));
                    if (res.equals("Gol")) {
                        gol2++;
                    }
                    tiri2++;
                }
            }
        }
        titolari1 = s1.getTitolari();
        titolari2 = s2.getTitolari();
        list.add(map1);
        list.add(map2);
        return list;
    }

    private String rigore(Calciatore tiratore, Squadra dif) {
        double tirRating = tiratore.getRating().getY().getX() * (0.8 + new Random().nextDouble(0.4));
        double porRating = dif.getPortiereTit().getRating().getY().getZ() * (0.8 + new Random().nextDouble(0.4));
        double modPorRating = porRating * 0.75 /*costante rigori fatti nei shootout*/ ;
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
    public Squadra getWinner() {
        if (gol1 > gol2) {
            return s1;
        } else {
            return s2;
        }
    }
}