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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Calciatore;
import data.Squadra;
import data.SquadraUtente;
import data.TorneoColl;
import gui.Base;
import gui.Partita;
import logics.CreaSquadreAvversarie;
import logics.CreaSquadreAvversarieImpl;
import logics.LogicsRigori;
import logics.LogicsRigoriImpl;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;

public class Torneo extends Base {
	List<Squadra> turnoDaSimul=new ArrayList<>();
	List<JPanel> liPanelFase=new ArrayList<>();
	private Torneo pane;
	final int turni=3;
	final int nSquadre=(int) Math.pow(2, turni);
	int count=0;
	private TorneoColl tabellone;
	public Torneo(Squadra squadra, List<Calciatore> li) {
		pane=this;
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		
		//CREA LE SQUADRE E TABELLONE(1 TURNO)
		CreaSquadreAvversarie creaS=new CreaSquadreAvversarieImpl(li, nSquadre-1);
		List<Squadra> liSquadre=new ArrayList<>();
		liSquadre.add(squadra);
		try {
			liSquadre.addAll(creaS.getSquadre());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabellone=new TorneoColl(liSquadre);
		
		//AGGIUNGE 1 TURNO
		JPanel panelFase=new JPanel();
		for(int i=0;i<tabellone.getLastLi().size();i++) {
			JPanel matchPanel=new JPanel();
			matchPanel.add(new JLabel(tabellone.getLastLi().get(i).getNomeSquadra()));
			matchPanel.add(new JLabel("-"));
			i++;
			matchPanel.add(new JLabel(tabellone.getLastLi().get(i).getNomeSquadra()));
			panelFase.add(matchPanel);
		};
		liPanelFase.add(panelFase);
		//////////////////
		JButton btnSimula=new JButton("Simula");
		
		btnSimula.addActionListener(new ActionListener() {
			List<Squadra> liSquadreVinc=new ArrayList<>();
			@Override
			public void actionPerformed(ActionEvent e) {
				//SVUOLTO LIPANELFASE
				liPanelFase=new ArrayList<>();
				turnoDaSimul=tabellone.getLastLi();
				//SE NON ABBIAMO UN VINCITORE
				if(turnoDaSimul.size()>1) {
					System.out.println(turnoDaSimul);
					Partita partita;
					try {
						partita = new Partita(turnoDaSimul.get(0), turnoDaSimul.get(1));
					
						partita.createAndShowGUI();
						partita.addWindowListener(new WindowAdapter() {
							@Override
						    public void windowClosed(WindowEvent e) {
								System.out.print(liSquadreVinc);
								liSquadreVinc.add(partita.getWinner());
								tabellone.addLi(liSquadreVinc);
								liSquadreVinc=new ArrayList<>();
								
								JPanel panelFase=new JPanel();
								
								for(int i=0;i<tabellone.getLastLi().size();i++) {
									JPanel matchPanel=new JPanel();
									matchPanel.add(new JLabel(tabellone.getLastLi().get(i).getNomeSquadra()));
									//CHECK se non vincitore
									if(tabellone.getLastLi().size()>1) {
										matchPanel.add(new JLabel("-"));
										i++;
										matchPanel.add(new JLabel(tabellone.getLastLi().get(i).getNomeSquadra()));
									}panelFase.add(matchPanel);
								}
								liPanelFase.add(panelFase);
								
								liPanelFase.forEach(lis->{
									count++;
									gbc.gridy=turni-count;
									contentPane.add(lis, gbc);
								});
								pane.revalidate();
								pane.repaint();
							};
							
						});
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i=2; i< turnoDaSimul.size()-1;i=i+2) {
						if(!(turnoDaSimul.get(i) instanceof SquadraUtente))
						{
							try {
								SimulatingMatch sim=new SimulatingMatchImpl(turnoDaSimul.get(i), turnoDaSimul.get(i+1));
								
								//tabellone.addSemi(sim.risultato().keySet().iterator().next());
								Iterator<Squadra> it=sim.risultato().keySet().iterator();
								Squadra s1=it.next();
								Squadra s2=it.next();
								int score1=sim.risultato().get(s1);
								int score2=sim.risultato().get(s2);
								if(score1==score2)
								{
									score1=sim.risultatoSuppl().get(s1);
									score2=sim.risultatoSuppl().get(s2);
									//RIGORI
									if(score1==score2) {
										LogicsRigori rigori=new LogicsRigoriImpl(s1, s2);
										liSquadreVinc.add(rigori.getWinner());
									}
									else {
										liSquadreVinc.add(score1>score2 ? s1 : s2);
									}
								}
								else{
									liSquadreVinc.add(score1>score2 ? s1 : s2);
								}
								

							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		gbc.insets=new Insets(5, 5, 5, 5);
		
		liPanelFase.forEach(lis->{
			count++;
			gbc.gridy=turni-count;
			contentPane.add(lis, gbc);
		});
		
		
		gbc.gridy=5;
		contentPane.add(btnSimula, gbc);
	}
}
