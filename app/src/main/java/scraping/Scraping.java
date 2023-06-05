package scraping;
import java.util.List;
import data.Calciatore;
public interface Scraping {
    //prima leggere con readTable poi getLista
    public List<Calciatore> getLiCalciatori();
    public List<String> getStagioni();
    public Boolean ReadTable(String stagione);
    public Boolean ReadTable();
    public Boolean readStagioni();
}
