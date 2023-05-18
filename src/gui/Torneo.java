package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Torneo extends Base{

	JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel panelCenter = new JPanel(new GridLayout());
	JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	
	public Torneo() {
		
		this.contentPane.setLayout(new BorderLayout());
		
		
		
		
		this.panelSud.setBackground(new Color(0,64,128));
		this.panelCenter.setBackground(new Color(0,64,128));
		this.panelNord.setBackground(new Color(0,128,128));
		this.add(panelSud,BorderLayout.SOUTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNord, BorderLayout.NORTH);
		
	}
	
	
}
