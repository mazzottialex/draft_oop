package manageData;

import java.util.ArrayList;
import java.util.List;

import scraping.Scraping;
import scraping.ScrapingImpl;

public class ManageStagioniImpl extends Thread implements ManageStagioni {
	private Boolean online;
	private LogicsFile file=new LogicsFileImpl();
	private Scraping s;
	private List<String> li=new ArrayList<>();
	
	public ManageStagioniImpl( Boolean online) {
		this.online=online;
		this.s= new ScrapingImpl();
	}
	
	public List<String> getStagioni() {
		return li=file.loadStagioni();
	}
	
	public Boolean updateStagioni() {
		if(online) {
			this.start();
			return true;
		}
		return false;
	}
	
	public void run() {
		li=s.getStagioni();
		file.SaveStagioni(li);
	}
}
