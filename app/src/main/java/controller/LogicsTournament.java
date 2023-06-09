package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import model.data.Team;

/**
 * The interface that models the logic of Tournament.
 * 
 * @author Davide Braccini
 *
 */
public interface LogicsTournament extends Serializable {

    /**
     * 
     * @return the list of opposing teams
     */
    List<Team> getListAvversari();

    /**
     * 
     * @param list the list of opposing teams
     */
    void setListAvversari(List<Team> list);

    /**
     * 
     * @return the user's team
     */
    Team getMiaSquadra();

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
     * 
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
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
    boolean isEliminated();

    /**
     * 
     * @param eliminated true if the user's team is eliminated
     */
    void setEliminated(boolean eliminated);

    /**
     * 
     * @return the team against the user's team
     */
    Team getSquadraAvv();

    /**
     * 
     * @param squadra the team against the user's team
     */
    void setSquadraAvv(Team squadra);

    /**
     * 
     * @return the result of the match that is played when the user team is
     *         eliminated
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
    boolean isElimThisTurn();

    /**
     * 
     * @param b true if the user's team was eliminated this round
     */
    void setElimThisTurn(boolean b);

}
