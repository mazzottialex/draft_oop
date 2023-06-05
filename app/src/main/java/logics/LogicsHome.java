package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;
public interface LogicsHome {
	public void setStagione(String stagione);
	public List<String> getStagioni();
	public Boolean loadStagione(String stagione);
	public Boolean downloadStagione(String stagione);
	public List<Calciatore> getLi() throws FileNotFoundException, ClassNotFoundException, IOException;
}
