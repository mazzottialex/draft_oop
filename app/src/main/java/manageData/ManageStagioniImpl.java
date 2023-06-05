package manageData;
import java.util.ArrayList;
import java.util.List;
import scraping.Scraping;
import scraping.ScrapingImpl;
public class ManageStagioniImpl extends Thread implements ManageStagioni {
    private final LogicsFile file = new LogicsFileImpl();
    private final Scraping s;
    private List<String> li = new ArrayList<>();
    private Boolean checkFlag=false;
    public ManageStagioniImpl() {
        this.s = new ScrapingImpl();
    }
    public List<String> getStagioni() {
        return li = file.loadStagioni();
    }
    public void updateStagioni() {
        this.start();
    }
    public void run() {
    	if (!s.readStagioni()) {
            checkFlag=false;
    	}
    	else {
    		li = s.getStagioni();
            file.SaveStagioni(li);
            checkFlag = true;
    	}
    }
    public Boolean check() {
    	return checkFlag;
    }
}
