package test;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Calciatore;
import data.Modulo;
import data.SquadraAvversaria;
import gui.HomeImpl;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
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
//		Scraping sc = new ScrapingImpl();
//		ManageData md = new ManageDataImpl(sc.getStagioni().get(3));
		ManageData md = new ManageDataImpl("2022-2023");
//		System.out.print(sc.getStagioni().get(3));
		md.LoadData();
		List<Calciatore> li = md.getLi();
		CalcoloRating r = new CalcoloRatingImpl(li);
		li = r.updateRating();
		ExtractData ex = new ExtractDataImpl(li);
//		ex.getLi()
//			.stream()
//			.sorted((c1,c2)->ra.getRating(c1).getX()-ra.getRating(c2).getX())
//			.forEach(c -> System.out.println(c.getSquadra()+" "+ c.getNominativo()+"     \t RAT:"+ra.getRating(c).getX()+" \t A: "+ra.getRating(c).getY().getX()+" \t C: "+ra.getRating(c).getY().getY()+" \t D: "+ra.getRating(c).getY().getZ()+" \t Amm: "+ra.getRatingCartellino(c).getX()+" \t Esp: "+ra.getRatingCartellino(c).getY()));

//		Home gui = new HomeImpl();
//		gui.setVisible(true);

//		System.out.println(ex.getTitolariBySquadraByRuolo("NAP", "P", Modulo.M352));
//		System.out.println(ex.getCalciatoreBySquadra("NAP"));
		
		SquadraAvversaria sa = new SquadraAvversaria(0, "NAP", Modulo.M433, li);
//		System.out.println(sa.getNomeTitolari());
//		System.out.println(sa.getNomeRiserve());
		System.out.println(sa.getNomeCalciatori());
		
	}
}
