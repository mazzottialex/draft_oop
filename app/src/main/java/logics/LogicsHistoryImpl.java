package logics;

import java.util.List;

import data.Team;
import manageData.LogicsFile;
import manageData.LogicsFileImpl;

public class LogicsHistoryImpl implements LogicsHistory{
	private final String season;
	private final Boolean online;
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
	public List<Team> getLiTeam() {
		return file.LoadStorico();
	}
	
	

}
