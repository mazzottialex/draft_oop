package data;

import java.awt.Image;
import java.util.List;

public class Squadra {
	private final String nomeSquadra;
	private final String stemma;
	private final Modulo modulo;
	private final List<Calciatore> liCalciatori;
	
	public Squadra(String nomeSquadra, String stemma, Modulo modulo, List<Calciatore> liCalciatori) {
		this.nomeSquadra = nomeSquadra;
		this.stemma = stemma;
		this.modulo = modulo;
		this.liCalciatori = liCalciatori;
	}
	public String getNomeSquadra() {
		return nomeSquadra;
	}
	public String getStemma() {
		return stemma;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public List<Calciatore> getLiCalciatori() {
		return liCalciatori;
	}
	
}
