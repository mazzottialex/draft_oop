package logics;

import java.net.URL;
import java.net.URLConnection;

import manageData.ManageStagioniImpl;

public class LogicStartImpl {
	private final ManageStagioniImpl ms;
	private final Boolean online;
	
	public LogicStartImpl() {
		this.online=checkConnection();
		ms=new ManageStagioniImpl(online);
		ms.updateStagioni();
	}
	
	public Boolean getOnline() {
		return this.online;
	}
	
	private Boolean checkConnection() {
		try {
			URL url = new URL("http://www.google.com");
	    	URLConnection connection = url.openConnection();
	    	connection.connect();
	    	return true;
		}catch(Exception e) {
	    	return false;
	    }
	}
}
