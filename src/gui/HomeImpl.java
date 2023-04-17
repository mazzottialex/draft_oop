package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JList;

public class HomeImpl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeImpl frame = new HomeImpl();
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
	public HomeImpl() {
		getContentPane().setBackground(new Color(164, 205, 255));
		setForeground(new Color(255, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\alexm\\OneDrive\\Desktop\\workspace-prog\\draft_oop\\res\\icon.png"));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 408);
		getContentPane().setLayout(null);
		
		Button btnStart = new Button("INIZIA PARTITA");
		btnStart.setBounds(207, 118, 100, 22);
		getContentPane().add(btnStart);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(251, 212, 62, 20);
		getContentPane().add(textPane);
		
		JLabel lblCaricaStagione = new JLabel("Carica stagione:");
		lblCaricaStagione.setFont(new Font("Serif", Font.PLAIN, 13));
		lblCaricaStagione.setBounds(115, 258, 100, 22);
		getContentPane().add(lblCaricaStagione);
		
		JLabel lblStagioneSelezionata = new JLabel("Stagione selezionata");
		lblStagioneSelezionata.setFont(new Font("Serif", Font.PLAIN, 13));
		lblStagioneSelezionata.setBounds(115, 212, 126, 22);
		getContentPane().add(lblStagioneSelezionata);
		
		JList listCarica = new JList();
		listCarica.setBounds(238, 262, 75, 20);
		getContentPane().add(listCarica);
		
		JLabel lblDraft = new JLabel("DRAFT");
		lblDraft.setFont(new Font("Serif", Font.PLAIN, 18));
		lblDraft.setBounds(224, 82, 62, 22);
		getContentPane().add(lblDraft);
		
		JLabel lblNewLabel_1 = new JLabel("Aggiorna stagione:");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(115, 306, 126, 22);
		getContentPane().add(lblNewLabel_1);
		
		JList listAggiorna = new JList();
		listAggiorna.setBounds(238, 310, 75, 20);
		getContentPane().add(listAggiorna);
	}
}
