package data;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SquadraAvversaria implements Squadra{
	private int id;
	private String nomeSquadra;
	private final String stemma;
	private Modulo modulo;
	private List<Calciatore> liTitolari = new ArrayList<>();
	private List<Calciatore> liRiserve = new ArrayList<>();
	private List<Calciatore> liCalciatori;
	
	
	public SquadraAvversaria(int id, String nomeSquadra, Modulo modulo, List<Calciatore> li) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.id = id;
		this.nomeSquadra = nomeSquadra;
		this.modulo = modulo;
		this.liCalciatori = li;
		ExtractData ed = new ExtractDataImpl(li);
		this.liTitolari = ed.getTitolari(nomeSquadra, modulo);
		this.liRiserve = ed.getRiserve(nomeSquadra, modulo);
		this.stemma=this.setStemma();
	}

	private String setStemma() {
		final List<String> liStemmi=List.of("arancione.png", "azzurro.png", "bianco.png", "blu.png", "giallo.png", "nero.png", "rosso.png", "verde.png", "viola.png");
		Random rnd = new Random();
        int pos = rnd.nextInt(liStemmi.size());
        return liStemmi.get(pos);
	}
	
	public int getId() {
		return id;
	}

	public String getNomeSquadra() {
		return nomeSquadra;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public List<Calciatore> getTitolari(){
		return liTitolari;
	}

	public List<Calciatore> getRiserve() {
		return liRiserve;
	}	
	
	@Override
	public List<Calciatore> getLiCalciatori() {
		return liCalciatori;
	}
	
	public String getStemma() {
		return this.stemma;
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
