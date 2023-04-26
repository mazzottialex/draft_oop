package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		contentPane.setLayout(new GridLayout(10, 3, 10, 10));
		
		JLabel labelEmpty1 = new JLabel("uno");
		contentPane.add(labelEmpty1);
		
		JLabel labelEmpty2 = new JLabel("due");
		contentPane.add(labelEmpty2);
		
		JLabel labelEmpty3 = new JLabel("tre");
		contentPane.add(labelEmpty3);
		
		JLabel labelEmpty4 = new JLabel("quattro");
		contentPane.add(labelEmpty4);
		/*
		ImageIcon img=new ImageIcon("res/icon.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		JLabel labelIcon = new JLabel(img);
		contentPane.add(labelIcon);
		*/
		JLabel labelEmpty5 = new JLabel("cinque");
		contentPane.add(labelEmpty5);
		
		JLabel labelEmpty6 = new JLabel("sei");
		contentPane.add(labelEmpty6);
		
		JLabel labelEmpty7 = new JLabel("sette");
		contentPane.add(labelEmpty7);
		
		JLabel labelEmpty8 = new JLabel("otto");
		contentPane.add(labelEmpty8);
		
		JLabel labelEmpty9 = new JLabel("nove");
		contentPane.add(labelEmpty9);
		
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
		
		JLabel labelEmpty10 = new JLabel("dieci");
		contentPane.add(labelEmpty10);
		
		JLabel labelEmpty11 = new JLabel("undici");
		contentPane.add(labelEmpty11);
		
		JLabel labelEmpty12 = new JLabel("dodici");
		contentPane.add(labelEmpty12);
		
		JLabel labelEmpty13 = new JLabel("tredici");
		contentPane.add(labelEmpty13);
		
		JLabel labelEmpty14 = new JLabel("quattordici");
		contentPane.add(labelEmpty14);
		
		JPanel panelSelectioned = new JPanel();
		contentPane.add(panelSelectioned);
		
//		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata:");
//		lblStagioneSelezionata.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		panelSelectioned.add(lblStagioneSelezionata);
//		
//		JLabel lblStagione = new JLabel(log.getStagione());
//		lblStagione.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
//		panelSelectioned.add(lblStagione);
		
		JLabel labelEmpty15 = new JLabel("quindici");
		contentPane.add(labelEmpty15);
		
		JLabel labelEmpty16 = new JLabel("sedici");
		contentPane.add(labelEmpty16);
		
		JPanel panelLoad = new JPanel();
		panelLoad.setBackground(new Color(240, 240, 240));
		contentPane.add(panelLoad);
				
		panelLoad.setLayout(new BoxLayout(panelLoad, BoxLayout.X_AXIS));
		
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

		
		JLabel labelEmpty17 = new JLabel("diciassette");
		contentPane.add(labelEmpty17);
		
		JLabel labelEmpty18 = new JLabel("diciotto");
		contentPane.add(labelEmpty18);
		
		
		JPanel panelDownLoad = new JPanel();
		panelDownLoad.setBackground(new Color(240, 240, 240));
				
		panelDownLoad.setLayout(new BoxLayout(panelDownLoad, BoxLayout.X_AXIS));
		
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
		contentPane.add(panelDownLoad);

	}

}
