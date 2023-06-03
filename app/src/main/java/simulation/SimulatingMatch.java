package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Squadra;

public interface SimulatingMatch {
    public double prestazioneDifensiva(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double capacitaRealizzativa(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double prestazioneOffensiva(int squadra)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public Map<Squadra, Integer> risultato()
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public Map<Squadra, Integer> risultatoSuppl()
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public Map<Squadra, Integer> risultatoSub(int minuto)
    		throws FileNotFoundException, ClassNotFoundException, IOException;
}