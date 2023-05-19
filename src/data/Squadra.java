package data;

import java.awt.Image;
import java.util.List;

public class Squadra {
	public final String nomeSquadra;
	public final String stemma;
	//private Modulo modulo;
	private final List<Calciatore> titolari;
	private final List<Calciatore> riserve;
	
	public Squadra(String nomeSquadra, String stemma, List<Calciatore> titolari, List<Calciatore> riserve) {
		this.nomeSquadra=nomeSquadra;
		this.stemma=stemma;
		this.titolari = titolari;
		this.riserve = riserve;
	}

	public String getNomeSquadra() {
		return this.nomeSquadra;
	}

	public String getStemma() {
		return this.stemma;
	}

	public List<Calciatore> getTitolari() {
		return this.titolari;
	}

	public List<Calciatore> getRiserve() {
		return this.riserve;
	}

	@Override
	public String toString() {
		return "Squadra [nomeSquadra=" + nomeSquadra + ", stemma=" + stemma + "]";
	}
}
