package manageData;
import java.util.ArrayList;
import java.util.List;
import data.Player;
import scraping.Scraping;
import scraping.ScrapingImpl;

/**
 * Implementation of the ManageData interface
 * Manages the data of players for a specific season
 */
public class ManageDataImpl implements ManageData {
    private List<Player> li;
    private final LogicsFile logFile;
    private final String stagione;
    
    /**
     * Constructs a new instance of ManageDataImpl with the specified season
     *
     * @param stagione The season for which to manage the data.
     */
    public ManageDataImpl(final String stagione) {
        li = new ArrayList<>();
        logFile = new LogicsFileImpl();
        this.stagione = stagione;
    }
    
    @Override
    public List<Player> getLi() {
        return li;
    }
    
    @Override
    public void loadData() throws Exception{
        li = logFile.loadData(stagione);
    }
    
    @Override
    public Boolean downloadData() {
        final Scraping scr = new ScrapingImpl();
        if (!scr.readTable(this.stagione))
            return false;
        li = scr.getLiPlayer();
        logFile.saveData(li, stagione);
        return true;
    }
}
