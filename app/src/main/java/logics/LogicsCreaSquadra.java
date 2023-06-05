package logics;

import java.io.Serializable;
//import java.util.*;
import java.util.List;
import data.Calciatore;
import data.Modulo;
import data.Squadra;
import manageData.ExtractData;

/** Models the logic of team creation.
 * @author Davide Braccini
 *
 */
public interface LogicsCreaSquadra extends Serializable {

	/**
	 * 
	 * @return the user's team 
	 */
	Squadra getSquadra();
	
	/**
	 * 
	 * @return the module list
	 */
	List<Modulo> getModuli();
	
	/**
	 * 
	 * @param mod the module you want to set
	 */
	void setModulo(Modulo mod);
	
	/**
	 * 
	 * @return the module of the team
	 */
	Modulo getModulo();
	
	/**
	 * 
	 * @return the number of defenders
	 */
	int getNumDif();
	
	/**
	 * 
	 * @return the number of midfielders
	 */
	int getNumCen();
	
	/**
	 * 
	 * @return the number of attackers
	 */
	int getNumAtt();
	
	/**
	 * 
	 * @return the field ExtractData 
	 */
	ExtractData getEx();
	
	/**
	 * 
	 * @param ruolo the position selected
	 * @param n the number of players
	 * @return a list of n players of the requested position
	 */
	List<Calciatore> getRandom(String ruolo, int n);
	
	/**
	 * 
	 * @return the name of the player
	 */
	String getNamePlayer();
	
	/**
	 * 
	 * @param name the name of the player
	 */
	void setNameString(String name);
	
	/**
	 * 
	 * @return the palyer selected to get into team formation
	 */
	Calciatore getCalciatoreSelect();
	
	/**
	 * 
	 * @param calciatore the palyer selected to get into team formation
	 */
	void setCalciatoreSelect(Calciatore calciatore);
	
	/**
	 * 
	 * @return the position of the player selected
	 */
	String getRuoloSelect();
	
	/**
	 * 
	 * @param ruolo the position of the player selected
	 */
	void setRuoloSelect(String ruolo);
	
	/**
	 * 
	 * @return the position in the array of the player selected
	 */
	int getposSelect();
	
	/**
	 * 
	 * @param pos the position in the array of the player selected
	 */
	void setposSelect(int pos);
	
	
	/**
	 * 
	 * @param calciatore player to add to the team
	 */
	void addPlayerInTeam(Calciatore calciatore);
	
	/**
	 * clear the team.
	 */
	void clearTeam();
	
	/**
	 * 
	 * @return true if the team is complete, false otherwise
	 */
	boolean teamComplete();
	
	/**
	 * 
	 * @return the name of the team
	 */
	String getNomeSquadra();
	
	/**
	 * 
	 * @return the arms
	 */
	String getStemma();
	
	/**
	 * 
	 * @return the list of starting players
	 */
	List<Calciatore> getTitolari();
	
	/**
	 * 
	 * @return the rating
	 */
	int getRating();
	
	/**
	 * 
	 * @param rating the rating 
	 */
	void setRating(int rating);
	
	/**
	 * 
	 * @return the list of player already used
	 */
	List<Calciatore> getCalcUsciti();
	
	/**
	 * 
	 * @param list the list of player already used
	 */
	void addCalcUsciti(List<Calciatore> list);
	
	/**
	 * 
	 * @return true if the module was selected
	 */
	boolean getClickModulo();
	
	/**
	 * 
	 * @param click true if the module was selected
	 */
	void setClickModulo(boolean click);
	
}
