package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SquadraAvversaria{
	private int id;
	private String nomeSquadra;
	private Modulo modulo;
	public List<Calciatore> titolari = new ArrayList<>();
	public List<Calciatore> riserve = new ArrayList<>();
	
	public SquadraAvversaria(int id, String nomeSquadra, Modulo modulo) throws FileNotFoundException, IOException {
		this.id = id;
		this.nomeSquadra = nomeSquadra;
		this.modulo = modulo;
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
}
