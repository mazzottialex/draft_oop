package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import data.Player;
import data.Module;
import data.Team;
import data.TeamUser;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

/**
 * The class that handles the team creation logic.
 * 
 * @author Davide Braccini
 *
 */
public class LogicsCreaSquadraImpl implements LogicsCreaSquadra {

    private static final long serialVersionUID = 1L;
    private static final int NUM_PLAYER_IN_TEAM = 11;
    private Module moduloSelect;
    // private ManageData md;
    private ExtractData ex;
    private String namePlayer; // nome del calciatore selezionato per entrare in formazione
    private Player calciatoreSelect; // calciatore selezionato per entrare in formazione
    private String ruoloSelect; // ruolo del calciatore selezionato per entrrare in formazione
    private int posSelect; // posizione del calciatore selezionato per entrare in formazione
    private int ratingSelect; // rating del calciatore selezionato per entrare in formazione
    private final List<Player> liSquadra;
    private final List<Player> riserve;
    private final String nomeSquadra;
    private final String stemma;
    // private Squadra squadra;
    private final List<Player> calcUsciti;
    private boolean clickModulo;
    private Random r = new Random();

    /**
     * Constructor of LogicsCreaSquadraImpl.
     * 
     * @param nomeSquadra the name of the team
     * @param stemma      the arms
     * @param li          the list of all the players in Serie A
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public LogicsCreaSquadraImpl(final String nomeSquadra, final String stemma, final List<Player> li)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        this.moduloSelect = Module.M343;
        // this.md = new ManageDataImpl(stagione);
        // this.md.LoadData();
        this.ex = new ExtractDataImpl(li); // (this.md.getLi());
        this.calciatoreSelect = new Player(0, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.ruoloSelect = null;
        this.posSelect = -1;
        this.ratingSelect = 0;
        this.liSquadra = new ArrayList<>();
        this.calcUsciti = new ArrayList<>();
        this.nomeSquadra = nomeSquadra;
        this.stemma = stemma;
        riserve = new ArrayList<>();
        riserve.addAll(this.getRandom("P", 1));
        riserve.addAll(this.getRandom("D", 2));
        riserve.addAll(this.getRandom("C", 2));
        riserve.addAll(this.getRandom("A", 2));
        this.calcUsciti.addAll(riserve);
        this.clickModulo = false;
    }

    /**
     * {@inheritDoc}
     */
    public Team getSquadra() {
        return new TeamUser(nomeSquadra, stemma, moduloSelect, liSquadra, riserve); // sistemare titolari e riserve
    }

    /**
     * {@inheritDoc}
     */
    public List<Module> getModuli() {
        List<Module> list = new ArrayList<>();
        for (Module m : Module.values()) {
            list.add(m);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModulo(final Module mod) {
        this.moduloSelect = mod;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Module getModulo() {
        return this.moduloSelect;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumDif() {
        return this.moduloSelect.getNumDif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumCen() {
        return this.moduloSelect.getNumCen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumAtt() {
        return this.moduloSelect.getNumAtt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtractData getEx() {
        return this.ex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getRandom(final String ruolo, final int n) {
        List<Player> list = this.ex.getListaByRuolo(ruolo);
        Set<Player> set = new HashSet<>();
        List<Player> randomList = new ArrayList<>();
        boolean test = false;
        // Random r = new Random();
        while (set.size() != n) {
            int random = r.nextInt(list.size());
            Player c = list.get(random);
            test = false;
            for (Player uscito : this.calcUsciti) {
                if (c.equals(uscito)) {
                    test = true;
                }
            }
            if (!test) {
                set.add(list.get(random));
            }
        }
        randomList.addAll(set);
        return randomList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlayer() {
        return this.namePlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNameString(final String name) {
        this.namePlayer = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCalciatoreSelect() {
        // return this.calciatoreSelect;
        final Player copy = new Player(this.calciatoreSelect.getId(), this.calciatoreSelect.getName(),
                this.calciatoreSelect.getPos(), this.calciatoreSelect.getTeam(), this.calciatoreSelect.getMatchesPlayed(),
                this.calciatoreSelect.getMinutes(), this.calciatoreSelect.getGoals(), this.calciatoreSelect.getShots(),
                this.calciatoreSelect.getDribbles(), this.calciatoreSelect.getAssists(),
                this.calciatoreSelect.getPasses(), this.calciatoreSelect.getKeyPasses(),
                this.calciatoreSelect.getYellowCards(), this.calciatoreSelect.getRedCards(),
                this.calciatoreSelect.getBallsRecovered(), this.calciatoreSelect.getTacklesWon(),
                this.calciatoreSelect.getCleanSheets(), this.calciatoreSelect.getSaves());
        copy.setRating(this.calciatoreSelect.getRating());
        copy.setCardRating(this.calciatoreSelect.getCardRating());
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCalciatoreSelect(final Player calciatore) {
        final Player copy = new Player(calciatore.getId(), calciatore.getName(), calciatore.getPos(),
                calciatore.getTeam(), calciatore.getMatchesPlayed(), calciatore.getMinutes(), calciatore.getGoals(),
                calciatore.getShots(), calciatore.getDribbles(), calciatore.getAssists(), calciatore.getPasses(),
                calciatore.getKeyPasses(), calciatore.getYellowCards(), calciatore.getRedCards(),
                calciatore.getBallsRecovered(), calciatore.getTacklesWon(), calciatore.getCleanSheets(), calciatore.getSaves());
        copy.setRating(calciatore.getRating());
        copy.setCardRating(calciatore.getCardRating());
        this.calciatoreSelect = copy;
        // this.calciatoreSelect = calciatore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRuoloSelect() {
        return this.ruoloSelect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRuoloSelect(final String ruolo) {
        this.ruoloSelect = ruolo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getposSelect() {
        return this.posSelect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setposSelect(final int pos) {
        this.posSelect = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayerInTeam(final Player calciatore) {
        if (!this.liSquadra.contains(calciatore)) {
            this.liSquadra.add(calciatore);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearTeam() {
        this.liSquadra.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean teamComplete() {
        if (this.liSquadra.size() == LogicsCreaSquadraImpl.NUM_PLAYER_IN_TEAM) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomeSquadra() {
        return this.nomeSquadra;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStemma() {
        return this.stemma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getTitolari() {
        // return this.liSquadra;
        return List.copyOf(this.liSquadra);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRating() {
        return this.ratingSelect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRating(final int rating) {
        this.ratingSelect = rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getCalcUsciti() {
        // return this.calcUsciti;
        return List.copyOf(this.calcUsciti);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCalcUsciti(final List<Player> list) {
        this.calcUsciti.addAll(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getClickModulo() {
        return this.clickModulo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClickModulo(final boolean click) {
        this.clickModulo = click;
    }

}
