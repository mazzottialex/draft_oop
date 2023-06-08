package logics;
import java.net.URL;
import java.net.URLConnection;

import managedata.LogicsFile;
import managedata.LogicsFileImpl;
import managedata.ManageSeasonImpl;
/**
 * The LogicStartImpl class represents the implementation of starting logic operations.
 */
public class LogicStartImpl implements LogicStart {
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
        } catch (final Exception e) {
            return false;
        }
    }
}
