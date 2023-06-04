package manageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;
public interface ManageData {
	public List<Calciatore> getLi();
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	public Boolean DownloadData() throws FileNotFoundException, ClassNotFoundException, IOException;
}
