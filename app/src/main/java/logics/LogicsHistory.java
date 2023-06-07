package logics;

import java.util.List;

import data.Team;

public interface LogicsHistory {
	/**
     * Gets the season.
     *
     * @return the season
     */
	public String getSeason();
	
	/**
     * Gets the online status.
     *
     * @return true if online, false if offline
     */
	public Boolean getOnline();
	
	/**
     * Retrieves the list of teams from the history.
     *
     * @return the list of teams
     */
	public List<Team> getLiTeam();
}
