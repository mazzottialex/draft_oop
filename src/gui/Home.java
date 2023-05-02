package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logics.LogicsHome;
import logics.LogicsHomeImpl;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Home extends Base {
	
	private final LogicsHome log;
	private final String stagioneDefault="2022-2023";
	
	public Home(Boolean online) {
		log=new LogicsHomeImpl(stagioneDefault, online);
		log.loadStagione(stagioneDefault);
		
		//add(contentPane);
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		
		JButton btnStart = new JButton();
		ImageIcon img=new ImageIcon("res/start.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		btnStart.setBorderPainted(false);
		btnStart.setIcon(img);
		btnStart.setBackground(getForeground());
		btnStart.setPreferredSize(new Dimension(150,40));
		btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnStart.setBackground(Color.GREEN);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnStart.setBackground(new Color(0, 64, 128));
		    }
		});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.ipady=10;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(140, 0, 80, 0);
		contentPane.add(btnStart,gbc);

		
		JPanel panelSelectioned = new JPanel();
		panelSelectioned.setPreferredSize(new Dimension(70,40));
		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
		lblStagioneSelezionata.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagioneSelezionata);
		JLabel lblStagione = new JLabel(log.getStagione());
		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagione);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets=new Insets(8, 0, 8, 0);
		contentPane.add(panelSelectioned, gbc);
		
		
		JPanel panelLoad = new JPanel();
		panelLoad.setBackground(new Color(240, 240, 240));
				
		panelLoad.setLayout(new BoxLayout(panelLoad, BoxLayout.X_AXIS));
		
		JButton btnCarica = new JButton("Carica");
		btnCarica.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnCarica.setBorderPainted(false);
		btnCarica.setBackground(getForeground());
		btnCarica.setRolloverEnabled(true);
		
		String[] array = log.getStagioni().toArray(new String[log.getStagioni().size()]);
		JComboBox<String> comboBoxCarica = new JComboBox<>(array);
		comboBoxCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

		btnCarica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(log.loadStagione(comboBoxCarica.getItemAt(comboBoxCarica.getSelectedIndex())))
					lblStagione.setText(log.getStagione());
				else 
					JOptionPane.showMessageDialog(null, "Errore nel caricamento");
			}	
		});
		panelLoad.add(btnCarica);
		panelLoad.add(comboBoxCarica);
		gbc.gridx=0;
		gbc.gridy=2;
		contentPane.add(panelLoad,gbc);
		
		JPanel panelDownLoad = new JPanel();
		panelDownLoad.setBackground(new Color(240, 240, 240));
				
		panelDownLoad.setLayout(new BoxLayout(panelDownLoad, BoxLayout.X_AXIS));
		
		JButton btnAggiorna= new JButton("Aggiorna");
		
		JLabel labelAvviso = new JLabel("");
		JComboBox<String> comboBoxAggiorna = new JComboBox<>(array);
		if(!log.getOnline())
		{
			btnAggiorna.setEnabled(false);
			comboBoxAggiorna.setEnabled(false);
			labelAvviso = new JLabel("Sei offline");
			labelAvviso.setForeground(Color.yellow);
			labelAvviso.setFont(new Font("DejaVu Sans", Font.ITALIC, 12));
		}
		btnAggiorna.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnAggiorna.setBorderPainted(false);
		btnAggiorna.setBackground(getForeground());
		btnAggiorna.setRolloverEnabled(true);
		panelDownLoad.add(btnAggiorna);

		comboBoxAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

		btnAggiorna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Attendere qualche istante");
				if(log.downloadStagione(comboBoxAggiorna.getItemAt(comboBoxAggiorna.getSelectedIndex()))) {
					lblStagione.setText(log.getStagione());
					JOptionPane.showMessageDialog(null, "Caricamento completato");
			}
				else 
					JOptionPane.showMessageDialog(null, "Errore nel caricamento");
			}
		});
		
		panelDownLoad.add(comboBoxAggiorna);
		gbc.gridx=0;
		gbc.gridy=3;
		//gbc.anchor=100;
		contentPane.add(panelDownLoad,gbc);
		gbc.gridx=0;
		gbc.gridy=4;
		contentPane.add(labelAvviso,gbc);
		
		JButton btnArchivio=new JButton("Archivio");
		btnArchivio.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnArchivio.setBorderPainted(false);
		btnArchivio.setRolloverEnabled(true);
		btnArchivio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Archivio archivio;
				try {
					archivio = new Archivio(log.getLi(), log.getStagione(), log.getOnline());
					contentPane.removeAll();
					contentPane.add(archivio.getPanel());
	                contentPane.updateUI();
	                
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		gbc.gridx=0;
		gbc.gridy=5;
		contentPane.add(btnArchivio,gbc);
	}
	
	public JPanel getPanel() {
		contentPane.updateUI();
		return contentPane;
	}

}
