package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import data.Calciatore;
import data.Squadra;
import logics.LogicsRigori;
import logics.LogicsRigoriImpl;
import utils.Pair;

public class Rigori extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5140476454072046580L;
	private Squadra s1;
    private Squadra s2;
    private JLabel results1;
    private JLabel results2;
    private JLabel result;
    private int gol1;
    private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private Squadra winner;
    private JButton chiudi;
    private LogicsRigori logics;
//    private Iterator<Calciatore> tiratori1;
//    private Iterator<Calciatore> tiratori2;
//    private Iterator<String> ris1;
//    private Iterator<String> ris2;
    private Map<Integer, Pair<Calciatore, String>> rig1;
    private Map<Integer, Pair<Calciatore, String>> rig2;
    private JPanel panel;
    private String str1;
    private String str2;

	public Rigori(Squadra s1, Squadra s2, Partita partita) {
    	this.s1 = s1;
        this.s2 = s2;
		gol1 = 0;
		gol2 = 0;
		tiri1 = 0;
		tiri2 = 0;
		
		totTiri = 0;
		logics = new LogicsRigoriImpl(s1, s2);
//		tiratori1 = logics.compute_old().get(0).keySet().iterator();
//		tiratori2 = logics.compute_old().get(1).keySet().iterator();
//		ris1 = logics.compute_old().get(0).values().iterator();
//		ris2 = logics.compute_old().get(1).values().iterator();
		rig1 = logics.compute().get(0);
		rig2 = logics.compute().get(1);
		str1 = "";
		str2 = "";
		
		// gui di prova
//        setLayout(new BorderLayout());
//        setLocationRelativeTo(null);
        
        this.panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel(s1.getNomeSquadra(), SwingConstants.RIGHT), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel(s2.getNomeSquadra(), SwingConstants.LEFT), gbc);

        results1 = new JLabel();
//        results1.setLayout(new BoxLayout(results1, BoxLayout.Y_AXIS));
//        add(results1, BorderLayout.WEST);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 200;
        panel.add(results1, gbc);

        results2 = new JLabel();
//        results2.setLayout(new BoxLayout(results2, BoxLayout.Y_AXIS));
//        add(results2, BorderLayout.EAST);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipady = 200;
        panel.add(results2, gbc);

        result = new JLabel();
//        result.setHorizontalAlignment(JLabel.CENTER);
//        add(result, BorderLayout.CENTER);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(result, gbc);
        
        JButton inizia = new JButton("inizia");
        inizia.addActionListener(e -> {
            inizia.setEnabled(false);
            start();
        });
        chiudi = new JButton("chiudi");
        chiudi.addActionListener(e -> {
        	dispose();
        	partita.setWinnerR(winner, gol1, gol2);
        });
        chiudi.setEnabled(false);
        add(inizia, BorderLayout.NORTH);
        add(chiudi, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	dispose();
            	partita.setWinnerR(winner, gol1, gol2);
            }
        };

        addWindowListener(windowListener);
    }
    
    private void start() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//            	if (!tiratori1.hasNext() && !tiratori2.hasNext()) {
//            		JOptionPane.showMessageDialog(null, "Tiri di rigore terminati. Squadra vincente: " + getNomeWinner());
//            		result.setText(gol1 + " - " + gol2);
//            		chiudi.setEnabled(true);
//            		timer.cancel();
//				} else {
//					if (totTiri % 2 == 0) {
//						if (tiratori1.hasNext() && ris1.hasNext()) {
//							String res = ris1.next();
//							String resultLabel = tiratori1.next().getNominativo() + ": " + res;
//							str1 = str1 + resultLabel + "<br>";
//							results1.setText("<html>" + str1 + "</html>");
//							if (res.equals("Gol")) {
//                            	gol1++;
//                            }
//							totTiri++;
//						}
//					} else {
//						if (tiratori2.hasNext() && ris2.hasNext()) {
//							String res = ris2.next();
//							String resultLabel = tiratori2.next().getNominativo() + ": " + res;
//							str2 = str2 + resultLabel + "<br>";
//							results2.setText("<html>" + str2 + "</html>");
//							if (res.equals("Gol")) {
//                            	gol2++;
//                            }
//							totTiri++;
//						}
//					}
//				}
            	
            	
            	if (!rig1.containsKey(tiri1) && !rig2.containsKey(tiri2)) {
            		result.setText(gol1 + " - " + gol2);
            		JOptionPane.showMessageDialog(null, "Tiri di rigore terminati. Squadra vincente: " + getNomeWinner());
            		chiudi.setEnabled(true);
            		timer.cancel();
				} else {
					if (totTiri % 2 == 0) {
						if (rig1.containsKey(tiri1)) {
							String res = rig1.get(tiri1).getY();
							String tiratore = rig1.get(tiri1).getX().getNominativo();
							String resultLabel = tiratore + ": " + res;
							str1 = str1 + resultLabel + "<br>";
							results1.setText("<html>" + str1 + "</html>");
							if (res.equals("Gol")) {
                            	gol1++;
                            }
							tiri1++;
						}
					} else {
						if (rig2.containsKey(tiri2)) {
							String res = rig2.get(tiri2).getY();
							String tiratore = rig2.get(tiri2).getX().getNominativo();
							String resultLabel = tiratore + ": " + res;
							str2 = str2 + resultLabel + "<br>";
							results2.setText("<html>" + str2 + "</html>");
							if (res.equals("Gol")) {
                            	gol2++;
                            }
							tiri2++;
						}
					}
					totTiri = tiri1 + tiri2;
				}
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    
    private String getNomeWinner() {
    	return getWinner().getNomeSquadra();
	}

	public Squadra getWinner() {
		if (gol1 > gol2) {
			winner = s1;
		} else {
			winner = s2;
		}
    	return winner;
    }
    
    public void createAndShowGUI() {
    	add(panel);
    	setVisible(true);
    }
}
