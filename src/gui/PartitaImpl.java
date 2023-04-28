package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		JButton jbAvvia = new JButton("Avvia");
		JButton jbSubs = new JButton("Sostituzioni");
		JSeparator jsVert1 = new JSeparator(SwingConstants.VERTICAL);
		jsVert1.setPreferredSize(new Dimension(5,1));
		JSeparator jsVert2 = new JSeparator(SwingConstants.VERTICAL);
		jsVert2.setPreferredSize(new Dimension(5,3));
		JSeparator jsOr = new JSeparator(SwingConstants.HORIZONTAL);
		jsOr.setPreferredSize(new Dimension(1,5));
		//def prog bar
		
		// Define the panel to hold the components
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        Insets ins = new Insets(10, 10, 10, 10);
        gbc.insets = ins;
        
        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlNomeSq1, gbc);
        
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        //gbc.weighty = 1;
//        panel.add(jsVert1, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlNomeSq2, gbc);
                
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        //gbc.weightx = 1;
//        gbc.gridwidth = 3;
//        panel.add(jsOr, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlScoreSq1, gbc);
                
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        //gbc.weighty = 1;
//        gbc.gridheight = 2;
//        panel.add(jsVert2, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        //gbc.ipady = 10;
        panel.add(jlScoreSq2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 50;
        gbc.ipady = 500;
        panel.add(jlTabSq1, gbc);
                
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipadx = 50;
        gbc.ipady = 500;
        panel.add(jlTabSq2, gbc);
 
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        //gbc.gridwidth = 2;
//        panel.add(new JButton("Button 3"), gbc);
//        
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridheight = 3;
//        panel.add(new JButton("Button x"), gbc);
//        
        // add panels into the frame
//      f.setLayout(new BorderLayout());
      	f.add(panel);
		
		/*
		//define panel to hold components
		JPanel jpText = new JPanel();
		JPanel jpButton = new JPanel();
		JPanel jpSq1 = new JPanel();
		JPanel jpSq2 = new JPanel();
		
		//setup the box layout
		BoxLayout layoutText = new BoxLayout(jpText, BoxLayout.X_AXIS);
		jpText.setLayout(layoutText);
		BoxLayout layoutButton = new BoxLayout(jpButton, BoxLayout.X_AXIS);
		jpButton.setLayout(layoutButton);
		BoxLayout layoutSq1 = new BoxLayout(jpSq1, BoxLayout.Y_AXIS);
		jpSq1.setLayout(layoutSq1);
		BoxLayout layoutSq2 = new BoxLayout(jpSq2, BoxLayout.Y_AXIS);
		jpSq2.setLayout(layoutSq2);
		
		//add tools to panels
		jpText.add(jpSq1);
		jpSq1.add(jlNomeSq1);
		jpSq1.add(jlScoreSq1);
		jpSq1.add(jlTabSq1);
		
		jpText.add(jsVert);
		
		jpText.add(jpSq2);
		jpSq2.add(jlNomeSq2);
		jpSq2.add(jlScoreSq2);
		jpSq2.add(jlTabSq2);
		
		jpButton.add(jbAvvia);
		jpButton.add(jbSubs);
		
		//add panels into the frame
		f.setLayout(new BorderLayout());
		f.add(jpText, BorderLayout.CENTER);
		f.add(jpButton, BorderLayout.SOUTH)*/
	}
	
	@Override
	public void setVisible(boolean b) {
		f.pack();
		f.setVisible(b);
	}

}
