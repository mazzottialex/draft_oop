package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Player;
import manageData.ManageData;
import manageData.ManageDataImpl;
import manageData.ManageSeason;
import manageData.ManageSeasonImpl;
import rating.AnalysisRating;
import rating.AnalysisRatingImpl;
public class LogicsHomeImpl implements LogicsHome {

    private List<Player> li;
    private String season;
    private Boolean online;
    private final ManageSeason ms;
    public LogicsHomeImpl(final String season, final Boolean online) { //di default
        li = new ArrayList<>();
        this.season = season;
        this.online = online;
        ms = new ManageSeasonImpl();
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
        return ms.getSeason();
    }
    @Override
    public Boolean loadStagione(final String season) {
        final ManageData md = new ManageDataImpl(season);
        this.season = season;
        try {
            md.loadData();
        } catch (Exception e) {
            return false;
        }
        this.li = md.getLi();
        return true;
    }
    @Override
    public Boolean downloadSeason(final String season) {
        final ManageData md = new ManageDataImpl(season);
        this.season = season;
        if (!md.downloadData()) {
            return false;
        }
        this.li = md.getLi();
        return true;
    }
    @Override
    public List<Player> getLi()
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        final AnalysisRating rat = new AnalysisRatingImpl(this.li);
        return rat.updateRating();
    }
}
