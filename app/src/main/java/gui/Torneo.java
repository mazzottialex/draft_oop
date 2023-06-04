package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.Calciatore;
import data.Squadra;
import logics.LogicsRigoriImpl;
import logics.LogicsTorneo;
import logics.LogicsTorneoImpl;
import utils.RoundedBorder;


/**
 * The GUI where the tournament is played.
 * @author Davide Braccini
 *
 */
public class Torneo extends Base {

	private static final int NUM_TURN_1 = 16;
	private static final int NUM_TURN_2 = 8;
	private static final int NUM_TURN_3 = 4;
	private static final int NUM_TURN_4 = 2;
	
	private final LogicsTorneo logTor;
	private final JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private final GridLayout gl = new GridLayout(6, 1);
	private final JPanel panelCenter = new JPanel(gl);
	private final JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private final GridLayout g0 = new GridLayout(1, 1);
	private final GridLayout g1 = new GridLayout(1, 1);
	private final GridLayout g2 = new GridLayout(1, NUM_TURN_4);
	private final GridLayout g3 = new GridLayout(1, NUM_TURN_3);
	private final GridLayout g4 = new GridLayout(1, NUM_TURN_2);
	private final GridLayout g5 = new GridLayout(1, NUM_TURN_1);
	private final JPanel p0 = new JPanel(g0);  //Panel a riga 0 (squadra vincitrice)
	private final JPanel p1 = new JPanel(g1);  //Panel a riga 1 --> 2 squadre --> 1 partita
	private final JPanel p2 = new JPanel(g2);	 //Panel a riga 2 --> 4 squadre --> 2 partite
	private final JPanel p3 = new JPanel(g3);	 //Panel a riga 3 --> 8 squadre ---> 4 partite
	private final JPanel p4 = new JPanel(g4);  //Panel a riga 4 --> 16 squadre --> 8 partite
	private final JPanel p5 = new JPanel(g5);  //Panel a riga 5 --> 16 squadre
	private final MyJLabel[] labelsp5 = new MyJLabel[NUM_TURN_1];
	private final MyJLabel[] labelsp4 = new MyJLabel[NUM_TURN_2];
	private final MyJLabel[] labelsp3 = new MyJLabel[NUM_TURN_3];
	private final MyJLabel[] labelsp2 = new MyJLabel[NUM_TURN_4];
	private MyJLabel labelp1 = new MyJLabel();
	private MyJLabel labelp0 = new MyJLabel();
	private List<Squadra> listAvversarie;
	private int risSquadraUte = 0;
	private int risSquadraAvv = 0;
	//boolean eliminatedThisTurn = false;
	private final JButton buttonSimula;
	private final Color panelColor = new Color(0, 64, 128);
	
