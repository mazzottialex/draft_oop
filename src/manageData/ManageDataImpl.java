package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageDataImpl implements ManageData{
	private List<Calciatore> li;
	private LogicsFile logFile;
	
	public ManageDataImpl() {
		li=new ArrayList<>();
		logFile=new LogicsFileImpl();
	}
	
	public List<Calciatore> getLi() {
		return li;
	}
	
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		li=logFile.LoadData();
	}
	public void UploadData() throws FileNotFoundException, ClassNotFoundException, IOException {
		Scraping scr=new ScrapingImpl(7);
		li=scr.getLista();
		logFile.SaveData(li);
	}
}
