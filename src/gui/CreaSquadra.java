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
	final JPanel panelSud = new JPanel(new GridBagLayout()); //panel Sud del frame principale
	final JPanel panelCenter = new JPanel(new GridBagLayout()); // Panel Center del frame principale 
	final GridBagConstraints gbc = new GridBagConstraints();
	final JLabel lblmodSel;
	JLabel lblmoduloSelect;
	final JButton buttonIniziaTorneo;
	final JButton[] buttonsAtt;
	final JButton[] buttonsCen;
	final JButton[] buttonsDif;
	JButton buttonPor;
	
	public CreaSquadra() {
		this.log = new LogicsCreaSquadraImpl();
		
		
		// Mi occupo del frame principale 
		contentPane.setLayout(new BorderLayout());
		
		// utilizzo gbc per la disposizione a griglia nel panel sud del frame principale
		gbc.insets = new Insets(8,0,8,8);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		lblmodSel = new JLabel("Modulo selezionato: ");
		panelSud.add(lblmodSel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		lblmoduloSelect = new JLabel("" + log.getModulo());
		panelSud.add(lblmoduloSelect,gbc);
		
		gbc.insets = new Insets(30,5,30,5);
		gbc.gridx = 5;
		gbc.gridy = 0;
		
		buttonIniziaTorneo = new JButton("Inizia Torneo");
		buttonIniziaTorneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelSud.add(buttonIniziaTorneo,gbc);
		
		// aggiungo il pannello sud al frame principale
		contentPane.add(panelSud, BorderLayout.SOUTH);
		
		//mi occupo del frame principale --> dei giocatori
		this.buttonsAtt = new JButton[log.getNumAtt()];
		this.buttonsCen = new JButton[log.getNumCen()];
		this.buttonsDif = new JButton[log.getNumDif()];
		this.buttonPor = new JButton("P");
		
		//gbc.insets = new Insets(40,20,40,20);
		
		/*se l'attaccante è uno solo si posiziona al centro dell'attacco
		  se sono 2 scalano di una colonna 
		if (log.getNumAtt() == 1) {
			gbc.gridx = log.getNumCen() / 2;
		} else if (log.getNumAtt() == 2) {
			gbc.gridx = 1;
		} else {
			gbc.gridx = 0;
		}
		*/
		gbc.gridx = this.getGbcX("A");
		gbc.gridy = 0;
		gbc.ipady = 10;
		for (int i=0; i<= log.getNumAtt() - 1; i++) {
			this.buttonsAtt[i] = new JButton("A");
			Dimension d = this.buttonsAtt[i].getPreferredSize();
			this.buttonsAtt[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.panelCenter.add(this.buttonsAtt[i],gbc);
			gbc.gridx++;
		}
		
		//gbc.gridx = (log.getNumCen() == 3 && log.getNumDif() == 5) ? 1 : 0;
		gbc.gridx = this.getGbcX("C");
		gbc.gridy = gbc.gridy + 3;
		for (int i=0; i<= log.getNumCen() - 1; i++) {
			this.buttonsCen[i] = new JButton("C");
			Dimension d = this.buttonsCen[i].getPreferredSize();
			this.buttonsCen[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.panelCenter.add(this.buttonsCen[i],gbc);
			gbc.gridx++;
		}
		
		//gbc.gridx = (log.getNumDif() == 3 && log.getNumCen() > 4) ? 1 : 0;
		gbc.gridx = this.getGbcX("D");
		gbc.gridy = gbc.gridy + 3;
		for (int i=0; i<= log.getNumDif() - 1; i++) {
			this.buttonsDif[i] = new JButton("D");
			Dimension d = this.buttonsDif[i].getPreferredSize();
			this.buttonsDif[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.panelCenter.add(this.buttonsDif[i],gbc);
			gbc.gridx++;
		}
		
		/* se l'attaccante è uno solo, il portiere è nella sua stessa linea
		   se la difesa è a 3 o 4 o 5 sta al centro della difesa 
		if (log.getNumAtt() == 1) {
			gbc.gridx = log.getNumCen() / 2;
		} else if (log.getNumDif() == 3 && log.getNumCen() > 4) {
			gbc.gridx = 2;
		} else if (log.getNumDif() == 5) {
			gbc.gridx = log.getNumDif() / 2;
		} else {
			gbc.gridx = (log.getNumDif() / 2) -1;
		}
		*/
		gbc.gridx = this.getGbcX("P");
		gbc.gridy = gbc.gridy + 3;
		this.buttonPor = new JButton("P");
		Dimension d = this.buttonPor.getPreferredSize();
		this.buttonPor.setPreferredSize(new Dimension(d.width*2,d.height*2));
		this.panelCenter.add(this.buttonPor,gbc);
		
		
		// aggiungo il pannello Center al frame principale
		this.panelCenter.setBackground(new Color(0,128,128));
		d = this.panelCenter.getPreferredSize();
		this.panelCenter.setPreferredSize(new Dimension(d.width*2, d.height*2));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		
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
					//System.out.println(buttonSelect);
				}
			});
			panelModuloNorth.add(buttons[i],gbc);
			gbc.gridx++;
			if (gbc.gridx == CreaSquadra.MAX_FOR_ROW) {
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}
		
		// quando premo il tasto OK salvo nella variabile log.moduloSelct il modulo scelto
		final JButton buttonOk = new JButton("OK");
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!buttonSelect.isEmpty()) {
					log.setModulo(buttonSelect.get(buttonSelect.size()-1));
				}
				//System.out.println(log.getModulo());
				changeButtonModulo();
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
			
	}
	
	
	public void changeButtonModulo() {
		gbc.insets = new Insets(8,0,8,8);
		gbc.gridx = 1;
		gbc.gridy = 0;		
		panelSud.remove(lblmoduloSelect);
		lblmoduloSelect = new JLabel("" + log.getModulo());
		panelSud.add(lblmoduloSelect,gbc);
		panelSud.validate();		
	}
	
	public int getGbcX(String s) {
		switch (s) {
		case "A":
			if (this.log.getNumAtt() == 1) {
				return this.log.getNumCen() / 2;
			} else if (this.log.getNumAtt() == 2) {
				return 1;
			} else {
				return 0;
			}
		case "C":
			return (this.log.getNumCen() == 3 && this.log.getNumDif() == 5) ? 1 : 0;
		case "D":
			return (this.log.getNumDif() == 3 && this.log.getNumCen() > 4) ? 1 : 0;	
		case "P":
			if (this.log.getNumAtt() == 1) {
				return this.log.getNumCen() / 2;
			} else if (this.log.getNumDif() == 3 && this.log.getNumCen() > 4) {
				return 2;
			} else if (this.log.getNumDif() == 5 || this.log.getNumDif() == 3) {
				return this.log.getNumDif() / 2;
			} else {
				return (this.log.getNumDif() / 2) -1;
			} 
		default:
			return 0;
		}
	}
	
}
