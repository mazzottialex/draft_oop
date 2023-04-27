package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logics.LogicsHome;
import logics.LogicsHomeImpl;

public class PartitaImpl extends JFrame implements Partita {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8420554109103794277L;

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public PartitaImpl() {
		
		setTitle("SIMULAZIONE PARTITA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 700);
		setMinimumSize(new Dimension(640,700));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel jpText = new JPanel();
		contentPane.add(jpText);
		JPanel jpButton = new JPanel();
		contentPane.add(jpButton);
		
		jpText.setLayout(new BoxLayout(jpButton, BoxLayout.X_AXIS));
		JPanel jpSq1 = new JPanel();
		jpText.add(jpSq1);
		jpText.add(new JSeparator(SwingConstants.VERTICAL));
		JPanel jpSq2 = new JPanel();
		jpText.add(jpSq2);
		
		jpSq1.setLayout(new BoxLayout(jpSq1, BoxLayout.Y_AXIS));
		JLabel sq1 = new JLabel("sq1");
		jpSq1.add(sq1);
		jpSq1.add(Box.createVerticalStrut(10));
		jpSq1.add(new JSeparator(SwingConstants.HORIZONTAL));
		jpSq1.add(Box.createVerticalStrut(10));
		JLabel nGolSq1 = new JLabel("nGolsq1");
		jpSq1.add(nGolSq1);
		jpSq1.add(Box.createVerticalStrut(20));
		JLabel sq1Label = new JLabel("sq1Label");
		jpSq1.add(sq1Label);
		
		jpSq2.setLayout(new BoxLayout(jpSq2, BoxLayout.Y_AXIS));
		JLabel sq2 = new JLabel("sq2");
		jpSq2.add(sq2);
		jpSq2.add(Box.createVerticalStrut(10));
		jpSq2.add(new JSeparator(SwingConstants.HORIZONTAL));
		jpSq2.add(Box.createVerticalStrut(10));
		JLabel nGolSq2 = new JLabel("nGolsq2");
		jpSq2.add(nGolSq2);
		jpSq2.add(Box.createVerticalStrut(20));
		JLabel sq2Label = new JLabel("sq2Label");
		jpSq2.add(sq2Label);
		
		jpButton.setLayout(new BoxLayout(jpButton, BoxLayout.Y_AXIS));
		//barra avanzamento da aggiungere
		JButton avvia = new JButton("Avvia");
		jpButton.add(avvia);
		JButton subs = new JButton("Sostituzioni");
		jpButton.add(subs);
		jpButton.add(subs, BorderLayout.EAST);
		//panel.add(Label2,BorderLayout.WEST);
		
		
		
//		JLabel labelEmpty1 = new JLabel("uno");
//		contentPane.add(labelEmpty1);		
		
//		JButton btnStart = new JButton();
//		img=new ImageIcon("res/start.png");
//		image = img.getImage(); // transform it 
//		newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//		img = new ImageIcon(newimg);
//		btnStart.setBorderPainted(false);
//		btnStart.setIcon(img);
//		btnStart.setBackground(getForeground());
//		btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
//		    public void mouseEntered(java.awt.event.MouseEvent evt) {
//		        btnStart.setBackground(Color.GREEN);
//		    }
//
//		    public void mouseExited(java.awt.event.MouseEvent evt) {
//		    	btnStart.setBackground(new Color(0, 0, 128));
//		    }
//		});
		
//		contentPane.add(btnStart);
		
//		JPanel panelSelectioned = new JPanel();
//		contentPane.add(panelSelectioned);
		
//		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
//		lblStagioneSelezionata.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		panelSelectioned.add(lblStagioneSelezionata);
//		
//		JLabel lblStagione = new JLabel(log.getStagione());
//		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		panelSelectioned.add(lblStagione);
		
//		JPanel panelLoad = new JPanel();
//		panelLoad.setBackground(new Color(240, 240, 240));
//		contentPane.add(panelLoad);
//				
//		panelLoad.setLayout(new BoxLayout(panelLoad, BoxLayout.X_AXIS));
		
//		JButton btnCarica = new JButton("Carica");
//		btnCarica.setHorizontalAlignment(SwingConstants.RIGHT);
//		btnCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		btnCarica.setBorderPainted(false);
//		btnCarica.setBackground(getForeground());
//		btnCarica.setRolloverEnabled(true);
		
//		String[] array = log.getStagioni().toArray(new String[log.getStagioni().size()]);
//		JComboBox<String> comboBoxCarica = new JComboBox<>(array);
//		comboBoxCarica.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

//		btnCarica.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(log.loadStagione(comboBoxCarica.getItemAt(comboBoxCarica.getSelectedIndex())))
//					lblStagione.setText(log.getStagione());
//				else 
//					JOptionPane.showMessageDialog(null, "Errore nel caricamento");
//			}	
//		});
//		
//		panelLoad.add(btnCarica);
//		panelLoad.add(comboBoxCarica);		
		
//		JPanel panelDownLoad = new JPanel();
//		panelDownLoad.setBackground(new Color(240, 240, 240));
//				
//		panelDownLoad.setLayout(new BoxLayout(panelDownLoad, BoxLayout.X_AXIS));
		
//		JButton btnAggiorna= new JButton("Aggiorna");
//		btnAggiorna.setHorizontalAlignment(SwingConstants.RIGHT);
//		btnAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		btnAggiorna.setBorderPainted(false);
//		btnAggiorna.setBackground(getForeground());
//		btnAggiorna.setRolloverEnabled(true);
//		panelDownLoad.add(btnAggiorna);

//		JComboBox<String> comboBoxAggiorna = new JComboBox<>(array);
//		comboBoxAggiorna.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));

//		btnAggiorna.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Attendere qualche istante");
//				if(log.downloadStagione(comboBoxAggiorna.getItemAt(comboBoxAggiorna.getSelectedIndex()))) {
//					lblStagione.setText(log.getStagione());
//					JOptionPane.showMessageDialog(null, "Caricamento completato");
//
//					
//			}
//				else 
//					JOptionPane.showMessageDialog(null, "Errore nel caricamento");
//			}
//		});
//		
//		panelDownLoad.add(comboBoxAggiorna);
//		contentPane.add(panelDownLoad);

	}

}
