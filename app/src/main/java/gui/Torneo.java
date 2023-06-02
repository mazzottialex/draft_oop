package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraAvversaria;
import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;
import logics.LogicsImpostazioni;
import logics.LogicsImpostazioniImpl;
import logics.LogicsTorneo;
import logics.LogicsTorneoImpl;
import simulation.SimulatingMatchImpl;
import v2.gui.DialogScelta;

public class Torneo extends Base{

	LogicsTorneo logTor;
	JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	GridLayout gl = new GridLayout(6,1);
	JPanel panelCenter = new JPanel(gl);
	JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
	GridLayout g0 = new GridLayout(1,1);
	GridLayout g1 = new GridLayout(1,1);
	GridLayout g2 = new GridLayout(1,2);
	GridLayout g3 = new GridLayout(1,4);
	GridLayout g4 = new GridLayout(1,8);
	GridLayout g5 = new GridLayout(1,16);
	JPanel p0 = new JPanel(g0);  //Panel a riga 0 (squadra vincitrice)
	JPanel p1 = new JPanel(g1);  //Panel a riga 1 --> 2 squadre --> 1 partita
	JPanel p2 = new JPanel(g2);	 //Panel a riga 2 --> 4 squadre --> 2 partite
	JPanel p3 = new JPanel(g3);	 //Panel a riga 3 --> 8 squadre ---> 4 partite
	JPanel p4 = new JPanel(g4);  //Panel a riga 4 --> 16 squadre --> 8 partite
	JPanel p5 = new JPanel(g5);  //Panel a riga 5 --> 16 squadre
	JButton[] buttonsp5 = new JButton[16];
	JButton[] buttonsp4 = new JButton[8];
	JButton[] buttonsp3 = new JButton[4];
	JButton[] buttonsp2 = new JButton[2];
	JButton buttonp1 = new JButton();
	JButton buttonp0 = new JButton();
	private List<SquadraAvversaria> listAvversarie;
	int risSquadraUte = 0;
	int risSquadraAvv = 0;
	//boolean eliminatedThisTurn = false;
	
	
	public Torneo(Squadra squadra, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
					
		this.logTor = new LogicsTorneoImpl(squadra, li);
		
		this.listAvversarie = logTor.getListAvversari();
		
		this.contentPane.setLayout(new BorderLayout());
		
		// Aggiungo il bottone Simula nel panelSud
		JButton buttonSimula = new JButton("Simula");
		buttonSimula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//eliminatedThisTurn = false;
				logTor.setElimThisTurn(false);
				
				if (!logTor.getEliminated()) {
					Partita p;
					
					try {
						p = new Partita(logTor.getMiaSquadra(), logTor.getListAvversari().get(0));
						p.createAndShowGUI();
						p.addWindowListener(new WindowAdapter() {
							public void windowClosed(WindowEvent e) {
								if (p.getWinner() != logTor.getMiaSquadra()) {
									logTor.setEliminated(true);
									logTor.setSquadraAvv(logTor.getListAvversari().get(0));
									//eliminatedThisTurn = true;
									logTor.setElimThisTurn(true);
								}
								//System.out.println(p.getGolS1());
								//System.out.println(p.getWinner().getNomeSquadra());
								//System.out.println(p.getGolS2());
								risSquadraUte = p.getGolS1();
								risSquadraAvv = p.getGolS2();	
								//risSquadraUte = 1;
								//risSquadraAvv = 2;	
								
								try {
									logTor.simulaMatch();
									createLevel();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						logTor.simulaMatch();
						createLevel();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					/*
					JPanel panel=(JPanel) btn.getParent();
		            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					Partita dialog;
					try {
						dialog = new Partita(parent, true, logTor.getMiaSquadra(), logTor.getListAvversari().get(0));
						dialog.createAndShowGUI();
						dialog.setVisible(true);	
						if (dialog.getWinner() != logTor.getMiaSquadra()) {
							logTor.setEliminated(true);
							logTor.setSquadraAvv(logTor.getListAvversari().get(0));
							eliminatedThisTurn = true;
						}
						risSquadraUte = dialog.getGolS1();
						risSquadraAvv = dialog.getGolS2();	
						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}
				*/
				/*
				try {
					logTor.simulaMatch();
					createLevel();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
			}
			
		});
		this.panelSud.add(buttonSimula);
		
		//Mi occupo del PanelCenter
		p5.setBorder(new EmptyBorder(20,5,20,5));
		g5.setHgap(5);
		p4.setBorder(new EmptyBorder(20,20,20,20));
		g4.setHgap(40);
		p3.setBorder(new EmptyBorder(20,40,20,40));
		g3.setHgap(140);
		p2.setBorder(new EmptyBorder(20,180,20,180));
		g2.setHgap(300);
		p1.setBorder(new EmptyBorder(20,450,20,450));
		p0.setBorder(new EmptyBorder(20,450,20,450));
		
		//Aggiungo le varie squadre nel panel5 (la prima è sempre quella dell'utente)
		this.buttonsp5[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra());
		this.p5.add(this.buttonsp5[0]);
		//Collections.shuffle(this.logTor.getListAvversari());
		for (int i=0;i<this.logTor.getListAvversari().size();i++) {
			this.buttonsp5[i+1] = new JButton(this.logTor.getListAvversari().get(i).getNomeSquadra());
			this.p5.add(this.buttonsp5[i+1]);
		}
		
		//System.out.println(this.logTor.getListAvversari().size());
		int cont = 1;
		this.buttonsp4[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
		this.p4.add(this.buttonsp4[0]);
		for (int i=1;i<this.logTor.getListAvversari().size() - 1;i = i + 2, cont++) {
			this.buttonsp4[cont] = new JButton(this.logTor.getListAvversari().get(i).getNomeSquadra() + " - " + this.logTor.getListAvversari().get(i+1).getNomeSquadra());
			this.p4.add(this.buttonsp4[cont]);
		}
		
		
		p0.setBackground(new Color(0,64,128));
		p1.setBackground(new Color(0,64,128));
		p2.setBackground(new Color(0,64,128));
		p3.setBackground(new Color(0,64,128));
		p4.setBackground(new Color(0,64,128));
		p5.setBackground(new Color(0,64,128));
		
		this.panelCenter.add(p0);
		this.panelCenter.add(p1);
		this.panelCenter.add(p2);
		this.panelCenter.add(p3);
		this.panelCenter.add(p4);
		this.panelCenter.add(p5);
		
		this.panelSud.setBackground(new Color(0,64,128));
		this.panelCenter.setBackground(new Color(0,64,128));
		this.panelNord.setBackground(new Color(0,128,128));
		this.panelNord.setBounds(200, 300, 200, 300);
		this.contentPane.add(panelSud,BorderLayout.SOUTH);
		this.contentPane.add(panelCenter, BorderLayout.CENTER);
		this.contentPane.add(panelNord, BorderLayout.NORTH);
		
	}
	
	public void createLevel() {
		final int numSquadre = logTor.getNumSquadre();
		switch (numSquadre) {
		case 8: 
			
			this.p4.removeAll();
			this.p4.repaint();
			
			// mettere il risultato della partita della squadra utente (prima da fare in Logic)
			// metto i risultati nelle partite che si svolgono
			int cont = 1;
			this.buttonsp4[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " + " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
			
			this.p4.add(this.buttonsp4[0]);
			for (int i=1;i<this.listAvversarie.size() - 1;i = i + 2, cont++) {
				String squad1 = new String(this.listAvversarie.get(i).getNomeSquadra());
				var ris1 = this.logTor.getRisultati().get(squad1);
				String squad2 = new String(this.listAvversarie.get(i+1).getNomeSquadra());
				var ris2 = this.logTor.getRisultati().get(squad2);
				this.buttonsp4[cont] = new JButton(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
				this.p4.add(this.buttonsp4[cont]);
			}
			
			
			
			// ... creo il panel 3 con le squadre che hanno vinto 
			if (!logTor.getEliminated()) {
				this.buttonsp3[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p3.add(this.buttonsp3[0]);
			} else {
				this.buttonsp3[0] = new JButton(this.logTor.getSquadraAvv().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p3.add(this.buttonsp3[0]);
			}
			cont = 1;
			for (int i=1;i<this.logTor.getListAvversari().size() - 1;i = i + 2, cont++) {
				this.buttonsp3[cont] = new JButton(this.logTor.getListAvversari().get(i).getNomeSquadra() + " - " + this.logTor.getListAvversari().get(i+1).getNomeSquadra());
				this.p3.add(this.buttonsp3[cont]);
			}
			
			
			this.p3.validate();
			this.p4.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
			
		case 4:
			this.p3.removeAll();
			this.p3.repaint();
			
			// mettere il risultato della partita della squadra utente (prima da fare in Logic)
			// metto i risultati nelle partite che si svolgono
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				this.buttonsp3[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " + " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p3.add(this.buttonsp3[0]);
				
			} else {
				int ris1 =this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris2 =this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.buttonsp3[0] = new JButton(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris1 + " " + " - " + " " + ris2 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p3.add(this.buttonsp3[0]);
				if (ris2 > ris1) {
					this.logTor.setSquadraAvv(this.listAvversarie.get(0));
				}
			}
			int cont1 = 1;
			for (int i=1;i<this.listAvversarie.size() - 1;i = i + 2, cont1++) {

				String squad1 = new String(this.listAvversarie.get(i).getNomeSquadra());
				var ris1 = this.logTor.getRisultati().get(squad1);
				String squad2 = new String(this.listAvversarie.get(i+1).getNomeSquadra());
				var ris2 = this.logTor.getRisultati().get(squad2);
				this.buttonsp3[cont1] = new JButton(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
				this.p3.add(this.buttonsp3[cont1]);
			}
			
			// ... creo il panel 2 con le squadre che hanno vinto 
			// (ora faccio finta che vinca sempre la squadra dell'utente poi dovrò cambiare) 
			if (!logTor.getEliminated()) {
				this.buttonsp2[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p2.add(this.buttonsp2[0]);
			} else {
				this.buttonsp2[0] = new JButton(this.logTor.getSquadraAvv().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p2.add(this.buttonsp2[0]);
			}
			this.buttonsp2[1] = new JButton(this.logTor.getListAvversari().get(1).getNomeSquadra() + " - " + this.logTor.getListAvversari().get(2).getNomeSquadra());
			this.p2.add(this.buttonsp2[1]);

			this.logTor.getRisMatch().clear();
			this.p2.validate();
			this.p3.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 2:
			this.p2.removeAll();
			this.p2.repaint();
			
			// metto i risultati nel panel 2 (ora non ho quelli della squadraUtente)
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				System.out.println("entrato 1");
				this.buttonsp2[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " + " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p2.add(this.buttonsp2[0]);
			} else {
				int ris1 =this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris2 =this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.buttonsp2[0] = new JButton(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris1 + " " + " - " + " " + ris2 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p2.add(this.buttonsp2[0]);
				if (ris2 > ris1) {
					this.logTor.setSquadraAvv(this.listAvversarie.get(0));
				}
			}
			String squad1 = new String(this.listAvversarie.get(1).getNomeSquadra());
			var ris1 = this.logTor.getRisultati().get(squad1);
			String squad2 = new String(this.listAvversarie.get(2).getNomeSquadra());
			var ris2 = this.logTor.getRisultati().get(squad2);
			this.buttonsp2[1] = new JButton(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
			this.p2.add(this.buttonsp2[1]);
			
			// aggiungo la nuova partita ...
			if (!logTor.getEliminated()) {
				this.buttonp1 = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p1.add(this.buttonp1);
			} else {
				this.buttonp1 = new JButton(this.logTor.getSquadraAvv().getNomeSquadra()+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p1.add(this.buttonp1);
			}
			
			this.logTor.getRisMatch().clear();
			this.p1.validate();
			this.p2.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 1:
			this.p1.removeAll();
			this.p1.repaint();
			
			// metto i risultati nel panel 1
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				this.buttonp1 = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " + " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p1.add(this.buttonp1);
			} else {
				int ris11 =this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris21 =this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.buttonp1 = new JButton(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris11 + " " + " - " + " " + ris21 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p1.add(this.buttonp1);
			}
			
			String winner = new String();
			
			if (this.logTor.getElimThisTurn()) {
				this.buttonp0 = new JButton(this.logTor.getSquadraAvv().getNomeSquadra());
				winner = this.logTor.getSquadraAvv().getNomeSquadra();
			} else if (!logTor.getEliminated()) {
				this.buttonp0 = new JButton(this.logTor.getMiaSquadra().getNomeSquadra());
				winner = this.logTor.getMiaSquadra().getNomeSquadra();
			} else {
				this.buttonp0 = new JButton(this.logTor.getWinner());
				winner = this.logTor.getWinner();
			}
			this.p0.add(this.buttonp0);
			
			// da mettere a posto
			JLabel label = new JLabel(winner + " HA VINTO IL TORNEO!!!!");
			this.panelNord.add(label);
			
			
			this.logTor.setNumSquadre(0);
			this.panelNord.validate();
			this.p0.validate();
			this.p1.validate();
			this.panelCenter.validate();
			break;
		}
		
	}
	
	
}