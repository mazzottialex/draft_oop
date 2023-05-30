package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.Calciatore;
import data.Squadra;
import data.SquadraAvversaria;

public interface LogicsPartita {
	public void scorers(int tempo) throws FileNotFoundException, ClassNotFoundException, IOException;
	public List<Integer> getMinGol(Squadra s);
	public Calciatore addScorer(Squadra s);
	public void sanctions();
}
