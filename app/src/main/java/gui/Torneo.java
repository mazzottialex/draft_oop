package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	
	public Torneo(Squadra squadra, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
					
		this.logTor = new LogicsTorneoImpl(squadra, li);
		
		this.listAvversarie = logTor.getListAvversari();
		
		this.contentPane.setLayout(new BorderLayout());
		
		// Aggiungo il bottone Simula nel panelSud
		JButton buttonSimula = new JButton("Simula");
		buttonSimula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		this.panelSud.add(buttonSimula);
		
		//Mi occupo del PanelCenter
		p5.setBorder(new EmptyBorder(20,5,20,5));
		g5.setHgap(5);
		p4.setBorder(new EmptyBorder(20,20,20,20));
		g4.setHgap(40);
		p3.setBorder(new EmptyBorder(20,40,20,40));
		g3.setHgap(140);
		p2.setBorder(new EmptyBorder(20,200,20,200));
		g2.setHgap(400);
		p1.setBorder(new EmptyBorder(20,450,20,450));
		p0.setBorder(new EmptyBorder(20,450,20,450));
		
		//Aggiungo le varie squadre nel panel5 (la prima è sempre quella dell'utente)
		this.buttonsp5[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra());
		this.p5.add(this.buttonsp5[0]);
		Collections.shuffle(this.logTor.getListAvversari());
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
		
		
		/*
		final JButton b1 = new JButton("squadra 1");
		final JButton b2 = new JButton("squadra 2");
		final JButton b3 = new JButton("squadra 3");
		final JButton b4 = new JButton("squadra 4");
		final JButton b5 = new JButton("squadra 5");
		final JButton b6 = new JButton("squadra 6");
		final JButton b7 = new JButton("squadra 7");
		final JButton b8 = new JButton("squadra 8");
		final JButton b9 = new JButton("squadra 9");
		final JButton b10 = new JButton("squadra 10");
		final JButton b11 = new JButton("squadra 11");
		final JButton b12 = new JButton("squadra 12");
		final JButton b13 = new JButton("squadra 13");
		final JButton b14 = new JButton("squadra 14");
		final JButton b15 = new JButton("squadra 15");
		final JButton b16 = new JButton("squadra 16");
		p5.add(b1);
		p5.add(b2);
		p5.add(b3);
		p5.add(b4);
		p5.add(b5);
		p5.add(b6);
		p5.add(b7);
		p5.add(b8);
		p5.add(b9);
		p5.add(b10);
		p5.add(b11);
		p5.add(b12);
		p5.add(b13);
		p5.add(b14);
		p5.add(b15);
		p5.add(b16);
		final JButton c1 = new JButton("squadra 1");
		final JButton c3 = new JButton("squadra 1");
		final JButton c5 = new JButton("squadra 1");
		final JButton c7 = new JButton("squadra 1");
		final JButton c9 = new JButton("squadra 1");
		final JButton c11 = new JButton("squadra 1");
		final JButton c13 = new JButton("squadra 1");
		final JButton c15 = new JButton("squadra 1");
		p4.add(c1);
		p4.add(c3);
		p4.add(c5);
		p4.add(c7);
		p4.add(c9);
		p4.add(c11);
		p4.add(c13);
		p4.add(c15);
		final JButton d1 = new JButton("squadra 1");
		final JButton d2 = new JButton("squadra 1");
		final JButton d3 = new JButton("squadra 1");
		final JButton d4 = new JButton("squadra 1");
		p3.add(d1);
		p3.add(d2);
		p3.add(d3);
		p3.add(d4);
		final JButton e1 = new JButton("squadra 1");
		final JButton e2 = new JButton("squadra 1");
		p2.add(e1);
		p2.add(e2);
		final JButton f1 = new JButton("squadra 1");
		p1.add(f1);
		*/
		
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
			this.buttonsp4[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.listAvversarie.get(0).getNomeSquadra());
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
			// (ora faccio finta che vinca sempre la squadra dell'utente poi dovrò cambiare) 
			this.buttonsp3[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
			this.p3.add(this.buttonsp3[0]);
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
			int cont1 = 1;
			this.buttonsp3[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.listAvversarie.get(0).getNomeSquadra());
			this.p3.add(this.buttonsp3[0]);
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
			this.buttonsp2[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
			this.p2.add(this.buttonsp2[0]);
			this.buttonsp2[1] = new JButton(this.logTor.getListAvversari().get(1).getNomeSquadra() + " - " + this.logTor.getListAvversari().get(2).getNomeSquadra());
			this.p2.add(this.buttonsp2[1]);

			this.p2.validate();
			this.p3.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 2:
			this.p2.removeAll();
			this.p2.repaint();
			
			// metto i risultati nel panel 2 (ora non ho quelli della squadraUtente)
			this.buttonsp2[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra() + " - " + this.listAvversarie.get(0).getNomeSquadra());
			this.p2.add(this.buttonsp2[0]);
			
			String squad1 = new String(this.listAvversarie.get(1).getNomeSquadra());
			var ris1 = this.logTor.getRisultati().get(squad1);
			String squad2 = new String(this.listAvversarie.get(2).getNomeSquadra());
			var ris2 = this.logTor.getRisultati().get(squad2);
			this.buttonsp2[1] = new JButton(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
			this.p2.add(this.buttonsp2[1]);
			
			// aggiungo la nuova partita ...
			// ...
			
			
			this.p1.validate();
			this.p2.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 1:
			//...
			break;
		}
		
	}
	
	
}
