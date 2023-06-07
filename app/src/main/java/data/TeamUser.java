package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TeamUser implements Team, Serializable {

    private static final long serialVersionUID = 1L;
    private final int id;
    private final String teamName;
    private final String logo;
    private final Module modulo;
    private final List<Player> liPlayers;
    private List<Player> liStarting;
    private List<Player> liSubstitution;

    public TeamUser(final String teamName, final String logo, final Module modulo,
        final List<Player> liStarting, final List<Player> liSubstitution) {
        this.id = 0;
        this.teamName = teamName;
        this.logo = logo;
        this.modulo = modulo;
        this.liStarting = liStarting;
        this.liSubstitution = liSubstitution;
        this.liPlayers = new ArrayList<>();
        liPlayers.addAll(liStarting);
        liPlayers.addAll(liSubstitution);
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
    public Module getModule() {
        return modulo;
    }

    @Override
    public List<Player> getLiPlayers() {
        return liPlayers;
    }

    @Override
    public List<Player> getStarting() {
        return liStarting.stream()
            .sorted((c1, c2) -> c2.getPos().compareTo(c1.getPos()))
            .collect(Collectors.toList());
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
        return "TeamUser [teamUser=" + teamName + "]";
    }
}