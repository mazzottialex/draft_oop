package controller;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import model.managedata.LogicsFile;
import model.managedata.LogicsFileImpl;
import model.managedata.ManageSeasonImpl;

/**
 * The LogicStartImpl class represents the implementation of starting logic operations.
 */
public final class LogicStartImpl implements LogicStart {
    private final ManageSeasonImpl manageSeason;
    private final Boolean online;
    private final String season;

    /**
     * Constructs a new instance of LogicStartImpl.
     * @throws IOException 
     */
    public LogicStartImpl() throws IOException {
        this.online = checkConnection();
        manageSeason = new ManageSeasonImpl();
        manageSeason.updateSeason();
        final LogicsFile lf = new LogicsFileImpl();
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

    private Boolean checkConnection() throws IOException {
        final URL url = new URL("http://www.google.com");
        final URLConnection connection = url.openConnection();
        connection.connect();
        return true;
    }
}
