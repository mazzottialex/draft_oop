package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
	public Draft()  {
		getContentPane().add(contentPane);
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		JPanel panelGiocatore;
		JPanel panelPosizione = new JPanel();
		
		gbc.insets=new Insets(5, 5, 2, 2);
		
		//Attaccanti
		for(int i=0;i<3;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
	                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
					panel.remove(btn);
					
					DialogScelta dialog = new DialogScelta(parent, true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

					
					JLabel lblNome=new JLabel(dialog.getCalciatore());
					lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
					JLabel lblIcona = new JLabel();
					ImageIcon img=new ImageIcon("res/attaccante.png");
					Image image = img.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					img = new ImageIcon(newimg);
					lblIcona.setIcon(img);
					gbc.gridy=0;
					panel.add(lblIcona,gbc);
					gbc.gridy=1;
					panel.add(lblNome,gbc); 
					
					revalidate();
					repaint();
				}
			});
			panelGiocatore.add(btnScegli);
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
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
					panel.remove(btn);
					
					JLabel lblNome=new JLabel("Barella");
					lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
					JLabel lblIcona = new JLabel();
					ImageIcon img=new ImageIcon("res/centrocampista.png");
					Image image = img.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					img = new ImageIcon(newimg);
					lblIcona.setIcon(img);
					gbc.gridy=0;
					panel.add(lblIcona,gbc);
					gbc.gridy=1;
					panel.add(lblNome,gbc); 
					
					revalidate();
					repaint();
				}
			});
			panelGiocatore.add(btnScegli);
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
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
					panel.remove(btn);
					
					JLabel lblNome=new JLabel("Osimenh");
					lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
					JLabel lblIcona = new JLabel();
					ImageIcon img=new ImageIcon("res/difensore.png");
					Image image = img.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					img = new ImageIcon(newimg);
					lblIcona.setIcon(img);
					gbc.gridy=0;
					panel.add(lblIcona,gbc);
					gbc.gridy=1;
					panel.add(lblNome,gbc); 
					
					revalidate();
					repaint();
				}
			});
			panelGiocatore.add(btnScegli);
			panelPosizione.add(panelGiocatore);
		}
		gbc.gridy=2;
		contentPane.add(panelPosizione, gbc);
		
		panelPosizione=new JPanel();
		//Portiere
		for(int i=0;i<1;i++) {
			panelPosizione.setLayout(layout);
			panelGiocatore=new JPanel();
			panelGiocatore.setLayout(layout);
			JButton btnScegli=new JButton("Scegli");
			btnScegli.setPreferredSize(new Dimension(100,50));
			btnScegli.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn= (JButton) e.getSource();
					JPanel panel=(JPanel) btn.getParent();
					panel.remove(btn);
					
					JLabel lblNome=new JLabel("Osimenh");
					lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
					JLabel lblIcona = new JLabel();
					ImageIcon img=new ImageIcon("res/portiere.png");
					Image image = img.getImage(); // transform it 
					Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					img = new ImageIcon(newimg);
					lblIcona.setIcon(img);
					gbc.gridy=0;
					panel.add(lblIcona,gbc);
					gbc.gridy=1;
					panel.add(lblNome,gbc); 
					
					revalidate();
					repaint();
				}
			});
			panelGiocatore.add(btnScegli);
			panelPosizione.add(panelGiocatore);
		}
		gbc.gridy=3;
		contentPane.add(panelPosizione, gbc);
	}

}
