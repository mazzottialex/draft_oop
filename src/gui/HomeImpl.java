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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeImpl frame = new HomeImpl();
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
		
		JLabel label_3_2_1_1 = new JLabel("");
		contentPane.add(label_3_2_1_1);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		contentPane.add(label_4);
		
		JLabel label_4_1 = new JLabel("");
		contentPane.add(label_4_1);
		
		ImageIcon img=new ImageIcon("res/draft.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		JLabel lblNewLabel_1 = new JLabel(img);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		
		
		JLabel label_3_4 = new JLabel("");
		contentPane.add(label_3_4);
		
		
		
		
		JLabel label_3_2 = new JLabel("");
		contentPane.add(label_3_2);
		
		JLabel label_3_2_1 = new JLabel("");
		contentPane.add(label_3_2_1);
		
		JLabel label_3_5 = new JLabel("");
		contentPane.add(label_3_5);
		
		JButton btnStart = new JButton();
		img=new ImageIcon("res/start.png");
		image = img.getImage(); // transform it 
		newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		btnStart.setBorderPainted(false);
		btnStart.setIcon(img);
		btnStart.setBackground(getForeground());
		btnStart.setRolloverEnabled(true);
		
		
		//btnStart.setPreferredSize(new Dimension(100,10));
		//btnStart.setMaximumSize(new Dimension(50,20));
		contentPane.add(btnStart);
		
		JLabel label_2_4 = new JLabel("");
		contentPane.add(label_2_4);
		
		JLabel label_2_3 = new JLabel("");
		contentPane.add(label_2_3);
		
		JLabel label_2_2 = new JLabel("");
		contentPane.add(label_2_2);
		
		JLabel label_3_6 = new JLabel("");
		contentPane.add(label_3_6);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JPanel panelSelectioned = new JPanel();
		contentPane.add(panelSelectioned);
		
		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
		lblStagioneSelezionata.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagioneSelezionata);
		
		JLabel lblStagione = new JLabel("2022-23");
		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelSelectioned.add(lblStagione);
		
		JLabel label_1_2 = new JLabel("");
		contentPane.add(label_1_2);
		
		JLabel label_1_1 = new JLabel("");
		contentPane.add(label_1_1);
		
		JPanel panelLoad = new JPanel();
		contentPane.add(panelLoad);
		
		JLabel lblCarica = new JLabel("Carica stagione:");
		lblCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelLoad.add(lblCarica);
		
		JList listCarica = new JList();
		panelLoad.add(listCarica);
		
		JLabel label_2_1 = new JLabel("");
		contentPane.add(label_2_1);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JPanel panelDownload = new JPanel();
		contentPane.add(panelDownload);
		
		JLabel lblAggiorna = new JLabel("Aggiorna stagione:");
		lblAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		panelDownload.add(lblAggiorna);
		
		JList listAggiorna = new JList();
		panelDownload.add(listAggiorna);
	}

}