	/**
	 * Constructor of Torneo, add the necessary graphics components.
	 * @param squadra the user's team
	 * @param li the list of all the players in Serie A
	 * @throws FileNotFoundException if ...
	 * @throws ClassNotFoundException if ...
	 * @throws IOException if ...
	 */
	public Torneo(final Squadra squadra, final List<Calciatore> li) 
			throws FileNotFoundException, ClassNotFoundException, IOException {			
		this.logTor = new LogicsTorneoImpl(squadra, li);
		this.listAvversarie = logTor.getListAvversari();
		this.contentPane.setLayout(new BorderLayout());
		// Aggiungo il bottone Simula nel panelSud
		buttonSimula = new JButton("Simula");
		buttonSimula.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				//eliminatedThisTurn = false;
				logTor.setElimThisTurn(false);
				if (!logTor.getEliminated()) {
					Partita p;
					try {
						p = new Partita(logTor.getMiaSquadra(), logTor.getListAvversari().get(0));
						buttonSimula.setVisible(false);
						p.createAndShowGUI();
						p.addWindowListener(new WindowAdapter() {
							public void windowClosed(final WindowEvent e) {
								buttonSimula.setVisible(true);
								if (p.getWinner() != logTor.getMiaSquadra()) {
									logTor.setEliminated(true);
									logTor.setSquadraAvv(logTor.getListAvversari().get(0));
									//eliminatedThisTurn = true;
									logTor.setElimThisTurn(true);
								}
								//System.out.println(p.getGolS1());
								//System.out.println(p.getWinner().getNomeSquadra());
								//System.out.println(p.getGolS2());
								risSquadraUte = p.getGolS1();
								risSquadraAvv = p.getGolS2();	
								//risSquadraUte = 1;
								//risSquadraAvv = 2;			
								try {
									logTor.simulaMatch();
									createLevel();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						logTor.simulaMatch();
						createLevel();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//buttonSimula.setVisible(true);
			}
		});
		this.panelSud.add(buttonSimula);
		//Mi occupo del PanelCenter
		p5.setBorder(new EmptyBorder(20, 5, 20, 5));
		g5.setHgap(5);
		p4.setBorder(new EmptyBorder(20, 20, 20, 20));
		g4.setHgap(40);
		p3.setBorder(new EmptyBorder(20, 40, 20, 40));
		g3.setHgap(140);
		p2.setBorder(new EmptyBorder(20, 180, 20, 180));
		g2.setHgap(300);
		p1.setBorder(new EmptyBorder(20, 450, 20, 450));
		p0.setBorder(new EmptyBorder(20, 450, 20, 450));
		//Aggiungo le varie squadre nel panel5 (la prima è sempre quella dell'utente)
		this.labelsp5[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra());
		this.p5.add(this.labelsp5[0]);
		//Collections.shuffle(this.logTor.getListAvversari());
		for (int i = 0; i < this.logTor.getListAvversari().size(); i++) {
			this.labelsp5[i + 1] = new MyJLabel(this.logTor.getListAvversari().get(i).getNomeSquadra());
			this.p5.add(this.labelsp5[i + 1]);
		}
		//System.out.println(this.logTor.getListAvversari().size());
		int cont = 1;
		this.labelsp4[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() + " - " 
						+ this.logTor.getListAvversari().get(0).getNomeSquadra());
		this.p4.add(this.labelsp4[0]);
		for (int i = 1; i < this.logTor.getListAvversari().size() - 1; i = i + 2, cont++) {
			this.labelsp4[cont] = new MyJLabel(this.logTor.getListAvversari().get(i).getNomeSquadra() 
								+ " - " + this.logTor.getListAvversari().get(i + 1).getNomeSquadra());
			this.p4.add(this.labelsp4[cont]);
		}
		p0.setBackground(this.panelColor);
		p1.setBackground(this.panelColor);
		p2.setBackground(this.panelColor);
		p3.setBackground(this.panelColor);
		p4.setBackground(this.panelColor);
		p5.setBackground(this.panelColor);
		this.panelCenter.add(p0);
		this.panelCenter.add(p1);
		this.panelCenter.add(p2);
		this.panelCenter.add(p3);
		this.panelCenter.add(p4);
		this.panelCenter.add(p5);
		this.panelSud.setBackground(this.panelColor);
		this.panelCenter.setBackground(this.panelColor);
		this.panelNord.setBackground(Color.white);
		//this.panelNord.setBounds(200, 300, 200, 300);
		this.contentPane.add(panelSud, BorderLayout.SOUTH);
		this.contentPane.add(panelCenter, BorderLayout.CENTER);
		this.contentPane.add(panelNord, BorderLayout.NORTH);
	}
	
	private void createLevel() {
		final int numSquadre = logTor.getNumSquadre();
		switch (numSquadre) {
		case 8: 
			this.p4.removeAll();
			this.p4.repaint();
			// mettere il risultato della partita della squadra utente (prima da fare in Logic)
			// metto i risultati nelle partite che si svolgono
			int cont = 1;
			this.labelsp4[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " 
							+ " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
			this.p4.add(this.labelsp4[0]);
			for (int i = 1; i < this.listAvversarie.size() - 1; i = i + 2, cont++) {
				String squad1 = new String(this.listAvversarie.get(i).getNomeSquadra());
				var ris1 = this.logTor.getRisultati().get(squad1);
				String squad2 = new String(this.listAvversarie.get(i + 1).getNomeSquadra());
				var ris2 = this.logTor.getRisultati().get(squad2);
				this.labelsp4[cont] = new MyJLabel(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
				this.p4.add(this.labelsp4[cont]);
			}
			// ... creo il panel 3 con le squadre che hanno vinto 
			if (!logTor.getEliminated()) {
				this.labelsp3[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() 
						+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p3.add(this.labelsp3[0]);
			} else {
				this.labelsp3[0] = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra() 
						+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p3.add(this.labelsp3[0]);
			}
			cont = 1;
			for (int i = 1 ; i < this.logTor.getListAvversari().size() - 1; i = i + 2, cont++) {
				this.labelsp3[cont] = new MyJLabel(this.logTor.getListAvversari().get(i).getNomeSquadra() 
						+ " - " + this.logTor.getListAvversari().get(i + 1).getNomeSquadra());
				this.p3.add(this.labelsp3[cont]);
			}
			this.p3.validate();
			this.p4.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 4:
			this.p3.removeAll();
			this.p3.repaint();
			// mettere il risultato della partita della squadra utente
			// metto i risultati nelle partite che si svolgono
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				this.labelsp3[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " 
								+ " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p3.add(this.labelsp3[0]);
			} else {
				int ris1 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris2 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.labelsp3[0] = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris1 + " " 
								+ " - " + " " + ris2 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p3.add(this.labelsp3[0]);
				if (ris2 > ris1) {
					this.logTor.setSquadraAvv(this.listAvversarie.get(0));
				} else if (ris1 == ris2) {
					// rigori
					Squadra vinc = new LogicsRigoriImpl(this.logTor.getSquadraAvv(), this.listAvversarie.get(0)).getWinner();
					if (vinc.getNomeSquadra().equals(this.listAvversarie.get(0).getNomeSquadra())) {
						this.logTor.setSquadraAvv(this.listAvversarie.get(0));
					}
				}
			}
			int cont1 = 1;
			for (int i = 1; i < this.listAvversarie.size() - 1; i = i + 2, cont1++) {
				String squad1 = new String(this.listAvversarie.get(i).getNomeSquadra());
				var ris1 = this.logTor.getRisultati().get(squad1);
				String squad2 = new String(this.listAvversarie.get(i+1).getNomeSquadra());
				var ris2 = this.logTor.getRisultati().get(squad2);
				this.labelsp3[cont1] = new MyJLabel(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
				this.p3.add(this.labelsp3[cont1]);
			}
			// ... creo il panel 2 con le squadre che hanno vinto 
			// (ora faccio finta che vinca sempre la squadra dell'utente poi dovrò cambiare) 
			if (!logTor.getEliminated()) {
				this.labelsp2[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() 
									+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p2.add(this.labelsp2[0]);
			} else {
				this.labelsp2[0] = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra()
									+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p2.add(this.labelsp2[0]);
			}
			this.labelsp2[1] = new MyJLabel(this.logTor.getListAvversari().get(1).getNomeSquadra()
								+ " - " + this.logTor.getListAvversari().get(2).getNomeSquadra());
			this.p2.add(this.labelsp2[1]);
			this.logTor.clearRisMatch();
			this.p2.validate();
			this.p3.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 2:
			this.p2.removeAll();
			this.p2.repaint();
			// metto i risultati nel panel 2 (ora non ho quelli della squadraUtente)
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				this.labelsp2[0] = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " " +
								" - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p2.add(this.labelsp2[0]);
			} else {
				int ris1 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris2 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.labelsp2[0] = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris1 + " " +
								" - " + " " + ris2 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p2.add(this.labelsp2[0]);
				if (ris2 > ris1) {
					this.logTor.setSquadraAvv(this.listAvversarie.get(0));
				} else if (ris1 == ris2) {
					// rigori
					Squadra vinc = new LogicsRigoriImpl(this.logTor.getSquadraAvv(), this.listAvversarie.get(0)).getWinner();
					if (vinc.getNomeSquadra().equals(this.listAvversarie.get(0).getNomeSquadra())) {
						this.logTor.setSquadraAvv(this.listAvversarie.get(0));
					}
				}
			}
			String squad1 = new String(this.listAvversarie.get(1).getNomeSquadra());
			var ris1 = this.logTor.getRisultati().get(squad1);
			String squad2 = new String(this.listAvversarie.get(2).getNomeSquadra());
			var ris2 = this.logTor.getRisultati().get(squad2);
			this.labelsp2[1] = new MyJLabel(squad1 + " " + ris1 + " " + " - " + " " + ris2 + " " + squad2);
			this.p2.add(this.labelsp2[1]);
			// aggiungo la nuova partita ...
			if (!logTor.getEliminated()) {
				this.labelp1 = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra()
									+ " - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p1.add(this.labelp1);
			} else {
				this.labelp1 = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra() +
									" - " + this.logTor.getListAvversari().get(0).getNomeSquadra());
				this.p1.add(this.labelp1);
			}
			this.logTor.clearRisMatch();
			this.p1.validate();
			this.p2.validate();
			this.panelCenter.validate();
			this.listAvversarie = logTor.getListAvversari();
			break;
		case 1:
			this.p1.removeAll();
			this.p1.repaint();
			// metto i risultati nel panel 1
			if (!logTor.getEliminated() || this.logTor.getElimThisTurn()) {
				this.labelp1 = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra() + " " + this.risSquadraUte + " "
								+ " - " + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p1.add(this.labelp1);
			} else {
				int ris11 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getNomeSquadra());
				int ris21 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getNomeSquadra());
				this.labelp1 = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra() + " " + ris11 + " " 
								+ " - " + " " + ris21 + " " + this.listAvversarie.get(0).getNomeSquadra());
				this.p1.add(this.labelp1);
			}
			String winner = new String();
			if (this.logTor.getElimThisTurn()) {
				this.labelp0 = new MyJLabel(this.logTor.getSquadraAvv().getNomeSquadra());
				winner = this.logTor.getSquadraAvv().getNomeSquadra();
			} else if (!logTor.getEliminated()) {
				this.labelp0 = new MyJLabel(this.logTor.getMiaSquadra().getNomeSquadra());
				winner = this.logTor.getMiaSquadra().getNomeSquadra();
			} else {
				this.labelp0 = new MyJLabel(this.logTor.getWinner());
				winner = this.logTor.getWinner();
			}
			this.p0.add(this.labelp0);
			final JLabel label = new JLabel("The winner is: " + winner + "!");
			label.setBorder(new RoundedBorder(Color.red, 20));
			label.setBackground(Color.white);
			label.setForeground(Color.red);
			label.setOpaque(true);
			this.panelNord.add(label);
			
			buttonSimula.setVisible(false);
			contentPane.validate();
			this.logTor.setNumSquadre(0);
			this.panelNord.validate();
			this.p0.validate();
			this.p1.validate();
			this.panelCenter.validate();
			break;
		}
		
	}
	
	/**
	 * The inner classe that extends JLabel
	 * @author Davide Braccini
	 *
	 */
	private static class MyJLabel extends JLabel {
		public MyJLabel() {
			super();
		}
		public MyJLabel(final String text) {
			super(text, SwingConstants.CENTER);
			setBorder(new RoundedBorder(Color.white, 20));
			setBackground(new Color(0, 64, 128));
			setForeground(Color.white);

			setOpaque(true);

		}
	}
}
