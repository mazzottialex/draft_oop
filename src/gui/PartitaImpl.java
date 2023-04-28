package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PartitaImpl implements Partita{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8420554109103794277L;
	JFrame f = new JFrame();
	/**
	 * Create the frame.
	 */
	public PartitaImpl() {
		
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
		JLabel jlNomeSq1 = new JLabel("sq1", SwingConstants.RIGHT);
		JLabel jlScoreSq1 = new JLabel("nGolsq1", SwingConstants.RIGHT);
		jlScoreSq1.setVerticalAlignment(SwingConstants.TOP);
		JLabel jlTabSq1 = new JLabel("sq1Label", SwingConstants.RIGHT);
		jlTabSq1.setVerticalAlignment(SwingConstants.TOP);
		JLabel jlNomeSq2 = new JLabel("sq2", SwingConstants.LEFT);
		JLabel jlScoreSq2 = new JLabel("nGolsq2", SwingConstants.LEFT);
		jlScoreSq2.setVerticalAlignment(SwingConstants.TOP);
		JLabel jlTabSq2 = new JLabel("sq2Label", SwingConstants.LEFT);
		jlTabSq2.setVerticalAlignment(SwingConstants.TOP);
		JButton startStop = new JButton("> / ||");
		JButton jbSubs = new JButton("Subs");
		JLabel min = new JLabel("Minuto: 0°");
		/*mettere progress bar*/JButton jbPB = new JButton("progress bar");
		
		
		// Define the panel to hold the components
        JPanel panel = new JPanel();
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
        panel.add(jbPB, gbc);
        
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
	}
	
	@Override
	public void setVisible(boolean b) {
		f.pack();
		f.setVisible(b);
	}

}
