package controller;

import java.util.List;

import model.data.Team;
/**
 * The LogicsHistory interface for view logics that manage the history of the teams.
 */
public interface LogicsHistory {
    /**
     * Gets the season.
     *
     * @return the season
     */
    String getSeason();

    /**
     * Gets the online status.
     *
     * @return true if online, false if offline
     */
    Boolean getOnline();

    /**
     * Retrieves the list of teams from the history.
     *
     * @return the list of teams
     */
    List<Team> getLiTeam();
}
