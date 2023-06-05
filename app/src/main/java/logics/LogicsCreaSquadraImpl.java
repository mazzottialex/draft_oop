package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraUtente;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

/**
 * The class that handles the team creation logic.
 * @author Davide Braccini
 *
 */
public class LogicsCreaSquadraImpl implements LogicsCreasquadra {

	private static final long serialVersionUID = 1L;
	private static final int NUM_PLAYER_IN_TEAM = 11;  
	
	private Modulo moduloSelect;
	//private ManageData md;
	private ExtractData ex;
	private String namePlayer; //nome del calciatore selezionato per entrare in formazione
	private Calciatore calciatoreSelect; // calciatore selezionato per entrare in formazione 
	private String ruoloSelect; //ruolo del calciatore selezionato per entrrare in formazione 
	private int posSelect; //posizione del calciatore selezionato per entrare in formazione 
	private int ratingSelect; //rating del calciatore selezionato per entrare in formazione
	private final List<Calciatore> liSquadra;
	private final List<Calciatore> riserve;
	private final String nomeSquadra;
	private final String stemma;
	//private Squadra squadra;
	private final List<Calciatore> calcUsciti;
	private boolean clickModulo;
	
	/**
	 * Constructor of LogicsCreaSquadraImpl.
	 * @param nomeSquadra the name of the team
	 * @param stemma the arms
	 * @param li the list of all the players in Serie A
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public LogicsCreaSquadraImpl(final String nomeSquadra, final String stemma, final List<Calciatore> li) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		this.moduloSelect = Modulo.M343;
		//this.md = new ManageDataImpl(stagione);
		//this.md.LoadData();
		this.ex = new ExtractDataImpl(li); //(this.md.getLi());
		this.namePlayer = new String();
		this.calciatoreSelect = new Calciatore(0, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.ruoloSelect = null;
		this.posSelect = -1;
		this.ratingSelect = 0;
		this.liSquadra = new ArrayList<>();
		this.calcUsciti = new ArrayList<>();
		this.nomeSquadra = nomeSquadra;
		this.stemma = stemma;
		riserve = new ArrayList<>();
		riserve.addAll(this.getRandom("P", 1));
		riserve.addAll(this.getRandom("D", 2));
		riserve.addAll(this.getRandom("C", 2));
		riserve.addAll(this.getRandom("A", 2));
		this.calcUsciti.addAll(riserve);
		this.clickModulo = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Squadra getSquadra() {
		return new SquadraUtente(nomeSquadra, stemma, moduloSelect, liSquadra, riserve); //sistemare titolari e riserve
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Modulo> getModuli() {
		List<Modulo> list = new ArrayList<>();
		for (Modulo m : Modulo.values()) {
			list.add(m);
		}
		return list;
 	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModulo(final Modulo mod) {
		this.moduloSelect = mod;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Modulo getModulo() {
		return this.moduloSelect;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNumDif() {
		return this.moduloSelect.getNumDif();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumCen() {
		return this.moduloSelect.getNumCen();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumAtt() {
		return this.moduloSelect.getNumAtt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExtractData getEx() {
		return this.ex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Calciatore> getRandom(final String ruolo, final int n) {
		List<Calciatore> list = this.ex.getListaByRuolo(ruolo);
		Set<Calciatore> set = new HashSet<>();
		List<Calciatore> randomList = new ArrayList<>();
		boolean test = false;
		Random r = new Random();
		while (set.size() != n) {
			int random = r.nextInt(list.size());
			Calciatore c = list.get(random);
			test = false;
			for (Calciatore uscito : this.calcUsciti) {
				if (c.equals(uscito)) {
					test = true;
				}
			}
			if (!test) {
				set.add(list.get(random));
			}
		}
		randomList.addAll(set);
		return randomList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNamePlayer() {
		return this.namePlayer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNameString(final String name) {
		this.namePlayer = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Calciatore getCalciatoreSelect() {
		return this.calciatoreSelect;
		//Calciatore copy = this.calciatoreSelect;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCalciatoreSelect(final Calciatore calciatore) {
		this.calciatoreSelect = calciatore;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRuoloSelect() {
		return this.ruoloSelect;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRuoloSelect(final String ruolo) {
		this.ruoloSelect = ruolo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getposSelect() {
		return this.posSelect; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setposSelect(final int pos) {
		this.posSelect = pos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPlayerInTeam(final Calciatore calciatore) {
		if (!this.liSquadra.contains(calciatore)) {
			this.liSquadra.add(calciatore);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearTeam() {
		this.liSquadra.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean teamComplete() {
		if (this.liSquadra.size() == LogicsCreaSquadraImpl.NUM_PLAYER_IN_TEAM) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomeSquadra() {
		return this.nomeSquadra;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStemma() {
		return this.stemma;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Calciatore> getTitolari() {
		return this.liSquadra;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRating() {
		return this.ratingSelect;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRating(final int rating) {
		this.ratingSelect = rating;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Calciatore> getCalcUsciti() {
		//return this.calcUsciti;
		return List.copyOf(this.calcUsciti);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCalcUsciti(List<Calciatore> list) {
		this.calcUsciti.addAll(list);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getClickModulo() {
		return this.clickModulo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClickModulo(boolean click) {
		this.clickModulo = click;
	}

}
