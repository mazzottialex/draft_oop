package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;

public interface LogicsFile {
	public List<Calciatore> LoadData() throws FileNotFoundException, IOException, ClassNotFoundException;
	public Boolean SaveData(List<Calciatore> li);
	public Boolean SaveStagioni(List<String> li);
}
