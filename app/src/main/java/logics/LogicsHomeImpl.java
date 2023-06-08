package logics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Player;
import managedata.ManageData;
import managedata.ManageDataImpl;
import managedata.ManageSeason;
import managedata.ManageSeasonImpl;
import rating.AnalysisRating;
import rating.AnalysisRatingImpl;
import scraping.ScrapingImpl;

/**
 * This class represents the implementation of home logic operations.
 */
public final class LogicsHomeImpl implements LogicsHome {
    private List<Player> li;
    private String season;
    private Boolean online;
    private final ManageSeason ms;
    /**
     * 
     * Constructs a new instance of LogicsHomeImpl with the specified season and online status.
     *
     * @param season the season
     * @param online the online status
     */
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
    @Override
    public Boolean checkBrowser() {
    	return new ScrapingImpl().checkBrowsers();
    }
}
