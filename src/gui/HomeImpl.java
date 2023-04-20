package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logics.LogicsHome;
import logics.LogicsHomeImpl;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JComboBox;


public class HomeImpl extends JFrame implements Home   {

	private JPanel contentPane;
	private final LogicsHome log;
	private final String stagioneDefault="2022-23";
	/**
	 * Create the frame.
	 */
	public HomeImpl() {
		log=new LogicsHomeImpl(stagioneDefault);
		
		setTitle("DRAFT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 700);
		setMinimumSize(new Dimension(640,700));
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
		panelLoad.add(btnCarica);
		
		
		String[] array = log.getStagioni().toArray(new String[log.getStagioni().size()]);
		JComboBox<String> comboBox = new JComboBox<>(array);
		comboBox.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

		panelLoad.add(comboBox);

		
		JLabel labelEmpty17 = new JLabel("");
		contentPane.add(labelEmpty17);
		
		JLabel labelEmpty18 = new JLabel("");
		contentPane.add(labelEmpty18);
		
		
		JPanel panelDownLoad = new JPanel();
		panelDownLoad.setBackground(new Color(240, 240, 240));
				
		panelDownLoad.setLayout(new BoxLayout(panelDownLoad, BoxLayout.X_AXIS));
		
		JButton btnAggiorna= new JButton("Aggiorna");
		btnAggiorna.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnAggiorna.setBorderPainted(false);
		btnAggiorna.setBackground(getForeground());
		btnAggiorna.setRolloverEnabled(true);
		panelDownLoad.add(btnAggiorna);

		JComboBox<String> comboBox2 = new JComboBox<>(array);
		comboBox2.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

		panelDownLoad.add(comboBox2);
		contentPane.add(panelDownLoad);

	}

}
