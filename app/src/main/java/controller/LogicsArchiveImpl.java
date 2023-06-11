package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.data.Player;
import model.data.managedata.ExtractData;
import model.data.managedata.ExtractDataImpl;

/**
 * Implementation of the LogicsArchive interface.
 */
public final class LogicsArchiveImpl implements LogicsArchive {
    private final String season;
    private final Boolean online;

    /**
     * Constructs a new instance of LogicsArchiveImpl.
     * 
     * @param season the season
     * @param online the online status
     */
    public LogicsArchiveImpl(final String season, final Boolean online) {
        this.season = season;
        this.online = online;
    }

    @Override
    public List<Player> liOrdered(final List<Player> li)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        ExtractData ex = new ExtractDataImpl(li);
        return ex.getListOrdered(c -> -c.getRating().getX());
    }

    @Override
    public String getSeason() {
        return this.season;
    }

    @Override
    public Boolean getOnline() {
        return this.online;
    }

}
