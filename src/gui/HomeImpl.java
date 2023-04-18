package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

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
		setBounds(100, 100, 650, 450);
		setMinimumSize(new Dimension(600,400));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(217, 232, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 3, 10, 10));
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		contentPane.add(label_4);
		
		JLabel label_4_1 = new JLabel("");
		contentPane.add(label_4_1);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel lblDraft = new JLabel("DRAFT");
		lblDraft.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDraft.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDraft);
		
		JLabel label_3_4 = new JLabel("");
		contentPane.add(label_3_4);
		
		JLabel label_3_3 = new JLabel("");
		contentPane.add(label_3_3);
		
		JLabel label_3_2 = new JLabel("");
		contentPane.add(label_3_2);
		
		JLabel label_3_5 = new JLabel("");
		contentPane.add(label_3_5);
		
		JLabel label_3_1 = new JLabel("");
		contentPane.add(label_3_1);
		
		JButton btnStart = new JButton("INIZIO PARTITA");
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
		panelSelectioned.add(lblStagioneSelezionata);
		
		JLabel lblStagione = new JLabel("2022-23");
		panelSelectioned.add(lblStagione);
		
		JLabel label_1_2 = new JLabel("");
		contentPane.add(label_1_2);
		
		JLabel label_1_1 = new JLabel("");
		contentPane.add(label_1_1);
		
		JPanel panelLoad = new JPanel();
		contentPane.add(panelLoad);
		
		JLabel lblCarica = new JLabel("Carica stagione:");
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
		panelDownload.add(lblAggiorna);
		
		JList listAggiorna = new JList();
		panelDownload.add(listAggiorna);
	}

}
