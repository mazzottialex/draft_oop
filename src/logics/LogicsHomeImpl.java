package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import data.Calciatore;
import manageData.ManageData;
import manageData.ManageDataImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;
import scraping.Scraping;
import scraping.ScrapingImpl;

public class LogicsHomeImpl implements LogicsHome {

	private List<Calciatore> li;
	private String stagione;
	
	public LogicsHomeImpl(String stagione) { //di default
		li=new ArrayList<>();
		this.stagione=stagione;
	}
	
	@Override
	public String getStagione() {
		return stagione;
	}
	
	@Override
	public void setStagione(String stagione) {
		this.stagione=stagione;
		
	}
	
	@Override
	public List<String> getStagioni() {
		Scraping sc=new ScrapingImpl();
		return sc.getStagioni();
	}

	@Override
	public Boolean loadStagione(String stagione) {
		ManageData md=new ManageDataImpl(stagione);
		this.stagione=stagione;
		try {
			md.LoadData();
		} catch (ClassNotFoundException | IOException e) {
			return false;
		}
		this.li=md.getLi();
		return true;
	}

	@Override
	public Boolean downloadStagione(String stagione) {
		ManageData md=new ManageDataImpl(stagione);
		this.stagione=stagione;
		try {
			md.DownloadData();
		} catch (ClassNotFoundException | IOException e) {
			return false;
		}
		this.li=md.getLi();
		return true;
	}

	@Override
	public List<Calciatore> getLi() throws FileNotFoundException, ClassNotFoundException, IOException {
		CalcoloRating rat=new CalcoloRatingImpl(this.li);
		rat.updateRating();
		return rat.getLi();

	}

	@Override
	public Boolean checkConnection() {
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
