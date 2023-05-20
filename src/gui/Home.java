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
	private final String stagione;
	
	public Home(Boolean online, String stagione) {
		this.stagione=stagione;
		log=new LogicsHomeImpl(stagione, online);
		log.loadStagione(stagione);
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		
		JButton btnStart = new JButton();
		ImageIcon img=new ImageIcon("res/start.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		btnStart.setBorderPainted(false);
		btnStart.setFocusPainted(false);
		btnStart.setIcon(img);
		btnStart.setBackground(getForeground());
		btnStart.setPreferredSize(new Dimension(150,40));
		btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnStart.setBackground(Color.GREEN);
		        Image newimg = image.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH);
		        ImageIcon img = new ImageIcon(newimg);
				btnStart.setIcon(img);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnStart.setBackground(new Color(0, 64, 128));
		    	Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
		        ImageIcon img = new ImageIcon(newimg);
				btnStart.setIcon(img);
		    }
		});
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					changeJPanel(new Impostazioni(log.getLi()));
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
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
		btnCarica.setFocusPainted(false);
		
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
		btnAggiorna.setFocusPainted(false);
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
		
		JButton btnArchivio=new JButton("ARCHIVIO");
		btnArchivio.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnArchivio.setBackground(Color.white);
		btnArchivio.setForeground(Color.BLUE);
		btnArchivio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					changeJPanel(new Archivio(log.getLi(), log.getStagione(), log.getOnline()));
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridx=0;
		gbc.gridy=5;
		contentPane.add(btnArchivio,gbc);
		JButton btnStorico=new JButton("STORICO");
		btnStorico.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnStorico.setBackground(Color.white);
		btnStorico.setForeground(Color.BLUE);
		btnStorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeJPanel(new Storico());
			}
		});
		gbc.gridy=6;
		contentPane.add(btnStorico,gbc);
	}
	
	

}
