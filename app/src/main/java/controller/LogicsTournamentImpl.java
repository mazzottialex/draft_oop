package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.*;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import model.data.Module;
import model.data.Player;
import model.data.Team;
import model.data.TeamOpponent;
import model.data.TeamUser;
import model.managedata.CreateOpponentTeamsImpl;
import model.simulating.SimulatingMatchImpl;
//import view.Tournament;

/**
 * The class that manages the logic of the tournament.
 * 
 * @author Davide Braccini
 *
 */
public class LogicsTournamentImpl implements LogicsTournament {

    private static final long serialVersionUID = 1L;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LogicsTournamentImpl.class);
    private static final int NUM_AVVERSARI = 15;
    // private ManageData md;
    // private ExtractData ex;
    private final Team miasquadra;
    private List<Team> listSquadre;
    // private List<Integer> golFatti;
    private int numSquadre;
    private final Map<String, Integer> risultati;
    // private final List<Calciatore> li;
    private boolean eliminated;
    private Team squadraAvv; // squadra al posto della squadra utente se vince
    private final Map<String, Integer> risMatch; // ris della squadra al posto della squadra utente
    private String winner;
    private boolean elimThisTurn;
    private static final int START_REG = 0;
    private static final int START_EXTRA = 90;

    /**
     * Constructor of LogicsTournamentImpl.
     * 
     * @param squadra the user's team
     * @param li      the list of all the players in Serie A
     * @throws FileNotFoundException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException an I/O error occurs
     */
    public LogicsTournamentImpl(final Team squadra, final List<Player> li)
            throws FileNotFoundException, ClassNotFoundException, IOException {

        this.listSquadre = new ArrayList<>();
        // this.golFatti = new ArrayList<>();
        // Creo la squadra dell'utente
        this.miasquadra = new TeamUser(squadra.getTeamName(), squadra.getLogo(),
                squadra.getModule(), squadra.getStarting(), squadra.getSubstitution());
        // this.miasquadra = squadra;
        // Creo le squadre avversarie (quelle esistenti in serie A)
        /*
         * this.listSquadre.add(new SquadraAvversaria(0, "NAP", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(1, "LAZ", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(2, "JUV", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(3, "INT", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(4, "MIL", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(5, "ROM", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(6, "ATA", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(7, "FIO", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(8, "UDI", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(9, "MON", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(10, "CRE", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(11, "VER", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(12, "BOL", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(13, "TOR", Modulo.M442, li));
         * this.listSquadre.add(new SquadraAvversaria(14, "SAL", Modulo.M442, li));
         */
        // System.out.println(this.listSquadre);
        final CreateOpponentTeamsImpl cs = new CreateOpponentTeamsImpl(li, NUM_AVVERSARI);
        this.listSquadre.addAll(cs.getTeams());
        this.numSquadre = 16;
        this.risultati = new HashMap<>();
        this.eliminated = false;
        this.squadraAvv = new TeamOpponent(1, "aaa", Module.M442, li);
        this.risMatch = new HashMap<>();
        this.elimThisTurn = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Team> getListAvversari() {
        // return this.listSquadre;
        return List.copyOf(this.listSquadre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team getMiaSquadra() {
        return new TeamUser(miasquadra.getTeamName(), miasquadra.getLogo(),
                miasquadra.getModule(), miasquadra.getStarting(), miasquadra.getSubstitution());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumSquadre() {
        return this.numSquadre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumSquadre(final int numSquadre) {
        this.numSquadre = numSquadre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListAvversari(final List<Team> list) {
        this.listSquadre = new ArrayList<>(list);
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    @Override
    public void simulaMatch() throws FileNotFoundException, ClassNotFoundException, IOException {
        List<Team> newList = new ArrayList<>();
        final int numSquadre = this.getNumSquadre();
        final Map<String, Integer> map = new HashMap<>(); // map per il risultato
        final List<String> list = new ArrayList<>(); // lista per i nomi delle squadre che si sfidano
        String teamWin; // nome della squadra vincente
        String teamLose; // nome della squadra perdente
        Map<Team, Integer> map2;
        final List<Team> l = new ArrayList<>(); // appoggio

        switch (numSquadre) {
        case 16:
            newList = simulateAll();         
            this.setListAvversari(newList);
            this.setNumSquadre(8);
            break;
        case 8:
            this.risultati.clear();
            matchTeamAvv();       
            newList = simulateAll();
            this.setListAvversari(newList);
            this.setNumSquadre(4);
            break;
        case 4:
            this.risultati.clear();
            matchTeamAvv();         
            newList = simulateSemi();
            this.setListAvversari(newList);
            this.setNumSquadre(2);
            break;
        case 2:
            this.risultati.clear();
            simulateFinal();
            this.setNumSquadre(1);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getRisultati() {
        // return this.risultati;
        return Map.copyOf(this.risultati);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEliminated() {
        return this.eliminated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEliminated(final boolean eliminated) {
        this.eliminated = eliminated;
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    @Override
    public Team getSquadraAvv() {
        // return this.squadraAvv;
        try {
            return new TeamOpponent(this.squadraAvv.getId(),
                    this.squadraAvv.getTeamName(), this.squadraAvv.getModule(), this.squadraAvv.getLiPlayers());
        } catch (ClassNotFoundException | IOException e) {
            LOG.error("Error", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSquadraAvv(final Team squadra) {
        // this.squadraAvv = squadra;
        try {
            this.squadraAvv = new TeamOpponent(squadra.getId(), squadra.getTeamName(), squadra.getModule(),
                    squadra.getLiPlayers());
        } catch (ClassNotFoundException | IOException e) {
            LOG.error("Error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getRisMatch() {
        // return this.risMatch;
        return Map.copyOf(this.risMatch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWinner() {
        return this.winner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isElimThisTurn() {
        return this.elimThisTurn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setElimThisTurn(final boolean b) {
        this.elimThisTurn = b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearRisMatch() {
        this.risMatch.clear();
    }

    private List<Team> simulateAll() throws FileNotFoundException, ClassNotFoundException, IOException {
        List<Team> newList = new ArrayList<>();
        final Map<String, Integer> map = new HashMap<>(); // map per il risultato
        final List<String> list = new ArrayList<>(); // lista per i nomi delle squadre che si sfidano
        String teamWin; // nome della squadra vincente
        String teamLose; // nome della squadra perdente
        Map<Team, Integer> map2;
        final List<Team> l = new ArrayList<>(); // appoggio
        
        
        for (int i = 1; i < numSquadre - 1; i = i + 2) {
            final SimulatingMatchImpl s = new SimulatingMatchImpl(this.getListAvversari().get(i),
                    this.getListAvversari().get(i + 1));
            map.clear();
            map2 = s.result(START_REG);
            l.clear();
            l.addAll(map2.keySet());
            map.put(l.get(0).getTeamName(), map2.get(l.get(0)));
            map.put(l.get(1).getTeamName(), map2.get(l.get(1)));
            l.clear();
            list.addAll(map.keySet());
            if (map.get(list.get(0)).equals(map.get(list.get(1)))) { // == map.get(list.get(1))) {
                map.clear();
                map2 = s.result(START_EXTRA);
                l.addAll(map2.keySet());
                map.put(l.get(0).getTeamName(), map2.get(l.get(0)));
                map.put(l.get(1).getTeamName(), map2.get(l.get(1)));
                l.clear();
                // map = s.risultatoSuppl2();
                list.clear();
            }
            list.addAll(map.keySet());
            if (map.get(list.get(0)) > map.get(list.get(1))) {
                teamWin = list.get(0);
            } else if (map.get(list.get(0)) < map.get(list.get(1))) {
                teamWin = list.get(1);
            } else {
                final LogicsShootoutImpl r = new LogicsShootoutImpl(this.getListAvversari().get(i),
                        this.getListAvversari().get(i + 1));
                teamWin = r.getWinner().getTeamName();
                if (teamWin.equals(this.getListAvversari().get(i).getTeamName())) {
                    teamLose = this.getListAvversari().get(i + 1).getTeamName();
                } else {
                    teamLose = this.getListAvversari().get(i).getTeamName();
                }
                map.clear();
                if (r.getGoals1() > r.getGoals2()) {
                    map.put(teamWin, r.getGoals1());
                    map.put(teamLose, r.getGoals2());
                } else {
                    map.put(teamWin, r.getGoals2());
                    map.put(teamLose, r.getGoals1());
                }
            }
            if (this.getListAvversari().get(i).getTeamName().equals(teamWin)) {
                newList.add(this.getListAvversari().get(i));
            } else {
                newList.add(this.getListAvversari().get(i + 1));
            }
            list.clear();
            this.risultati.putAll(map); 
        }
        return newList;
    }
    
    private void matchTeamAvv() throws FileNotFoundException, ClassNotFoundException, IOException {
        Map<Team, Integer> map2;
        final List<Team> l = new ArrayList<>(); // appoggio
        if (this.isEliminated() && !this.isElimThisTurn()) { 
            final SimulatingMatchImpl s = new SimulatingMatchImpl(this.squadraAvv, this.getListAvversari().get(0));
            map2 = s.result(START_REG);
            l.clear();
            l.addAll(map2.keySet());
            this.risMatch.put(l.get(0).getTeamName(), map2.get(l.get(0)));
            this.risMatch.put(l.get(1).getTeamName(), map2.get(l.get(1)));
            final int r1 = map2.get(l.get(0));
            final int r2 = map2.get(l.get(1));
            if (this.risMatch.get(l.get(0).getTeamName())
                    .equals(this.risMatch.get(l.get(1).getTeamName()))) {
                map2 = s.result(START_EXTRA);
                l.clear();
                l.addAll(map2.keySet());
                this.risMatch.clear();
                this.risMatch.put(l.get(0).getTeamName(), map2.get(l.get(0)) + r1);
                this.risMatch.put(l.get(1).getTeamName(), map2.get(l.get(1)) + r2);
                l.clear();
            }
            // this.risMatch = s.risultato2();
        }
    }
    
    private List<Team> simulateSemi() throws FileNotFoundException, ClassNotFoundException, IOException {
        List<Team> newList = new ArrayList<>();
        final Map<String, Integer> map = new HashMap<>(); // map per il risultato
        final List<String> list = new ArrayList<>(); // lista per i nomi delle squadre che si sfidano
        String teamWin; // nome della squadra vincente
        String teamLose; // nome della squadra perdente
        Map<Team, Integer> map2;
        final List<Team> l = new ArrayList<>(); // appoggio
        
        final SimulatingMatchImpl s = new SimulatingMatchImpl(this.getListAvversari().get(1),
                this.getListAvversari().get(2));
        map.clear();
        map2 = s.result(START_REG);
        l.clear();
        l.addAll(map2.keySet());
        map.put(l.get(0).getTeamName(), map2.get(l.get(0)));
        map.put(l.get(1).getTeamName(), map2.get(l.get(1)));
        l.clear();
        list.addAll(map.keySet());
        if (map.get(list.get(0)).equals(map.get(list.get(1)))) {
            map.clear();
            map2 = s.result(START_EXTRA);
            l.addAll(map2.keySet());
            map.put(l.get(0).getTeamName(), map2.get(l.get(0)));
            map.put(l.get(1).getTeamName(), map2.get(l.get(1)));
            l.clear();
            list.clear();
        }
        list.addAll(map.keySet());
        if (map.get(list.get(0)) > map.get(list.get(1))) {
            teamWin = list.get(0);
        } else if (map.get(list.get(0)) < map.get(list.get(1))) {
            teamWin = list.get(1);
        } else {
            final LogicsShootoutImpl r = new LogicsShootoutImpl(this.getListAvversari().get(1),
                    this.getListAvversari().get(2));
            teamWin = r.getWinner().getTeamName();
            if (teamWin.equals(this.getListAvversari().get(1).getTeamName())) {
                teamLose = this.getListAvversari().get(2).getTeamName();
            } else {
                teamLose = this.getListAvversari().get(1).getTeamName();
            }
            map.clear();
            if (r.getGoals1() > r.getGoals2()) {
                map.put(teamWin, r.getGoals1());
                map.put(teamLose, r.getGoals2());
            } else {
                map.put(teamWin, r.getGoals2());
                map.put(teamLose, r.getGoals1());
            }
        }
        if (this.getListAvversari().get(1).getTeamName().equals(teamWin)) {
            newList.add(this.getListAvversari().get(1));
        } else {
            newList.add(this.getListAvversari().get(2));
        }
        list.clear();
        this.risultati.putAll(map);
        return newList;
    }
    
    private void simulateFinal() throws FileNotFoundException, ClassNotFoundException, IOException {
        Map<Team, Integer> map2;
        final List<Team> l = new ArrayList<>(); // appoggio
        if (this.isEliminated() && !this.isElimThisTurn()) {
            final SimulatingMatchImpl s2 = new SimulatingMatchImpl(this.squadraAvv, this.getListAvversari().get(0));
            map2 = s2.result(START_REG);
            l.clear();
            l.addAll(map2.keySet());
            this.risMatch.put(l.get(0).getTeamName(), map2.get(l.get(0)));
            this.risMatch.put(l.get(1).getTeamName(), map2.get(l.get(1)));
            // l.clear();
            final int r1 = map2.get(l.get(0));
            final int r2 = map2.get(l.get(1));
            if (r1 > r2) {
                this.winner = l.get(0).getTeamName();
            } else if (r2 > r1) {
                this.winner = l.get(1).getTeamName();
            } else {
                map2 = s2.result(START_EXTRA);
                l.clear();
                l.addAll(map2.keySet());
                this.risMatch.clear();
                this.risMatch.put(l.get(0).getTeamName(), map2.get(l.get(0)) + r1);
                this.risMatch.put(l.get(1).getTeamName(), map2.get(l.get(1)) + r2);
                final int p1 = map2.get(l.get(0));
                final int p2 = map2.get(l.get(1));
                if (p1 > p2) {
                    this.winner = l.get(0).getTeamName();
                } else if (p2 > p1) {
                    this.winner = l.get(1).getTeamName();
                    // l.clear();
                } else {
                    this.winner = new LogicsShootoutImpl(this.squadraAvv, this.getListAvversari().get(0))
                            .getWinner().getTeamName();
                }
            }
        }
    }
}

