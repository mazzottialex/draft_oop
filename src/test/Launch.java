package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import manageData.Calciatore;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.Scraping;
import manageData.ScrapingImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;

public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ExtractData ex=new ExtractDataImpl();
		CalcoloRating ra=new CalcoloRatingImpl();
		//ex.getLi().stream().sorted((c1,c2)->ra.getRating(c1).getX()-ra.getRating(c2).getX()).forEach(c -> System.out.println(c.getSquadra()+" "+ c.getNominativo()+"     \t RAT:"+ra.getRating(c).getX()+" \t A: "+ra.getRating(c).getY().getX()+" \t C: "+ra.getRating(c).getY().getY()+" \t D: "+ra.getRating(c).getY().getZ()));
		Scraping sc=new ScrapingImpl();
		System.out.println(sc.getStagioni());
	}
}
