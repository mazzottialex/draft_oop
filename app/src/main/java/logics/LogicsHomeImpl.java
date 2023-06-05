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
    private String season;
    private Boolean online;
    private final ManageStagioni ms;
    public LogicsHomeImpl(final String season, final Boolean online) { //di default
        li = new ArrayList<>();
        this.season = season;
        this.online = online;
        ms = new ManageStagioniImpl(online);
        loadStagione(season);
    }
    @Override
    public String getSeason() {
    	return this.season;
    }
    @Override
    public Boolean getOnline() {
    	return this.online;
    }
    @Override
    public void setSeason(final String season) {
        this.season = season;
    }
    @Override
    public List<String> getLiSeasons() {
        return ms.getStagioni();
    }
    @Override
    public Boolean loadStagione(final String season) {
        final ManageData md = new ManageDataImpl(season);
        this.season = season;
        try {
            md.LoadData();
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
        this.li = md.getLi();
        return true;
    }
    @Override
    public Boolean downloadSeason(final String season) {
        final ManageData md = new ManageDataImpl(season);
        this.season = season;
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
