package managedata;
import java.util.ArrayList;
import java.util.List;
import data.Player;
import scraping.Scraping;
import scraping.ScrapingImpl;

/**
 * Implementation of the {@code ManageData} interface.
 * Manages the data of players for a specific season.
 */
public final class ManageDataImpl implements ManageData {

    private List<Player> li;
    private final LogicsFile logFile;
    private final String season;

    /**
     * Constructs a new instance of {@code ManageDataImpl} with the specified season.
     *
     * @param season The season for which to manage the data.
     */
    public ManageDataImpl(final String season) {
        li = new ArrayList<>();
        logFile = new LogicsFileImpl();
        this.season = season;
    }

    @Override
    public List<Player> getLi() {
        return li;
    }

    @Override
    public void loadData() throws Exception {
        li = logFile.loadData(season);
    }

    @Override
    public Boolean downloadData() {
        final Scraping scr = new ScrapingImpl();
        if (!scr.readTable(this.season)) {
            return false;
        }
        li = scr.getLiPlayer();
        logFile.saveData(li, season);
        return true;
    }
}