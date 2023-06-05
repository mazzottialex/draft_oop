package logics;
import java.util.List;

import data.Calciatore;
import data.Squadra;
import data.SquadraUtente;
public class LogicsSettingsImpl implements LogicsSettings {
    private final String defaultLogo = "res/stemmi/bianco.png";
    private final String defaultTeamName = "Squadra 1";
    private final List<Calciatore> li;
    private final List<String> liLogo=List.of("src/main/resources/stemmi/bianco.png",
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

    public LogicsSettingsImpl(final List<Calciatore> li) {
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
	public List<Calciatore> getLi() {
		return li;
	}
	
	@Override
	public List<String> getLiLogo() {
		return liLogo;
	}
}
