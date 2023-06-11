package model.managedata;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import model.data.Player;
import model.data.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the LogicsFile interface.
 */
public final class LogicsFileImpl implements LogicsFile {

	private static final Logger LOG = LoggerFactory.getLogger(LogicsFileImpl.class);
	private static final String MESSAGGE_ERROR = "Generic error";
	/**
     * Constructs a new instance of LogicsFileImpl.
     */
	@SuppressWarnings("PMD")
    public LogicsFileImpl() { 
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Player> loadData(final String season) throws ClassNotFoundException {
        List<Player> li = new ArrayList<>();
        try(InputStream file = ClassLoader.getSystemResourceAsStream("backup" + season + ".txt");
                InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(bstream);) {
        	 li = (List<Player>) ostream.readObject();
             ostream.close();
        } catch (IOException e) {
        	return li;
		}
        return li;
    }

    @Override
    public Boolean saveData(final List<Player> li, final String season) {
        try (OutputStream file = new FileOutputStream("src/main/resources/backup" + season + ".txt");
        		OutputStream bstream = new BufferedOutputStream(file);
        		ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            ostream.writeObject(li);
            ostream.close();
        } catch (IOException  e) {
        	LOG.error(MESSAGGE_ERROR, e);
		}
        return true;
    }

    @Override
    public List<String> loadSeason() {
        final List<String> ls = new ArrayList<>();
        try (InputStream file = ClassLoader.getSystemResourceAsStream("backupSeasons.txt");
        		InputStream bstream = new BufferedInputStream(file);
        		ObjectInputStream ostream = new ObjectInputStream(bstream);) {
            String str = ostream.readUTF();
            while (str != null) {
                ls.add(str);
                str = ostream.readUTF();
            }
            ostream.close();
        } catch (IOException e) {
        	return ls;
		}
        return ls;
    }

    @Override
    public Boolean saveSeason(final List<String> li) {
        try (OutputStream file = new FileOutputStream("src/main/resources/backupSeasons.txt");
        		OutputStream bstream = new BufferedOutputStream(file);
        		ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            li.forEach(s -> {
                    try {
						ostream.writeUTF(s);
					}  catch (IOException e) {
			        	LOG.error(MESSAGGE_ERROR, e);
					}
            });
            ostream.close();
        } catch (final IOException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Team> loadHistory() {
        try (InputStream file = ClassLoader.getSystemResourceAsStream("history.txt");
        	    InputStream bstream = new BufferedInputStream(file);
        		ObjectInputStream ostream = new ObjectInputStream(file);) {
        	final List<Team> li = (List<Team>) ostream.readObject();
            ostream.close();
            return li;
        } catch (IOException | ClassNotFoundException e) {
        	return new ArrayList<>();
		}
    }

    @Override
    public Boolean saveHistory(final Team s) {
        final List<Team> li = loadHistory();
        li.add(s);
        try (OutputStream file = new FileOutputStream("src/main/resources/history.txt");
        		OutputStream bstream = new BufferedOutputStream(file);
        		ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            ostream.writeObject(li);
            ostream.close();
        } catch (IOException e) {
        	LOG.error(MESSAGGE_ERROR, e);
		}
        return true;
    }
}
