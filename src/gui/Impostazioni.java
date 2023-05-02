package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Impostazioni extends Base {
	private JTextField textFieldNomeSquadra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Impostazioni frame = new Impostazioni();
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
	public Impostazioni() {
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		
		contentPane.setLayout(layout);
		
		JLabel lblNomeSquadra = new JLabel("Nome Squadra:");
		lblNomeSquadra.setForeground(Color.white);
		lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets=new Insets(0,5, 0, 5);
		contentPane.add(lblNomeSquadra, gbc);
		
		
		textFieldNomeSquadra = new JTextField();
		textFieldNomeSquadra.setColumns(10);
		textFieldNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		gbc.gridx=1;
		gbc.gridy=0;
		contentPane.add(textFieldNomeSquadra,gbc);
		
		
		
		add(contentPane);
	}
}
