package logics;

import java.net.URL;
import java.net.URLConnection;

import manageData.*;

public class LogicStartImpl implements LogicStart {
	private final ManageStagioni ms=new ManageStagioniImpl();
	private final Boolean online;
	
	public LogicStartImpl() {
		this.online=checkConnection();
		if(online) {
			ms.updateStagioni();
		}
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
