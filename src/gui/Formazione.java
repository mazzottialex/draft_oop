package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Formazione extends Base{
	
	public Formazione() {
		JButton btnScegli=new JButton("Scegli Formazione");
		btnScegli.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnScegli.setBackground(Color.white);
		btnScegli.setRolloverEnabled(true);
		btnScegli.setForeground(Color.BLUE);
		
	}
}
