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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import data.Calciatore;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class Archivio extends Base {
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
	public Archivio() throws FileNotFoundException, ClassNotFoundException, IOException {
		
		DefaultTableModel tm= new DefaultTableModel(new String[] {"RUOLO","GIOCATORE","RATING","ATT","CEN","DIF"},0);
				
		LogicsHome log=new LogicsHomeImpl("2022-2023");
		log.loadStagione("2020-2021");
		List<Calciatore> li= log.getLi();
		
		ExtractData ex =new ExtractDataImpl(li);
		li=ex.getListOrdered(c->-c.getRating().getX());
		li.stream().forEach(c -> tm.addRow(c.toVector()));
		table= new JTable(tm);
		JScrollPane scrollPane=new JScrollPane(table);
		contentPane.add(scrollPane);
		
		
	}

}
