package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
	GridLayout gl = new GridLayout(5,1);
	JPanel panelCenter = new JPanel(gl);
	JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
	GridLayout g1 = new GridLayout(1,1);
	GridLayout g2 = new GridLayout(1,2);
	GridLayout g3 = new GridLayout(1,4);
	GridLayout g4 = new GridLayout(1,8);
	GridLayout g5 = new GridLayout(1,16);
	JPanel p1 = new JPanel(g1);  //Panel a riga 0 (squadra vincitrice)
	JPanel p2 = new JPanel(g2);	 //Panel a riga 1 --> 2 squadre
	JPanel p3 = new JPanel(g3);	 //Panel a riga 2 --> 4 squadre
	JPanel p4 = new JPanel(g4);  //Panel a riga 3 --> 8 squadre
	JPanel p5 = new JPanel(g5);  //Panel a riga 4 --> 16 squadre
	JButton[] buttonsp5 = new JButton[16];
	JButton[] buttonsp4 = new JButton[8];
	JButton[] buttonsp3 = new JButton[4];
	JButton[] buttonsp2 = new JButton[2];
	JButton buttonp1 = new JButton();
	List<SquadraAvversaria> newList = new ArrayList<>();
	
	public Torneo(Squadra squadra, List<Calciatore> li) throws FileNotFoundException, ClassNotFoundException, IOException {
					
		this.logTor = new LogicsTorneoImpl(squadra, li);
		
		this.contentPane.setLayout(new BorderLayout());
		
		// Aggiungo il bottone Simula nel panelSud
		JButton buttonSimula = new JButton("Simula");
		buttonSimula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					simulaMatch();
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
		
		//Aggiungo le varie squadre nel panel5 (la prima è sempre quella dell'utente)
		this.buttonsp5[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra());
		this.p5.add(this.buttonsp5[0]);
		Collections.shuffle(this.logTor.getListAvversari());
		for (int i=0;i<this.logTor.getListAvversari().size();i++) {
			this.buttonsp5[i+1] = new JButton(this.logTor.getListAvversari().get(i).getNomeSquadra());
			this.p5.add(this.buttonsp5[i+1]);
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
		
		p1.setBackground(new Color(0,64,128));
		p2.setBackground(new Color(0,64,128));
		p3.setBackground(new Color(0,64,128));
		p4.setBackground(new Color(0,64,128));
		p5.setBackground(new Color(0,64,128));
		
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
	
	
	public void simulaMatch() throws FileNotFoundException, ClassNotFoundException, IOException {
		final int numSquadre = logTor.getNumSquadre();
		Map<String, Integer> map = new HashMap<>(); //map per il risultato
		List<String> list = new ArrayList<>(); //lista per i nomi delle squadre che si sfidano 
		String teamWin = new String(); //nome della squadra vincente
		switch (numSquadre) {
		case 16: 
			
			for (int i = 1; i < this.buttonsp5.length - 1; i = i + 2) {
				//System.out.println((new SimulatingMatchImpl(logTor.getListAvversari().get(i),logTor.getListAvversari().get(i+1)).risultato()));
				map = new SimulatingMatchImpl(logTor.getListAvversari().get(i),logTor.getListAvversari().get(i+1)).risultato();
				list.addAll(map.keySet());			
				if (map.get(list.get(0)) >= map.get(list.get(1))) {
					teamWin = list.get(0);
				} else {
					teamWin = list.get(1);
				}			
				if (logTor.getListAvversari().get(i).getNomeSquadra().equals(teamWin)) {
					this.newList.add(logTor.getListAvversari().get(i));
				} else {
					this.newList.add(logTor.getListAvversari().get(i+1));
				}
				list.clear();
			}
			logTor.setListAvversari(this.newList);
			createLevel();
			logTor.setNumSquadre(8);
			break;
		case 8:
			// ...
			logTor.setNumSquadre(4);
			break;
		case 4:
			// ...
			logTor.setNumSquadre(2);
			break;
		case 2:
			//...
			logTor.setNumSquadre(1);
			break;
		}
	}
	
	
	public void createLevel() {
		final int numSquadre = logTor.getNumSquadre();
		switch (numSquadre) {
		case 16: 
			//Aggiungo le varie squadre nel panel4 (la prima è sempre quella dell'utente solo per ora)
			this.buttonsp4[0] = new JButton(this.logTor.getMiaSquadra().getNomeSquadra());
			this.p4.add(this.buttonsp4[0]);
			for (int i=0;i<this.logTor.getListAvversari().size();i++) {
				this.buttonsp4[i+1] = new JButton(this.logTor.getListAvversari().get(i).getNomeSquadra());
				this.p4.add(this.buttonsp4[i+1]);
			}
			break;
		case 8:
			// ...
			break;
		case 4:
			// ...
			break;
		case 2:
			//...
			break;
		}
		
	}
	
	
}
