package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Squadra;
import logics.LogicsSostituzione;
import logics.LogicsSostituzioneImpl;

public class Sostituzione extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5244133982320404420L;
	private final List<String> ruoli = List.of("P", "D", "C", "A");
	private JPanel panelSquadra = new JPanel();
	private LogicsSostituzione logics;
	private static JPanel panelTit;
	private static JPanel panelRis;
	private Partita superGui;
	
	public Sostituzione(Squadra squadra, Partita superGui) {
		logics = new LogicsSostituzioneImpl(squadra, this);
		this.superGui = superGui;
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 2, 2);
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		panelSquadra.setBackground(getForeground());
		
		JLabel lblNomeSquadra = new JLabel("Titolari");
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblNomeSquadra.setForeground(Color.white);
		panelSquadra.add(lblNomeSquadra);
		
		gbc.gridy = 0;
		contentPane.add(panelSquadra, gbc);
		
		//titolari
		JPanel panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);

		int count = 0;
		for(int i = 0; i < ruoli.size(); i++) {
			panelPosizione = new JPanel();
			for(int j = 0; j < squadra.getModulo().getN(ruoli.get(i)); j++) {
				Calciatore c = squadra.getTitolari().get(count);
				JPanel panel = (utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
				panel.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
//		                JOptionPane.showMessageDialog(null, c.getNominativo() + " -> " + c.getRuolo());
		            	if (panelTit != null) {
		            		panelTit.setBackground(null);
	                    }
		            	panelTit = panel;
		            	panelTit.setBackground(Color.YELLOW);
		            	logics.selezTit(c);
		            }
		        });
				panelPosizione.add(panel);
				count++;
			}
			gbc.gridy = i + 1;
			contentPane.add(panelPosizione, gbc);
		}
		
		JLabel lblPanchina = new JLabel("Riserve");
		lblPanchina.setForeground(Color.white);
		lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridy = 5;
		contentPane.add(lblPanchina, gbc);
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//panchinari
		panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);
		for(int j = 0; j < 7; j++) {
			Calciatore c = squadra.getRiserve().get(j);
			JPanel panel = (utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
			panel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
//	                JOptionPane.showMessageDialog(null, c.getNominativo() + " -> " + c.getRuolo());
	            	if (panelRis != null) {
	            		panelRis.setBackground(null);
                    }
	            	panelRis = panel;
	            	panelRis.setBackground(Color.YELLOW);
	            	logics.selezRis(c);
	            }
	        });
			panelPosizione.add(panel);

		}
		gbc.gridy = 6;
		contentPane.add(panelPosizione, gbc);
		
		JButton sostituisci = new JButton("Sostitutisci");
		sostituisci.addActionListener(e -> {
			logics.sub(panelTit.getParent(), panelRis.getParent(), panelTit, panelRis);
			
		});
		gbc.gridy = 7;
		contentPane.add(sostituisci, gbc);
	}
	
	public void chiudi() {
        this.dispose();
    }
	
	public void completato() {
		superGui.addCambio();
	}
}
