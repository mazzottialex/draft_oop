package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
public class LogicsArchiveImpl implements LogicsArchive {
	private final String season;
	private final Boolean online;
	public LogicsArchiveImpl(String season, Boolean online) {
		this.season=season;
		this.online=online;
	}
	@Override
	public List<Player> liOrdered(List<Player> li)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ex =new ExtractDataImpl(li);
		li=ex.getListOrdered(c->-c.getRating().getX());
		return li;
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
