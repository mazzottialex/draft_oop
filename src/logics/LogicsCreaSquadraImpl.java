package logics;

import java.util.*;

import data.Modulo;

public class LogicsCreaSquadraImpl implements LogicsCreasquadra {

	private Modulo moduloSelect = Modulo.M343;
	
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
	
}
