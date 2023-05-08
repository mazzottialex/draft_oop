package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;

public class Draft extends Base {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draft frame = new Draft();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Draft() {
		getContentPane().add(contentPane);
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		JPanel panelGiocatore;
		JLabel lblNome;
		JLabel lblIcona;
		ImageIcon img;
		Image image;
		Image newimg;
		JPanel panelPosizione = new JPanel();
		
		gbc.insets=new Insets(5, 5, 2, 2);
		
		//Attaccanti
		for(int i=0;i<3;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			lblNome=new JLabel("Osimenh");
			lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
			lblIcona = new JLabel();
			img=new ImageIcon("res/attaccante.png");
			image = img.getImage(); // transform it 
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			lblIcona.setIcon(img);
			gbc.gridy=0;
			panelGiocatore.add(lblIcona,gbc);
			gbc.gridy=1;
			panelGiocatore.add(lblNome,gbc); 

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
			lblNome=new JLabel("Barella");
			lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
			lblIcona = new JLabel();
			img=new ImageIcon("res/centrocampista.png");
			image = img.getImage(); // transform it 
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			lblIcona.setIcon(img);
			gbc.gridy=0;
			panelGiocatore.add(lblIcona,gbc);
			gbc.gridy=1;
			panelGiocatore.add(lblNome,gbc); 

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
			lblNome=new JLabel("Smalling");
			lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
			lblIcona = new JLabel();
			img=new ImageIcon("res/difensore.png");
			image = img.getImage(); // transform it 
			newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			lblIcona.setIcon(img);
			gbc.gridy=0;
			panelGiocatore.add(lblIcona,gbc);
			gbc.gridy=1;
			panelGiocatore.add(lblNome,gbc); 
			panelPosizione.add(panelGiocatore);
		}
		gbc.gridy=2;
		contentPane.add(panelPosizione, gbc);
		
		//Portiere
		panelGiocatore=new JPanel();
		panelGiocatore.setLayout(layout);
		lblNome=new JLabel("Vicario");
		lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblIcona = new JLabel();
		img=new ImageIcon("res/portiere.png");
		image = img.getImage(); // transform it 
		newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblIcona.setIcon(img);
		gbc.gridy=0;
		panelGiocatore.add(lblIcona,gbc);
		gbc.gridy=1;
		panelGiocatore.add(lblNome,gbc); 
		gbc.gridy=3;
		contentPane.add(panelGiocatore,gbc);
	}

}
