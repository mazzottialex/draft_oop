package logics;

import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import manageData.ExtractData;

/** Models the logic of team creation
 * @author Davide Braccini
 *
 */
public interface LogicsCreasquadra {

	/**
	 * 
	 * @return the user's team 
	 */
	public Squadra getSquadra();
	
	/**
	 * 
	 * @return the module list
	 */
	public List<Modulo> getModuli();
	
	/**
	 * 
	 * @param mod the module you want to set
	 */
	public void setModulo(Modulo mod);
	
	/**
	 * 
	 * @return the module of the team
	 */
	public Modulo getModulo();
	
	/**
	 * 
	 * @return the number of defenders
	 */
	public int getNumDif();
	
	/**
	 * 
	 * @return the number of midfielders
	 */
	public int getNumCen();
	
	/**
	 * 
	 * @return the number of attackers
	 */
	public int getNumAtt();
	
	/**
	 * 
	 * @return the field ExtractData 
	 */
	public ExtractData getEx();
	
	/**
	 * 
	 * @param ruolo the position selected
	 * @param n the number of players
	 * @return a list of n players of the requested position
	 */
	public List<Calciatore> getRandom(String ruolo, int n);
	
	/**
	 * 
	 * @return the name of the player
	 */
	public String getNamePlayer();
	
	/**
	 * 
	 * @param name the name of the player
	 */
	public void setNameString(String name);
	
	/**
	 * 
	 * @return the palyer selected to get into team formation
	 */
	public Calciatore getCalciatoreSelect();
	
	/**
	 * 
	 * @param calciatore the palyer selected to get into team formation
	 */
	public void setCalciatoreSelect(Calciatore calciatore);
	
	/**
	 * 
	 * @return the position of the player selected
	 */
	public String getRuoloSelect();
	
	/**
	 * 
	 * @param ruolo the position of the player selected
	 */
	public void setRuoloSelect(String ruolo);
	
	/**
	 * 
	 * @return the position in the array of the player selected
	 */
	public int getposSelect();
	
	/**
	 * 
	 * @param pos the position in the array of the player selected
	 */
	public void setposSelect(int pos);
	
	
	/**
	 * 
	 * @param calciatore player to add to the team
	 */
	public void addPlayerInTeam(Calciatore calciatore);
	
	/**
	 * clear the team
	 */
	public void clearTeam();
	
	/**
	 * 
	 * @return true if the team is complete, false otherwise
	 */
	public boolean teamComplete();
	
	/**
	 * 
	 * @return the name of the team
	 */
	public String getNomeSquadra();
	
	/**
	 * 
	 * @return the arms
	 */
	public String getStemma();
	
	/**
	 * 
	 * @return the list of starting players
	 */
	public List<Calciatore> getTitolari();
	
	/**
	 * 
	 * @return the rating
	 */
	public int getRating();
	
	/**
	 * 
	 * @param rating the rating 
	 */
	public void setRating(int rating);
	
	/**
	 * 
	 * @return the list of player already used
	 */
	public List<Calciatore> getCalcUsciti();
}
