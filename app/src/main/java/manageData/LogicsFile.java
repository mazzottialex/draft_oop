package manageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;
import data.Team;
public interface LogicsFile {
	public List<Player> LoadData(String stagione)
			throws FileNotFoundException, IOException, ClassNotFoundException;
	public Boolean SaveData(List<Player> li, String stagione);
	public List<String> loadStagioni();
	public Boolean SaveStagioni(List<String> li);
	public List<Team> LoadStorico();
	public Boolean SaveStorico(Team s);
}
