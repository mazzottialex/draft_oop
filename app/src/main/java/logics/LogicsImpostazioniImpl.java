package logics;

import java.awt.Image;

import data.Squadra;

public class LogicsImpostazioniImpl implements LogicsImpostazioni {

	private String stemma="res/stemmi/bianco.png";
	private String nomeSquadra;
	
	
	public LogicsImpostazioniImpl() {
	}

	@Override
	public Squadra getSquadra(String nomeSquadra) {
		//check se squadra non presente in lista
		if (nomeSquadra.equals(""))
			return null; //new Squadra("Squadra 1", this.stemma);
		return null; //new Squadra(nomeSquadra, this.stemma);
	}

	@Override
	public void setStemma(String img) {
		this.stemma=img;
	}

	
	public String getStemma() {
		return this.stemma;
	}

}
