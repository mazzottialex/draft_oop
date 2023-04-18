package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblDraft = new JLabel("DRAFT");
		contentPane.add(lblDraft);
		
		JButton btnStart = new JButton("INIZIO PARTITA");
		contentPane.add(btnStart);
		
		JPanel panelSelectioned = new JPanel();
		contentPane.add(panelSelectioned);
		
		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
		panelSelectioned.add(lblStagioneSelezionata);
		
		JLabel lblStagione = new JLabel("2022-23");
		panelSelectioned.add(lblStagione);
		
		JPanel panelLoad = new JPanel();
		contentPane.add(panelLoad);
		
		JLabel lblCarica = new JLabel("Carica stagione:");
		panelLoad.add(lblCarica);
		
		JList listCarica = new JList();
		panelLoad.add(listCarica);
		
		JPanel panelDownload = new JPanel();
		contentPane.add(panelDownload);
		
		JLabel lblAggiorna = new JLabel("Aggiorna stagione:");
		panelDownload.add(lblAggiorna);
		
		JList listAggiorna = new JList();
		panelDownload.add(listAggiorna);
	}

}
