package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Torneo extends Base{

	JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	GridLayout gl = new GridLayout(5,1);
	JPanel panelCenter = new JPanel(gl);
	JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	
	public Torneo() {
		
		this.contentPane.setLayout(new BorderLayout());
		
		// Aggiungo il bottone Simula nel panelSud
		JButton buttonSimula = new JButton("Simula");
		// ... (Action Listener)
		this.panelSud.add(buttonSimula);
		
		//Mi occupo del PanelCenter
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
		
		
		
		
		
		
		
		
		
		p1.setBackground(new Color(0,128,128));
		p2.setBackground(new Color(0,128,128));
		p3.setBackground(new Color(0,128,128));
		p4.setBackground(new Color(0,128,128));
		p5.setBackground(new Color(0,128,128));
		
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
