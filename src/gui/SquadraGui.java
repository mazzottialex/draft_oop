package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		
		//JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);
		//panelGiocatore=new JPanel();
		//panelGiocatore.setLayout(layout);
		int count=0;
		for(int i=0; i<ruoli.size();i++) {
			panelPosizione=new JPanel();
			//panelGiocatore.setLayout(layout);
			for(int j=0;j<squadra.getModulo().getN(ruoli.get(i));j++) {
				//panelGiocatore=new JPanel();
				Calciatore c=squadra.getTitolari().get(count);
				JPanel panel=(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
				count++;
				//panelGiocatore.add(panel);
				panelPosizione.add(panel);
			}
			gbc.gridy=i;
			contentPane.add(panelPosizione, gbc);
		}
	}

}
