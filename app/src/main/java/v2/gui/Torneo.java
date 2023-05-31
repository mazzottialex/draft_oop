package v2.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;


import data.Calciatore;
import data.Squadra;
import gui.Base;

public class Torneo extends Base {

	final int turni=3;
	final int nSquadre=2^turni;
	
	public Torneo(Squadra squadra, List<Calciatore> li) {
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
	}

}
