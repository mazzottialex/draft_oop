package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import logics.LogicsCreaSquadraImpl;
import logics.LogicsCreasquadra;
import logics.LogicsImpostazioni;
import logics.LogicsImpostazioniImpl;
import logics.LogicsTorneo;
import logics.LogicsTorneoImpl;

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
	
	
	
	
	public Torneo(String nomeSquadra, String stemma, List<Calciatore> titolari, List<Calciatore> riserve) throws FileNotFoundException, ClassNotFoundException, IOException {
					
		this.logTor = new LogicsTorneoImpl("2022-2023", nomeSquadra, stemma, titolari, riserve);
		
		this.contentPane.setLayout(new BorderLayout());
		
		// Aggiungo il bottone Simula nel panelSud
		JButton buttonSimula = new JButton("Simula");
		// ... (Action Listener)
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
	
	
}
