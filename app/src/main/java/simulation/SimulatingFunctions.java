package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Squadra;
import data.SquadraAvversaria;

public interface SimulatingFunctions {

	public int golSubitiFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException;

	public int autogolFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException;

	public int rigoriParatiFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException;

//	public double prestazioneDifensiva(SquadraAvversaria s)
//			throws FileNotFoundException, ClassNotFoundException, IOException;

	public int differenzaRigoriFattiSbagliatiFanta(Squadra s)
			throws FileNotFoundException, ClassNotFoundException, IOException;

//	public double capacitaRealizzativa(SquadraAvversaria s, SquadraAvversaria avv)
//			throws FileNotFoundException, ClassNotFoundException, IOException;

	public double votoDifFanta(Squadra s, Map<String, Double> v)
			throws FileNotFoundException, ClassNotFoundException, IOException;

	public double votoOffFanta(Squadra s, Map<String, Double> v)
			throws FileNotFoundException, ClassNotFoundException, IOException;

	public int golFattiFanta(Squadra s) throws FileNotFoundException, ClassNotFoundException, IOException;

//	public double cR(SquadraAvversaria s, SquadraAvversaria avv)
//			throws FileNotFoundException, ClassNotFoundException, IOException;
//
//	public double prestazioneOffensiva(SquadraAvversaria s, SquadraAvversaria avv)
//			throws FileNotFoundException, ClassNotFoundException, IOException;
//
//	public double superioritaManifesta(SquadraAvversaria s, SquadraAvversaria avv)
//			throws FileNotFoundException, ClassNotFoundException, IOException;
//
//	public int golSegnati(SquadraAvversaria s, SquadraAvversaria avv)
//			throws FileNotFoundException, ClassNotFoundException, IOException;
}