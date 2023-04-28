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

import data.Calciatore;

public class LogicsFileImpl implements LogicsFile{
	
	private List<Calciatore> li;
	private String stagione;
	
	public LogicsFileImpl(String stagione) {
		li=new ArrayList<>();
		this.stagione=stagione;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Calciatore> LoadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		try(final InputStream file = new FileInputStream("res/backup"+stagione+".txt");
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
		try(final OutputStream file = new FileOutputStream("res/backup"+stagione+".txt");
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
	
	@Override
	public Boolean SaveStagioni(List<String> li) {
		try(final OutputStream file = new FileOutputStream("res/backupStagioni.txt");
				final OutputStream bstream = new BufferedOutputStream(file);
				final ObjectOutputStream ostream=new ObjectOutputStream(file);
					){
				li.forEach(s-> {
					try {
						ostream.writeUTF(s);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} );
				ostream.close();
			}
		catch (Exception e) {
			return false;
		}
		return true;
	}
}
