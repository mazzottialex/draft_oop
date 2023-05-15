package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Calciatore;
import data.Modulo;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;

public class LogicsCreaSquadraImpl implements LogicsCreasquadra {

	private Modulo moduloSelect;
	private ManageData md;
	private ExtractData ex;
	private String namePlayer; //nome del calciatore selezionato per entrare in formazione
	private Calciatore calciatoreSelect; // calciatore selezionato per entrare in formazione 
	private String ruoloSelect; //ruolo del calciatore selezionato per entrrare in formazione 
	private int posSelect; //posizione del calciatore selezionato per entrare in formazione 
	
	public LogicsCreaSquadraImpl(String stagione) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.moduloSelect = Modulo.M343;
		this.md = new ManageDataImpl(stagione);
		this.md.LoadData();
		this.ex = new ExtractDataImpl(this.md.getLi());
		this.namePlayer = new String();
		this.calciatoreSelect = new Calciatore(0,null,null,null,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
		this.ruoloSelect = null;
		this.posSelect = -1;
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
		Random r = new Random();
		while (set.size() != n) {
			int random = r.nextInt(list.size());
			set.add(list.get(random));
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

	
	
	

}
