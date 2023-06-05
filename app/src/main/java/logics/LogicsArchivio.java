package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;
public interface LogicsArchivio {
	public List<Calciatore> liOrdinata(List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException;
	public Boolean getOnline();
}
