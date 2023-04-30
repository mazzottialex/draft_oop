package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import logics.LogicsHome;
import logics.LogicsHomeImpl;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;


public class Home extends Base{

	
	private final LogicsHome log;
	private final String stagioneDefault="2022-23";

	
	/**
	 * Create the frame.
	 */
	public Home() {

		log=new LogicsHomeImpl(stagioneDefault);
		
		contentPane.setLayout(new GridLayout(10, 3, 10, 10));
		
		JLabel labelEmpty1 = new JLabel("");
		contentPane.add(labelEmpty1);
		
		JLabel labelEmpty2 = new JLabel("");
		contentPane.add(labelEmpty2);
		
		JLabel labelEmpty3 = new JLabel("");
		contentPane.add(labelEmpty3);
		
		JLabel labelEmpty4 = new JLabel("");
		contentPane.add(labelEmpty4);
		
		ImageIcon img=new ImageIcon("res/icon.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		JLabel labelIcon = new JLabel(img);
		contentPane.add(labelIcon);
		
		JLabel labelEmpty5 = new JLabel("");
		contentPane.add(labelEmpty5);
		
		JLabel labelEmpty6 = new JLabel("");
		contentPane.add(labelEmpty6);
		
		JLabel labelEmpty7 = new JLabel("");
		contentPane.add(labelEmpty7);
		
		JLabel labelEmpty8 = new JLabel("");
		contentPane.add(labelEmpty8);
		
		JLabel labelEmpty9 = new JLabel("");
		contentPane.add(labelEmpty9);
		
		JButton btnStart = new JButton();
		img=new ImageIcon("res/start.png");
		image = img.getImage(); // transform it 
		newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		btnStart.setBorderPainted(false);
		btnStart.setIcon(img);
		btnStart.setBackground(getForeground());
		btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnStart.setBackground(Color.GREEN);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnStart.setBackground(new Color(0, 0, 128));
		    }
		});
		
		contentPane.add(btnStart);
		
		JLabel labelEmpty10 = new JLabel("");
		contentPane.add(labelEmpty10);
		
		JLabel labelEmpty11 = new JLabel("");
		contentPane.add(labelEmpty11);
		
		JLabel labelEmpty12 = new JLabel("");
		contentPane.add(labelEmpty12);
		
		JLabel labelEmpty13 = new JLabel("");
		contentPane.add(labelEmpty13);
		
		JLabel labelEmpty14 = new JLabel("");
		contentPane.add(labelEmpty14);
		
		JPanel panelSelectioned = new JPanel();
		contentPane.add(panelSelectioned);
		
		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
		lblStagioneSelezionata.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagioneSelezionata);
		
		JLabel lblStagione = new JLabel(log.getStagione());
		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagione);
		
		JLabel labelEmpty15 = new JLabel("");
		contentPane.add(labelEmpty15);
		
		JLabel labelEmpty16 = new JLabel("");
		contentPane.add(labelEmpty16);
		
		JPanel panelLoad = new JPanel();
		panelLoad.setBackground(new Color(240, 240, 240));
		contentPane.add(panelLoad);
				
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

		
		JLabel labelEmpty17 = new JLabel("");
		contentPane.add(labelEmpty17);
		
		JLabel labelEmpty18 = new JLabel("");
		contentPane.add(labelEmpty18);
		
		
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
		contentPane.add(panelDownLoad);
		contentPane.add(labelAvviso);
		
		JLabel labelEmpty19 = new JLabel("");
		contentPane.add(labelEmpty19);
		
		JButton btnArchivio= new JButton("Vedi archivio");

		btnArchivio.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnArchivio.setBackground(Color.white);
		btnArchivio.setRolloverEnabled(true);
		btnArchivio.setSize(new Dimension(5,5));
		btnArchivio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Archivio frame;
				contentPane.removeAll();
				contentPane.updateUI();
			
				try {
					frame = new Archivio();
					contentPane.add(frame.getPanel());
					validate();
			        repaint();
			        //setVisible(true);
					//contentPane.updateUI();


				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(btnArchivio);
	}

}
