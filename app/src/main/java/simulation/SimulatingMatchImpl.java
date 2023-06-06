package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.Player;
import data.Team;
import manageData.ExtractDataImpl;

public class SimulatingMatchImpl implements SimulatingMatch {
    private SimulatingFunctions sf;
    private Team s1;
    private Team s2;
    private Map<Player, Double> voti1;
    private Map<Player, Double> voti2;
    private int golSubiti1;
    private int golSubiti2;
    private int autogol1;
    private int autogol2;
    private int rigoriParati1;
    private int rigoriParati2;
    private double catenaccio1;
    private double catenaccio2;
    private Map<String, Double> votiMod1;
    private Map<String, Double> votiMod2;
    private double votoDif1;
    private double votoDif2;
    private double votoOff1;
    private double votoOff2;
    private int golFatti1;
    private int golFatti2;
    private int rigoriFatti1;
    private int rigoriFatti2;
    private static final double COST_SUB_DIFF = 14; // da aumentare di 1
    private static final double COST_SUB_OFF = 5; // da aumentare di 2
    private static final double COST_DIV_DIFF_OFF_CR = 5;
    private static final int SQUADRA1 = 1;
    private static final int SQUADRA2 = 2;
    private static final int MINUTES_REG = 90;
    private final static int MINUTES_SUPPL = 120;

    public SimulatingMatchImpl(Team s1, Team s2)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        sf = new SimulatingFunctionsImpl();
        this.s1 = s1;
        this.s2 = s2;
        voti1 = SimulatingFunctionsImpl.getFantasyRantings(this.s1.getStarting());
        voti2 = SimulatingFunctionsImpl.getFantasyRantings(this.s2.getStarting());
        golSubiti1 = sf.getFantasyConcededGoals(this.s1);
        golSubiti2 = sf.getFantasyConcededGoals(this.s2);
        autogol1 = sf.getFantasyOwngoals(this.s1);
        autogol2 = sf.getFantasyOwngoals(this.s2);
        rigoriParati1 = sf.getFantasySavedPenalties(this.s1);
        rigoriParati2 = sf.getFantasySavedPenalties(this.s2);
        catenaccio1 = SimulatingFunctionsImpl.getLockdownDefenseRating(this.s1, voti1);
        catenaccio2 = SimulatingFunctionsImpl.getLockdownDefenseRating(this.s2, voti2);
        votiMod1 = SimulatingFunctionsImpl.modifiedFantasyRatings(this.s1, voti1);
        votiMod2 = SimulatingFunctionsImpl.modifiedFantasyRatings(this.s2, voti2);
        votoDif1 = sf.getFantasyDefensiveRating(s1, votiMod1);
        votoDif2 = sf.getFantasyDefensiveRating(s2, votiMod2);
        votoOff1 = sf.getFantasyOffensiveRating(s1, votiMod1);
        votoOff2 = sf.getFantasyOffensiveRating(s2, votiMod2);
        golFatti1 = sf.getFantasyScoredGoals(s1);
        golFatti2 = sf.getFantasyScoredGoals(s2);
        rigoriFatti1 = sf.getDeltaScoredSavedPenalties(s1);
        rigoriFatti2 = sf.getDeltaScoredSavedPenalties(s2);
    }

    // prestazioneDifensiva, se squadra == 1 -> s1; se squadra == 2 -> s2
    @Override
    public double prestazioneDifensiva(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        double pd = 0;
        switch (squadra) {
            case SQUADRA1:
                pd = (votoDif1 + new ExtractDataImpl(s1.getStarting()).getListByPos("D").size() + catenaccio1 -
                    2 * golSubiti1 - 2 * autogol1 + 3 * rigoriParati1 - COST_SUB_DIFF) / COST_DIV_DIFF_OFF_CR;
                break;

            case SQUADRA2:
                pd = (votoDif2 + new ExtractDataImpl(s2.getStarting()).getListByPos("D").size() + catenaccio2 -
                    2 * golSubiti2 - 2 * autogol2 + 3 * rigoriParati2 - COST_SUB_DIFF) / COST_DIV_DIFF_OFF_CR;
                break;

            default:
                break;
        }
        return pd;
    }

    // capacitaRealizzativa
    @Override
    public double capacitaRealizzativa(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        double cr = 0;
        switch (squadra) {
            case SQUADRA1:
                cr = golFatti1 + autogol2 + rigoriFatti1;
                break;
                
            case SQUADRA2:
                cr = golFatti2 + autogol1 + rigoriFatti2;
                break;

            default:
                break;
        }
        return cr;
    }

    // prestazioneOffensiva
    @Override
    public double prestazioneOffensiva(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        double po = 0;
        switch (squadra) {
            case SQUADRA1:
                po = (votoOff1 + (capacitaRealizzativa(squadra) / 2) - COST_SUB_OFF) / COST_DIV_DIFF_OFF_CR;
                break;
                
            case SQUADRA2:
                po = (votoOff2 + (capacitaRealizzativa(squadra) / 2) - COST_SUB_OFF) / COST_DIV_DIFF_OFF_CR;
                break;

            default:
                break;
        }
        return po;
    }

    // risultatoFinale
    @Override
    public Map<Team, Integer> risultato()
    throws FileNotFoundException, ClassNotFoundException, IOException {
        int sq1 = (int) Math.round(Math.min(capacitaRealizzativa(SQUADRA1),
            (prestazioneOffensiva(SQUADRA1) - prestazioneDifensiva(SQUADRA2))));
        int sq2 = (int) Math.round(Math.min(capacitaRealizzativa(SQUADRA2),
            (prestazioneOffensiva(SQUADRA2) - prestazioneDifensiva(SQUADRA1))));
        Map<Team, Integer> map = new HashMap < > ();
        map.put(this.s1, sq1 >= 0 ? sq1 : 0);
        map.put(this.s2, sq2 >= 0 ? sq2 : 0);
        return map;
    }

    @Override
    public Map<Team, Integer> risultatoSuppl()
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        return risultatoSub(MINUTES_REG);
    }

    @Override
    public Map<Team, Integer> risultatoSub(int minuto)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        Map<Team, Integer> map = new HashMap<>();
        if (minuto < MINUTES_REG) {
            map.put(s1,
                (int)(risultato().get(s1) * (double)((MINUTES_REG - minuto) / MINUTES_REG)));
            map.put(s2, (int)(risultato().get(s1) *
                (double)((MINUTES_REG - minuto) / MINUTES_REG)));
        } else {
            map.put(s1,
                (int)(risultato().get(s1) * (double)((MINUTES_SUPPL - MINUTES_REG - minuto) / MINUTES_REG)));
            map.put(s2, (int)(risultato().get(s1) *
                (double)((MINUTES_SUPPL - MINUTES_REG - minuto) / MINUTES_REG)));
        }
        return map;
    }

}