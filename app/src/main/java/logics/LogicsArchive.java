package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;
public interface LogicsArchive {
	public List<Player> liOrdered(List<Player> li)
			throws FileNotFoundException, ClassNotFoundException, IOException;
	public Boolean getOnline();
	public String getSeason();
}
