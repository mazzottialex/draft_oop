package gui;
import javax.swing.*;

import data.SquadraAvversaria;
import logics.LogicsPartita;
import logics.LogicsPartitaImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProgressBarExample extends Base {
    private JProgressBar progressBar;
	private JLabel jlNomeSq1;
	private JLabel jlScoreSq1;
	private JLabel jlTabSq1;
	private JLabel jlNomeSq2;
	private JLabel jlScoreSq2;
	private JLabel jlTabSq2;
	private JButton startStop;
	private JButton jbSubs;
	//private JLabel min;
	private JButton next;
	private JPanel panel;
	
	private LogicsPartita logics;
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
    
    private boolean isRunning;
    private boolean suppl;
    private int fineTempo;

    public ProgressBarExample(SquadraAvversaria s1, SquadraAvversaria s2) throws FileNotFoundException, ClassNotFoundException, IOException {
    	this.s1 = s1;
		this.s2 = s2;
		logics = new LogicsPartitaImpl(this.s1, this.s2);
    	
		// Define the panel to hold the components
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

		//define components
		jlNomeSq1 = new JLabel(s1.getNomeSquadra(), SwingConstants.RIGHT);
		jlScoreSq1 = new JLabel("0", SwingConstants.RIGHT);
		jlScoreSq1.setVerticalAlignment(SwingConstants.TOP);
		jlTabSq1 = new JLabel("sq1Label", SwingConstants.RIGHT);
		jlTabSq1.setVerticalAlignment(SwingConstants.TOP);
		jlNomeSq2 = new JLabel(s2.getNomeSquadra(), SwingConstants.LEFT);
		jlScoreSq2 = new JLabel("0", SwingConstants.LEFT);
		jlScoreSq2.setVerticalAlignment(SwingConstants.TOP);
		jlTabSq2 = new JLabel("sq2Label", SwingConstants.LEFT);
		jlTabSq2.setVerticalAlignment(SwingConstants.TOP);
		startStop = new JButton("Play");
		jbSubs = new JButton("Subs");
		jbSubs.setEnabled(false);
		//min = new JLabel("Minuto: 0°");
		next = new JButton("Avanti");
		next.setEnabled(false);
		progressBar = new JProgressBar(0, 90);
        progressBar.setStringPainted(true);
        progressBar.setString("Minuto 0°");
        suppl = false;
        fineTempo = 90;
        
        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(jlNomeSq1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(jlNomeSq2, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(jlScoreSq1, gbc);
                
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        panel.add(new JLabel("-", SwingConstants.CENTER), gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(jlScoreSq2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 200;
        panel.add(jlTabSq1, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipady = 200;
        panel.add(jlTabSq2, gbc);
       
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.ipady = 5;
        panel.add(progressBar, gbc);
        
        JPanel southWest = new JPanel();
        southWest.add(jbSubs);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        panel.add(southWest, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        panel.add(startStop, gbc);
        
        JPanel southEast = new JPanel();
        southEast.add(next);
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(southEast, gbc);
        
        // TODO aggiungere barra per supplementari
        
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
		contentPane.add(
				new JLabel(
						new ImageIcon(
								new ImageIcon("res/icon.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH))));


        startStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    stopProgress();
                } else {
                    startProgress();
                }
            }
        });

        jbSubs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopProgress();
				// TODO Aprire finestra per cambiare giocatori
			}
		});

    }

    private void startProgress() {
    	startStop.setText("Stop");
        isRunning = true;
        

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = progressBar.getValue(); i <= fineTempo && isRunning; i++) {
                    final int value = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	// TODO se il num di cambi è variato, ricalcolare il ris della partita e porlo in base al tempo rimanente
                            //Make progress.
                            progressBar.setValue(value);
                            progressBar.setString("Minuto " + String.valueOf(value) + "°");
                            // chiama funzione per gol
                            /*
                            try {
								changeScore();
							} catch (ClassNotFoundException | IOException e) {
								e.printStackTrace();
							} */
                            // chiama funzione per ammonizioni / espulsioni
                            logics.sanctions();
                            //Abilita bottone sostituzioni
                            if (progressBar.getValue() > 0) {
            					jbSubs.setEnabled(true);
            				}
                            //Fine 1° tempo
                            if (progressBar.getValue() == 45) { // TODO non funziona bene
                            	stopProgress();
        						// TODO aggiungere message dialog
        					}
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!suppl) {
					if (progressBar.getValue() == 90) {
						fineTempo = 120;
						progressBar.setMaximum(fineTempo);
						suppl = true;
					} 
				}
                
                
				
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	startStop.setText("Play");
                    }
                });
            }
        });

        thread.start();
    }
    
    public void changeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
        if (logics.getMinGol1().contains(progressBar.getValue())) {
        	logics.addScorer(s1);
        	jlScoreSq1.setText(Integer.toString(Integer.valueOf(jlScoreSq1.getText()) + 1));
        }
        if (logics.getMinGol2().contains(progressBar.getValue())) {
        	logics.addScorer(s2);
        	jlScoreSq2.setText(Integer.toString(Integer.valueOf(jlScoreSq2.getText()) + 1));
        }
	}

    private void stopProgress() {
        isRunning = false;
        startStop.setText("Play");
    }
    
    /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
     */
    public void createAndShowGUI() {
		//Create and set up the content pane.
    	add(contentPane);
    	contentPane.add(panel);
		
		//Display the window.
		pack();
		setVisible(true);
    }
}


