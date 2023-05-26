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

public class Sostituzione extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5244133982320404420L;
	private final List<String> ruoli = List.of("P", "D", "C", "A");
	private JPanel panelSquadra = new JPanel();
	private LogicsSostituzione logics;
	
	public Sostituzione(Squadra squadra) {
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 2, 2);
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		panelSquadra.setBackground(getForeground());
		
		JLabel lblStemma = new JLabel();
		ImageIcon img = new ImageIcon(squadra.getStemma());
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(55, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblStemma.setBackground(Color.white);
		lblStemma.setIcon(img);
		lblStemma.setBorder(new EmptyBorder(new Insets(2, 0, 2, 25)));
		panelSquadra.add(lblStemma);
		
		JLabel lblNomeSquadra = new JLabel(squadra.getNomeSquadra());
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblNomeSquadra.setForeground(Color.white);
		panelSquadra.add(lblNomeSquadra);
		
		JLabel lblRating = new JLabel("Valutazione: " + squadra.getValutazione());
		lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblRating.setForeground(Color.white);
		lblRating.setBorder(new EmptyBorder(new Insets(2, 25, 2, 0)));
		panelSquadra.add(lblRating);
		
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
		            	// TODO chiamare funz in logics
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
//			if(j < 4) {
//				gbc.gridy = 0;
//			}
//			else {
//				gbc.gridy = 1;
//			}
			JPanel panel = (utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
			panel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
//	                JOptionPane.showMessageDialog(null, c.getNominativo() + " -> " + c.getRuolo());
	            	// TODO chiamare funz in logics
	            }
	        });
			panelPosizione.add(panel);

		}
		gbc.gridy = 6;
		contentPane.add(panelPosizione, gbc);
		
	}
}
