package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import data.Modulo;
import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;

public class CreaSquadra extends Base{

	private final static int MAX_FOR_ROW = 3;
	
	private LogicsCreasquadra log;
	final JFrame frameModulo;
	final JFrame frameCalciatori;
	
	public CreaSquadra() {
		this.log = new LogicsCreaSquadraImpl();
		
		
		// Mi occupo del frame principale 
		contentPane.setLayout(new BorderLayout());
		//final JPanel panelSudEst = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		final JPanel panelSud = new JPanel(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(8,0,8,8);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		final JLabel lblmodSel = new JLabel("Modulo selezionato: ");
		panelSud.add(lblmodSel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		final JLabel lblmoduloSelect = new JLabel("" + log.getModulo());
		panelSud.add(lblmoduloSelect,gbc);
		
		gbc.insets = new Insets(5,30,5,5);
		gbc.gridx = 5;
		gbc.gridy = 0;
		
		final JButton buttonIniziaTorneo = new JButton("Inizia Torneo");
		buttonIniziaTorneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelSud.add(buttonIniziaTorneo,gbc);
		
		
		// Creo 2 frame aggiuntivi, uno per modulo e uno per calciatori
		this.frameModulo = new JFrame("Seleziona modulo: ");
		this.frameCalciatori = new JFrame("Seleziona calciatori: ");
		this.frameModulo.setBounds(600, 100, 200, 300);
		this.frameCalciatori.setBounds(600, 400, 200, 300);
		
		//Mi occupo del frame Modulo
		final JPanel panelModulo = new JPanel(new BorderLayout());
		panelModulo.setBackground(new Color(0,64,128));
		this.frameModulo.add(panelModulo);
		final JPanel panelModuloNorth = new JPanel(new GridBagLayout());
		final JPanel panelModuloSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelModuloNorth.setBackground(new Color(0,64,128));
		panelModuloSouth.setBackground(new Color(0,64,128));
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		// creo tanti bottoni quanti sono i moduli disponibili
		JButton[] buttons = new JButton[log.getModuli().size()];
		ArrayList<Modulo> buttonSelect = new ArrayList<>();
		for (int i = 0; i <= buttons.length - 1; i++) {
			buttons[i] = new JButton("" + log.getModuli().get(i));
			int ind = i; 
			// faccio in modo che alla pressione del bottono si salvi il modulo selezionato nell'array buttonSelect
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonSelect.add(log.getModuli().get(ind));
					System.out.println(buttonSelect);
				}
			});
			panelModuloNorth.add(buttons[i],gbc);
			gbc.gridx++;
			if (gbc.gridx == this.MAX_FOR_ROW) {
				System.out.println(gbc.gridx);
				System.out.println(gbc.gridy);
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}
		
		// quando premo il tasto OK salvo nella variabile log.moduloSelct il modulo scelto
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
		
		
		//Mi occupo del frame Calciatori
		final JPanel panelCalciatori = new JPanel(new BorderLayout());
		panelCalciatori.setBackground(new Color(0,64,128));
		this.frameCalciatori.add(panelCalciatori);
		final JPanel panelCalciatoriNorth = new JPanel(new FlowLayout());
		final JPanel panelCalciatoriSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCalciatoriNorth.setBackground(new Color(0,64,128));
		panelCalciatoriSouth.setBackground(new Color(0,64,128));
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
		
		// aggiungo il pannello sud del frame principale
		//contentPane.add(panelSudEst, BorderLayout.SOUTH);
		contentPane.add(panelSud, BorderLayout.SOUTH);
		
		
	}
	
}
