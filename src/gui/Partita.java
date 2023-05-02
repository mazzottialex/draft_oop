package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;


public class Partita extends JPanel implements ActionListener, PropertyChangeListener{

	private static final long serialVersionUID = 1353924410908490014L;
	
	JProgressBar jpPb;
	JLabel jlNomeSq1;
	JLabel jlScoreSq1;
	JLabel jlTabSq1;
	JLabel jlNomeSq2;
	JLabel jlScoreSq2;
	JLabel jlTabSq2;
	JButton startStop;
	JButton jbSubs;
	JLabel min;
	JButton next;
	
	private Task task;
	
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
		public Void doInBackground() {
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            while (progress < 100) {
            	if (!isPaused()) {
            		//Sleep for up to one second.
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {}
                    //Make progress.
                    progress += 1;
                    setProgress(Math.min(progress, 100));
                    //Abilita bottone sostituzioni
                    if (progress > 0) {
    					jbSubs.setEnabled(true);
    				}
                    //Fine 1° tempo
//                    if (progress == 50) {
//						task.pause();
//					}
    			} else {
    				try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignore) {}
    			}
            }
            return null;
        }
		
		/*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {	//quando finisce
        	next.setEnabled(true);
        	JOptionPane.showMessageDialog(null, "Partita finita");	//dire che è finito
        }
	}

	public Partita() {
		// Define the panel to hold the components
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

		//define components
		jlNomeSq1 = new JLabel("sq1", SwingConstants.RIGHT);
		jlScoreSq1 = new JLabel("nGolsq1", SwingConstants.RIGHT);
		jlScoreSq1.setVerticalAlignment(SwingConstants.TOP);
		jlTabSq1 = new JLabel("sq1Label", SwingConstants.RIGHT);
		jlTabSq1.setVerticalAlignment(SwingConstants.TOP);
		jlNomeSq2 = new JLabel("sq2", SwingConstants.LEFT);
		jlScoreSq2 = new JLabel("nGolsq2", SwingConstants.LEFT);
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
		min = new JLabel("Minuto: 0°");
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
        /*panel.*/add(jlNomeSq1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        /*panel.*/add(new JLabel("vs", SwingConstants.CENTER), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        /*panel.*/add(jlNomeSq2, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        /*panel.*/add(jlScoreSq1, gbc);
                
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        /*panel.*/add(new JLabel("-", SwingConstants.CENTER), gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        /*panel.*/add(jlScoreSq2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 200;
        /*panel.*/add(jlTabSq1, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipady = 200;
        /*panel.*/add(jlTabSq2, gbc);
       
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.ipady = 5;
        /*panel.*/add(jpPb, gbc);
        
        JPanel southWest = new JPanel();
        southWest.add(jbSubs);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        /*panel.*/add(southWest, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        /*panel.*/add(startStop, gbc);
        
        JPanel southEast = new JPanel();
        southEast.add(next);
        gbc.gridx = 2;
        gbc.gridy = 5;
        /*panel.*/add(southEast, gbc);
	}
	
	/**
     * Invoked when the user presses the start button.
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
    	//Create and set up the window.
    	JFrame f = new JFrame("SIMULAZIONE PARTITA");
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth();
		final int sh = (int) screen.getHeight();
		f.setSize(2 * sw / 3, 2 * sh / 3);
//		f.setBounds(100, 100, 640, 700);
//		f.setMinimumSize(new Dimension(640,700));
		f.setLocationRelativeTo(null);
		
		//Create and set up the content pane.
        JComponent newContentPane = new Partita();
        newContentPane.setOpaque(true); //content panes must be opaque
        f.setContentPane(newContentPane);
		
		//Display the window.
		f.pack();
		f.setVisible(true);
    }
    
    /*
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
}