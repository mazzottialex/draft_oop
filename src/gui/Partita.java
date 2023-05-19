package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import data.SquadraAvversaria;
import logics.LogicsPartita;
import logics.LogicsPartitaImpl;


public class Partita extends Base implements ActionListener, PropertyChangeListener{

	private static final long serialVersionUID = 1353924410908490014L;
	
	private JProgressBar jpPb;
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
	
	private Task task;
	private LogicsPartita logics;
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;

	private int progress;
	
	/**
	 * Create the frame.
	 */
	
	abstract class PausableSwingWorker<K, V> extends SwingWorker<K, V> {

        private volatile boolean isPaused;
        private volatile boolean isRunning = false;

        public final void pause() {
            if (!isPaused() && !isDone()) {
                isPaused = true;
                firePropertyChange("paused", false, true);
            }
        }

        public final void resume() {
            if (isPaused() && !isDone()) {
                isPaused = false;
                firePropertyChange("paused", true, false);
            }
        }
        
        public final void start() {
            if (!isDone()) {
            	isRunning = true;
            }
        }

        public final boolean isPaused() {
            return isPaused;
        }
        
        public final boolean isRunning() {
            return isRunning;
        }
    }
	
	class Task extends PausableSwingWorker<Void, Void> {

		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() throws FileNotFoundException, ClassNotFoundException, IOException {
            progress = 0;
            //Initialize progress property.
            setProgress(0);
            logics.scorers();
            while (progress < 100) {
            	if (!isPaused()) {
            		//Sleep for up to one second.
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {}
                    // TODO se il num di cambi è variato, ricalcolare il ris della partita e porlo in base al tempo rimanente
                    //Make progress.
                    progress += 1;
                    setProgress(Math.min(progress, 100));
                    // chiama funzione per gol
                    changeScore();
                    // chiama funzione per ammonizioni / espulsioni
                    logics.sanctions();
                    //Abilita bottone sostituzioni
                    if (progress > 0) {
    					jbSubs.setEnabled(true);
    				}
                    //Fine 1° tempo
                    if (progress == 50) {
						task.pause();
						// TODO aggiungere message dialog
					}
    			} else {
    				try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignore) {}
    			}
            }
            // TODO fare if, dove se risultato è pari, far apparire nuova barra per supplementari
            if (jlScoreSq1.getText() == jlScoreSq2.getText()) {
            	
            }
            // TODO fare if dove se risultato supplementari è pari, fare rigori
            return null;
        }
		
		/*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {	//quando finisce
        	next.setEnabled(true);
        	// TODO scrivere chi ha vinto
        	JOptionPane.showMessageDialog(null, "Partita finita");	//dire che è finito
        }
	}
	
	public void changeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
        if (logics.getMinGol1().contains(progress)) {
        	logics.addScorer(s1);
        	jlScoreSq1.setText(Integer.toString(Integer.valueOf(jlScoreSq1.getText()) + 1));
        }
        if (logics.getMinGol2().contains(progress)) {
        	logics.addScorer(s2);
        	jlScoreSq2.setText(Integer.toString(Integer.valueOf(jlScoreSq2.getText()) + 1));
        }
	}

	public Partita(SquadraAvversaria s1, SquadraAvversaria s2) throws FileNotFoundException, ClassNotFoundException, IOException {
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
		startStop = new JButton("> / ||");
		startStop.setActionCommand("start");
		startStop.addActionListener(this);
		jbSubs = new JButton("Subs");
		jbSubs.setEnabled(false);
		jbSubs.setActionCommand("subs");
		jbSubs.addActionListener(this);
		//min = new JLabel("Minuto: 0°");
		next = new JButton("Avanti");
		next.setEnabled(false);
		jpPb = new JProgressBar();
		jpPb.setValue(0);
		jpPb.setStringPainted(true);
		jpPb.setString("Minuto 0°");
		task = new Task();
        task.addPropertyChangeListener(this);
		
        
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
        panel.add(jpPb, gbc);
        
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
	}
	
	/**
     * Invoked when the user presses buttons.
     */
    public void actionPerformed(ActionEvent evt) {
        //Instances of javax.swing.SwingWorker are not reusuable, so we create new instances as needed.
    	if (evt.getActionCommand() == "start") {
    		if (!task.isRunning()) {
    			task.execute();
    			task.start();
			} else {
				if (task.isPaused()) {
					task.resume();
				} else {
					task.pause();
				}
			}
		} else if (evt.getActionCommand() == "subs") {
			task.pause();
			// TODO Aprire finestra per cambiare giocatori
		}
    }

    public void setNomeS1(JLabel jlNomeSq1) {
		this.jlNomeSq1 = jlNomeSq1;
	}

	public void setScoreS1(JLabel jlScoreSq1) {
		this.jlScoreSq1 = jlScoreSq1;
	}

	public void setTabS1(JLabel jlTabSq1) {
		this.jlTabSq1 = jlTabSq1;
	}

	public void setNomeS2(JLabel jlNomeSq2) {
		this.jlNomeSq2 = jlNomeSq2;
	}

	public void setScoreS2(JLabel jlScoreSq2) {
		this.jlScoreSq2 = jlScoreSq2;
	}

	public void setTabS2(JLabel jlTabSq2) {
		this.jlTabSq2 = jlTabSq2;
	}

	/**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
    	int val;
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            jpPb.setValue(progress);
            val = Math.round(((float)task.getProgress() / 100f) * 90f);
            jpPb.setString("Minuto " + Integer.toString(val) + "°");
        } 
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