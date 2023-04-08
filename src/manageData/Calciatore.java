package manageData;

public class Calciatore {
	private int id;
	private String nominativo;
	private String ruolo;
	private String squadra;
	private int pg; //partite giocate
	private int minuti;
	private int gol;
	private int tiri;
	private int dribling;
	private int assist;
	private int passaggi;
	private int passaggiChiave;
	private int ammonizioni;
	private int espulsioni;
	private int rubati;
	private int tackle;
	private int cleanSheet;
	private int parate;

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

	public int getId() {
		return id;
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
