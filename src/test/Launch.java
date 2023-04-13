package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import manageData.Calciatore;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;
import manageData.Scraping;
import manageData.ScrapingImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;

public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ManageData md=new ManageDataImpl("2022-2023");
		md.LoadData();
		List<Calciatore> li=md.getLi();
		ExtractData ex=new ExtractDataImpl(li);
		CalcoloRating ra=new CalcoloRatingImpl(li);
		ex.getLi().stream().sorted((c1,c2)->ra.getRating(c1).getX()-ra.getRating(c2).getX()).forEach(c -> System.out.println(c.getSquadra()+" "+ c.getNominativo()+"     \t RAT:"+ra.getRating(c).getX()+" \t A: "+ra.getRating(c).getY().getX()+" \t C: "+ra.getRating(c).getY().getY()+" \t D: "+ra.getRating(c).getY().getZ()));
		//Scraping sc=new ScrapingImpl();
		//System.out.println(sc.getStagioni());
	}
}
