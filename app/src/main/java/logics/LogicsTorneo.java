package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.Squadra;
//import data.SquadraAvversaria;

/**
 * The interface that models the logic of Torneo.
 * @author Davide Braccini
 *
 */
public interface LogicsTorneo {

	/**
	 * 
	 * @return the list of opposing teams
	 */
	List<Squadra> getListAvversari();
	
	/**
	 * 
	 * @param list the list of opposing teams
	 */
	void setListAvversari(List<Squadra> list);
	
	/**
	 * 
	 * @return the user's team
	 */
	Squadra getMiaSquadra(); 
	
	/**
	 * 
	 * @return the number of teams
	 */
	int getNumSquadre();
	
	/**
	 * 
	 * @param numSquadre the number of teams
	 */
	void setNumSquadre(int numSquadre);
	
	/**
	 * Simulate the matches of the turnament.
	 * @throws FileNotFoundException if..
	 * @throws ClassNotFoundException if..
	 * @throws IOException if..
	 */
	void simulaMatch() throws FileNotFoundException, ClassNotFoundException, IOException;
	
	/**
	 * 
	 * @return the map with the results
	 */
	Map<String, Integer> getRisultati();
	
	/**
	 * 
	 * @return true if the user's team is eliminated
	 */
	boolean getEliminated();
	
	/**
	 * 
	 * @param eliminated true if the user's team is eliminated
	 */
	void setEliminated(boolean eliminated);
	
	/**
	 * 
	 * @return the team against the user's team
	 */
	Squadra getSquadraAvv();
	
	/**
	 * 
	 * @param squadra the team against the user's team
	 */
	void setSquadraAvv(Squadra squadra);
	
	/**
	 * 
	 * @return the result of the match that is played when the user team is eliminated
	 */
	Map<String, Integer> getRisMatch();
	
	/**
	 * Clear RisMatch.
	 */
	void clearRisMatch();
	
	/**
	 * 
	 * @return the winner of the turnament
	 */
	String getWinner();
	
	/**
	 * 
	 * @return true if the user's team was eliminated this round
	 */
	boolean getElimThisTurn();
	
	/**
	 * 
	 * @param b true if the user's team was eliminated this round
	 */
	void setElimThisTurn(boolean b);
	
}
