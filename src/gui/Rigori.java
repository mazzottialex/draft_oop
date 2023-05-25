package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import data.Calciatore;
import data.SquadraAvversaria;
import logics.LogicsRigori;
import logics.LogicsRigoriImpl;

public class Rigori extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5140476454072046580L;
	private SquadraAvversaria s1;
    private SquadraAvversaria s2;
    private JPanel results1;
    private JPanel results2;
    private JLabel result;
    private int gol1;
    private int gol2;
    private int totTiri;
    private SquadraAvversaria winner;
    private JButton chiudi;
    private boolean fine;
    private LogicsRigori logics;
    private Iterator<Calciatore> tiratori1;
    private Iterator<Calciatore> tiratori2;
    private Iterator<String> ris1;
    private Iterator<String> ris2;

	public Rigori(SquadraAvversaria s1, SquadraAvversaria s2) {
    	this.s1 = s1;
        this.s2 = s2;
		this.gol1 = 0;
		this.gol2 = 0;
		this.totTiri = 0;
		this.fine = false;
		logics = new LogicsRigoriImpl(s1, s2);
		tiratori1 = logics.compute().get(0).keySet().iterator();
		tiratori2 = logics.compute().get(1).keySet().iterator();
		ris1 = logics.compute().get(0).values().iterator();
		ris2 = logics.compute().get(1).values().iterator();
		
		//gui di prova
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        results1 = new JPanel();
        results1.setLayout(new BoxLayout(results1, BoxLayout.Y_AXIS));
        add(results1, BorderLayout.WEST);

        results2 = new JPanel();
        results2.setLayout(new BoxLayout(results2, BoxLayout.Y_AXIS));
        add(results2, BorderLayout.EAST);

        result = new JLabel();
        result.setHorizontalAlignment(JLabel.CENTER);
        add(result, BorderLayout.CENTER);
        
        JButton inizia = new JButton("inizia");
        inizia.addActionListener(e -> {
            inizia.setEnabled(false);
            start();
        });
        chiudi = new JButton("chiudi");
        chiudi.addActionListener(e -> {
        	// TODO
        	fine = true;
        	dispose();
        });
        chiudi.setEnabled(false);
        add(inizia, BorderLayout.NORTH);
        add(chiudi, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //TODO
            	fine = true;
            	dispose();
            }
        };

        addWindowListener(windowListener);
    }
	
    public boolean isFine() {
		return fine;
	}
    
    private void start() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	if (!tiratori1.hasNext() && !tiratori2.hasNext()) {
            		//TODO dire chi ha vinto
            		result.setText("Sfida terminata. Squadra vincente: " +  winner());
            		timer.cancel();
				} else {
					if (totTiri % 2 == 0) {
						if (tiratori1.hasNext() && ris1.hasNext()) {
							String res = ris1.next();
							JLabel resultLabel = new JLabel(tiratori1.next().getNominativo() + ": " + res);
							resultLabel.setHorizontalAlignment(JLabel.LEFT);
							results1.add(resultLabel);
							results1.revalidate();
							results1.repaint();
							if (res.equals("Gol")) {
                            	gol1++;
                            }
							totTiri++;
						}
					} else {
						if (tiratori2.hasNext() && ris2.hasNext()) {
							String res = ris2.next();
							JLabel resultLabel = new JLabel(tiratori2.next().getNominativo() + ": " + res);
							resultLabel.setHorizontalAlignment(JLabel.RIGHT);
							results2.add(resultLabel);
							results2.revalidate();
							results2.repaint();
							if (res.equals("Gol")) {
                            	gol2++;
                            }
							totTiri++;
						}
					}
				}
//            	if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2) || ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
//            		timer.cancel();
//                    if (gol1 > gol2) {
//                    	chiudi.setEnabled(true);
//                    	winner = s1;
//                        result.setText("Sfida terminata. Squadra vincente: " +  s1.getNomeSquadra());
//                    } else {
//                    	winner = s2;
//                        result.setText("Sfida terminata. Squadra vincente: " +  s2.getNomeSquadra());
//                    }
//				} else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
//                    if ((tiri1 + tiri2) % 2 == 0) {
//                    	if (!shooterIterator1.hasNext()) {
//                    		shooterIterator1 = backIterator(s1.getTitolari());
//                        }
//                        if (shooterIterator1.hasNext()) {
//                        	Calciatore tiratore = shooterIterator1.next();
//                        	String res = rigore(tiratore, s2);
//                            JLabel resultLabel = new JLabel(tiratore.getNominativo() + ": " + res);
//                            resultLabel.setHorizontalAlignment(JLabel.LEFT);
//                            results1.add(resultLabel);
//                            results1.revalidate();
//                            results1.repaint();
//                            if (res.equals("Gol")) {
//                            	gol1++;
//                            }
//                            tiri1++;
//                        }
//                    } else {
//                    	if (!shooterIterator2.hasNext()) {
//                    		shooterIterator2 = backIterator(s2.getTitolari());
//                        }
//                        if (shooterIterator2.hasNext()) {
//                        	Calciatore tiratore = shooterIterator2.next();
//                            String res = rigore(tiratore, s1); 
//                            JLabel resultLabel = new JLabel(tiratore.getNominativo() + ": " + res);
//                            resultLabel.setHorizontalAlignment(JLabel.RIGHT);
//                            results2.add(resultLabel);
//                            results2.revalidate();
//                            results2.repaint();
//                            if (res.equals("Gol")) {
//                            	gol2++;
//                            }
//                            tiri2++;
//                        }
//                    }
//				}
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    
    private String winner() {
    	if (gol1 > gol2) {
			winner = s1;
			return s1.getNomeSquadra();
		} else {
			winner = s2;
			return s2.getNomeSquadra();
		}
	}

	public SquadraAvversaria getWinner() {
    	return winner;
    }
    
    public void createAndShowGUI() {
    	setVisible(true);
    }
}
