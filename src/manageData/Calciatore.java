package manageData;

import java.io.Serializable;

import utils.Pair;
import utils.Triple;

public class Calciatore implements Serializable{

	private static final long serialVersionUID = -557391519003956401L; //per serializzazione
	private final int id;
	private final String nominativo;
	private final String ruolo;
	private final String squadra;
	private final int pg; //partite giocate
	private final int minuti;
	private final int gol;
	private final int tiri;
	private final int dribling;
	private final int assist;
	private final int passaggi;
	private final int passaggiChiave;
	private final int ammonizioni;
	private final int espulsioni;
	private final int rubati;
	private final int tackle;
	private final int cleanSheet;
	private final int parate;
	private final Pair<Integer, Triple<Integer, Integer, Integer>> rating;

	public Calciatore(int id, String nominativo, String ruolo, String squadra, int pg, int minuti, int gol, int tiri,
			int dribling, int assist, int passaggi, int passaggiChiave, int ammonizioni, int espulsioni, int rubati,
			int tackle, int cleanSheet, int parate) {
		this.id = id;
		this.nominativo = nominativo;
		this.ruolo = ruolo;
		this.squadra = squadra;
		this.pg = pg;
		this.minuti = minuti;
		this.gol = gol;
		this.tiri = tiri;
		this.dribling = dribling;
		this.assist = assist;
		this.passaggi = passaggi;
		this.passaggiChiave = passaggiChiave;
		this.ammonizioni = ammonizioni;
		this.espulsioni = espulsioni;
		this.rubati = rubati;
		this.tackle = tackle;
		this.cleanSheet = cleanSheet;
		this.parate = parate;
		this.rating=null;
	}

	public int getId() {
		return id;
	}
	
	public String getNominativo() {
		return nominativo;
	}
	
	public String getRuolo() {
		return ruolo;
	}

	public String getSquadra() {
		return squadra;
	}

	public int getPg() {
		return pg;
	}

	public int getMinuti() {
		return minuti;
	}

	public int getGol() {
		return gol;
	}

	public int getTiri() {
		return tiri;
	}

	public int getDribling() {
		return dribling;
	}

	public int getAssist() {
		return assist;
	}

	public int getPassaggi() {
		return passaggi;
	}

	public int getPassaggiChiave() {
		return passaggiChiave;
	}

	public int getAmmonizioni() {
		return ammonizioni;
	}

	public int getEspulsioni() {
		return espulsioni;
	}

	public int getRubati() {
		return rubati;
	}

	public int getTackle() {
		return tackle;
	}

	public int getCleanSheet() {
		return cleanSheet;
	}

	public int getParate() {
		return parate;
	}

	public Pair<Integer, Triple<Integer, Integer, Integer>> getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return "Calciatore [id=" + id + ", nominativo=" + nominativo + ", ruolo=" + ruolo + ", squadra=" + squadra
				+ ", pg=" + pg + ", minuti=" + minuti + ", gol=" + gol + ", tiri=" + tiri + ", dribling=" + dribling
				+ ", assist=" + assist + ", passaggi=" + passaggi + ", passaggiChiave=" + passaggiChiave
				+ ", ammonizioni=" + ammonizioni + ", espulsioni=" + espulsioni + ", rubati=" + rubati + ", tackle="
				+ tackle + ", cleanSheet=" + cleanSheet + ", parate=" + parate + "]";
	}
	
}
