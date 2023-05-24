package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.Calciatore;
import data.SquadraAvversaria;

public interface LogicsPartita {
	public void scorers(int tempo) throws FileNotFoundException, ClassNotFoundException, IOException;
	public List<Integer> getMinGol(SquadraAvversaria s);
//	public List<Integer> getMinGol1();
//	public List<Integer> getMinGol2();
	public Calciatore addScorer(SquadraAvversaria s);
	public void sanctions();
}
