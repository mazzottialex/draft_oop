package logics;

import java.util.List;

import data.Squadra;
import manageData.LogicsFile;
import manageData.LogicsFileImpl;

public class LogicsHistoryImpl implements LogicsHistory{
	private String season;
	private Boolean online;
	private LogicsFile file=new LogicsFileImpl();
	public LogicsHistoryImpl(final String season, final Boolean online ) {
		this.season=season;
		this.online=online;
	}
	@Override
	public String getSeason() {
		return this.season;
	}

	@Override
	public Boolean getOnline() {
		return this.online;
	}
	@Override
	public List<Squadra> getLiTeam() {
		return file.LoadStorico();
	}
	
	

}
