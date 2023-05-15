package logics;

import java.util.*;

import data.Calciatore;
import data.Modulo;
import manageData.ExtractData;

public interface LogicsCreasquadra {

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
	
}
