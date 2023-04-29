package gui;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Calciatore;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import manageData.ExtractDataImpl;

import javax.swing.JTable;

public class Archivio extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Archivio frame = new Archivio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Archivio() throws FileNotFoundException, ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		table = new JTable(0,5);
		
		DefaultTableModel tm= (DefaultTableModel) table.getModel();
		
		LogicsHome log=new LogicsHomeImpl("2022-2023");
		log.loadStagione("2022-2023");
		List<Calciatore> li= log.getLi();
		
		li.stream().forEach(c -> tm.addRow(c.toVector()));
		//System.out.print(li.get(2).toString());
		contentPane.add(table);
		
		
		
	}

}
