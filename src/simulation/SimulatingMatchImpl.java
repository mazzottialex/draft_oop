package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import data.Calciatore;
import data.SquadraAvversaria;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SimulatingMatchImpl implements SimulatingMatch {
	
	private SquadraAvversaria sq1;
	private SquadraAvversaria sq2;
	public static final double AUTOGOL_RATE = 2.904040404040404; //percentuale di autogol su gol
	public static final double PENALITY_RATE = 0.2875; //rigori per partita
	public static final double MISSED_PENALITIES_RATE = 22.82608695652174; //percentuale rigori sbagliati
	public static final double SAVED_MISSED_PENALITIES_RATE = 71.42857142857143; //percentuale rigori parati tra quelli sbagliati
	public static final int GOL_1 = 51;
	public static final int GOL_2 = 77;
	public static final int GOL_3 = 90;
	public static final int GOL_4 = 96;
	public static final int GOL_5 = 99;
	
	public SimulatingMatchImpl(SquadraAvversaria sq1, SquadraAvversaria sq2) {
		super();
		this.sq1 = sq1;
		this.sq2 = sq2;
	}
	
	public double prob(double min/*0.8*/, double max/*0.12*/) {
		Random random = new Random();
		return random.nextDouble(max-min) + min;
	}
	
	public int golSubitiFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData edTitSq1 = new ExtractDataImpl(sq1.titolari);
		Calciatore portiere = edTitSq1.getListaByRuolo("P").get(0);
		double probCleanSheet = portiere.getCleanSheet() / portiere.getPg();
		if (prob(0, 1) <= probCleanSheet) {
			return 0;
		} else {
			int gol = 1;
			int p = (int) (1 + (prob(0, 1) * 100));
			if (p > GOL_1 && p <= GOL_2) {
				gol = 2;
			} else if (p > GOL_2 && p <= GOL_3) {
				gol = 3;
			} else if (p > GOL_3 && p <= GOL_4) {
				gol = 4;
			} else if (p > GOL_4 && p <= GOL_5) {
				gol = 5;
			} else if (p > GOL_5) {
				gol = 6;
			}
			return gol;
		}
	}
	
	public int autogolFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException {
		int ag = 0;
		for (int i = 0; i < golSubitiFanta(sa); i++) {
			if (prob(0, 100) <= AUTOGOL_RATE) {
				ag++;
			}
		}
		return ag;
	}
}
