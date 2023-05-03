package logics;

import java.awt.Image;

import data.Squadra;

public class LogicsImpostazioniImpl implements LogicsImpostazioni {

	private String stemma;
	private String nomeSquadra;
	
	
	public LogicsImpostazioniImpl() {
	}

	@Override
	public Squadra getSquadra(String nomeSquadra) {
		//check se squadra non presente in lista
		if (nomeSquadra.equals(""))
			return new Squadra("Squadra 1", this.stemma);
		return new Squadra(nomeSquadra, this.stemma);
	}

	@Override
	public void setStemma(String img) {
		if(img.equals(""))
			this.stemma="res/stemmi/bianco.png";
		else
			this.stemma=img;
	}

}
