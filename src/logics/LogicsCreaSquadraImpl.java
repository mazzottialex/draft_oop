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
	
	public LogicsCreaSquadraImpl(String stagione) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.moduloSelect = Modulo.M343;
		this.md = new ManageDataImpl(stagione);
		this.md.LoadData();
		this.ex = new ExtractDataImpl(this.md.getLi());
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

	
	
	

}
