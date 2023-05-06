package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import data.Calciatore;
import data.Squadra;
import logics.LogicsImpostazioni;
import logics.LogicsImpostazioniImpl;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Impostazioni extends Base {
	private JTextField textFieldNomeSquadra;
	private LogicsImpostazioni log;

	public Impostazioni(List<Calciatore> li) {
		
		log=new LogicsImpostazioniImpl();
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		
		JLabel lblNomeSquadra = new JLabel("Nome Squadra:");
		lblNomeSquadra.setForeground(Color.white);
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.insets=new Insets(10, 5, 10, 5);
		contentPane.add(lblNomeSquadra, gbc);
		
		
		textFieldNomeSquadra = new JTextField();
		textFieldNomeSquadra.setColumns(10);
		textFieldNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		gbc.gridx=1;
		gbc.gridy=0;
		contentPane.add(textFieldNomeSquadra, gbc);

		JLabel lblScegliStemma = new JLabel("Stemma:");
		lblScegliStemma.setForeground(Color.white);
		lblScegliStemma.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		
	    List<String> liStemmi=new ArrayList<>();
	    liStemmi.add("res/stemmi/bianco.png");
	    liStemmi.add("res/stemmi/blu.png");
	    liStemmi.add("res/stemmi/giallo.png");
	    liStemmi.add("res/stemmi/nero.png");
	    liStemmi.add("res/stemmi/verde.png");
	    liStemmi.add("res/stemmi/viola.png");
	    liStemmi.add("res/stemmi/rosso.png");
	    liStemmi.add("res/stemmi/azzurro.png");
	    liStemmi.add("res/stemmi/arancione.png");
	    
	    JPanel stemmi=new JPanel();
		stemmi.setLayout(layout);
		List<JButton> liButton=new ArrayList<>();
	    for(int i=0;i<liStemmi.size();i++)
	    {	   
	 
	    	JButton btnStemma=new JButton();
	    	liButton.add(btnStemma);
	    	String url=liStemmi.get(i);
			ImageIcon img=new ImageIcon(url);
			Image image = img.getImage(); // transform it 
			Image newimg = image.getScaledInstance(65, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			btnStemma.setBackground(Color.white);
			btnStemma.setIcon(img);
			btnStemma.setBorderPainted(false);
			btnStemma.setFocusPainted(false);
			gbc.gridx=i%3;
			gbc.gridy=i/3;
			gbc.insets=new Insets(2, 2, 2, 2);
			stemmi.add(btnStemma,gbc);
			
			btnStemma.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn=(JButton)e.getSource();
					liButton.forEach(b-> b.setBackground(Color.white));
					btn.setBackground(new Color(0, 64, 128));
					log.setStemma(url);
				}
			});
	    };
	    
	    gbc.insets=new Insets(25, 2, 5, 2);
	    gbc.gridwidth=0;
	    gbc.gridx=0;
	    gbc.gridy=1;
	    contentPane.add(lblScegliStemma, gbc);
	    
	    gbc.insets=new Insets(5, 2, 5, 2);
	    gbc.gridx=0;
		gbc.gridy=2;
	    contentPane.add(stemmi,gbc);

	    JButton btnProsegui=new JButton("Prosegui");
	    btnProsegui.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		btnProsegui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Squadra squadra=log.getSquadra(textFieldNomeSquadra.getText());
				System.out.print(squadra.toString());
			}
		});
	    
	    gbc.gridx=0;
		gbc.gridy=3;
	    gbc.insets=new Insets(45, 2, 5, 2);
	    contentPane.add(btnProsegui, gbc);
		
		//add(contentPane);
	}
}



