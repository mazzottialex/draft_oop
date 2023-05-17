package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Calciatore;
import data.SquadraAvversaria;

public interface LogicsPartita {
	public void computeScore() throws FileNotFoundException, ClassNotFoundException, IOException;
	public int getGol1();
	public int getGol2();
	public void addScorer(SquadraAvversaria s);
	public void sanctions();
}
