package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class LogicsArchivioImpl implements LogicsArchivio {

	
	public LogicsArchivioImpl() {
		
	}

	@Override
	public List<Calciatore> liOrdinata(List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ex =new ExtractDataImpl(li);
		li=ex.getListOrdered(c->-c.getRating().getX());
		return li;
	}
	

}
