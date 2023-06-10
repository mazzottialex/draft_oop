package controller;

/**
 * The LogicsViewTeam class represents the implementation of View team.
 */
public interface LogicsViewTeam {
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
}
