package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogScelta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String calciatore="";
	private JButton okButton;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DialogScelta(Frame parent, Boolean modale) {
		super(parent, modale);
		String selezionato="Osime";
		
		setBounds(100, 100, 650, 300);
		setMinimumSize(getSize());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPanel.setLayout(layout);
		
		for(int i=0;i<5;i++) {
			JButton panelGiocatore=new JButton();
			panelGiocatore.setLayout(layout);
			panelGiocatore.setPreferredSize(new Dimension(110,150));
			JLabel lblNome=new JLabel("Osimenh");
			lblNome.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
			JLabel lblIcona = new JLabel();
			ImageIcon img=new ImageIcon("res/attaccante.png");
			Image image = img.getImage(); // transform it 
			Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			img = new ImageIcon(newimg);
			lblIcona.setIcon(img);
			gbc.gridy=0;
			panelGiocatore.add(lblIcona,gbc);
			gbc.gridy=1;
			panelGiocatore.add(lblNome,gbc); 
			contentPanel.add(panelGiocatore);
		}
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						calciatore=selezionato;
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}
	
	public String getCalciatore() {
		return this.calciatore;
	}
}
