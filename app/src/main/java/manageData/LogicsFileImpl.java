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
import data.Player;
import data.Team;
public class LogicsFileImpl implements LogicsFile {
    public LogicsFileImpl(){}
    @SuppressWarnings("unchecked")
    @Override
    public List<Player> LoadData(String stagione) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Player> li = new ArrayList<>();
        try (final InputStream file = new FileInputStream("src/main/resources/backup" + stagione + ".txt");
        		final InputStream bstream = new BufferedInputStream(file);
        		final ObjectInputStream ostream = new ObjectInputStream(file);) {
            li = (List<Player>) ostream.readObject();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    @Override
    public Boolean SaveData(List<Player> li, String stagione) {
        try (final OutputStream file = new FileOutputStream("src/main/resources/backup" + stagione + ".txt");
        		final OutputStream bstream = new BufferedOutputStream(file);
        		final ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            ostream.writeObject(li);
            ostream.close();
        } catch (final Exception e) {
            return false;
        }
        return true;
    }
    public List<String> loadStagioni() {
        List<String> ls = new ArrayList<>();
        try (final InputStream file = new FileInputStream("src/main/resources/backupStagioni.txt");
        		final InputStream bstream = new BufferedInputStream(file);
        		final ObjectInputStream ostream = new ObjectInputStream(file);) {
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
    public Boolean SaveStagioni(final List<String> li) {
        try (final OutputStream file = new FileOutputStream("src/main/resources/backupStagioni.txt"); final OutputStream bstream = new BufferedOutputStream(file); final ObjectOutputStream ostream = new ObjectOutputStream(file);) {
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
    public List<Team> LoadStorico() {
        List<Team> li = new ArrayList<>();
        try (final InputStream file = new FileInputStream("src/main/resources/storico.txt"); final InputStream bstream = new BufferedInputStream(file); final ObjectInputStream ostream = new ObjectInputStream(file);) {
            li = (List<Team>) ostream.readObject();
            ostream.close();
        } catch (final Exception e) {
            return new ArrayList<>();
        }

        return li;
    }
    @Override
    public Boolean SaveStorico(final Team s) {
        List<Team> li = LoadStorico();
        li.add(s);
        try (final OutputStream file = new FileOutputStream("src/main/resources/storico.txt"); final OutputStream bstream = new BufferedOutputStream(file); final ObjectOutputStream ostream = new ObjectOutputStream(file);) {
            ostream.writeObject(li);
            ostream.close();
        } catch (final Exception e) {
            return false;
        }
        return true;
    }
}
