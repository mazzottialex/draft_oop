package model.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import managedata.ExtractData;
import managedata.ExtractDataImpl;

/** 
 * This class represents an opposing team and implements the {@code Team} interface.
 * Each opposing team has an ID, a name, an emblem, a formation, a list of starters,
 * a list of substitutes, and a list of all players for a particular season.
 *
 */
public final class TeamOpponent implements Team, Serializable {

    private static final long serialVersionUID = 1L;
    private final int id;
    private final String teamName;
    private final URL logo;
    private final Module module;
    private final List<Player> liPlayers;
    private List<Player> liStarting;
    private List<Player> liSubstitution;


    /**
     * Constructor for the {@code TeamOpponent} class.
     * 
     * @param id            The ID of the team.
     * @param teamName   The name of the team.
     * @param module        The formation of the team.
     * @param li            The list of players for a particular season.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public TeamOpponent(final int id, final String teamName, final Module module, final List<Player> li)
    throws FileNotFoundException, IOException, ClassNotFoundException {
        this.id = id;
        this.teamName = teamName;
        this.module = module;
        this.liPlayers = new ArrayList<>(li);
        ExtractData ed = new ExtractDataImpl(li);
        this.liStarting = ed.getStarting(teamName, module);
        this.liSubstitution = ed.getSubstitution(teamName, module);
        this.logo = this.setStemma();
    }

    /**
     * Sets the emblem for the opposing team.
     *
     * @return The emblem filename.
     */
    private URL setStemma() {
        final List<String> liStemmi = List.of("arancione.png", "azzurro.png",
            "bianco.png", "blu.png", "giallo.png", "nero.png", "rosso.png",
            "verde.png", "viola.png");
        return ClassLoader.getSystemResource(liStemmi.get(new Random().nextInt(liStemmi.size())));
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public URL getLogo() {
        return logo;
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public List<Player> getLiPlayers() {
        return new ArrayList<>(liPlayers);
    }

    @Override
    public List<Player> getStarting() {
        return new ArrayList<>(liStarting);
    }

    @Override
    public List<Player> getStartingDesc() {
        return liStarting.stream()
            .sorted((p1, p2) -> p1.getPos().compareTo(p2.getPos()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getSubstitution() {
        return new ArrayList<>(liSubstitution);
    }

    @Override
    public void setStarting(final List<Player> liStarting) {
        this.liStarting = new ArrayList<>(liStarting);
    }

    @Override
    public void setSubstitution(final List<Player> liSubstitution) {
        this.liSubstitution = new ArrayList<>(liSubstitution);
    }

    @Override
    public int getRating() {
        return (int) Math.floor(
            liStarting.stream()
            .map(p -> p.getRating()
                .getX())
            .mapToDouble(p -> p)
            .average()
            .orElse(0));
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Player getPlayerById(final int id) {
        Player p = null;
        for (Player player: liPlayers) {
            if (player.getId() == id) {
                p = player;
            }
        }
        return p;
    }

    @Override
    public Player getStartingKeeper() {
        Player gk = null;
        for (Player player: getStarting()) {
            if (player.getPos().equals("P")) {
                gk = player;
            }
        }
        return gk;
    }

    @Override
    public String toString() {
        return "TeamOpponent [teamOpponent=" + teamName + "]";
    }
}
