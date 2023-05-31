package v2.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Calciatore;
import data.Squadra;
import gui.Base;
import gui.Partita;
import logics.CreaSquadreAvversarie;
import logics.CreaSquadreAvversarieImpl;

public class Torneo extends Base {

	final int turni=3;
	final int nSquadre=(int) Math.pow(2, turni);
	int count=0;
	public Torneo(Squadra squadra, List<Calciatore> li) {
		t=this;
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		CreaSquadreAvversarie creaS=new CreaSquadreAvversarieImpl(li, nSquadre-1);
		List<Squadra> liSquadre=new ArrayList<>();
		liSquadre.add(squadra);
		try {
			liSquadre.addAll(creaS.getSquadre());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<JPanel> liPanelFase=new ArrayList<>();
		for(int i=0; i<turni; i++) {
			liPanelFase.add(new JPanel());
		}
		
		for(int i=0;i<liSquadre.size();i++) {
			JPanel matchPanel=new JPanel();
			matchPanel.add(new JLabel(liSquadre.get(i).getNomeSquadra()));
			matchPanel.add(new JLabel("-"));
			i++;
			matchPanel.add(new JLabel(liSquadre.get(i).getNomeSquadra()));
			liPanelFase.get(count).add(matchPanel);
		};
		
		JButton btnSimula=new JButton("Simula");
		
		btnSimula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				for(int i=0;i<liSquadre.size()/Math.pow(2, count);i++) {
					JPanel matchPanel=new JPanel();
					
					matchPanel.add(new JLabel(liSquadre.get(i).getNomeSquadra()));
					matchPanel.add(new JLabel("-"));
					i++;
					matchPanel.add(new JLabel(liSquadre.get(i).getNomeSquadra()));
					liPanelFase.get(count).add(matchPanel);
				};
				JButton btn= (JButton) e.getSource();
				JPanel panel=(JPanel) btn.getParent();
				panel.revalidate();
				panel.repaint();
				
			}
		});
		
		JButton prova=new JButton("prova");
		prova.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Partita partita=new Partita(squadra, liSquadre.get(1));
					
					partita.createAndShowGUI();
					partita.addWindowListener(new WindowAdapter() {
						@Override
					    public void windowClosed(WindowEvent e) {
							partita.getWinner();
						}
					});
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.gridy=3;
		contentPane.add(liPanelFase.get(0), gbc);
		gbc.gridy=2;
		contentPane.add(liPanelFase.get(1), gbc);
		gbc.gridy=1;
		contentPane.add(liPanelFase.get(2), gbc);
		
		gbc.gridy=4;
		contentPane.add(btnSimula, gbc);
		
		gbc.gridy=5;
		contentPane.add(prova, gbc);
	}
}
