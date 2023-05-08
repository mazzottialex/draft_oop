package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.devtools.v109.dom.model.RGBA;

public class DialogScelta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String calciatore="";
	String selezionato="Osime";
	private JButton okButton;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DialogScelta(Frame parent, Boolean modale) {
		super(parent, modale);
		
		
		setBounds(100, 100, 650, 300);
		setMinimumSize(getSize());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints gbc=new GridBagConstraints();
		GridBagLayout layout=new GridBagLayout();
		contentPanel.setLayout(layout);
		
		List<JButton> li=new  ArrayList<>();
		for(int i=0;i<5;i++) {
			String nomeCalciatore="ciro";
			JButton btnCalciatore=utilsGUI.getButtonCalciatore(nomeCalciatore, "P");
			li.add(btnCalciatore);
			btnCalciatore.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selezionato=nomeCalciatore;
					JButton btn= (JButton)e.getSource();
					li.forEach(el-> el.setBackground(new Color(240,240,240)));
					btn.setBackground(Color.LIGHT_GRAY);
				}	
			});
			contentPanel.add(btnCalciatore);
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
