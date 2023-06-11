package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import model.data.Module;
import model.data.Player;
import model.data.Team;
import model.data.TeamUser;
import model.managedata.ExtractData;
import model.managedata.ExtractDataImpl;
import model.managedata.LogicsFile;
import model.managedata.LogicsFileImpl;
/**
 * The class that handles the team creation logic.
 * 
 * @author Davide Braccini
 *
 */
public class LogicsCreateTeamImpl implements LogicsCreateTeam {

    private static final long serialVersionUID = 1L;
    private static final int NUM_PLAYER_IN_TEAM = 11;
    private Module moduloSelect;
    // private ManageData md;
    private final ExtractData ex;
    private String namePlayer; // nome del calciatore selezionato per entrare in formazione
    private Player calciatoreSelect; // calciatore selezionato per entrare in formazione
    private String ruoloSelect; // ruolo del calciatore selezionato per entrrare in formazione
    private int posSelect; // posizione del calciatore selezionato per entrare in formazione
    private int ratingSelect; // rating del calciatore selezionato per entrare in formazione
    private final List<Player> liSquadra;
    private final List<Player> riserve;
    private final String nomeSquadra;
    private final URL stemma;
    // private Squadra squadra;
    private final List<Player> calcUsciti;
    private boolean clickModulo;
    private final Random r = new Random();

    /**
     * Constructor of LogicsCreateTeamImpl.
     * 
     * @param nomeSquadra the name of the team
     * @param url      the arms
     * @param li          the list of all the players in Serie A
     * @throws FileNotFoundException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException an I/O error occurs
     */
    public LogicsCreateTeamImpl(final String nomeSquadra, final URL url, final List<Player> li)
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
        this.stemma = url;
        riserve = new ArrayList<>();
        riserve.addAll(this.getRandom("P", 1));
        riserve.addAll(this.getRandom("D", 2));
        riserve.addAll(this.getRandom("C", 2));
        riserve.addAll(this.getRandom("A", 2));
        this.calcUsciti.addAll(riserve);
        this.clickModulo = false;
    }

    @Override
    public final Team getSquadra() {
        return new TeamUser(nomeSquadra, stemma, moduloSelect, liSquadra, riserve); // sistemare titolari e riserve
    }

    @Override
    public final List<Module> getModuli() {
        final List<Module> list = new ArrayList<>();
        for (final Module m : Module.values()) {
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
    @Override
    public int getNumDif() {
        return this.moduloSelect.getNumDef();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumCen() {
        return this.moduloSelect.getNumMid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumAtt() {
        return this.moduloSelect.getNumFor();
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
    public final List<Player> getRandom(final String ruolo, final int n) {
        final List<Player> list = this.ex.getListByPos(ruolo);
        final Set<Player> set = new HashSet<>();
        final List<Player> randomList = new ArrayList<>();
        //boolean test = false;
        // Random r = new Random();
        while (set.size() != n) {
            final int random = r.nextInt(list.size());
            final Player c = list.get(random);
            boolean test = false;
            for (final Player uscito : this.calcUsciti) {
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
        return this.liSquadra.size() == LogicsCreateTeamImpl.NUM_PLAYER_IN_TEAM ? true : false;
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
    public URL getStemma() {
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
    public boolean isClickModulo() {
        return this.clickModulo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClickModulo(final boolean click) {
        this.clickModulo = click;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTeam() {
        final LogicsFile logFile = new LogicsFileImpl();
        logFile.saveHistory(this.getSquadra());
    }
}
