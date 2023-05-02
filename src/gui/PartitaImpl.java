package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class PartitaImpl implements Partita {

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
	JButton next;
	
	/**
	 * Create the frame.
	 */
	public PartitaImpl() {
		
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
		jbSubs.setEnabled(false);
		min = new JLabel("Minuto: 0°");
		next = new JButton("Avanti");
		next.setEnabled(false);
		jpPb = new JProgressBar(10, 90);					//non funziona
		jpPb.setVisible(true);
		jpPb.setValue(10);
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
        
        
        JPanel southEast = new JPanel();
        //southEast.setLayout(new FlowLayout());
        southEast.add(jbSubs);
        southEast.add(next);
        
        gbc.insets = new Insets(10, 0, 10, 0);
        //gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 5;
        //gbc.weightx = 1;
        panel.add(southEast, gbc);
      
        
        // add panels into the frame
      	f.add(panel);
      	
      	//fill();
	}

	@Override
	public void setVisible(boolean b) {
		f.pack();
		f.setVisible(b);
		
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

}
