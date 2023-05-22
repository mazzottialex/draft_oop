package test;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import data.Calciatore;
import data.Modulo;

import data.SquadraAvversaria;
import gui.Home;
import gui.Partita;
import gui.Partita;
import gui.Start;
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
import simulation.SimulatingFunctions;
import simulation.SimulatingFunctionsImpl;
import simulation.SimulatingMatchImpl;

public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		Boolean flagGui = true;
		Boolean marto = true;
		
		if(flagGui) {
			if (!marto) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Start frame = new Start();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				ManageData md = new ManageDataImpl("2022-2023");
				md.LoadData();
				List<Calciatore> li = md.getLi();
				CalcoloRating cr = new CalcoloRatingImpl(li);
				li = cr.updateRating();
				SquadraAvversaria nap = new SquadraAvversaria(0, "NAP", Modulo.M442, li);				
				SquadraAvversaria laz = new SquadraAvversaria(0, "LAZ", Modulo.M442, li);

				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                try {
							new Partita(nap, laz).createAndShowGUI();
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
		            }
		        });
			}
		} else {
//			Scraping sc = new ScrapingImpl();
//			ManageData md = new ManageDataImpl(sc.getStagioni().get(3));
			ManageData md = new ManageDataImpl("2022-2023");
//			System.out.print(sc.getStagioni().get(3));
			md.LoadData();
			List<Calciatore> li = md.getLi();
			CalcoloRating r = new CalcoloRatingImpl(li);
			li = r.updateRating();
			ExtractData ex = new ExtractDataImpl(li);
//			ex.getLi()
//				.stream()
//				.sorted((c1,c2)->ra.getRating(c1).getX()-ra.getRating(c2).getX())
//				.forEach(c -> System.out.println(c.getSquadra()+" "+ c.getNominativo()+"     \t RAT:"+ra.getRating(c).getX()+" \t A: "+ra.getRating(c).getY().getX()+" \t C: "+ra.getRating(c).getY().getY()+" \t D: "+ra.getRating(c).getY().getZ()+" \t Amm: "+ra.getRatingCartellino(c).getX()+" \t Esp: "+ra.getRatingCartellino(c).getY()));

			//System.out.println(ex.getTitolariBySquadraByRuolo("NAP", "P", Modulo.M352));
//			System.out.println(ex.getCalciatoreBySquadra("NAP"));
			
			//SquadraAvversaria nap = new SquadraAvversaria(0, "NAP", Modulo.M433, li);
//			System.out.println(nap.getNomeTitolari());
//			System.out.println(nap.getNomeRiserve());

			//System.out.println(sa.getNomeCalciatori());
			
//			SquadraAvversaria udi = new SquadraAvversaria(0, "UDI", Modulo.M442, li);
////			System.out.println(udi.getNomeTitolari());
////			System.out.println(udi.getTitolari());
//			System.out.println(udi.getTsr());
			
//			SquadraAvversaria sal = new SquadraAvversaria(0, "SAL", Modulo.M442, li);
//			System.out.println(sal.getTsr());
//			
//			SquadraAvversaria cre = new SquadraAvversaria(0, "CRE", Modulo.M442, li);
//			System.out.println(cre.getTsr());
//			
//			SquadraAvversaria sam = new SquadraAvversaria(0, "SAM", Modulo.M442, li);
//			System.out.println(sam.getTsr());
//			

			SquadraAvversaria nap = new SquadraAvversaria(0, "NAP", Modulo.M442, li);
			//System.out.println(nap.getTsr());
			
			SquadraAvversaria laz = new SquadraAvversaria(0, "LAZ", Modulo.M442, li);
			//System.out.println(laz.getTsr());
			
//			System.out.println(new SimulatingFunctionsImpl().golSegnati(nap, laz));
//			System.out.println(new SimulatingFunctionsImpl().golSegnati(laz, nap));
			
//			System.out.println(new SimulatingMatchImpl(nap, laz).titS1.size());
			
			System.out.println(new SimulatingMatchImpl(nap, laz).risultato());

			
//			System.out.println(ex.getListaByRuolo("A")
//									.stream()
//									.map(c -> c.toVector())
//									.toList());
			
//			ex.getLi()
//			.stream()
//			.sorted((c1, c2) -> /*r.getRatingCartellino(c1).getX() - r.getRatingCartellino(c2).getX()*/ c1.getPg() - c2.getPg())
//			.forEach(c -> System.out.println(c.getNominativo() + "\t Amm: " + r.getRatingCartellino(c).getX() + "\t Esp: " + r.getRatingCartellino(c).getY()));
			
//			int rating = 83;
//			double rand = 0.8;
//			System.out.println(((6.1 * rating) / 81) * rand);
			
//			Random random = new Random();
//			double min = 0.8; // numero minimo
//			double max = 1.2; // numero massimo
//			System.out.println(random.nextDouble((max-min)) + min);
			
//			System.out.println(ex.getLi()
//				.stream()
//				.map(c -> c.getGol())
//				.reduce((m, n) -> m + n));

		}

			//System.out.println(sa.getNomeCalciatori());
	}
}

