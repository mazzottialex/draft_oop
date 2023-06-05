package logics;
import java.net.URL;
import java.net.URLConnection;

import manageData.LogicsFile;
import manageData.LogicsFileImpl;
import manageData.ManageStagioniImpl;
public class LogicStartImpl implements LogicStart {
    private final ManageStagioniImpl ms;
    private final Boolean online;
    public LogicStartImpl() {
        this.online = checkConnection();
        ms = new ManageStagioniImpl(online);
        ms.updateStagioni();
    }
    @Override
    public Boolean getOnline() {
        return this.online;
    }
    @Override
	public String getFirstSeason() {
		LogicsFile lf=new LogicsFileImpl();
    	return lf.loadStagioni().get(0);
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
