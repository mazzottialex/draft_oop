package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;
import data.Squadra;

public interface LogicsFile {
	public List<Calciatore> LoadData(String stagione) throws FileNotFoundException, IOException, ClassNotFoundException;
	public Boolean SaveData(List<Calciatore> li, String stagione);
	public List<String> loadStagioni();
	public Boolean SaveStagioni(List<String> li);
	public List<Squadra> LoadStorico();
	public Boolean SaveStorico(Squadra s);
}