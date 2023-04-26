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
	private Modulo modulo;
	public List<Calciatore> titolari = new ArrayList<>();
	public List<Calciatore> riserve = new ArrayList<>();
	private List<Calciatore> li;
	
	
	public SquadraAvversaria(int id, String nomeSquadra, Modulo modulo, List<Calciatore> li) throws FileNotFoundException, IOException {
		this.id = id;
		this.nomeSquadra = nomeSquadra;
		this.modulo = modulo;
		this.li = li;
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
		return ed.getTitolari(nomeSquadra, modulo);
	}

	public List<Calciatore> getRiserve() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return ed.getRiserve(nomeSquadra, modulo);
	}
	
	public List<String> getNomeTitolari() throws FileNotFoundException, ClassNotFoundException, IOException {
		return getTitolari()
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
	
	public List<String> getNomeRiserve() throws FileNotFoundException, ClassNotFoundException, IOException {
		return getRiserve()
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
	
	public List<String> getNomeCalciatori() throws FileNotFoundException, ClassNotFoundException, IOException {
		ExtractData ed = new ExtractDataImpl(li);
		return ed
				.getCalciatoreBySquadra(nomeSquadra)
				.stream()
				.map(c -> c.getNominativo())
				.toList();
	}
}
