package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.Squadra;
import data.SquadraAvversaria;

public interface LogicsTorneo {

	public List<SquadraAvversaria> getListAvversari();
	
	public void setListAvversari(List<SquadraAvversaria> list);
	
	public Squadra getMiaSquadra(); 
	
	public int getNumSquadre();
	
	public void setNumSquadre(int numSquadre);
	
	public void simulaMatch() throws FileNotFoundException, ClassNotFoundException, IOException;
	
	public Map<String,Integer> getRisultati();
	
	public boolean getEliminated();
	
	public void setEliminated(boolean eliminated);
	
	public SquadraAvversaria getSquadraAvv();
	
	public void setSquadraAvv(SquadraAvversaria squadra);
	
	public Map<String,Integer> getRisMatch();
	
	public String getWinner();
}
