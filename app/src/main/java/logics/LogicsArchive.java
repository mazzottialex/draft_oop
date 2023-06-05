package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import data.Calciatore;
public interface LogicsArchive {
	public List<Calciatore> liOrdered(List<Calciatore> li)
			throws FileNotFoundException, ClassNotFoundException, IOException;
	public Boolean getOnline();
	public String getSeason();
}
