package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.data.Player;

/**
 * The LogicsSettingsImpl class represents the implementation of settings logic operations in a team management system.
 */
public final class LogicsSettingsImpl implements LogicsSettings {
    private static final URL DEFAULT_LOGO = ClassLoader.getSystemResource("bianco.png");
    private static final String DEFAULT_TEAM_NAME = "Squadra 1";
    private final List<Player> li;
    private final List<String> liLogo = List.of(
            "stemmi/bianco.png",
            "stemmi/blu.png",
            "stemmi/giallo.png",
            "stemmi/nero.png",
            "stemmi/verde.png",
            "stemmi/viola.png",
            "stemmi/rosso.png",
            "stemmi/azzurro.png",
            "stemmi/arancione.png"
    );
    private URL logo;
    private String teamName;

    /**
     * Constructs a new instance of LogicsSettingsImpl with the specified list of players.
     *
     * @param li the list of players
     */
    public LogicsSettingsImpl(final List<Player> li) {
        this.li = new ArrayList<>(li);
        this.logo = DEFAULT_LOGO;
        this.teamName = DEFAULT_TEAM_NAME;
    }
    @Override
    public URL getLogo() {
        return logo;
    }
    @Override
    public void setLogo(final URL logo) {
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
        return new ArrayList<>(li);
    }

    @Override
    public List<String> getLiLogo() {
        return liLogo;
    }
}
