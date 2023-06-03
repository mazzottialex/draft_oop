package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Squadra;

public interface SimulatingFunctions {

    public int golSubitiFanta(Squadra s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int autogolFanta(Squadra s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int rigoriParatiFanta(Squadra s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int differenzaRigoriFattiSbagliatiFanta(Squadra s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double votoDifFanta(Squadra s, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double votoOffFanta(Squadra s, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int golFattiFanta(Squadra s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;
}