package guiV2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Start extends Base {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		ImageIcon img=new ImageIcon("res/icon.png");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		JLabel labelIcon = new JLabel(img);
		
		labelIcon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ImageIcon img = new ImageIcon(newimg);
				labelIcon.setIcon(img);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Image newimg = image.getScaledInstance(180, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ImageIcon img = new ImageIcon(newimg);
				labelIcon.setIcon(img);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().removeAll();
				Home home=new Home();
				getContentPane().add(home.getPanel());
				validate();
				contentPane.updateUI();
				
			}
		});
		
		contentPane.add(labelIcon);
		
		
	}

}
