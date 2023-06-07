package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import data.Player;
import data.Module;
import data.Team;
import data.TeamOpponent;

/**
 * The {@code CreateOpponentTeamsImpl} class implements the {@code CreateOpponentTeams} interface
 * and provides the functionality to create opposing teams.
 */
public class CreateOpponentTeamsImpl implements CreateOpponentTeams {

    private int totTeams;
    private List<Player> li;
    private List<String> teams;

    /**
     * Constructs a {@code CreateOpponentTeamsImpl} object with the specified list of players and team count.
     *
     * @param players the list of players.
     * @param totTeams the number of teams to create.
     */
    public CreateOpponentTeamsImpl(final List<Player> players, final int totTeams) {
        this.totTeams = totTeams;
        this.li = players;
        this.teams = findTeams(players);
    }

    /**
     * Collects the unique team names from the given list of players.
     *
     * @param players the list of players.
     * @return a list of unique team names.
     */
    private static List<String> findTeams(final List<Player> players) {
        Set<String> teamsSet = new HashSet<>();
        for (Player p: players) {
            teamsSet.add(p.getTeam());
        }
        return new ArrayList<>(teamsSet);
    }

    /**
     * Selects random teams from the given list of teams and returns a list of {@code TeamOpponent} objects.
     *
     * @param teams the list of teams.
     * @param module the team formation.
     * @param totTeams the number of teams to select.
     * @return a list of randomly selected opposing teams.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    private List<Team> selectRandomTeams(final List<String> teams, final Module module, final int totTeams)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        Collections.shuffle(teams); // Mescola l'ordine delle squadre
        int id = 1;
        List<Team> teamsList = new ArrayList<>();
        for (String t: teams) {
            teamsList.add(new TeamOpponent(id, t, module, li));
            id++;
        }
        return teamsList.subList(0, Math.min(totTeams, teams.size()));
    }

    /**
     * Selects a random team formation.
     *
     * @return a randomly selected team formation.
     */
    private static Module selectModule() {
        List < Module > module = new ArrayList<>(List.of(Module.values()));
        Random random = new Random();
//        int index = random.nextInt(module.size());
        return module.get(random.nextInt(module.size()));
    }

    @Override
    public List<Team> getTeams() throws FileNotFoundException, ClassNotFoundException, IOException {
        return selectRandomTeams(teams, selectModule(), totTeams);
    }
}
