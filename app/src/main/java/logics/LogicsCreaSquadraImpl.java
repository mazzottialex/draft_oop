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
import manageData.ManageData;
import manageData.ManageDataImpl;

public class LogicsCreaSquadraImpl implements LogicsCreasquadra {

	private final static int NUM_PLAYER_IN_TEAM = 11;  
	
	private Modulo moduloSelect;
	//private ManageData md;
	private ExtractData ex;
	private String namePlayer; //nome del calciatore selezionato per entrare in formazione
	private Calciatore calciatoreSelect; // calciatore selezionato per entrare in formazione 
	private String ruoloSelect; //ruolo del calciatore selezionato per entrrare in formazione 
	private int posSelect; //posizione del calciatore selezionato per entrare in formazione 
	private int ratingSelect; //rating del calciatore selezionato per entrare in formazione
	private List<Calciatore> liSquadra;
	private List<Calciatore> riserve;
	private String nomeSquadra;
	private String stemma;
	private Squadra squadra;
	private List<Calciatore> calcUsciti;
	
	public LogicsCreaSquadraImpl(String nomeSquadra, String stemma, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.moduloSelect = Modulo.M343;
		//this.md = new ManageDataImpl(stagione);
		//this.md.LoadData();
		this.ex = new ExtractDataImpl(li); //(this.md.getLi());
		this.namePlayer = new String();
		this.calciatoreSelect = new Calciatore(0,null,null,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		this.ruoloSelect = null;
		this.posSelect = -1;
		this.ratingSelect = 0;
		this.liSquadra = new ArrayList<>();
		this.calcUsciti = new ArrayList<>();
		this.nomeSquadra = nomeSquadra;
		this.stemma = stemma;
		riserve = new ArrayList<>();
		riserve.addAll(this.getRandom("P", 2));
		riserve.addAll(this.getRandom("D", 3));
		riserve.addAll(this.getRandom("C", 3));
		riserve.addAll(this.getRandom("A", 3));
		
	}
	
	public Squadra getSquadra() {
		return new SquadraUtente(nomeSquadra, stemma, moduloSelect, liSquadra, riserve); //sistemare titolari e riserve
	}
	
	public List<Modulo> getModuli() {
		List<Modulo> list = new ArrayList<>();
		for (Modulo m : Modulo.values()) {
			list.add(m);
		}
		return list;
 	}

	@Override
	public void setModulo(Modulo mod) {
		this.moduloSelect = mod;
	}

	@Override
	public Modulo getModulo() {
		return this.moduloSelect;
	}

	public int getNumDif() {
		return this.moduloSelect.getNumDif();
	}

	@Override
	public int getNumCen() {
		return this.moduloSelect.getNumCen();
	}

	@Override
	public int getNumAtt() {
		return this.moduloSelect.getNumAtt();
	}

	@Override
	public ExtractData getEx() {
		return this.ex;
	}

	@Override
	public List<Calciatore> getRandom(String ruolo, int n) {
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

	@Override
	public String getNamePlayer() {
		return this.namePlayer;
	}

	@Override
	public void setNameString(String name) {
		this.namePlayer = name;
	}

	@Override
	public Calciatore getCalciatoreSelect() {
		return this.calciatoreSelect;
	}

	@Override
	public void setCalciatoreSelect(Calciatore calciatore) {
		this.calciatoreSelect = calciatore;
	}

	@Override
	public String getRuoloSelect() {
		return this.ruoloSelect;
	}

	@Override
	public void setRuoloSelect(String ruolo) {
		this.ruoloSelect = ruolo;
	}

	@Override
	public int getposSelect() {
		return this.posSelect; 
	}

	@Override
	public void setposSelect(int pos) {
		this.posSelect = pos;
	}

	@Override
	public void addPlayerInTeam(Calciatore calciatore) {
		if (!this.liSquadra.contains(calciatore)) {
			this.liSquadra.add(calciatore);
		}
	}

	@Override
	public void clearTeam() {
		this.liSquadra.clear();
	}

	@Override
	public boolean teamComplete() {
		if (this.liSquadra.size() == LogicsCreaSquadraImpl.NUM_PLAYER_IN_TEAM) {
			return true;
		}
		return false;
	}

	@Override
	public String getNomeSquadra() {
		return this.nomeSquadra;
	}

	@Override
	public String getStemma() {
		return this.stemma;
	}

	@Override
	public List<Calciatore> getTitolari() {
		return this.liSquadra;
	}

	@Override
	public int getRating() {
		return this.ratingSelect;
	}

	@Override
	public void setRating(int rating) {
		this.ratingSelect = rating;
	}

	@Override
	public List<Calciatore> getCalcUsciti() {
		return this.calcUsciti;
	}

}
