package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Modulo;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Draft extends Base {

	private List<Calciatore> liRuolo;
	private final Modulo mod;
	private final List<String> ruoli=List.of("A","C","D","P");
	private final int nDraft=5;
	
	public Draft(List<Calciatore> li, Modulo mod)  {
		getContentPane().add(contentPane);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5, 5, 2, 2);
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		
		List<JButton> liBtn=new ArrayList<>();
		this.mod=mod;

		ExtractData ex;
		try {
			ex = new ExtractDataImpl(li);
			liRuolo=ex.getRandom(mod.getNumAtt()*nDraft, mod.getNumCen()*nDraft, mod.getNumDif()*nDraft, nDraft); 
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		
		for(int i=0; i<ruoli.size();i++) {
			panelPosizione = new JPanel();
			for(int j=0;j<mod.getN(ruoli.get(i));j++) {
				panelPosizione.setLayout(layout);
				panelGiocatore=new JPanel();
				panelGiocatore.setLayout(layout);
				JButton btnScegli=new JButton("Scegli");
				btnScegli.setPreferredSize(new Dimension(100,50));
				
				liBtn.add(btnScegli);
				btnScegli.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn= (JButton) e.getSource();
						JPanel panel=(JPanel) btn.getParent();
		                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
						int index=liBtn.indexOf(btn);
						DialogScelta dialog;
						try {
							dialog = new DialogScelta(parent, true, liRuolo.subList(5*index, 5*(index+1)), liRuolo.subList(5*index, 5*(index+1)).get(0).getRuolo());
							dialog.setVisible(true);
							Calciatore c=dialog.getCalciatore();
							if(c!=null) {
								panel.remove(btn);
								panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo()));
							}
						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
						panel.revalidate();
						panel.repaint();
					}
				});
				panelGiocatore.add(btnScegli);
				panelPosizione.add(panelGiocatore);
			}
			gbc.gridy=i;
			System.out.print(gbc.gridy);
			contentPane.add(panelPosizione, gbc);
		}

		
	}

}
