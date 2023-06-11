package controller;

import java.util.List;

import model.data.Team;
import model.managedata.LogicsFile;
import model.managedata.LogicsFileImpl;

/**
 * This class represents the implementation of historical logic operations on team data.
 */
public final class LogicsHistoryImpl implements LogicsHistory {
    private final String season;
    private final Boolean online;
    private final LogicsFile file = new LogicsFileImpl();

    /**
     * Constructs a new instance of LogicsHistoryImpl with the specified season and online status.
     *
     * @param season the season
     * @param online the online status
     */
    public LogicsHistoryImpl(final String season, final Boolean online) {
        this.season = season;
        this.online = online;
    }
    @Override
    public String getSeason() {
        return this.season;
    }

    @Override
    public Boolean getOnline() {
        return this.online;
    }
    @Override
    public List<Team> getLiTeam() {
        return file.loadHistory();
    }
}

