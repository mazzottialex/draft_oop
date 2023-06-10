package managedata;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import data.Player;
import data.Team;

/**
 * Implementation of the LogicsFile interface.
 */
public final class LogicsFileImpl implements LogicsFile {

    /**
     * Constructs a new instance of LogicsFileImpl.
     */
    public LogicsFileImpl() { }
    @SuppressWarnings("unchecked")
    @Override
    public List<Player> loadData(final String season) {
        List<Player> li = new ArrayList<>();
        try (InputStream file = ClassLoader.getSystemResourceAsStream("backup" + season + ".txt");
                InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(file);) {
            li = (List<Player>) ostream.readObject();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (final Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> loadSeason() {
        List<String> ls = new ArrayList<>();
        try (InputStream file = ClassLoader.getSystemResourceAsStream("backupSeasons.txt");
                InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(file);) {
            String str;
            while ((str = ostream.readUTF()) != null) {
                ls.add(str);
            }
            ostream.close();
        } catch (final Exception e) {
            //e.printStackTrace();
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
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            });
            ostream.close();
        } catch (final Exception e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Team> loadHistory() {
        List<Team> li = new ArrayList<>();
        try (InputStream file = ClassLoader.getSystemResourceAsStream("history.txt");
                InputStream bstream = new BufferedInputStream(file);
                ObjectInputStream ostream = new ObjectInputStream(file);) {
            li = (List<Team>) ostream.readObject();
            ostream.close();
        } catch (final Exception e) {
            return new ArrayList<>();
        }
        return li;
    }

    @Override
    public Boolean saveHistory(final Team s) {
        List<Team> li = loadHistory();
        li.add(s);
        try (OutputStream file = new FileOutputStream("src/main/resources/history.txt");
                OutputStream bstream = new BufferedOutputStream(file);
                ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            ostream.writeObject(li);
            ostream.close();
        } catch (final Exception e) {
            return false;
        }
        return true;
    }
}
