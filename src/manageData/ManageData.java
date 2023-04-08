package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ManageData {
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	public void UploadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	public List<Calciatore> getLi();
}
