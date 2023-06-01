package data;

import java.util.List;

/**
 * Questa interfaccia rappresenta un oggetto Squadra.
 * Ogni squadra ha un id, un nome, uno stemma, un modulo, una lista di titolari,
 * una lista di riserve, una lista di tutti i calciatori di una certa annata e
 * una valutazione complessiva.
 *
 */
public interface Squadra {
	
	/**
    * Restituisce la lista dei calciatori titolari della squadra nel
    * seguente ordine: portire, difensori, centrocampisti e attaccanti.
    *
    * @return I calciatori titolari della squadra.
    */
	public List<Calciatore> getTitolari();
	
	/**
     * Restituisce la lista dei calciatori che sono riserve della squadra.
    *
    * @return Le riserve della squadra.
    */
	public List<Calciatore> getRiserve();
	
	/**
     * Restituisce il nome della squadra.
     *
     * @return Il nome della squadra.
     */
	public String getNomeSquadra();
	
	/**
     * Restituisce lo stemma della squadra.
     *
     * @return Lo stemma della squadra.
     */
	public String getStemma();
	
	/**
     * Restituisce la valutazione complessiva della squadra.
     *
     * @return La valutazione della squadra.
     */
	public int getValutazione();
	
	/**
     * Restituisce il modulo usato dalla squadra.
     *
     * @return Il modulo della squadra.
     */
	public Modulo getModulo();
	
	/**
     * Restituisce l'id della squadra.
     *
     * @return L'id della squadra.
     */
	public int getId();
	
	/**
     * Restituisce la lista di tutti i calciatori dell'annata selezionata.
     *
     * @return I calciatori dell'annata prescelta squadra.
     */
	public List<Calciatore> getLiCalciatori();
	
	/**
     * Restituisce un calciatore passandogli il suo id.
     *
     * @param id L'id del calciatore.
     * @return Il calciatore con tale id.
     */
	public Calciatore getCalciatoreById(int id);
	
	/**
    * Restituisce il portiere titolare della squadra.
    *
    * @return Il calciatore di ruolo portire nella lista dei titolari.
    */
	public Calciatore getPortiereTit();
	
	/**
     * Imposta i titolari della squadra.
     *
     * @param liTitolari La nuova lista di titolari della squadra.
     */
	public void setTitolari(List<Calciatore> liTitolari);
	
	/**
     * Imposta le riserve della squadra.
     *
     * @param liRiserve La nuova lista di riserve della squadra.
     */
	public void setRiserve(List<Calciatore> liRiserve);
	
	/**
	    * Restituisce la lista dei calciatori titolari della squadra nel
	    * seguente ordine: attaccanti, centrocampisti, difensori e portiere.
	    *
	    * @return I calciatori titolari della squadra con ordine inverso
	    * rispetto a getTitolari().
	    */
	public List<Calciatore> getTitolariDesc();
}
