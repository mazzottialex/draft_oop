package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogNThread extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private static final int FONT_DIM = 14;
    private int nThread=0;

	public DialogNThread(Frame parent) {
		super(parent, true);
		setBounds(200, 200, 250, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Integer[] arr= {1,2,3,4,5,6,7,8,9,10,11,12};
		JComboBox<Integer> comboBox = new JComboBox<>(arr);
		JLabel labelThread = new JLabel("Scegli numero di thread: ");
		labelThread.setFont(new Font("DejaVu Sans", Font.PLAIN, FONT_DIM));
		comboBox.setFont(new Font("DejaVu Sans", Font.PLAIN, FONT_DIM));
		contentPanel.add(labelThread);
		contentPanel.add(comboBox);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						nThread = comboBox.getItemAt(comboBox.getSelectedIndex());
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	public int getNThread() {
		return this.nThread;
	}
}
