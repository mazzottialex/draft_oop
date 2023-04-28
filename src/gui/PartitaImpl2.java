package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.util.Random;

public class PartitaImpl2 implements ActionListener, PropertyChangeListener{

	JFrame f;
	JPanel panel;
	static JProgressBar jpPb;
	JLabel jlNomeSq1;
	JLabel jlScoreSq1;
	JLabel jlTabSq1;
	JLabel jlNomeSq2;
	JLabel jlScoreSq2;
	JLabel jlTabSq2;
	JButton startStop;
	JButton jbSubs;
	JLabel min;
	
	private Task task;
	
	/**
	 * Create the frame.
	 */
	
	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            while (progress < 100) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                //Make random progress.
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {	//quando finisce
            //Toolkit.getDefaultToolkit().beep();
            //startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
            //taskOutput.append("Done!\n");  //dire che è finito
        }
    }
	
	public PartitaImpl2() {
		
		f = new JFrame();
		f.setTitle("SIMULAZIONE PARTITA");
		
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth();
		final int sh = (int) screen.getHeight();
		f.setSize(2 * sw / 3, 2 * sh / 3);
//		f.setBounds(100, 100, 640, 700);
//		f.setMinimumSize(new Dimension(640,700));
		
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		jbSubs = new JButton("Subs");
		min = new JLabel("Minuto: 0°");
		jpPb = new JProgressBar();					//non funziona
		jpPb.setValue(0);
		jpPb.setStringPainted(true);
		
		
		
		// Define the panel to hold the components
        panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlNomeSq1, gbc);
        
        //gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        //gbc.weighty = 1;
        gbc.ipadx = 50;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
//        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlNomeSq2, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
//        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlScoreSq1, gbc);
                
        //gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        //gbc.weighty = 1;
        panel.add(new JLabel("-", SwingConstants.CENTER), gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
//        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlScoreSq2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
//        gbc.ipadx = 50;
        gbc.ipady = 200;
        panel.add(jlTabSq1, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
//        gbc.ipadx = 50;
        gbc.ipady = 200;
        panel.add(jlTabSq2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        //gbc.weightx = 1;
        gbc.gridwidth = 3;
        gbc.ipady = 5;
        panel.add(jpPb, gbc);
        
      //gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 5;
        //gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        gbc.insets = new Insets(10, 20, 10, 10);
        panel.add(min, gbc);
        
        //gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 5;
        //gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(startStop, gbc);
        
        //gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 5;
        //gbc.weightx = 1;
        panel.add(jbSubs, gbc);
      
        
        // add panels into the frame
      	f.add(panel);
      	
      	//fill();
	}
	
	/**
     * Invoked when the user presses the start button.
     */
    public void actionPerformed(ActionEvent evt) {
        startButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                    "Completed %d%% of task.\n", task.getProgress()));
        } 
    }
    
    /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ProgressBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ProgressBarDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	
//	public static void fill() {
//        int i = 0;
//        try {
//            while (i <= 100) {
//                // set text according to the level to which the bar is filled
//                if (i > 30 && i < 70) {
//                	jpPb.setString("wait for sometime");
//                } else if (i > 70 && i < 100) {
//                	jpPb.setString("almost finished loading");
//                } else if (i == 100) {
//                	jpPb.setString("finished loading");
//                } else {
//                	jpPb.setString("loading started");
//            	}
//                // fill the menu bar
//                jpPb.setValue(i + 10);
//                // delay the thread
//                Thread.sleep(3000);
//                i += 20;
//            }
//        } catch (Exception e) {
//        	
//        }
//    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}
