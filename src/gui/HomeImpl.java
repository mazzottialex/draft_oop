package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.Icon;

public class HomeImpl extends JFrame implements Home   {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomeImpl() {
		
		setTitle("DRAFT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		setMinimumSize(new Dimension(600,700));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
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
		btnStart.setRolloverEnabled(true);
		
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
		
		JLabel lblStagione = new JLabel("2022-23");
		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagione);
		
		JLabel labelEmpty15 = new JLabel("");
		contentPane.add(labelEmpty15);
		
		JLabel labelEmpty16 = new JLabel("");
		contentPane.add(labelEmpty16);
		
		JPanel panelLoad = new JPanel();
		contentPane.add(panelLoad);
		
		JLabel lblCarica = new JLabel("Carica stagione:");
		lblCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelLoad.add(lblCarica);
		
		JList listCarica = new JList();
		panelLoad.add(listCarica);
		
		JLabel labelEmpty17 = new JLabel("");
		contentPane.add(labelEmpty17);
		
		JLabel labelEmpty18 = new JLabel("");
		contentPane.add(labelEmpty18);
		
		JPanel panelDownload = new JPanel();
		contentPane.add(panelDownload);
		
		JLabel lblAggiorna = new JLabel("Aggiorna stagione:");
		lblAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelDownload.add(lblAggiorna);
		
		JList listAggiorna = new JList();
		panelDownload.add(listAggiorna);
	}

}
