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
	
	public LogicsFileImpl() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Calciatore> LoadData(String stagione) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Calciatore> li=new ArrayList<>();
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
	public Boolean SaveData(List<Calciatore> li, String stagione) {
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
	
	public List<String> loadStagioni(){
		List<String> ls=new ArrayList<>();
		try(final InputStream file = new FileInputStream("res/backupStagioni.txt");
				final InputStream bstream = new BufferedInputStream(file);
				final ObjectInputStream ostream=new ObjectInputStream(file);
						){
					while(ls.add(ostream.readUTF())) {}
					ostream.close();
				}
			catch (Exception e) {
				e.printStackTrace();
			}
			return ls;
	}
	
	@Override
	public Boolean SaveStagioni(List<String> li){
		try(final OutputStream file = new FileOutputStream("res/backupStagioni.txt");
				final OutputStream bstream = new BufferedOutputStream(file);
				final ObjectOutputStream ostream=new ObjectOutputStream(file);
					){
				li.forEach(s-> {
					try {
						ostream.writeUTF(s);
					} catch (IOException e) {
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
