package manageData;

public class Calciatore {
	private String nominativo;
	private String ruolo;
	private String squadra;
	private int pg; //partite giocate
	private int gol;
	private int ammonizioni;
	private int espulsioni;
	private float mv; //media voti
	public Calciatore(String nominativo, String ruolo, String squadra, int pg, int gol, int ammonizioni, int espulsioni,
			float mv) {
		this.nominativo = nominativo;
		this.ruolo = ruolo;
		this.squadra = squadra;
		this.pg = pg;
		this.gol = gol;
		this.ammonizioni = ammonizioni;
		this.espulsioni = espulsioni;
		this.mv = mv;
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

	@Override
	public String toString() {
		return "Calciatore [nominativo=" + nominativo + ", ruolo=" + ruolo + ", squadra=" + squadra + ", pg=" + pg
				+ ", gol=" + gol + ", ammonizioni=" + ammonizioni + ", espulsioni=" + espulsioni + ", mv=" + mv + "]";
	}
	
	
}
