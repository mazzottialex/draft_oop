package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.Calciatore;
import data.Squadra;
import data.Squadra;
import manageData.ExtractDataImpl;

public class SimulatingMatchImpl implements SimulatingMatch {

	private SimulatingFunctions sf;
	private Squadra s1;
	private Squadra s2;
	private Map<Calciatore, Double> voti1;
	private Map<Calciatore, Double> voti2;
	private int golSubiti1;
	private int golSubiti2;
	private int autogol1;
	private int autogol2;
	private int rigoriParati1;
	private int rigoriParati2;
	private double catenaccio1;
	private double catenaccio2;
	private Map<String, Double> votiMod1;
	private Map<String, Double> votiMod2;
	private double votoDif1;
	private double votoDif2;
	private double votoOff1;
	private double votoOff2;
	private int golFatti1;
	private int golFatti2;
	private int rigoriFatti1;
	private int rigoriFatti2;
	private static final double COST_SUB_DIFF = 14; // da aumentare di 1
	private static final double COST_SUB_OFF = 5; // da aumentare di 2
	private static final double COST_DIV_DIFF_OFF_CR = 5;
	private static final double COST_SUB_SM = 5;
	private static final int SQUADRA1 = 1;
	private static final int SQUADRA2 = 2;
	private static final int MINUTES_REG = 90;
	private final static int MINUTES_SUPPL = 120;

