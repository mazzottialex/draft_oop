package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;

import data.SquadraAvversaria;

public interface SimulatingMatch {
	public int golSubitiFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException;
	public int autogolFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException;
	public int rigoriParatiFanta(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException;
	public double prestazioneDifensiva(SquadraAvversaria sa) throws FileNotFoundException, ClassNotFoundException, IOException;
}
