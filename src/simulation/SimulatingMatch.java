package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface SimulatingMatch {
	public double prestazioneDifensiva(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException;

	public double cR(int squadra);

	public double capacitaRealizzativa(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException;

//	public double superioritaManifesta(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException;

	public double prestazioneOffensiva(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException;

	public Map<String, Integer> risultato()
			throws FileNotFoundException, ClassNotFoundException, IOException;
}