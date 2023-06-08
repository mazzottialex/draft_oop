package logics;
/**
 * The LogicsViewTeamImpl class represents the implementation of starting logic operations.
 */
public final class LogicsViewTeamImpl implements LogicsViewTeam{
	private final String season;
	private final Boolean online;
	/**
     * Constructs a new instance of LogicsViewTeamImpl.
     * 
     * @param season the season
     * @param online the online status
     */
	public LogicsViewTeamImpl(final String season, final Boolean online) {
		this.season = season;
		this.online = online;
	}

	@Override
	public String getSeason() {
		return this.season;
	}

	@Override
	public Boolean getOnline() {
		return this.online;
	}

}
