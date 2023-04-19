package test;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;
import gui.HomeImpl;
import gui.Home;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;
import scraping.Scraping;
import scraping.ScrapingImpl;

public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//Scraping sc=new ScrapingImpl();
		//ManageData md=new ManageDataImpl(sc.getStagioni().get(3));
		ManageData md=new ManageDataImpl("2022-2023");
		//System.out.print(sc.getStagioni().get(3));
		md.LoadData();
		List<Calciatore> li=md.getLi();
		ExtractData ex=new ExtractDataImpl(li);
		CalcoloRating ra=new CalcoloRatingImpl(li);
		ex.getLi().stream().sorted((c1,c2)->ra.getRating(c1).getX()-ra.getRating(c2).getX()).forEach(c -> System.out.println(c.getSquadra()+" "+ c.getNominativo()+"     \t RAT:"+ra.getRating(c).getX()+" \t A: "+ra.getRating(c).getY().getX()+" \t C: "+ra.getRating(c).getY().getY()+" \t D: "+ra.getRating(c).getY().getZ()+" \t Amm: "+ra.getRatingCartellino(c).getX()+" \t Esp: "+ra.getRatingCartellino(c).getY()));

		Home gui = new HomeImpl();
		gui.setVisible(true);
	}
}
