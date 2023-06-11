package model.data.managedata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.data.Module;
import model.data.Player;
import model.data.Team;
import model.data.TeamOpponent;

/**
 * The {@code CreateOpponentTeamsImpl} class implements the {@code CreateOpponentTeams} interface
 * and provides the functionality to create opposing teams.
 */
public final class CreateOpponentTeamsImpl implements CreateOpponentTeams {

    private int totTeams;
    private List<Player> li;
    private List<String> teams;
    private ExtractData ed;

    /**
     * Constructs a {@code CreateOpponentTeamsImpl} object with the specified list of players and team count.
     *
     * @param players the list of players.
     * @param totTeams the number of teams to create.
     */
    public CreateOpponentTeamsImpl(final List<Player> players, final int totTeams) {
        this.totTeams = totTeams;
        this.li = new ArrayList<>(players);
        ed = new ExtractDataImpl(players);
        teams = ed.findTeams();
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

    @Override
    public List<Team> getTeams() throws FileNotFoundException, ClassNotFoundException, IOException {
        return selectRandomTeams(teams, ed.selectModule(), totTeams);
    }
}
