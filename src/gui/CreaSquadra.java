package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import data.Modulo;
import data.Calciatore;
import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;

public class CreaSquadra extends Base{

	private final static int MAX_FOR_ROW = 2;
	private final static int NUM_PLAYER = 5;
	
	private LogicsCreasquadra log;
	final JFrame frameModulo;
	final JFrame frameCalciatori;
	final JPanel panelSud = new JPanel(new GridBagLayout()); //panel Sud del frame principale
	final JPanel panelCenter = new JPanel(new GridBagLayout()); // Panel Center del frame principale 
	final GridBagConstraints gbc = new GridBagConstraints();
	final JLabel lblmodSel;
	JLabel lblmoduloSelect;
	final JButton buttonIniziaTorneo;
	JButton[] buttonsAtt;
	JButton[] buttonsCen;
	JButton[] buttonsDif;
	JButton buttonPor;
	JButton[] buttonsPlayer;
	JPanel panelCalciatoriCenter;
	JButton buttonSelect;
	
	public CreaSquadra(String nomeSquadra, String stemma, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		
		this.log = new LogicsCreaSquadraImpl(nomeSquadra, stemma, li);
		
		//inizializzo il bottone per i giocatori che seleziono
		this.buttonSelect = new JButton();
		
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
				
				List<Calciatore> riserve = new ArrayList<>();
				riserve.addAll(log.getRandom("P", 2));
				riserve.addAll(log.getRandom("D", 3));
				riserve.addAll(log.getRandom("C", 3));
				riserve.addAll(log.getRandom("A", 3));
				try {
					changeJPanel(new Torneo(log.getSquadra(), li));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frameCalciatori.setVisible(false);
				frameModulo.setVisible(false);
				/*
				if (log.teamComplete()) {
					
					changeJPanel(new Torneo());
					frameCalciatori.setVisible(false);
					frameModulo.setVisible(false);
					// ...
				} else {
					final JFrame err = new JFrame("ERRORE");
					err.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					final JDialog prova = new JDialog(err, "ERRORE");
					prova.setLayout(new FlowLayout());
					prova.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					final JLabel label = new JLabel("NON HAI COMPLETATO LA SQUADRA");
					final JButton buttonOk = new JButton("OK");
					buttonOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WindowEvent close = new WindowEvent(prova, WindowEvent.WINDOW_CLOSING);
							prova.dispatchEvent(close);
						}
					});
					prova.add(label);
					prova.add(buttonOk);
					//err.add(prova);
					//err.setBounds( 300, 300, 200, 200);
					//err.setVisible(true);
					prova.setBounds( 300, 300, 250, 100);
					prova.setVisible(true);
				}
				*/
			}
		});
		panelSud.add(buttonIniziaTorneo,gbc);
		
		// aggiungo il pannello sud al frame principale
		contentPane.add(panelSud, BorderLayout.SOUTH);
		
		
		// mi occupo del panelCenter del frame principale --> quello con i giocatori
		this.panelCenter.setBackground(new Color(0,64,128));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		//disegno il modulo nel frame principale direttamente con questa funzione
		changeModulo();
		
		
		// Creo 2 frame aggiuntivi, uno per modulo e uno per calciatori
		this.frameModulo = new JFrame("Seleziona modulo: ");
		this.frameCalciatori = new JFrame("Seleziona calciatori: ");
		this.frameModulo.setBounds(this.getX() + 520, this.getY(), 200, 300);
		this.frameCalciatori.setBounds(this.getX() + 520 ,this.getY() + 300, 200, 300);
		
		//Mi occupo del frame Modulo
		final JPanel panelModulo = new JPanel(new BorderLayout());
		panelModulo.setBackground(new Color(0,64,128));
		this.frameModulo.add(panelModulo);
		final JPanel panelModuloCenter = new JPanel(new GridBagLayout());
		final JPanel panelModuloSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelModuloCenter.setBackground(new Color(0,64,128));
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
			panelModuloCenter.add(buttons[i],gbc);
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
				log.clearTeam();
				//System.out.println(log.getModulo());
				changeButtonModulo();
				changeModulo();
				frameModulo.setVisible(false);
			}	
		});
		panelModuloSouth.add(buttonOk);	
		
		panelModulo.add(panelModuloCenter, BorderLayout.CENTER);
		panelModulo.add(panelModuloSouth, BorderLayout.SOUTH);
		
		
		//Mi occupo del frame Calciatori
		final JPanel panelCalciatori = new JPanel(new BorderLayout());
		panelCalciatori.setBackground(new Color(0,64,128));
		this.frameCalciatori.add(panelCalciatori);
		panelCalciatoriCenter = new JPanel(new GridBagLayout());
		final JPanel panelCalciatoriSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCalciatoriCenter.setBackground(new Color(0,64,128));
		panelCalciatoriSouth.setBackground(new Color(0,64,128));
		final JButton buttonOkCalciatori = new JButton("OK");
		buttonOkCalciatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (log.getposSelect() != -1) {
					log.addPlayerInTeam(log.getCalciatoreSelect());
					changeButtonPlayer(log.getRuoloSelect(), log.getposSelect());
					panelCalciatoriCenter.removeAll();
					panelCalciatoriCenter.repaint();
					panelCalciatoriCenter.validate();
				}
			}
		});
		panelCalciatoriSouth.add(buttonOkCalciatori);
		
		panelCalciatori.add(panelCalciatoriCenter, BorderLayout.CENTER);
		panelCalciatori.add(panelCalciatoriSouth, BorderLayout.SOUTH);
		
		// Setto le impostazioni dei due frame aggiuntivi
		this.frameModulo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameCalciatori.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.frameModulo.setSize(300,300);
		this.frameCalciatori.setSize(300,350);
		this.frameModulo.setVisible(true);
		this.frameCalciatori.setVisible(true);
			
	}
	
	
	public void changeButtonModulo() {
		//gbc.insets = new Insets(8,0,8,8);
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
	
	
	/* metodo che crea la disposizione dei giocaotri nel frame principale */
	public void changeModulo() {
		this.panelCenter.removeAll();;
		this.panelCenter.repaint();
		
		this.buttonsAtt = new JButton[log.getNumAtt()];
		this.buttonsCen = new JButton[log.getNumCen()];
		this.buttonsDif = new JButton[log.getNumDif()];
		this.buttonPor = new JButton("P");		
		//gbc.insets = new Insets(40,20,40,20);	
		gbc.insets = new Insets(30,5,30,5);
		//ATTACCO
		gbc.gridx = this.getGbcX("A");
		gbc.gridy = 0;
		gbc.ipady = 10;
		for (int i=0; i<= log.getNumAtt() - 1; i++) {
			this.buttonsAtt[i] = new JButton("A");
			Dimension d = this.buttonsAtt[i].getPreferredSize();
			this.buttonsAtt[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.buttonsAtt[i].setBackground(Color.RED);
			final int ind = i;
			this.buttonsAtt[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choosePlayer("A",ind);
				}
			});
			this.panelCenter.add(this.buttonsAtt[i],gbc);
			gbc.gridx++;
		}
		//CENTROCAMPO
		gbc.gridx = this.getGbcX("C");
		gbc.gridy = gbc.gridy + 3;
		gbc.ipady = 10;
		for (int i=0; i<= log.getNumCen() - 1; i++) {
			this.buttonsCen[i] = new JButton("C");
			Dimension d = this.buttonsCen[i].getPreferredSize();
			this.buttonsCen[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.buttonsCen[i].setBackground(Color.GREEN);
			final int ind = i;
			this.buttonsCen[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choosePlayer("C",ind);
				}
			});
			this.panelCenter.add(this.buttonsCen[i],gbc);
			gbc.gridx++;
		}
		//DIFESA
		gbc.gridx = this.getGbcX("D");
		gbc.gridy = gbc.gridy + 3;
		gbc.ipady = 10;
		for (int i=0; i<= log.getNumDif() - 1; i++) {
			this.buttonsDif[i] = new JButton("D");
			Dimension d = this.buttonsDif[i].getPreferredSize();
			this.buttonsDif[i].setPreferredSize(new Dimension(d.width*2,d.height*2));
			this.buttonsDif[i].setBackground(Color.CYAN);
			final int ind = i;
			this.buttonsDif[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choosePlayer("D",ind);
				}
			});
			this.panelCenter.add(this.buttonsDif[i],gbc);
			gbc.gridx++;
		}
		//PORTIERE
		gbc.gridx = this.getGbcX("P");
		gbc.gridy = gbc.gridy + 3;
		this.buttonPor = new JButton("P");
		Dimension d = this.buttonPor.getPreferredSize();
		this.buttonPor.setPreferredSize(new Dimension(d.width*2,d.height*2));
		this.buttonPor.setBackground(Color.YELLOW);
		this.buttonPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosePlayer("P", 0);
			}
		});
		this.panelCenter.add(this.buttonPor,gbc);
		
		this.panelCenter.validate();
	}
	
	
	
	/* metodo che crea i 5 bottoni nel frame frameCalciatori*/
	public void choosePlayer(String ruolo, int pos) {
		this.panelCalciatoriCenter.removeAll();
		this.panelCalciatoriCenter.repaint();
		List<Calciatore> list = this.log.getRandom(ruolo, NUM_PLAYER);
		//System.out.println(list);
		//System.out.println("lunghezza lista: " + list.size());
		
		//creo i 5 bottoni nel frame calciatori 
		this.buttonsPlayer = new JButton[CreaSquadra.NUM_PLAYER];
		//gbc.insets = new Insets(5,5,5,5);
		//gbc.ipadx = 30;
		//gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 0;
		for (int i = 0; i < this.buttonsPlayer.length; i++) {
			this.buttonsPlayer[i] = new JButton("" + list.get(i).getNominativo() + " " + list.get(i).getRating().getX());
			this.panelCalciatoriCenter.add(this.buttonsPlayer[i],gbc);
			this.buttonsPlayer[i].setBackground(getColorByRuolo(ruolo));
			final int ind = i;
			this.buttonsPlayer[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					log.setNameString(list.get(ind).getNominativo());
					log.setCalciatoreSelect(list.get(ind));
					log.setRuoloSelect(ruolo);
					log.setposSelect(pos);
					log.setRating(list.get(ind).getRating().getX());
					//changeButtonPlayer(ruolo, pos);
				}
			});
			gbc.gridx++;
			if (gbc.gridx == CreaSquadra.MAX_FOR_ROW) {
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}
		
		this.panelCalciatoriCenter.validate();
		
	}
	
	
	/* metodo per cambiare la formazione nel frame principale
	 * mettendo al posto dei vari bottoni il nome del calciatore scelto*/
	
	public void changeButtonPlayer(String ruolo, int pos) {
		String s = new String();
		switch (ruolo) {
		case "A":
			s = textFormat(log.getNamePlayer() + " " + log.getRating());
			this.buttonsAtt[pos].setText(s);
			this.buttonsAtt[pos].setHorizontalTextPosition(SwingConstants.LEFT);
			if (this.buttonsAtt[pos].getActionListeners().length != 0) {
				this.buttonsAtt[pos].removeActionListener(this.buttonsAtt[pos].getActionListeners()[0]);
			}
			break;
		case "C":
			s = textFormat(log.getNamePlayer() + " " + log.getRating());
			this.buttonsCen[pos].setText(s);
			if (this.buttonsCen[pos].getActionListeners().length != 0) {
				this.buttonsCen[pos].removeActionListener(this.buttonsCen[pos].getActionListeners()[0]);
			}
			break;
		case "D":
			s = textFormat(log.getNamePlayer() + " " + log.getRating());
			this.buttonsDif[pos].setText(s);
			if (this.buttonsDif[pos].getActionListeners().length != 0) {
				this.buttonsDif[pos].removeActionListener(this.buttonsDif[pos].getActionListeners()[0]);
			}
			break;
		case "P":
			s = textFormat(log.getNamePlayer() + " " + log.getRating());
			this.buttonPor.setText(s);
			if (this.buttonPor.getActionListeners().length != 0) {
				this.buttonPor.removeActionListener(this.buttonPor.getActionListeners()[0]);
			}
			break;
		}
	
		this.panelCenter.validate();
		
	}
	
	
	public Color getColorByRuolo(String ruolo) {
		switch (ruolo) {
		case "A":
			return Color.RED;
		case "C":
			return Color.GREEN;	
		case "D":
			return Color.CYAN;
		case "P":
			return Color.YELLOW;
		default:
			return null;
		}
	}
	
	public String textFormat(String s) {	
		String label = "<html>";
		for (int i=0;i<s.length() - 2;i++) {
			label = label + s.charAt(i);
			if ((i % 14 == 0) && (i!= 0)) {
				label = label + "<br>";
			}
		}
		label = label + s.charAt(s.length()-2) + s.charAt(s.length()-1);
		return label;
	}
	
	
}
