package logics;

import data.SquadraAvversaria;
import gui.Partita;

public class LogicsPartitaImpl implements LogicsPartita{
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	
	public LogicsPartitaImpl(SquadraAvversaria s1, SquadraAvversaria s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
	}
}
