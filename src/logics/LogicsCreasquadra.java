package logics;

import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import manageData.ExtractData;

public interface LogicsCreasquadra {

	public Squadra getSquadra();
	
	public List<Modulo> getModuli();
	
	public void setModulo(Modulo mod);
	
	public Modulo getModulo();
	
	public int getNumDif();
	
	public int getNumCen();
	
	public int getNumAtt();
	
	//ritorna il campo ExtractDataImpl
	public ExtractData getEx();
	
	//ritorna n calciatori casuali del ruolo passato
	public List<Calciatore> getRandom(String ruolo, int n);
	
	public String getNamePlayer();
	
	public void setNameString(String name);
	
	public Calciatore getCalciatoreSelect();
	
	public void setCalciatoreSelect(Calciatore calciatore);
	
	public String getRuoloSelect();
	
	public void setRuoloSelect(String ruolo);
	
	public int getposSelect();
	
	public void setposSelect(int pos);
	
	public void addPlayerInTeam(Calciatore calciatore);
	
	public void clearTeam();
	
	// ritrona true se il Team é completo, false altrimenti 
	public boolean teamComplete();
	
	public String getNomeSquadra();
	
	public String getStemma();
	
	public List<Calciatore> getTitolari();
	
	public int getRating();
	
	public void setRating(int rating);
}
