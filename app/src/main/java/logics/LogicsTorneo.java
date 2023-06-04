package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.Squadra;
import data.SquadraAvversaria;

/**
 * The interface that models the logic of Torneo
 * @author Davide Braccini
 *
 */
public interface LogicsTorneo {

	/**
	 * 
	 * @return the list of opposing teams
	 */
	public List<Squadra> getListAvversari();
	
	/**
	 * 
	 * @param list the list of opposing teams
	 */
	public void setListAvversari(List<Squadra> list);
	
	/**
	 * 
	 * @return the user's team
	 */
	public Squadra getMiaSquadra(); 
	
	/**
	 * 
	 * @return the number of teams
	 */
	public int getNumSquadre();
	
	/**
	 * 
	 * @param numSquadre the number of teams
	 */
	public void setNumSquadre(int numSquadre);
	
	/**
	 * Simulate the matches of the turnament
	 * @throws FileNotFoundException if..
	 * @throws ClassNotFoundException if..
	 * @throws IOException if..
	 */
	public void simulaMatch() throws FileNotFoundException, ClassNotFoundException, IOException;
	
	/**
	 * 
	 * @return the map with the results
	 */
	public Map<String,Integer> getRisultati();
	
	/**
	 * 
	 * @return true if the user's team is eliminated
	 */
	public boolean getEliminated();
	
	/**
	 * 
	 * @param eliminated true if the user's team is eliminated
	 */
	public void setEliminated(boolean eliminated);
	
	/**
	 * 
	 * @return the team against the user's team
	 */
	public Squadra getSquadraAvv();
	
	/**
	 * 
	 * @param squadra the team against the user's team
	 */
	public void setSquadraAvv(Squadra squadra);
	
	/**
	 * 
	 * @return the result of the match that is played when the user team is eliminated
	 */
	public Map<String,Integer> getRisMatch();
	
	/**
	 * Clear RisMatch
	 */
	public void clearRisMatch();
	
	/**
	 * 
	 * @return the winner of the turnament
	 */
	public String getWinner();
	
	/**
	 * 
	 * @return true if the user's team was eliminated this round
	 */
	public boolean getElimThisTurn();
	
	/**
	 * 
	 * @param b true if the user's team was eliminated this round
	 */
	public void setElimThisTurn(boolean b);
}
