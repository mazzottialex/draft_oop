package logics;

import java.util.List;

import data.Player;

public interface LogicsSettings {

	public String getLogo();

	public void setLogo(String logo);

	public String getTeamName();

	public void setTeamName(String teamName);
	
	public List<Player> getLi();
	
	public List<String> getLiLogo();
}
