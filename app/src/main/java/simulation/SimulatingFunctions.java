package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Team;

public interface SimulatingFunctions {

    public int golSubitiFanta(Team s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int autogolFanta(Team s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int rigoriParatiFanta(Team s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int differenzaRigoriFattiSbagliatiFanta(Team s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double votoDifFanta(Team s, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public double votoOffFanta(Team s, Map<String, Double> v)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    public int golFattiFanta(Team s)
    		throws FileNotFoundException, ClassNotFoundException, IOException;
}