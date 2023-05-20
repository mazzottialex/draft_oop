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
import data.Squadra;
import data.SquadraAvversaria;
import data.SquadraUtente;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Draft extends Base {

	private List<Calciatore> liGiocatori;
	//private List<Calciatore> liPanchina; 
	private final List<String> ruoli=List.of("A","C","D","P");
	private final int nDraft=5;
	private List<Calciatore> liTitolari=new ArrayList<>();
	private List<Calciatore> liRiserve=new ArrayList<>();

	public Draft(List<Calciatore> li, Modulo mod, String nomeSquadra, String stemma)  {
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5, 5, 2, 2);
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		
		List<JButton> liBtn=new ArrayList<>();

		ExtractData ex;
		try {
			ex = new ExtractDataImpl(li);
			liGiocatori=ex.getRandom(mod.getNumAtt()*nDraft, mod.getNumCen()*nDraft, mod.getNumDif()*nDraft, nDraft);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		JButton btnProsegui=new JButton("Prosegui");
		btnProsegui.setVisible(false);
		
		//titolari
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
							dialog = new DialogScelta(parent, true, liGiocatori.subList(nDraft*index, nDraft*(index+1)), liGiocatori.subList(nDraft*index, nDraft*(index+1)).get(0).getRuolo());
							dialog.setVisible(true);
							Calciatore c=dialog.getCalciatore();
							if(c!=null) {
								panel.remove(btn);
								liTitolari.add(c);
								panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
								if(liTitolari.size()>=11 && liRiserve.size()>=7)
									btnProsegui.setVisible(true);
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
		JLabel lblPanchina=new JLabel("PANCHINA");
		lblPanchina.setForeground(Color.white);
		lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		gbc.insets=new Insets(20, 0, 0, 0);
		gbc.gridy=4;
		contentPane.add(lblPanchina, gbc);
		gbc.insets=new Insets(10, 10, 10, 10);
		
		//panchinari
		panelPosizione = new JPanel();
		for(int j=0;j<7;j++) {
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
						dialog = new DialogScelta(parent, true, liGiocatori.subList(nDraft*index, nDraft*(index+1)), liGiocatori.subList(nDraft*index, nDraft*(index+1)).get(0).getRuolo());
						dialog.setVisible(true);
						Calciatore c=dialog.getCalciatore();
						if(c!=null) {
							panel.remove(btn);
							liRiserve.add(c);
							panel.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), false));
							if(liTitolari.size()>=11 && liRiserve.size()>=7)
								btnProsegui.setVisible(true);
						}
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					panel.revalidate();
					panel.repaint();
				}
			});
			panelGiocatore.add(btnScegli);
			if(j<4)
				gbc.gridy=0;
			else
				gbc.gridy=1;
			panelPosizione.add(panelGiocatore,gbc);
			gbc.gridy=5;
			contentPane.add(panelPosizione, gbc);
		}
		
		
		
		gbc.insets=new Insets(10, 0, 0, 0);
		gbc.gridy=6;
		contentPane.add(btnProsegui, gbc);
		btnProsegui.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnProsegui.setBackground(Color.white);
		btnProsegui.setRolloverEnabled(true);
		btnProsegui.setForeground(Color.BLUE);
		btnProsegui.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
				Squadra squadra=new SquadraUtente(nomeSquadra, stemma, mod, liTitolari, liRiserve);
				
				//changeJPanel(new nomeGUI(squadra));
			}
		});
		 
		
	}

}
