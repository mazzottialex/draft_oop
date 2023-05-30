package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Squadra;
import v2.gui.DialogScelta;

public class SquadraGui extends Base {

	private final List<String> ruoli=List.of("A","C","D","P");
	private JPanel panelSquadra=new JPanel();
	
	public SquadraGui(Squadra squadra, List<Calciatore> li) {
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5, 5, 2, 2);
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		panelSquadra.setBackground(getForeground());
		
		JButton btnProsegui=new JButton("PROSEGUI");
		btnProsegui.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnProsegui.setBackground(Color.white);
		btnProsegui.setForeground(Color.BLUE);
		btnProsegui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					changeJPanel(new Torneo(squadra, li));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		if(stagione!=null)
			panelSquadra.add(btnProsegui);
		
		JLabel lblStemma=new JLabel();
		ImageIcon img=new ImageIcon(squadra.getStemma());
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(55, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblStemma.setBackground(Color.white);
		lblStemma.setIcon(img);
		lblStemma.setBorder(new EmptyBorder(new Insets(2, 0, 2, 25)));
		panelSquadra.add(lblStemma);
		
		JLabel lblNomeSquadra=new JLabel(squadra.getNomeSquadra());
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblNomeSquadra.setForeground(Color.white);
		panelSquadra.add(lblNomeSquadra);
		
		JLabel lblRating=new JLabel("Valutazione: "+squadra.getValutazione());
		lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		lblRating.setForeground(Color.white);
		lblRating.setBorder(new EmptyBorder(new Insets(2, 25, 2, 0)));
		panelSquadra.add(lblRating);
		
		gbc.gridy=0;
		contentPane.add(panelSquadra, gbc);
		
		JPanel panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);

		int count=0;
		for(int i=0; i<ruoli.size();i++) {
			panelPosizione=new JPanel();
			for(int j=0;j<squadra.getModulo().getN(ruoli.get(i));j++) {
				Calciatore c=squadra.getTitolari().get(count);

				JPanel panel=(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
				count++;
				panelPosizione.add(panel);
			}
			gbc.gridy=i+1;
			contentPane.add(panelPosizione, gbc);
		}
		
		JLabel lblPanchina=new JLabel("PANCHINA");
		lblPanchina.setForeground(Color.white);
		lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		gbc.insets=new Insets(10, 0, 0, 0);
		gbc.gridy=5;
		//contentPane.add(lblPanchina, gbc);
		gbc.insets=new Insets(5, 5, 5, 5);
		
		//panchinari
		panelPosizione = new JPanel();
		panelPosizione.setLayout(layout);
		for(int j=0;j<7;j++) {
			Calciatore c=squadra.getRiserve().get(j);
			if(j<4)
				gbc.gridy=0;
			else
				gbc.gridy=1;
			panelPosizione.add(utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), false), gbc);

		}
		gbc.gridy=6;
		contentPane.add(panelPosizione, gbc);
		
	}
	
	public SquadraGui(Squadra squadra, String stagioneP, Boolean online) {
		this(squadra, null);
		this.stagione=stagioneP;
		this.online=online;
		
		JButton btnStorico=new JButton("STORICO");
		btnStorico.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		btnStorico.setBackground(Color.white);
		btnStorico.setForeground(Color.BLUE);
		btnStorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeJPanel(new Storico(stagione, online));
			}
		});
		panelSquadra.add(btnStorico);
		
	}

}
