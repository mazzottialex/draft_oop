package data;

import java.awt.Image;
import java.util.List;

public class SquadraUtente implements Squadra{
	private final String nomeSquadra;
	private final String stemma;
	private final Modulo modulo;
	private final List<Calciatore> liCalciatori;
	private final List<Calciatore> liTitolari;
	private final List<Calciatore> liRiserve;
	
	public SquadraUtente(String nomeSquadra, String stemma, Modulo modulo, List<Calciatore> liTitolari, List<Calciatore> liRiserve) {
		this.nomeSquadra = nomeSquadra;
		this.stemma = stemma;
		this.modulo = modulo;
		this.liTitolari=liTitolari;
		this.liRiserve=liRiserve;
		this.liCalciatori = liTitolari;
		liCalciatori.addAll(liRiserve);
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
	@Override
	public String toString() {
		return "Squadra [nomeSquadra=" + nomeSquadra + ", stemma=" + stemma + ", modulo=" + modulo + ", liCalciatori="
				+ liCalciatori + "]";
	}
	@Override
	public List<Calciatore> getTitolari() {
		return this.liTitolari;
	}
	@Override
	public List<Calciatore> getRiserve() {
		return this.liRiserve;
	}
}