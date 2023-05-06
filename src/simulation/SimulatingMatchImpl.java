package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Calciatore;
import data.SquadraAvversaria;

public class SimulatingMatchImpl implements SimulatingMatch {
	
	private SimulatingFunctions sf;
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	public Map<Calciatore, Double> voti1;
	public Map<Calciatore, Double> voti2;
	public int golSubiti1;
	public int golSubiti2;
	public int autogol1;
	public int autogol2;
	public int rigoriParati1;
	public int rigoriParati2;
	public double catenaccio1;
	public double catenaccio2;
	public Map<String, Double> votiMod1;
	public Map<String, Double> votiMod2;
	public double votoDif1;
	public double votoDif2;
	public int golFatti1;
	public int golFatti2;
	public int rigoriFatti1;
	public int rigoriFatti2;
	
	public SimulatingMatchImpl(SquadraAvversaria s1, SquadraAvversaria s2) throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		sf = new SimulatingFunctionsImpl();
		this.s1 = s1;
		this.s2 = s2;
		voti1 = SimulatingFunctionsImpl.votiFanta(this.s1.titolari);
		voti2 = SimulatingFunctionsImpl.votiFanta(this.s2.titolari);
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
		golFatti1 = sf.golFattiFanta(s1);
		golFatti2 = sf.golFattiFanta(s2);
		rigoriFatti1 = sf.differenzaRigoriFattiSbagliatiFanta(s1);
		rigoriFatti2 = sf.differenzaRigoriFattiSbagliatiFanta(s2);
	}
	
	//TODO prestazioneDifensiva
	
	//TODO capacitaRealizzativa
	
	//TODO prestazioneOffensiva
	
	//TODO superioritaManifesta
	
	//TODO risultatoFinale
}