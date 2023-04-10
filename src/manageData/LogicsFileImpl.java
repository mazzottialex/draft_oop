package manageData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class LogicsFileImpl implements LogicsFile{
	
	private List<Calciatore> li;
	
	public LogicsFileImpl() {
		li=new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Calciatore> LoadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		try(final InputStream file = new FileInputStream("res/backup.txt");
			final InputStream bstream = new BufferedInputStream(file);
			final ObjectInputStream ostream=new ObjectInputStream(file);
					){
				li=(List<Calciatore>)ostream.readObject();
				ostream.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public Boolean SaveData(List<Calciatore> li) {
		try(final OutputStream file = new FileOutputStream("res/backup.txt");
				final OutputStream bstream = new BufferedOutputStream(file);
				final ObjectOutputStream ostream=new ObjectOutputStream(file);
					){
				ostream.writeObject(li);
				ostream.close();
			}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
