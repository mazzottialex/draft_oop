package logics;
import java.util.List;

import data.Player;
import data.Team;
import data.TeamUser;

/**
 * The LogicsSettingsImpl class represents the implementation of settings logic operations in a team management system.
 */
public class LogicsSettingsImpl implements LogicsSettings {
    private final String defaultLogo = "res/stemmi/bianco.png";
    private final String defaultTeamName = "Squadra 1";
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
    	this.li=li;
    	this.logo=defaultLogo;
    	this.teamName=defaultTeamName;
    }
    
    @Override
	public String getLogo() {
		return logo;
	}
    
	@Override
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String getTeamName() {
		return teamName;
	}
	
	@Override
	public void setTeamName(String teamName) {
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
