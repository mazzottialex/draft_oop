package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.JFrame;

import data.Modulo;
import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;

public class CreaSquadra extends Base{

	private LogicsCreasquadra log;
	final JFrame frameModulo;
	final JFrame frameCalciatori;
	
	public CreaSquadra() {
		this.log = new LogicsCreaSquadraImpl();
		
		// Creo 2 frame aggiuntivi, uno per modulo e uno per calciatori
		this.frameModulo = new JFrame("Seleziona modulo: ");
		this.frameCalciatori = new JFrame("Seleziona calciatori: ");
		
		//Mi occupo del frame Modulo
		final JPanel panelModulo = new JPanel(new BorderLayout());
		this.frameModulo.add(panelModulo);
		final JPanel panelModuloNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		final JPanel panelModuloSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
		
		JButton[] buttons = new JButton[log.getModuli().size()];
		ArrayList<Modulo> buttonSelect = new ArrayList<>();
		for (int i = 0; i <= buttons.length - 1; i++) {
			buttons[i] = new JButton("" + log.getModuli().get(i));
			int ind = i; 
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSelect.add(log.getModuli().get(ind));
					System.out.println(buttonSelect);
				}
			});
			panelModuloNorth.add(buttons[i]);
		}
		
		final JButton buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.setModulo(buttonSelect.get(buttonSelect.size()-1));
				System.out.println(log.getModulo());
			}	
		});
		panelModuloSouth.add(buttonOk);	
		
		
		
		panelModulo.add(panelModuloNorth, BorderLayout.NORTH);
		panelModulo.add(panelModuloSouth, BorderLayout.SOUTH);
		//System.out.println(log.getModuli());
		
		
		//Mi occupo del frame Calciatori
		final JPanel panelCalciatori = new JPanel(new BorderLayout());
		this.frameCalciatori.add(panelCalciatori);
		final JPanel panelCalciatoriNorth = new JPanel(new FlowLayout());
		final JPanel panelCalciatoriSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		final JButton buttonOkCalciatori = new JButton("OK");
		panelCalciatoriSouth.add(buttonOkCalciatori);
		
		panelCalciatori.add(panelCalciatoriNorth, BorderLayout.NORTH);
		panelCalciatori.add(panelCalciatoriSouth, BorderLayout.SOUTH);
		
		

		// Setto le impostazioni dei due frame aggiuntivi
		this.frameModulo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameCalciatori.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameModulo.setSize(300,300);
		this.frameCalciatori.setSize(300,300);
		this.frameModulo.setVisible(true);
		this.frameCalciatori.setVisible(true);
		
		
		
		
		
	}
	
}
