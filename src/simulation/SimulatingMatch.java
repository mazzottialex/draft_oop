package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SimulatingMatch {
	public double prestazioneDifensiva(int squadra) throws FileNotFoundException, ClassNotFoundException, IOException;
	public double cR(int squadra);
}
