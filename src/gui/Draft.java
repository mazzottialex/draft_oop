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
	/**
	 * Create the frame.
	 */
	public Draft(List<Calciatore> li)  {
		getContentPane().add(contentPane);
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		ExtractData ex;
		try {
			ex = new ExtractDataImpl(li);
			liRuolo=ex.getRandom(15,15,20,5);  //*
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		int n;
		String ruolo;
		
		gbc.insets=new Insets(5, 5, 2, 2);
		
		//Attaccanti
		panelPosizione = new JPanel();
		n=3;
		ruolo="A";
		List<JButton> liBtn=new ArrayList<>();
		for(int i=0;i<n;i++) {
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
					int i=liBtn.indexOf(btn);
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, liRuolo.subList(5*i, 5*(i+1)), "A");
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
		gbc.gridy=0;
		contentPane.add(panelPosizione, gbc);
		

		//Centrocampisti
		panelPosizione = new JPanel();
		n=3;
		ruolo="C";
		for(int i=0;i<n;i++) {
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
					int i=liBtn.indexOf(btn);
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, liRuolo.subList(5*i, 5*(i+1)), "C");
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
		gbc.gridy=1;
		contentPane.add(panelPosizione, gbc);
		
		//Difensori
		panelPosizione = new JPanel();
		n=4;
		ruolo="D";
		for(int i=0;i<n;i++) {
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
					int i=liBtn.indexOf(btn);
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, liRuolo.subList(5*i, 5*(i+1)), "D");
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
		gbc.gridy=2;
		contentPane.add(panelPosizione, gbc);
		
		//Portiere
		panelPosizione = new JPanel();
		n=1;
		ruolo="P";
		for(int i=0;i<n;i++) {
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
					int i=liBtn.indexOf(btn);
					DialogScelta dialog;
					try {
						dialog = new DialogScelta(parent, true, liRuolo.subList(5*i, 5*(i+1)), "P");
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
		gbc.gridy=3;
		contentPane.add(panelPosizione, gbc);
	}

}
