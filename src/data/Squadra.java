package data;

import java.awt.Image;

public class Squadra {
	public final String nomeSquadra;
	public final String stemma;
	
	public Squadra(String nomeSquadra, String stemma) {
		this.nomeSquadra=nomeSquadra;
		this.stemma=stemma;
	}

	@Override
	public String toString() {
		return "Squadra [nomeSquadra=" + nomeSquadra + ", stemma=" + stemma + "]";
	}
}
