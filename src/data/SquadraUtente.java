package data;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SquadraUtente implements Squadra, Serializable{
	private final int id;
	private final String nomeSquadra;
	private final String stemma;
	private final Modulo modulo;
	private final List<Calciatore> liCalciatori;
	private final List<Calciatore> liTitolari;
	private final List<Calciatore> liRiserve;
	
	public SquadraUtente(String nomeSquadra, String stemma, Modulo modulo, List<Calciatore> liTitolari, List<Calciatore> liRiserve) {
		this.id=0;
		this.nomeSquadra = nomeSquadra;
		this.stemma = stemma;
		this.modulo = modulo;
		this.liTitolari=liTitolari;
		this.liRiserve=liRiserve;
		this.liCalciatori = new ArrayList<>();
		liCalciatori.addAll(liTitolari);
		liCalciatori.addAll(liRiserve);
	}
	
	@Override
	public String getNomeSquadra() {
		return nomeSquadra;
	}
	
	@Override
	public String getStemma() {
		return stemma;
	}
	
	@Override
	public Modulo getModulo() {
		return modulo;
	}
	
	@Override
	public List<Calciatore> getLiCalciatori() {
		return liCalciatori;
	}
	
	@Override
	public List<Calciatore> getTitolari() {
		return liTitolari.stream()
				.sorted((c1,c2)->c1.getRuolo().compareTo(c2.getRuolo()))
				.toList();
	}
	@Override
	public List<Calciatore> getRiserve() {
		return this.liRiserve;
	}
	@Override
	public int getValutazione() {
		return (int) Math.floor(
				liTitolari.stream()
						.map(c->c.getRating()
						.getX())
						.mapToDouble(c->c)
						.average()
						.orElse(0));
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Squadra [nomeSquadra=" + nomeSquadra + ", stemma=" + stemma + ", modulo=" + modulo + ", liCalciatori="
				+ liCalciatori + "]";
	}
}