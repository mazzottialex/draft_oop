package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.SquadraAvversaria;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SimulatingFunctionsImpl implements SimulatingFunctions {

//	private SquadraAvversaria sq;
//	private SquadraAvversaria sa;
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
	private static final double SUB_VOTE = 5;
	private static final double MOD_VOTE_DIF_D = 3;
	private static final double MOD_VOTE_DIF_C = 1;
	private static final double MOD_VOTE_DIF_A = 0.5;
	private static final double MOD_VOTE_OFF_D = 0.5;
	private static final double MOD_VOTE_OFF_C = 4;
	private static final double MOD_VOTE_OFF_A = 2.5;
	private static final double COST_SUB_PD = 14; // da aumentare di 1
	private static final double COST_SUB_PO = 5; // da aumentare di 2
	private static final double COST_DIV_PD_PO = 5;
	private static final double MINUTES = 90;
	private static final double COST_SUB_SM = 5;

	public SimulatingFunctionsImpl(/*SquadraAvversaria sq, SquadraAvversaria sa*/) {
//		super();
//		this.sq = sq;
//		this.sa = sa;
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

	public int golSubitiFanta(SquadraAvversaria s) throws FileNotFoundException, ClassNotFoundException, IOException {
		Calciatore portiere = new ExtractDataImpl(s.titolari).getListaByRuolo("P").get(0);
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

	public int autogolFanta(SquadraAvversaria s) throws FileNotFoundException, ClassNotFoundException, IOException {
		int ag = 0;
		for (int i = 0; i < golSubitiFanta(s); i++) {
			if (prob(0, 100) <= OWNGOAL_RATE) {
				ag++;
			}
		}
		return ag;
	}

	public int rigoriParatiFanta(SquadraAvversaria s)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		int r = 0;
		int rp = 0;
		double p = prob(0, 1);
		if (p <= PENALITY_RATE * PENALITY_RATE * PENALITY_RATE) {
			r = 3;
		} else if (p > PENALITY_RATE * PENALITY_RATE * PENALITY_RATE
				&& p <= (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
			r = 2;
		} else if (p > (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)
				&& p <= PENALITY_RATE + (PENALITY_RATE * PENALITY_RATE)
						+ (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
			r = 1;
		}
		for (int i = 0; i < r; i++) {
			p = prob(0, 1);
			if (p <= MISSED_PENALITIES_RATE) {
				rp++;
			}
		}
		return rp;
	}

	public static double catenaccioFanta(SquadraAvversaria s, Map<Calciatore, Double> votiDif)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(s.titolari);
		List<Calciatore> difensori = ed.getListaByRuolo("D");
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

	public static Map<String, Double> votoModFanta(SquadraAvversaria s, Map<Calciatore, Double> mapVoti)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		double pP = 0;
		double pD = 0;
		double pC = 0;
		double pA = 0;
		Map<String, Double> mapModVoti = new HashMap<>();
		for (Calciatore c : s.getTitolari()) {
			switch (c.getRuolo()) {
			case "P":
				pP += (mapVoti.get(c) - SUB_VOTE);
				break;
			case "D":
				pD += (mapVoti.get(c) - SUB_VOTE);
				break;
			case "C":
				pC += (mapVoti.get(c) - SUB_VOTE);
				break;
			case "A":
				pA += (mapVoti.get(c) - SUB_VOTE);
				break;
			}
		}
		mapModVoti.put("P", pP);
		mapModVoti.put("D", pD);
		mapModVoti.put("C", pC);
		mapModVoti.put("A", pA);
		return mapModVoti;
	}

	public double votoDifFanta(SquadraAvversaria s, Map<String, Double> v) throws FileNotFoundException, ClassNotFoundException, IOException {
		return (MOD_VOTE_DIF_D * (v.get("P") + v.get("D"))) + (MOD_VOTE_DIF_C * v.get("D"))
				+ (MOD_VOTE_DIF_A * v.get("A"));
	}

	public double votoOffFanta(SquadraAvversaria s, Map<String, Double> v) throws FileNotFoundException, ClassNotFoundException, IOException {
		return (MOD_VOTE_OFF_D * (v.get("D"))) + (MOD_VOTE_OFF_C * v.get("D")) + (MOD_VOTE_OFF_A * v.get("A"));
	}

	public double prestazioneDifensiva(SquadraAvversaria s)
			throws FileNotFoundException, ClassNotFoundException, IOException {
				return 0;
		//return (votoDifFanta(s) + new ExtractDataImpl(s.getTitolari()).getListaByRuolo("D").size() + catenaccioFanta(s)
		//		- golSubitiFanta(s) - COST_SUB_PD) / COST_DIV_PD_PO;
	}

	public int golFattiFanta(SquadraAvversaria s) throws FileNotFoundException, ClassNotFoundException, IOException {
		int gol = 0;
		for (Calciatore c : s.getTitolari()) {
			double probGol = c.getGol() / (c.getMinuti() / MINUTES);
			double p = prob(0, 1);
			if (p <= probGol * probGol * probGol) {
				gol += 3;
			} else if (p > probGol * probGol * probGol && p <= (probGol * probGol) + (probGol * probGol * probGol)) {
				gol += 2;
			} else if (p > (probGol * probGol) + (probGol * probGol * probGol)
					&& p <= probGol + (probGol * probGol) + (probGol * probGol * probGol)) {
				gol += 3;
			}
		}
		return gol;
	}

	public int differenzaRigoriFattiSbagliatiFanta(SquadraAvversaria s)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		int r = 0;
		int rs = 0;
		double p = prob(0, 1);
		if (p <= PENALITY_RATE * PENALITY_RATE * PENALITY_RATE) {
			r = 3;
		} else if (p > PENALITY_RATE * PENALITY_RATE * PENALITY_RATE
				&& p <= (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
			r = 2;
		} else if (p > (PENALITY_RATE * PENALITY_RATE) + (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)
				&& p <= PENALITY_RATE + (PENALITY_RATE * PENALITY_RATE)
						+ (PENALITY_RATE * PENALITY_RATE * PENALITY_RATE)) {
			r = 1;
		}
		for (int i = 0; i < r; i++) {
			p = prob(0, 1);
			if (p <= MISSED_PENALITIES_RATE) {
				rs++;
			}
		}
		return (r - rs) - rs;
	}

	public double capacitaRealizzativa(SquadraAvversaria s, SquadraAvversaria avv)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		double sm = superioritaManifesta(s, avv);
		if (sm > 0) {
			return cR(s, avv) + superioritaManifesta(s, avv);
		} else {
			return cR(s, avv);
		}
	}

	public double cR(SquadraAvversaria s, SquadraAvversaria avv)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		return golFattiFanta(s) + autogolFanta(avv) + differenzaRigoriFattiSbagliatiFanta(s);
	}

	public double prestazioneOffensiva(SquadraAvversaria s, SquadraAvversaria avv)
			throws FileNotFoundException, ClassNotFoundException, IOException {
				return 0;
		//return (votoOffFanta(s) + (capacitaRealizzativa(s, avv) / 2) - COST_SUB_PO) / COST_DIV_PD_PO;
	}

	public double superioritaManifesta(SquadraAvversaria s, SquadraAvversaria avv)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		return prestazioneOffensiva(s, avv) - 2 * prestazioneDifensiva(s) + (prestazioneOffensiva(s, avv)
				- 2 * prestazioneDifensiva(s) - 2 * prestazioneOffensiva(avv, s) - cR(avv, s) - COST_SUB_SM);
	}
	
	public int golSegnati(SquadraAvversaria s, SquadraAvversaria avv) throws FileNotFoundException, ClassNotFoundException, IOException {
		return (int) Math.min(capacitaRealizzativa(s, avv), (prestazioneOffensiva(s, avv) - prestazioneDifensiva(avv)));
	}
}
