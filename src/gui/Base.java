package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

public class Base extends JFrame {

	protected JPanel contentPane;

	public Base() {
		setTitle("DRAFT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		setMinimumSize(getSize());
		setBackground(new Color(0, 64, 128));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setLayout(new BorderLayout());
		add(contentPane);
	}

	public JPanel getPanel() {
		return contentPane;
	}
	
	protected void changeJPanel(Base newPanel) {
		JFrame frame = (JFrame)contentPane.getTopLevelAncestor();
		frame.remove(contentPane);
		frame.add(newPanel.getPanel());
		frame.revalidate();
		frame.repaint();
	}
}
