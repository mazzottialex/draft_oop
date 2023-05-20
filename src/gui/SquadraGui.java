package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Squadra;
import v2.gui.DialogScelta;

public class SquadraGui extends Base {

	private final List<String> ruoli=List.of("A","C","D","P");

	/**
	 * Create the frame.
	 */
	public SquadraGui(Squadra squadra) {
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5, 5, 2, 2);
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);

		JLabel lblNomeSquadra=new JLabel(squadra.getNomeSquadra());
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblNomeSquadra.setForeground(Color.white);
		gbc.gridy=0;
		contentPane.add(lblNomeSquadra,gbc);
		
		JPanel panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);

		int count=0;
		for(int i=0; i<ruoli.size();i++) {
			panelPosizione=new JPanel();
			for(int j=0;j<squadra.getModulo().getN(ruoli.get(i));j++) {
				Calciatore c=squadra.getTitolari().get(count);
				JPanel panel=(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
				count++;
				panelPosizione.add(panel);
			}
			gbc.gridy=i+1;
			contentPane.add(panelPosizione, gbc);
		}
		
		JLabel lblRating=new JLabel("Valutazione: "+squadra.getValutazione());
		lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblRating.setForeground(Color.white);
		gbc.gridy=6;
		contentPane.add(lblRating,gbc);
	}

}
