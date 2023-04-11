package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import utils.Pair;

public class ScrapingImpl implements Scraping{
	private List<Calciatore> li=new ArrayList<>();
	private final int nThread;
	
	public ScrapingImpl(int nThread) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.nThread=nThread;
		ManageThreads(); //restituisce nella li tutti i calciatori
	}
	
	public ScrapingImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		this.nThread=7;
		ManageThreads(); //restituisce nella li tutti i calciatori
	}
	
	private void ManageThreads() {
		List<Pair<RunnableScraping, Thread>> liThr=new ArrayList<>();
		
		for(int i=0; i<nThread;i++) {
			RunnableScraping runnable=new RunnableScraping(i, nThread);
			Thread thr=new Thread(runnable);
			liThr.add(new Pair<>(runnable,thr));
			thr.start();
		}
		
		liThr.forEach(el-> {
			try {
				el.getY().join();
				li.addAll(el.getX().getLi());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	public List<Calciatore> getLista() {
		return li;
	}
}
