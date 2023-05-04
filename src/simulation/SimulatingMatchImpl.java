package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.SquadraAvversaria;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SimulatingMatchImpl implements SimulatingMatch {

	private SquadraAvversaria sq1;
	private SquadraAvversaria sq2;
	private static final double OWNGOAL_RATE = 2.904040404040404; // percentuale di autogol su gol
	private static final double PENALITY_RATE = 0.2875; // rigori per partita
	private static final double MISSED_PENALITIES_RATE = 22.82608695652174; // percentuale rigori sbagliati
	private static final double COST_VOTE_P = 0.0746835443037975; // costante calcolo voto portiere
	private static final double COST_VOTE_D = 0.0721518987341772; // costante calcolo voto difensore
	private static final double COST_VOTE_C = 0.0753086419753086; // costante calcolo voto centrocampista
	private static final double COST_VOTE_A = 0.0729411764705882; // costante calcolo voto attaccante
	private static final int GOL_1 = 51;
	private static final int GOL_2 = 77;
	private static final int GOL_3 = 90;
	private static final int GOL_4 = 96;
	private static final int GOL_5 = 99;
	private static final double THRESHOLD_3_DIF = 6.5;
	private static final double THRESHOLD_4_5_DIF = 6;
	private static final double BONUS_4_DIF = 1;
	private static final double BONUS_5_DIF = 2;
	private static final double MODIFIER_VOTE = 5;
	private static final double MOD_VOTE_DIF_D = 3;
	private static final double MOD_VOTE_DIF_C = 1;
	private static final double MOD_VOTE_DIF_A = 0.5;
	private static final double MOD_PREST_DIF_SUB = 14;
	private static final double MOD_PREST_DIF_DIV = 5;

	public SimulatingMatchImpl(SquadraAvversaria sq1, SquadraAvversaria sq2) {
		super();
		this.sq1 = sq1;
		this.sq2 = sq2;
	}

	public static double prob(double min/* 0.8 */, double max/* 0.12 */) {
		Random random = new Random();
		return random.nextDouble(max - min) + min;
	}

	public static double votoFanta(Calciatore c) {
		double k = 0;
		switch (c.getRuolo()) {
		case "P":
			k = COST_VOTE_P;
			break;
		case "D":
			k = COST_VOTE_D;
			break;
		case "C":
			k = COST_VOTE_C;
			break;
		case "A":
			k = COST_VOTE_A;
			break;
		}
		return k * c.getRating().getX() * prob(0.8, 1.2);
	}

	public static Map<Calciatore, Double> votiFanta(List<Calciatore> titolari) {
		Map<Calciatore, Double> map = new LinkedHashMap<>();
		for (Calciatore c : titolari) {
			map.put(c, votoFanta(c));
		}
		return map;
	}

	public int golSubitiFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException {
		Calciatore portiere = new ExtractDataImpl(sa.titolari).getListaByRuolo("P").get(0);
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
			if (prob(0, 100) <= OWNGOAL_RATE) {
				ag++;
			}
		}
		return ag;
	}

	public int rigoriParatiFanta(SquadraAvversaria sa)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		int rs = 0;
		int rp = 0;
		do {
			double p = prob(0, 1);
			if (p <= PENALITY_RATE * PENALITY_RATE * PENALITY_RATE) {
				rs = 3;
			} else if (p > PENALITY_RATE * PENALITY_RATE * PENALITY_RATE
					&& p <= (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
				rs = 2;
			} else if (p > (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)
					&& p <= PENALITY_RATE + (PENALITY_RATE * PENALITY_RATE)
							+ (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
				rs = 1;
			}
			for (int i = 0; i < rs; i++) {
				p = prob(0, 1);
				if (p < MISSED_PENALITIES_RATE) {
					rp++;
				}
			}
		} while (rs - rp > golSubitiFanta(sa)); // volendo si può togliere anche se non corretto logicamente
		return rp;
	}

	public static double catenaccioFanta(SquadraAvversaria sa)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(sa.titolari);
		List<Calciatore> difensori = ed.getListaByRuolo("D");
		Map<Calciatore, Double> votiDif = votiFanta(ed.getLi());
		double count = 0;
		double totVoti = 0;
		switch (difensori.size()) {
		case 3:
			for (Calciatore c : difensori) {
				if (votiDif.get(c) >= THRESHOLD_3_DIF) {
					totVoti += votiDif.get(c);
				} else {
					totVoti = 0;
					break;
				}
			}
			if (totVoti != 0) {
				count = totVoti - (difensori.size() * THRESHOLD_3_DIF);
			}
			break;
		case 4:
			for (Calciatore c : difensori) {
				if (votiDif.get(c) >= THRESHOLD_4_5_DIF) {
					totVoti += votiDif.get(c);
				} else {
					totVoti = 0;
					break;
				}
			}
			if (totVoti != 0) {
				count = BONUS_4_DIF + totVoti - (difensori.size() * THRESHOLD_4_5_DIF);
			}
			break;
		case 5:
			for (Calciatore c : difensori) {
				if (votiDif.get(c) >= THRESHOLD_4_5_DIF) {
					totVoti += votiDif.get(c);
				} else {
					totVoti = 0;
					break;
				}
			}
			if (totVoti != 0) {
				count = BONUS_5_DIF + totVoti - (difensori.size() * THRESHOLD_4_5_DIF);
			}
			break;
		}
		return count;
	}

	public static double votoDifensivoFanta(List<Calciatore> titolari) {
		double pD = 0;
		double pC = 0;
		double pA = 0;
		Map<Calciatore, Double> map = votiFanta(titolari);
		for (Calciatore c : titolari) {
			switch (c.getRuolo()) {
			case "P":
				pD += (map.get(c) - MODIFIER_VOTE);
				break;
			case "D":
				pD += (map.get(c) - MODIFIER_VOTE);
				break;
			case "C":
				pC += (map.get(c) - MODIFIER_VOTE);
				break;
			case "A":
				pA += (map.get(c) - MODIFIER_VOTE);
				break;
			}
		}
		return (MOD_VOTE_DIF_D * pD) + (MOD_VOTE_DIF_C * pC) + (MOD_VOTE_DIF_A * pA);
	}

	public double prestazioneDifensiva(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException {
		return (votoDifensivoFanta(sa.getTitolari()) + new ExtractDataImpl(sa.getTitolari()).getListaByRuolo("D").size() + catenaccioFanta(sa) - golSubitiFanta(sa) - MOD_PREST_DIF_SUB) / MOD_PREST_DIF_DIV;
	}
}
