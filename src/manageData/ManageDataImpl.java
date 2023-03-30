package manageData;

import java.util.List;
import java.util.Optional;

public class ManageDataImpl implements ManageData{

	private List<Calciatore> li;
	
	public ManageDataImpl() {
		Scraping sc = new ScrapingImpl();
		li=sc.getLista();
	}
	
	public Optional<Calciatore> getCalciatore(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Calciatore> getCalciatoreByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Calciatore> getListaByRuolo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
