package logics;
import java.util.List;

import data.Player;

/**
 * The LogicsSettingsImpl class represents the implementation of settings logic operations in a team management system.
 */
public final class LogicsSettingsImpl implements LogicsSettings {
    private static final String DEFAULT_LOGO = "res/stemmi/bianco.png";
    private static final String DEFAULT_TEAM_NAME = "Squadra 1";
    private final List<Player> li;
    private final List<String> liLogo = List.of(
            "src/main/resources/stemmi/bianco.png",
            "src/main/resources/stemmi/blu.png",
            "src/main/resources/stemmi/giallo.png",
            "src/main/resources/stemmi/nero.png",
            "src/main/resources/stemmi/verde.png",
            "src/main/resources/stemmi/viola.png",
            "src/main/resources/stemmi/rosso.png",
            "src/main/resources/stemmi/azzurro.png",
            "src/main/resources/stemmi/arancione.png"
    );
    private String logo;
    private String teamName;

    /**
     * Constructs a new instance of LogicsSettingsImpl with the specified list of players.
     *
     * @param li the list of players
     */
    public LogicsSettingsImpl(final List<Player> li) {
        this.li = li;
        this.logo = DEFAULT_LOGO;
        this.teamName = DEFAULT_TEAM_NAME;
    }
    @Override
    public String getLogo() {
        return logo;
    }
    @Override
    public void setLogo(final String logo) {
        this.logo = logo;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    @Override
    public List<Player> getLi() {
        return li;
    }

    @Override
    public List<String> getLiLogo() {
        return liLogo;
    }
}
