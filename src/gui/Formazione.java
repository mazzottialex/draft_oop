package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import data.Calciatore;
import data.Modulo;
import logics.LogicsFormazione;
import logics.LogicsFormazioneImpl;

public class Formazione extends Base{
	private List<Modulo> liModuli;
	private Modulo modulo;
	private LogicsFormazione log;

	public Formazione(List<Calciatore> li) {
		log=new LogicsFormazioneImpl();
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPane.setLayout(layout);
		
		JButton btnScegli=new JButton("Scegli Formazione");
		btnScegli.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnScegli.setBackground(Color.white);
		btnScegli.setRolloverEnabled(true);
		btnScegli.setForeground(Color.BLUE);
		liModuli=log.getRandomMod(5);
		btnScegli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn= (JButton) e.getSource();
				JPanel panel=(JPanel) btn.getParent();
				JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);

				try {
					DialogSceltaFormazione dialog=new DialogSceltaFormazione(parent, true, liModuli);
					dialog.setVisible(true);
					modulo=dialog.getModulo();
					if(modulo!=null) {
						panel.remove(btn);
						JLabel lblModulo=new JLabel("Modulo Scelto:"+modulo.toString());
						lblModulo.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
						lblModulo.setForeground(Color.white);
						gbc.gridy=0;
						gbc.insets=new Insets(10, 0, 10, 0);
						panel.add(lblModulo, gbc);
						JButton btnProsegui=new JButton("Prosegui");
						btnProsegui.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
						btnProsegui.setBackground(Color.white);
						btnProsegui.setRolloverEnabled(true);
						btnProsegui.setForeground(Color.BLUE);
						btnProsegui.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								changeJPanel(new Draft(li, modulo));
							}
						});
						gbc.gridy=1;
						panel.add(btnProsegui, gbc);
					}
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				panel.revalidate();
				panel.repaint();
				
			}
		});
		contentPane.add(btnScegli);
	}
	
}
