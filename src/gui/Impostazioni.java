package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		
	    
	    btnAddImg.addActionListener(new ActionListener() {
	         @Override
	    	public void actionPerformed(ActionEvent e) {
	        	 JFileChooser fileChooser = new JFileChooser();
	        	 fileChooser.addChoosableFileFilter(new ImageFilter());
	        	 fileChooser.setAcceptAllFileFilterUsed(false);

	        	 int option = fileChooser.showOpenDialog(contentPane);
	        	 if(option == JFileChooser.APPROVE_OPTION){
	        		 File file = fileChooser.getSelectedFile();
	        		 //lblImg.setText("File Selected: " + file.getName());
	        		 BufferedImage img = null;
	        		 try {
	        		     img = ImageIO.read(file);
	        		     JLabel lblImg = new JLabel(new ImageIcon(img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
	        		     lblImg.setBorder(new RoundedBorder(Color.BLACK, 10));

	        		     gbc.gridx=1;
	        			 gbc.gridy=1;
	        		     contentPane.add(lblImg,gbc);
	        		     contentPane.updateUI();
	        		 } catch (Exception e1) {
	        			 e1.printStackTrace();
	        		 }
	        	 }else{
	        		 lblImg.setText("Open command canceled");
	             }
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



