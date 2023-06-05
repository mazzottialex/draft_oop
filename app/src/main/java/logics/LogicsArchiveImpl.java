package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;
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
	public List<Calciatore> liOrdered(List<Calciatore> li)
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
