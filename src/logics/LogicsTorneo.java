package logics;

import java.util.List;

import data.Squadra;
import data.SquadraAvversaria;

public interface LogicsTorneo {

	public List<SquadraAvversaria> getListAvversari();
	
	public Squadra getMiaSquadra(); 
	
	public int getNumSquadre();
	
	public void setNumSquadre(int numSquadre);
}
