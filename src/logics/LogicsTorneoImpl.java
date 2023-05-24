package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraAvversaria;
import data.SquadraUtente;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;
import simulation.SimulatingMatchImpl;

public class LogicsTorneoImpl implements LogicsTorneo {

	private ManageData md;
	private ExtractData ex;
	private Squadra miasquadra;
	private List<SquadraAvversaria> listSquadre;
	//private List<Integer> golFatti;
	private int numSquadre;
	private List<Calciatore> li;
	
	public LogicsTorneoImpl(Squadra squadra, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.li=li;
		this.listSquadre = new ArrayList<>();
		//this.golFatti = new ArrayList<>();
		
		//Creo la squadr dell'utente
		this.miasquadra = squadra;
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
		
		this.numSquadre = 16;
		
	}

	@Override
	public List<SquadraAvversaria> getListAvversari() {
		return this.listSquadre;
	}

	@Override
	public Squadra getMiaSquadra() {
		return this.miasquadra;
	}

	@Override
	public int getNumSquadre() {
		return this.numSquadre;
	}

	@Override
	public void setNumSquadre(int numSquadre) {
		this.numSquadre = numSquadre;
	}

	@Override
	public void setListAvversari(List<SquadraAvversaria> list) {
		this.listSquadre = list;
	}

	@Override
	public void simulaMatch() {
		List<SquadraAvversaria> newList = new ArrayList<>();
		final int numSquadre = this.getNumSquadre();
		Map<String, Integer> map = new HashMap<>(); //map per il risultato
		List<String> list = new ArrayList<>(); //lista per i nomi delle squadre che si sfidano 
		String teamWin = new String(); //nome della squadra vincente
		switch (numSquadre) {
		case 16: 
			
			for (int i = 1; i < numSquadre - 1; i = i + 2) {
				try {
					map = new SimulatingMatchImpl(this.getListAvversari().get(i),this.getListAvversari().get(i+1)).risultato();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				list.addAll(map.keySet());			
				if (map.get(list.get(0)) >= map.get(list.get(1))) {
					teamWin = list.get(0);
				} else {
					teamWin = list.get(1);
				}			
				if (this.getListAvversari().get(i).getNomeSquadra().equals(teamWin)) {
					newList.add(this.getListAvversari().get(i));
				} else {
					newList.add(this.getListAvversari().get(i+1));
				}
				list.clear();
			}
			this.setListAvversari(newList);
			System.out.println(newList);
			this.setNumSquadre(8);
			break;
		case 8:
			// ...
			this.setNumSquadre(4);
			break;
		case 4:
			// ...
			this.setNumSquadre(2);
			break;
		case 2:
			//...
			this.setNumSquadre(1);
			break;
		}
		
	}

}
