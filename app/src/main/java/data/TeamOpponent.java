package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import manageData.ExtractData;
import manageData.ExtractDataImpl;

/** 
 * This class represents an opposing team and implements the {@code Squadra} interface.
 * Each opposing team has an ID, a name, an emblem, a formation, a list of starters,
 * a list of substitutes, and a list of all players for a particular season.
 *
 */
public class TeamOpponent implements Team {
	
	private static final long serialVersionUID = 1L;
    private final int id;
    private final String teamName;
    private final String logo;
    private final Module modulo;
    private final List<Player> liPlayers;
    private List<Player> liStarting;
    private List<Player> liSubstitution;


    /**
     * Constructor for the {@code SquadraAvversaria} class.
     * 
     * @param id            The ID of the team.
     * @param nomeSquadra   The name of the team.
     * @param modulo        The formation of the team.
     * @param li            The list of players for a particular season.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public TeamOpponent(final int id, final String teamName, final Module modulo, final List<Player> li)
    throws FileNotFoundException, IOException, ClassNotFoundException {
        this.id = id;
        this.teamName = teamName;
        this.modulo = modulo;
        this.liPlayers = li;
        ExtractData ed = new ExtractDataImpl(li);
        this.liStarting = ed.getStarting(teamName, modulo);
        this.liSubstitution = ed.getSubstitution(teamName, modulo);
        this.logo = this.setStemma();
    }

    /**
     * Sets the emblem for the opposing team.
     *
     * @return The emblem filename.
     */
    private String setStemma() {
        final List<String> liStemmi = List.of("arancione.png", "azzurro.png",
            "bianco.png", "blu.png", "giallo.png", "nero.png", "rosso.png",
            "verde.png", "viola.png");
        Random rnd = new Random();
        int pos = rnd.nextInt(liStemmi.size());
        return liStemmi.get(pos);
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public Module getModulo() {
        return modulo;
    }

    @Override
    public List<Player> getLiPlayers() {
        return liPlayers;
    }

    @Override
    public List<Player> getStarting() {
        return liStarting;
    }
    
    @Override
    public List<Player> getStartingDesc() {
        return liStarting.stream()
            .sorted((c1, c2) -> c1.getPos().compareTo(c2.getPos()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getSubstitution() {
        return this.liSubstitution;
    }

    @Override
    public void setStarting(final List<Player> liStarting) {
        this.liStarting = liStarting;
    }

    @Override
    public void setSubstitution(final List<Player> liSubstitution) {
        this.liSubstitution = liSubstitution;
    }

    @Override
    public int getRating() {
        return (int) Math.floor(
            liStarting.stream()
            .map(c -> c.getRating()
                .getX())
            .mapToDouble(c -> c)
            .average()
            .orElse(0));
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Player getPlayerById(final int id) {
        Player c = null;
        for (Player player: liPlayers) {
            if (player.getId() == id) {
                c = player;
            }
        }
        return c;
    }

    @Override
    public Player getStartingKeeper() {
        Player keeper = null;
        for (Player player: getStarting()) {
            if (player.getPos().equals("P")) {
            	keeper = player;
            }
        }
        return keeper;
    }
    @Override
    public String toString() {
        return "TeamOpponent [teamOpponent=" + teamName + "]";
    }

}
