package logics;

import java.util.List;

import data.Squadra;
import data.SquadraAvversaria;

public interface LogicsTorneo {

	public List<SquadraAvversaria> getListAvversari();
	
	public Squadra getMiaSquadra(); 
}
