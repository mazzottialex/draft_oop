package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SquadraAvversaria extends ManageDataImpl{
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
	/*
	public List<Calciatore> getTitolari() {
		List<Calciatore> liP = getTitolariBySquadraByRuolo(nomeSquadra, "P", modulo);
		List<Calciatore> liD = getTitolariBySquadraByRuolo(nomeSquadra, "D", modulo);
		List<Calciatore> liC = getTitolariBySquadraByRuolo(nomeSquadra, "C", modulo);
		List<Calciatore> liA = getTitolariBySquadraByRuolo(nomeSquadra, "A", modulo);
		titolari.addAll(liP);
		titolari.addAll(liD);
		titolari.addAll(liC);
		titolari.addAll(liA);
		return titolari;
	}
	
	public List<Calciatore> getRiserve() {
		List<Calciatore> liP = getRiserveBySquadraByRuolo(nomeSquadra, "P", modulo);
		List<Calciatore> liD = getRiserveBySquadraByRuolo(nomeSquadra, "D", modulo);
		List<Calciatore> liC = getRiserveBySquadraByRuolo(nomeSquadra, "C", modulo);
		List<Calciatore> liA = getRiserveBySquadraByRuolo(nomeSquadra, "A", modulo);
		riserve.addAll(liP);
		riserve.addAll(liD);
		riserve.addAll(liC);
		riserve.addAll(liA);
		return riserve;
	}
	*/
}
