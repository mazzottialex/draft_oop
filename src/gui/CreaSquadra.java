package gui;

import javax.swing.*;
import javax.swing.JFrame;

import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;

public class CreaSquadra extends Base{

	private LogicsCreasquadra log;
	final JFrame frameModulo;
	final JFrame frameCalciatori;
	
	public CreaSquadra() {
		this.log = new LogicsCreaSquadraImpl();
		
		this.frameModulo = new JFrame("Seleziona modulo: ");
		this.frameCalciatori = new JFrame("Seleziona calciatori: ");

		
		this.frameModulo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameCalciatori.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameModulo.setSize(300,300);
		this.frameCalciatori.setSize(300,300);
		this.frameModulo.setVisible(true);
		this.frameCalciatori.setVisible(true);
	}
	
}
