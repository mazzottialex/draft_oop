package logics;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

import data.Calciatore;

/**
 * The {@code LogicsSostituzione} interface for managing player substitutions.
 */
public interface LogicsSostituzione {
	
	/**
     * Retrieves the list of starting players (titolari).
     *
     * @return the list of starting players
     */
    List<Calciatore> getTitolari();
    
    /**
     * Retrieves the list of substitute players (riserve).
     *
     * @return the list of substitute players
     */
    List<Calciatore> getRiserve();
    
    /**
     * Selects a player from the starting lineup (titolari).
     *
     * @param c the player to be selected
     */
    void selezTit(Calciatore c);
    
    /**
     * Selects a player from the substitute bench (riserve).
     *
     * @param c the player to be selected
     */
    void selezRis(Calciatore c);
    
    /**
     * Substitutes a player by swapping their positions in the GUI containers.
     *
     * @param parent1 the parent container of the first player
     * @param parent2 the parent container of the second player
     * @param component1 the GUI component representing the first player
     * @param component2 the GUI component representing the second player
     */
    void sub(Container parent1, Container parent2, Component component1, Component component2);
    
    /**
     * Closes the GUI for player substitutions.
     */
    void chiudiGUI();
}
