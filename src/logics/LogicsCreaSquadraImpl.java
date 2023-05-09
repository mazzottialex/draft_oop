package logics;

import java.util.*;

import data.Modulo;

public class LogicsCreaSquadraImpl implements LogicsCreasquadra {

	public Set<Modulo> getModuli() {
		Set<Modulo> set = new HashSet<>();
		for (Modulo m : Modulo.values()) {
			set.add(m);
		}
		return set;
 	}

}
