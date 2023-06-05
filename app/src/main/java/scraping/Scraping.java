package scraping;
import java.util.List;
import data.Player;
public interface Scraping {
    //prima leggere con readTable poi getLista
    public List<Player> getLiCalciatori();
    public List<String> getStagioni();
    public Boolean ReadTable(String stagione);
    public Boolean ReadTable();
    public Boolean readStagioni();
}
