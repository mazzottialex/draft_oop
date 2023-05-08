package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class utilsGUI {

	public static JPanel getPanelCalciatore(String calciatore, String ruolo) {
		String file="";
		if(ruolo.equals("P"))
			file="res/portiere.png";
		else if(ruolo.equals("D"))
			file="res/difensore.png";
		else if(ruolo.equals("C"))
			file="res/centrocampista.png";
		else if(ruolo.equals("A"))
			file="res/attaccante.png";
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		JPanel panelGiocatore=new JPanel();
		panelGiocatore.setLayout(layout);
		
		JLabel lblNome=new JLabel(calciatore);
		lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		JLabel lblIcona = new JLabel();
		ImageIcon img=new ImageIcon(file);
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblIcona.setIcon(img);
		gbc.gridy=0;
		panelGiocatore.add(lblIcona,gbc);
		gbc.gridy=1;
		panelGiocatore.add(lblNome,gbc);
		
		return panelGiocatore;
	}
	public static JButton getButtonCalciatore(String calciatore, String ruolo) {
		String file="";
		if(ruolo.equals("P"))
			file="res/portiere.png";
		else if(ruolo.equals("D"))
			file="res/difensore.png";
		else if(ruolo.equals("C"))
			file="res/centrocampista.png";
		else if(ruolo.equals("A"))
			file="res/attaccante.png";
		
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		JButton buttonCalciatore=new JButton();
		buttonCalciatore.setLayout(layout);
		buttonCalciatore.setBackground(new Color(240,240,240));
		JLabel lblNome=new JLabel(calciatore);
		lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		JLabel lblIcona = new JLabel();
		ImageIcon img=new ImageIcon(file);
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblIcona.setIcon(img);
		gbc.gridy=0;
		buttonCalciatore.add(lblIcona,gbc);
		gbc.gridy=1;
		buttonCalciatore.add(lblNome,gbc); 
		return buttonCalciatore;
	}
}