	public SimulatingMatchImpl(Squadra s1, Squadra s2)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		sf = new SimulatingFunctionsImpl();
		this.s1 = s1;
		this.s2 = s2;
		voti1 = SimulatingFunctionsImpl.votiFanta(this.s1.getTitolari());
		voti2 = SimulatingFunctionsImpl.votiFanta(this.s2.getTitolari());
		golSubiti1 = sf.golSubitiFanta(this.s1);
		golSubiti2 = sf.golSubitiFanta(this.s2);
		autogol1 = sf.autogolFanta(this.s1);
		autogol2 = sf.autogolFanta(this.s2);
		rigoriParati1 = sf.rigoriParatiFanta(this.s1);
		rigoriParati2 = sf.rigoriParatiFanta(this.s2);
		catenaccio1 = SimulatingFunctionsImpl.catenaccioFanta(this.s1, voti1);
		catenaccio2 = SimulatingFunctionsImpl.catenaccioFanta(this.s2, voti2);
		votiMod1 = SimulatingFunctionsImpl.votoModFanta(this.s1, voti1);
		votiMod2 = SimulatingFunctionsImpl.votoModFanta(this.s2, voti2);
		votoDif1 = sf.votoDifFanta(s1, votiMod1);
		votoDif2 = sf.votoDifFanta(s2, votiMod2);
		votoOff1 = sf.votoOffFanta(s1, votiMod1);
		votoOff2 = sf.votoOffFanta(s2, votiMod2);
		golFatti1 = sf.golFattiFanta(s1);
		golFatti2 = sf.golFattiFanta(s2);
		rigoriFatti1 = sf.differenzaRigoriFattiSbagliatiFanta(s1);
		rigoriFatti2 = sf.differenzaRigoriFattiSbagliatiFanta(s2);
	}

	// prestazioneDifensiva, se squadra == 1 -> s1; se squadra == 2 -> s2
	@Override
	public double prestazioneDifensiva(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException {
		double pd = 0;
		switch (squadra) {
		case SQUADRA1:
			pd = (votoDif1 + new ExtractDataImpl(s1.getTitolari()).getListaByRuolo("D").size() + catenaccio1
					- 2 * golSubiti1 - 2 * autogol1 + 3 * rigoriParati1 - COST_SUB_DIFF) / COST_DIV_DIFF_OFF_CR;
			break;
		case SQUADRA2:
			pd = (votoDif2 + new ExtractDataImpl(s2.getTitolari()).getListaByRuolo("D").size() + catenaccio2
					- 2 * golSubiti2 - 2 * autogol2 + 3 * rigoriParati2 - COST_SUB_DIFF) / COST_DIV_DIFF_OFF_CR;
			break;

		default:
			break;
		}
		return pd;
	}

	// capacitaRealizzativa
	@Override
	public double cR(int squadra) {
		double cr = 0;
		switch (squadra) {
		case SQUADRA1:
			cr = golFatti1 + autogol2 + rigoriFatti1;
			break;
		case SQUADRA2:
			cr = golFatti2 + autogol1 + rigoriFatti2;
			break;

		default:
			break;
		}
		return cr;
	}

	@Override
	public double capacitaRealizzativa(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException {
//		double cr = 0;
//		double sm = superioritaManifesta(squadra);
//		if (sm > 0) {
//			cr = cR(squadra) + (superioritaManifesta(squadra) / COST_DIV_DIFF_OFF_CR);
//		} else {
//			cr = cR(squadra);
//		}
//		return cr;
		
		return cR(squadra);
	}

	// prestazioneOffensiva
	@Override
	public double prestazioneOffensiva(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException {
		double po = 0;
		switch (squadra) {
		case SQUADRA1:
			po = (votoOff1 + (capacitaRealizzativa(squadra) / 2) - COST_SUB_OFF) / COST_DIV_DIFF_OFF_CR;
			break;
		case SQUADRA2:
			po = (votoOff2 + (capacitaRealizzativa(squadra) / 2) - COST_SUB_OFF) / COST_DIV_DIFF_OFF_CR;
			break;

		default:
			break;
		}
		return po;
	}

	// superioritaManifesta
//	@Override
//	public double superioritaManifesta(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException {
//		double sm = 0;
//		int squadraAvv;
//		switch (squadra) {
//		case SQUADRA1:
//			squadraAvv = SQUADRA2;
//			sm = 2 * prestazioneOffensiva(squadra) - 4 * prestazioneDifensiva(squadra)
//					+ -2 * prestazioneOffensiva(squadraAvv) - cR(squadraAvv) - COST_SUB_SM;
//			break;
//		case SQUADRA2:
//			squadraAvv = SQUADRA1;
//			sm = 2 * prestazioneOffensiva(squadra) - 4 * prestazioneDifensiva(squadra)
//					+ -2 * prestazioneOffensiva(squadraAvv) - cR(squadraAvv) - COST_SUB_SM;
//			break;
//
//		default:
//			break;
//		}
//		return sm;
//	}

	// risultatoFinale
	@Override
	public Map<String, Integer> risultato()
			throws FileNotFoundException, ClassNotFoundException, IOException {
		int sq1 = (int) Math.round(Math.min(capacitaRealizzativa(SQUADRA1),
				(prestazioneOffensiva(SQUADRA1) - prestazioneDifensiva(SQUADRA2))));
		int sq2 = (int) Math.round(Math.min(capacitaRealizzativa(SQUADRA2),
				(prestazioneOffensiva(SQUADRA2) - prestazioneDifensiva(SQUADRA1))));
		Map<String, Integer> map = new HashMap<>();
		map.put(this.s1.getNomeSquadra(), sq1 >= 0 ? sq1 : 0);
		map.put(this.s2.getNomeSquadra(), sq2 >= 0 ? sq2 : 0);
		return map;
	}

	@Override
	public Map<String, Integer> risultatoSuppl() throws FileNotFoundException, ClassNotFoundException, IOException {
		return risultatoSub(MINUTES_REG);
	}

	@Override
	public Map<String, Integer> risultatoSub(int minuto)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		Map<String, Integer> map = new HashMap<>();
		if (minuto < MINUTES_REG) {
			map.put(s1.getNomeSquadra(),
					(int) (risultato().get(s1.getNomeSquadra()) * (double) ((MINUTES_REG - minuto) / MINUTES_REG)));
			map.put(s2.getNomeSquadra(), (int) (risultato().get(s1.getNomeSquadra())
					* (double) ((MINUTES_REG - minuto) / MINUTES_REG)));
		} else {
			map.put(s1.getNomeSquadra(),
					(int) (risultato().get(s1.getNomeSquadra()) * (double) ((MINUTES_SUPPL - MINUTES_REG - minuto) / MINUTES_REG)));
			map.put(s2.getNomeSquadra(), (int) (risultato().get(s1.getNomeSquadra())
					* (double) ((MINUTES_SUPPL - MINUTES_REG - minuto) / MINUTES_REG)));
		}
		return map;
	}
}