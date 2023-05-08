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

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Draft extends Base {

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Draft(List<Calciatore> li)  {
		getContentPane().add(contentPane);
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		
		gbc.insets=new Insets(5, 5, 2, 2);
		
		//Attaccanti
		for(int i=0;i<3;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
	                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, li, "A");
						dialog.setVisible(true);
						Calciatore c=dialog.getCalciatore();
						if(c!=null) {
							panel.remove(btn);
							panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRuolo()));
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
		gbc.gridy=0;
		contentPane.add(panelPosizione, gbc);
		
		panelPosizione = new JPanel();
		//Centrocampisti
		for(int i=0;i<3;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
	                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, li, "C");
						dialog.setVisible(true);
						Calciatore c=dialog.getCalciatore();
						if(c!=null) {
							panel.remove(btn);
							panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRuolo()));
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
		gbc.gridy=1;
		contentPane.add(panelPosizione, gbc);
		
		panelPosizione=new JPanel();
		//Difensori
		for(int i=0;i<4;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
	                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					
	                DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, li, "D");
						dialog.setVisible(true);
						Calciatore c=dialog.getCalciatore();
						if(c!=null) {
							panel.remove(btn);
							panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRuolo()));
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
		gbc.gridy=2;
		contentPane.add(panelPosizione, gbc);;
		
		panelPosizione=new JPanel();
		//Portiere
		for(int i=0;i<1;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
	                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					
	                DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, li, "P");
						dialog.setVisible(true);
						Calciatore c=dialog.getCalciatore();
						if(c!=null) {
							panel.remove(btn);
							panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRuolo()));
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
		gbc.gridy=3;
		contentPane.add(panelPosizione, gbc);
	}

}
