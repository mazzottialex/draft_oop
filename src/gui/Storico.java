package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import data.Squadra;
import manageData.LogicsFile;
import manageData.LogicsFileImpl;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Storico extends Base {

	public Storico() {
		LogicsFile logf=new LogicsFileImpl();
		List<Squadra> li=logf.LoadStorico();
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		JButton btnHome=new JButton("HOME");
		
		btnHome.setBounds(10, 8, 70, 28);
		btnHome.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnHome.setBackground(Color.white);
		btnHome.setRolloverEnabled(true);
		btnHome.setForeground(Color.BLUE);
		btnHome.setPreferredSize(new Dimension(100,50));
		
		contentPane.add(btnHome);
		
		JPanel panelLi = new JPanel();
		panelLi.setLayout(layout);
		panelLi.setBackground(new Color(0, 64, 128));

		int count=0;
		for (Squadra squadra : li) {
			JPanel panelSquadra=new JPanel();
			panelSquadra.setBackground(getForeground());
			JLabel lblStemma=new JLabel();
			ImageIcon img=new ImageIcon(squadra.getStemma());
			Image image = img.getImage(); // transform it 
			Image newimg = image.getScaledInstance(55, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			lblStemma.setBackground(Color.white);
			lblStemma.setIcon(img);
			lblStemma.setBorder(new EmptyBorder(new Insets(5, 0, 5, 25)));
			panelSquadra.add(lblStemma);
			
			JLabel lblNomeSquadra=new JLabel(squadra.getNomeSquadra());
			lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
			lblNomeSquadra.setForeground(Color.white);
			panelSquadra.add(lblNomeSquadra);
			
			JLabel lblRating=new JLabel("Valutazione: "+squadra.getValutazione());
			lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
			lblRating.setForeground(Color.white);
			lblRating.setBorder(new EmptyBorder(new Insets(2, 25, 2, 25)));
			
			panelSquadra.add(lblRating);
			panelSquadra.add(lblRating);

			JButton btnVedi=new JButton("VEDI");
			btnVedi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					changeJPanel(new SquadraGui(squadra));
					
				}
			});
			panelSquadra.add(btnVedi);
			gbc.gridy=count;
			panelLi.add(panelSquadra, gbc);
			count++;
		}
		JScrollPane scrollPane=new JScrollPane(panelLi);
		contentPane.add(scrollPane);
		
		
	}

}
