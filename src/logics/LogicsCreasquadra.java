package logics;

import java.util.*;

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
	
}
