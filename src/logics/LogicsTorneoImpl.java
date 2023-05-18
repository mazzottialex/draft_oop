package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Squadra;
import data.SquadraAvversaria;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.ManageData;
import manageData.ManageDataImpl;

public class LogicsTorneoImpl implements LogicsTorneo {

	private ManageData md;
	private ExtractData ex;
	private Squadra miasquadra;
	private List<SquadraAvversaria> listSquadre;
	
	public LogicsTorneoImpl(String stagione) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.md = new ManageDataImpl(stagione);
		this.md.LoadData();
		this.ex = new ExtractDataImpl(this.md.getLi());
		this.miasquadra = new Squadra(" ", " ");
		this.listSquadre = new ArrayList<>();
	}
	
	
	
	
}
