package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SquadraUtente implements Squadra, Serializable {
	private final int id;
	private final String nomeSquadra;
	private final String stemma;
	private final Modulo modulo;
	private final List<Calciatore> liCalciatori;
	private List<Calciatore> liTitolari;
	private List<Calciatore> liRiserve;
	
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
	public void setTitolari(List<Calciatore> liTitolari) {
		this.liTitolari = liTitolari;
	}
	
	@Override
	public void setRiserve(List<Calciatore> liRiserve) {
		this.liRiserve = liRiserve;
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

	@Override
	public Calciatore getCalciatoreById(int id) {
		Calciatore c = null;
		for (Calciatore calciatore : liCalciatori) {
			if (calciatore.getId() == id) {
				c = calciatore;
			}
		}
		return c;
	}

	@Override
	public Calciatore getPortiereTit() {
		Calciatore portiere = null;
		for (Calciatore calciatore : getTitolari()) {
			if (calciatore.getRuolo().equals("P")) {
				portiere = calciatore;
			}
		}
		return portiere;
	}
}