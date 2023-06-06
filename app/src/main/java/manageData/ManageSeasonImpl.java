package manageData;
import java.util.ArrayList;
import java.util.List;
import scraping.Scraping;
import scraping.ScrapingImpl;

/**
 * Implementation of the {@code ManageSeason} interface.
 * Manages the seasons data and updates it using web scraping.
 */
public class ManageSeasonImpl extends Thread implements ManageSeason {
    private final LogicsFile file = new LogicsFileImpl();
    private final Scraping s;
    private List<String> li = new ArrayList<>();
    private Boolean checkFlag = false;

    /**
     * Constructs a new instance of {@code ManageSeasonImpl}
     */
    public ManageSeasonImpl() {
        this.s = new ScrapingImpl();
    }

    @Override
    public List<String> getSeason() {
        li = file.loadSeason();
        return li;
    }

    @Override
    public void updateSeason() {
        this.start();
    }

    @Override
    public void run() {
        if (!s.readSeason()) {
            checkFlag = false;
        } else {
            li = s.getLiSeason();
            file.saveSeason(li);
            checkFlag = true;
        }
    }

    @Override
    public Boolean check() {
        return checkFlag;
    }
}