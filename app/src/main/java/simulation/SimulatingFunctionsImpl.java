package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.Squadra;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SimulatingFunctionsImpl implements SimulatingFunctions {
    private static final double OWNGOAL_RATE = 2.904040404040404; // percentuale di autogol su gol
    private static final double PENALITY_RATE = 0.2875; // rigori per partita
    private static final double MISSED_PENALITIES_RATE = 22.82608695652174; // percentuale rigori sbagliati
    private static final double COST_VOTE_P = 0.0746835443037975; // costante calcolo voto portiere
    private static final double COST_VOTE_D = 0.0721518987341772; // costante calcolo voto difensore
    private static final double COST_VOTE_C = 0.0753086419753086; // costante calcolo voto centrocampista
    private static final double COST_VOTE_A = 0.0729411764705882; // costante calcolo voto attaccante
    private static final int GOL_1 = 51;
    private static final int GOL_2 = 77;
    private static final int GOL_3 = 90;
    private static final int GOL_4 = 96;
    private static final int GOL_5 = 99;
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
    private static final Map<String, Double> RUOLO_COEFFICIENT_MAP = new HashMap<>();

    static {
        RUOLO_COEFFICIENT_MAP.put("P", COST_VOTE_P);
        RUOLO_COEFFICIENT_MAP.put("D", COST_VOTE_D);
        RUOLO_COEFFICIENT_MAP.put("C", COST_VOTE_C);
        RUOLO_COEFFICIENT_MAP.put("A", COST_VOTE_A);
    }

    public static double prob(double min, double max) {
        Random random = new Random();
        return random.nextDouble(max - min) + min;
    }

    public static double votoFanta(Calciatore c) {
        double k = RUOLO_COEFFICIENT_MAP.getOrDefault(c.getRuolo(), 0.0);
        return k * c.getRating().getX() * prob(0.8, 1.2);
    }

    public static Map<Calciatore, Double> votiFanta(List<Calciatore> titolari) {
        Map<Calciatore, Double> map = new LinkedHashMap<>();
        for (Calciatore c: titolari) {
            map.put(c, votoFanta(c));
        }
        return map;
    }

    public int golSubitiFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException {
        ExtractData ed = new ExtractDataImpl(s.getTitolari());
        Calciatore portiere = ed.getListaByRuolo("P").get(0);
        double probCleanSheet = portiere.getCleanSheet() / portiere.getPg();
        if (prob(0, 1) <= probCleanSheet) {
            return 0;
        } else {
            int gol = 1;
            int p = (int)(1 + (prob(0, 1) * 100));
            if (p > GOL_1 && p <= GOL_2) {
                gol = 2;
            } else if (p > GOL_2 && p <= GOL_3) {
                gol = 3;
            } else if (p > GOL_3 && p <= GOL_4) {
                gol = 4;
            } else if (p > GOL_4 && p <= GOL_5) {
                gol = 5;
            } else if (p > GOL_5) {
                gol = 6;
            }
            return gol;
        }
    }

    public int autogolFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException {
        int ag = 0;
        for (int i = 0; i < golSubitiFanta(s); i++) {
            if (prob(0, 100) <= OWNGOAL_RATE) {
                ag++;
            }
        }
        return ag;
    }

    public int rigoriParatiFanta(Squadra s)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        int r = 0;
        int rp = 0;
        double p = prob(0, 1);
        if (p <= Math.pow(PENALITY_RATE, 3)) {
            r = 3;
        } else if (p > Math.pow(PENALITY_RATE, 3) &&
            p <= Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3)) {
            r = 2;
        } else if (p > Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3) &&
            p <= PENALITY_RATE + Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3)) {
            r = 1;
        }
        for (int i = 0; i < r; i++) {
            p = prob(0, 1);
            if (p <= MISSED_PENALITIES_RATE) {
                rp++;
            }
        }
        return rp;
    }

    public static double catenaccioFanta(Squadra s, Map<Calciatore, Double> votiDif)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        ExtractData ed = new ExtractDataImpl(s.getTitolari());
        List<Calciatore> difensori = ed.getListaByRuolo("D");
        double count = 0;
        double totVoti = 0;

        if (difensori.size() >= 3) {
            double threshold = difensori.size() == 3 ? THRESHOLD_3_DIF : THRESHOLD_4_5_DIF;
            for (Calciatore c : difensori) {
                if (votiDif.get(c) >= threshold) {
                    totVoti += votiDif.get(c);
                } else {
                    totVoti = 0;
                    break;
                }
            }
            if (totVoti != 0) {
                if (difensori.size() == 3) {
                    count = totVoti - (difensori.size() * THRESHOLD_3_DIF);
                } else if (difensori.size() == 4) {
                    count = BONUS_4_DIF + totVoti - (difensori.size() * THRESHOLD_4_5_DIF);
                } else if (difensori.size() == 5) {
                    count = BONUS_5_DIF + totVoti - (difensori.size() * THRESHOLD_4_5_DIF);
                }
            }
        }
        return count;
    }

    public static Map<String, Double> votoModFanta(Squadra s, Map<Calciatore, Double> mapVoti)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        Map<String, Double> mapModVoti = new HashMap<>();
        for (Calciatore c : s.getTitolari()) {
            String ruolo = c.getRuolo();
            double voto = mapVoti.get(c) - SUB_VOTE;

            if (!mapModVoti.containsKey(ruolo)) {
                mapModVoti.put(ruolo, voto);
            } else {
                double old = mapModVoti.get(ruolo);
                mapModVoti.put(ruolo, old + voto);
            }
        }
        return mapModVoti;
    }


    public double votoDifFanta(Squadra s, Map<String, Double> v)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        return (MOD_VOTE_DIF_D * (v.get("P") + v.get("D"))) + (MOD_VOTE_DIF_C * v.get("D")) +
            (MOD_VOTE_DIF_A * v.get("A"));
    }

    public double votoOffFanta(Squadra s, Map<String, Double> v)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        return (MOD_VOTE_OFF_D * (v.get("D"))) + (MOD_VOTE_OFF_C * v.get("D")) + (MOD_VOTE_OFF_A * v.get("A"));
    }

    public int golFattiFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException {
        int gol = 0;
        for (Calciatore c: s.getTitolari()) {
            double probGol = c.getGol() / (c.getMinuti() / MINUTES);
            double p = prob(0, 1);
            if (p <= Math.pow(probGol, 3)) {
                gol += 3;
            } else if (p > Math.pow(probGol, 3) && p <= Math.pow(probGol, 2) + Math.pow(probGol, 3)) {
                gol += 2;
            } else if (p > Math.pow(probGol, 2) + Math.pow(probGol, 3) &&
                p <= probGol + Math.pow(probGol, 2) + Math.pow(probGol, 3)) {
                gol += 1;
            }
        }
        return gol;
    }

    public int differenzaRigoriFattiSbagliatiFanta(Squadra s)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        int r = 0;
        int rs = 0;
        double p = prob(0, 1);
        if (p <= Math.pow(PENALITY_RATE, 3)) {
            r = 3;
        } else if (p > Math.pow(PENALITY_RATE, 3) &&
            p <= Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3)) {
            r = 2;
        } else if (p > Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3) &&
            p <= PENALITY_RATE + Math.pow(PENALITY_RATE, 2) + Math.pow(PENALITY_RATE, 3)) {
            r = 1;
        }
        for (int i = 0; i < r; i++) {
            p = prob(0, 1);
            if (p <= MISSED_PENALITIES_RATE) {
                rs++;
            }
        }
        return (r - rs) - rs;
    }
}