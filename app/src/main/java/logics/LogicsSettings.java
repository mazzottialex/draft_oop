package logics;

import java.util.List;

import data.Calciatore;

public interface LogicsSettings {

	public String getLogo();

	public void setLogo(String logo);

	public String getTeamName();

	public void setTeamName(String teamName);
	
	public List<Calciatore> getLi();
	
	public List<String> getLiLogo();
}
