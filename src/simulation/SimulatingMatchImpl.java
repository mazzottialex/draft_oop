package simulation;

import java.util.Random;

import data.SquadraAvversaria;

public class SimulatingMatchImpl implements SimulatingMatch {
	
	private SquadraAvversaria sq1;
	private SquadraAvversaria sq2;
	public static final double AUTOGOL_RATE = 2.904040404040404; //percentuale di autogol su gol
	public static final double PENALITY_RATE = 0.2875; //rigori per partita
	public static final double MISSED_PENALITIES_RATE = 22.82608695652174; //percentuale rigori sbagliati
	public static final double SAVED_MISSED_PENALITIES_RATE = 71.42857142857143; //percentuale rigori parati tra quelli sbagliati
	
	public SimulatingMatchImpl(SquadraAvversaria sq1, SquadraAvversaria sq2) {
		super();
		this.sq1 = sq1;
		this.sq2 = sq2;
	}
	
	public double probVoto(double min/*0.8*/, double max/*0.12*/) {
		Random random = new Random();
		return random.nextDouble(max-min) + min;
	}
	
	public double probAutogol() {
		return 0;
	}
	
}
