package logics;

public class LogicsViewTeamImpl implements LogicsViewTeam{
	private final String season;
	private final Boolean online;
	
	public LogicsViewTeamImpl(String season, Boolean online) {
		this.season = season;
		this.online = online;
	}

	@Override
	public String getSeason() {
		// TODO Auto-generated method stub
		return this.season;
	}

	@Override
	public Boolean getOnline() {
		// TODO Auto-generated method stub
		return this.online;
	}

}
