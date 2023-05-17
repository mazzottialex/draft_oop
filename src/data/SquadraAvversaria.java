package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manageData.ExtractData;
import manageData.ExtractDataImpl;

public class SquadraAvversaria{
	private int id;
	private String nomeSquadra;
	private final String stemma;
	private Modulo modulo;
	public List<Calciatore> titolari = new ArrayList<>();
	public List<Calciatore> riserve = new ArrayList<>();
	private List<Calciatore> li = new ArrayList<>();;
	
	
	public SquadraAvversaria(int id, String nomeSquadra, String stemma, Modulo modulo, List<Calciatore> titolari, List<Calciatore> riserve) throws FileNotFoundException, IOException {
		this.id = id;
		this.nomeSquadra = nomeSquadra;
		this.stemma=stemma;
		this.modulo = modulo;
		this.titolari=titolari;
		this.riserve=riserve;
		this.li.addAll(titolari);
		this.li.addAll(riserve);
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

	public List<Calciatore> getTitolari() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		titolari = ed.getTitolari(nomeSquadra, modulo);
		return titolari;
	}

	public List<Calciatore> getRiserve() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		riserve = ed.getRiserve(nomeSquadra, modulo);
		return riserve;
	}
	/*
	public List<String> getNomeTitolari() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return ed.getNomeTitolari(nomeSquadra, modulo);
	}
	
	public List<String> getNomeRiserve() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return ed.getNomeRiserve(nomeSquadra, modulo);
	}
	
	public List<String> getNomeCalciatori() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return ed.getNomeCalciatori(nomeSquadra);
	}
	*/
	// ruolo, nome, rating
	public List<Object> getTsr() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return (List<Object>) ed.tsr(nomeSquadra, modulo);
	}
}
