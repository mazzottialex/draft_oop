package data;

import java.util.List;

/**
 * The {@code Squadra} interface represents a team in a football (soccer) match.
 */
public interface Squadra {

    /**
     * Returns a list of the starting players (titolari) for the team.
     *
     * @return a list of Calciatore objects representing the starting players.
     */
    List<Calciatore> getTitolari();

    /**
     * Returns a list of the reserve players (riserve) for the team.
     *
     * @return a list of Calciatore objects representing the reserve players.
     */
    List<Calciatore> getRiserve();

    /**
     * Returns the name of the team.
     *
     * @return a string representing the name of the team.
     */
    String getNomeSquadra();

    /**
     * Returns the emblem of the team.
     *
     * @return a string representing the emblem of the team.
     */
    String getStemma();

    /**
     * Returns the team's evaluation.
     *
     * @return an integer representing the team's evaluation.
     */
    int getValutazione();

    /**
     * Returns the formation (modulo) of the team.
     *
     * @return a Modulo object representing the formation of the team.
     */
    Modulo getModulo();

    /**
     * Returns the ID of the team.
     *
     * @return an integer representing the ID of the team.
     */
    int getId();

    /**
     * Returns a list of all the players (titolari and riserve) in the team.
     *
     * @return a list of Calciatore objects representing all the players in the team.
     */
    List<Calciatore> getLiCalciatori();

    /**
     * Returns the player (calciatore) with the specified ID.
     *
     * @param id the ID of the player to retrieve.
     * @return the Calciatore object with the specified ID.
     */
    Calciatore getCalciatoreById(int id);

    /**
     * Returns the starting goalkeeper (portiere titolare) for the team.
     *
     * @return the Calciatore object representing the starting goalkeeper.
     */
    Calciatore getPortiereTit();

    /**
     * Sets the list of starting players (titolari) for the team.
     *
     * @param liTitolari a list of Calciatore objects representing the starting players.
     */
    void setTitolari(List<Calciatore> liTitolari);

    /**
     * Sets the list of reserve players (riserve) for the team.
     *
     * @param liRiserve a list of Calciatore objects representing the reserve players.
     */
    void setRiserve(List<Calciatore> liRiserve);

    /**
     * Returns a list of the starting players (titolari) in descending order.
     *
     * @return a list of Calciatore objects representing the starting players in descending order.
     */
    List<Calciatore> getTitolariDesc();
}
