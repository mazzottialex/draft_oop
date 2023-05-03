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
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Impostazioni extends Base {
	private JTextField textFieldNomeSquadra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Impostazioni frame = new Impostazioni();
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
	public Impostazioni() {
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		
		JLabel lblNomeSquadra = new JLabel("Nome Squadra:");
		lblNomeSquadra.setForeground(Color.white);
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.insets=new Insets(10, 5, 10, 5);
		contentPane.add(lblNomeSquadra, gbc);
		
		
		textFieldNomeSquadra = new JTextField();
		textFieldNomeSquadra.setColumns(10);
		textFieldNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		gbc.gridx=1;
		gbc.gridy=0;
		contentPane.add(textFieldNomeSquadra, gbc);

		JButton btnAddImg = new JButton("Scegli Stemma");
		btnAddImg.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		final JLabel lblImg = new JLabel();
	    lblImg.setForeground(Color.white);
	    lblImg.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		
	    List<String> liStemmi=new ArrayList<>();
	    liStemmi.add("res/stemmi/bianco.png");
	    liStemmi.add("res/stemmi/blu.png");
	    liStemmi.add("res/stemmi/giallo.png");
	    liStemmi.add("res/stemmi/nero.png");
	    liStemmi.add("res/stemmi/verde.png");
	    liStemmi.add("res/stemmi/viola.png");
	    liStemmi.add("res/stemmi/rosso.png");
	    
	    JPanel stemmi=new JPanel();
		stemmi.setLayout(layout);
	    for(int i=0;i<liStemmi.size();i++)
	    {	   
	    	JButton btnStemma=new JButton();
			ImageIcon img=new ImageIcon(liStemmi.get(i));
			Image image = img.getImage(); // transform it 
			Image newimg = image.getScaledInstance(40, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			btnStemma.setBackground(Color.white);
			btnStemma.setIcon(img);
			btnStemma.setBorderPainted(false);
			gbc.gridx=i%3;
			gbc.gridy=i/3;
			gbc.insets=new Insets(2, 2, 2, 2);
			stemmi.add(btnStemma,gbc);
			
	    };
	    gbc.gridwidth=0;
	    gbc.gridx=0;
		gbc.gridy=2;
	    contentPane.add(stemmi,gbc);

	    btnAddImg.addActionListener(new ActionListener() {
	         @Override
	    	public void actionPerformed(ActionEvent e) {
	        	 
	         }
	    });
	      
	    gbc.gridx=0;
	    gbc.gridy=1;
	    contentPane.add(btnAddImg, gbc);
	    gbc.gridx=1;
	    gbc.gridy=1;
	    contentPane.add(lblImg,gbc);
		
		add(contentPane);
	}
}



