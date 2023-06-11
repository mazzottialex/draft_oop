package controller;
import java.net.URL;
import java.net.URLConnection;

import model.managedata.LogicsFile;
import model.managedata.LogicsFileImpl;
import model.managedata.ManageSeasonImpl;

/**
 * The LogicStartImpl class represents the implementation of starting logic operations.
 */
public final class LogicStartImpl implements LogicStart {
    private final ManageSeasonImpl ms;
    private final Boolean online;
    private final String season;

    /**
     * Constructs a new instance of LogicStartImpl.
     */
    public LogicStartImpl() {
        this.online = checkConnection();
        ms = new ManageSeasonImpl();
        ms.updateSeason();
        LogicsFile lf = new LogicsFileImpl();
        this.season = lf.loadSeason().get(0);
    }

    @Override
    public Boolean getOnline() {
        return this.online;
    }

    @Override
    public String getFirstSeason() {
        return this.season;
    }

    private Boolean checkConnection() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            return false;
        }
    }
}
