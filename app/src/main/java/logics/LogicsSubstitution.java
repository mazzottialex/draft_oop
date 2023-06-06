package logics;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

import data.Player;

/**
 * The {@code LogicsSubstitution} interface for managing player substitutions.
 */
public interface LogicsSubstitution {
	
	/**
     * Retrieves the list of starting players.
     *
     * @return the list of starting players
     */
    List<Player> getStarters();
    
    /**
     * Retrieves the list of substitute players.
     *
     * @return the list of substitute players
     */
    List<Player> getSubstitutes();
    
    /**
     * Selects a player from the starting lineup.
     *
     * @param p the player to be selected
     */
    void selectStarter(Player p);
    
    /**
     * Selects a player from the substitute bench.
     *
     * @param p the player to be selected
     */
    void selectSubstitute(Player p);
    
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
    void closeGui();
}
