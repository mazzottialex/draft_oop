package gui;

import java.awt.BorderLayout;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import data.Calciatore;
import data.SquadraAvversaria;

public class Rigori extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5140476454072046580L;
	private SquadraAvversaria s1;
    private SquadraAvversaria s2;
    private Iterator<Calciatore> shooterIterator1;
    private Iterator<Calciatore> shooterIterator2;
    private JPanel results1;
    private JPanel results2;
    private JLabel result;
    private int gol1;
    private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    
    public Rigori(SquadraAvversaria s1, SquadraAvversaria s2) {
    	this.s1 = s1;
        this.s2 = s2;
		this.shooterIterator1 = s1.getTitolari().iterator();
		this.shooterIterator2 = s2.getTitolari().iterator();
		this.gol1 = 0;
		this.gol2 = 0;
		this.tiri1 = 0;
		this.tiri2 = 0;
		this.totTiri = 10;
		
		//gui di prova
		setTitle("Penalty Shootout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 400);
        setLocationRelativeTo(null);

        results1 = new JPanel();
        results1.setLayout(new BoxLayout(results1, BoxLayout.Y_AXIS));
        JScrollPane scrollPane1 = new JScrollPane(results1);
        add(scrollPane1, BorderLayout.WEST);

        results2 = new JPanel();
        results2.setLayout(new BoxLayout(results2, BoxLayout.Y_AXIS));
        JScrollPane scrollPane2 = new JScrollPane(results2);
        add(scrollPane2, BorderLayout.EAST);

        result = new JLabel();
        result.setHorizontalAlignment(JLabel.CENTER);
        add(result, BorderLayout.CENTER);
        
        JButton inizia = new JButton("inizia");
        inizia.addActionListener(e -> {
            inizia.setEnabled(false);
            // funzione per far partire la gara
        });
        add(inizia, BorderLayout.NORTH);
    }
    
    private void startPenaltyShootout() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2) || ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
            		timer.cancel();
                    if (gol1 > gol2) {
                        result.setText("Sfida terminata: Vince Team 1");
                    } else {
                        result.setText("Sfida terminata: Vince Team 2");
                    }
				} else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                    if ((tiri1 + tiri2) % 2 == 0) {
                    	if (!shooterIterator1.hasNext()) {
                    		shooterIterator1 = s1.getTitolari().iterator();
                        }
                        if (shooterIterator1.hasNext()) {
                        	Calciatore tiratore = shooterIterator1.next();
                        	String res = rigore(tiratore, s1);
                            JLabel resultLabel = new JLabel(tiratore.getNominativo() + ": " + result);
                            resultLabel.setHorizontalAlignment(JLabel.LEFT);
                            results1.add(resultLabel);
                            results1.revalidate();
                            results1.repaint();
                            if (res.equals("Gol")) {
                            	gol1++;
                            }
                            tiri1++;
                        }
                    } else {
                    	if (!shooterIterator2.hasNext()) {
                    		shooterIterator2 = s2.getTitolari().iterator();
                        }
                        if (shooterIterator2.hasNext()) {
                        	Calciatore tiratore = shooterIterator2.next();
                            String res = rigore(tiratore, s2); 
                            JLabel resultLabel = new JLabel(tiratore.getNominativo() + ": " + result);
                            resultLabel.setHorizontalAlignment(JLabel.RIGHT);
                            results2.add(resultLabel);
                            results2.revalidate();
                            results2.repaint();
                            if (res.equals("Gol")) {
                            	gol2++;
                            }
                            tiri2++;
                        }
                    }
				}
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    
    private String rigore(Calciatore tiratore, SquadraAvversaria dif) {
        double tirRating = tiratore.getRating().getY().getX() * (0.8 + new Random().nextDouble(0.4));

        double porRating = dif.getPortiereTit().getRating().getY().getX() * (0.8 + new Random().nextDouble(0.4));

        double modPorRating = porRating * 0.75 /*costante rigori fatti nei shootout*/;
        if (tirRating > modPorRating) {
            return "Gol";
        } else {
            return "Sbagliato";
        }
    }
    
    public void createAndShowGUI() {
    	setVisible(true);
    }
}
