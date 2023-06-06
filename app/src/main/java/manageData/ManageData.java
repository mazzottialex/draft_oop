package manageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Player;
public interface ManageData {
	public List<Player> getLi();
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	public Boolean DownloadData() throws FileNotFoundException, ClassNotFoundException, IOException;
}
