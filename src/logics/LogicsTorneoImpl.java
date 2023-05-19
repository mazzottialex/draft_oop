package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraAvversaria;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;

public class LogicsTorneoImpl implements LogicsTorneo {

	private ManageData md;
	private ExtractData ex;
	private Squadra miasquadra;
	private List<SquadraAvversaria> listSquadre;
	
	public LogicsTorneoImpl(String stagione, String nomeSquadra, String stemma, List<Calciatore> titolari, List<Calciatore> riserve, Modulo modulo) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.md = new ManageDataImpl(stagione);
		this.md.LoadData();
		List<Calciatore> li = md.getLi();
		CalcoloRating r = new CalcoloRatingImpl(li);
		li = r.updateRating();
		ex = new ExtractDataImpl(li);
		this.listSquadre = new ArrayList<>();
		
		//Creo la squadr dell'utente
		this.miasquadra = new Squadra(nomeSquadra, stemma, titolari, riserve, modulo);
		//System.out.println(this.miasquadra);
		
		// Creo le squadre avversarie (quelle esistenti in serie A)
		this.listSquadre.add(new SquadraAvversaria(0, "NAP", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(1, "LAZ", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(2, "JUV", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(3, "INT", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(4, "MIL", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(5, "ROM", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(6, "ATA", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(7, "FIO", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(8, "UDI", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(9, "MON", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(10, "CRE", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(11, "VER", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(12, "BOL", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(13, "TOR", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(14, "SAL", Modulo.M442, li)); 
		//System.out.println(this.listSquadre);
		
	}

	@Override
	public List<SquadraAvversaria> getListAvversari() {
		return this.listSquadre;
	}

	@Override
	public Squadra getMiaSquadra() {
		return this.miasquadra;
	}
	
}
