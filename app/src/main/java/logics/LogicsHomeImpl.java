package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Calciatore;
import manageData.ManageData;
import manageData.ManageDataImpl;
import manageData.ManageStagioni;
import manageData.ManageStagioniImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;
public class LogicsHomeImpl implements LogicsHome {
    private List<Calciatore> li;
    private String stagione;
    private Boolean online;
    private final ManageStagioni ms;
    public LogicsHomeImpl(final String stagione, final Boolean online) { //di default
        li = new ArrayList<>();
        this.stagione = stagione;
        this.online = online;
        ms = new ManageStagioniImpl(online);
    }
    @Override
    public void setStagione(final String stagione) {
        this.stagione = stagione;
    }
    @Override
    public List<String> getStagioni() {
        return ms.getStagioni();
    }
    @Override
    public Boolean loadStagione(final String stagione) {
        final ManageData md = new ManageDataImpl(stagione);
        this.stagione = stagione;
        try {
            md.LoadData();
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
        this.li = md.getLi();
        return true;
    }
    @Override
    public Boolean downloadStagione(final String stagione) {
        final ManageData md = new ManageDataImpl(stagione);
        this.stagione = stagione;
        try {
            if (!md.DownloadData())
                return false;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.li = md.getLi();
        return true;
    }
    @Override
    public List<Calciatore> getLi()
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        final CalcoloRating rat = new CalcoloRatingImpl(this.li);
        rat.updateRating();
        return rat.getLi();
    }
}
