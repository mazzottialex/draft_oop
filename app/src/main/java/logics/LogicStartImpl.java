package logics;
import java.net.URL;
import java.net.URLConnection;

import manageData.LogicsFile;
import manageData.LogicsFileImpl;
import manageData.ManageStagioniImpl;
public class LogicStartImpl implements LogicStart {
    private final ManageStagioniImpl ms;
    private final Boolean online;
    private final String season;
    public LogicStartImpl() {
        this.online = checkConnection();
        ms = new ManageStagioniImpl();
        ms.updateStagioni();
        LogicsFile lf=new LogicsFileImpl();
        this.season=lf.loadStagioni().get(0);
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
