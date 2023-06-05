package manageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Calciatore;
import scraping.Scraping;
import scraping.ScrapingImpl;
public class ManageDataImpl implements ManageData {
    private List<Calciatore> li;
    private final LogicsFile logFile;
    private final String stagione;
    public ManageDataImpl(final String stagione) {
        li = new ArrayList<>();
        logFile = new LogicsFileImpl();
        this.stagione = stagione;
    }
    public List<Calciatore> getLi() {
        return li;
    }
    public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException {
        li = logFile.LoadData(stagione);
    }
    public Boolean DownloadData() throws FileNotFoundException, ClassNotFoundException, IOException {
        final Scraping scr = new ScrapingImpl();
        if (!scr.ReadTable(this.stagione))
            return false;
        li = scr.getLiCalciatori();
        logFile.SaveData(li, stagione);
        return true;
    }
}
