package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
		
		JPanel panelGiocatore=new JPanel();
		 
		panelGiocatore.setLayout(layout);
		
		JLabel lblNome=new JLabel("Osimenh");
		JLabel lblIcona = new JLabel();
		ImageIcon img=new ImageIcon("res/portiere.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblIcona.setIcon(img);
		lblIcona.setBackground(getForeground());
		
		gbc.gridy=0;
		panelGiocatore.add(lblIcona,gbc);
		gbc.gridy=1;
		panelGiocatore.add(lblNome,gbc); 
		contentPane.add(panelGiocatore);
	}

}
