package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.Calciatore;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import manageData.ExtractData;
import manageData.ExtractDataImpl;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;

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
		
		TableModel tm= new DefaultTableModel(new String[] {"RUOLO","GIOCATORE","RATING","ATT","CEN","DIF"},0);
				
		LogicsHome log=new LogicsHomeImpl("2022-2023");
		log.loadStagione("2022-2023");
		List<Calciatore> li= log.getLi();
		ExtractData ex =new ExtractDataImpl(li);
		li=ex.getListOrdered(c->-c.getRating().getX());
		li.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toVector()));
		
		
		
		table= new JTable(tm);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(620,610));
		contentPane.add(scrollPane);
		
		TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>(tm);
		sorter.setComparator(0, new Comparator<Object>() {
		    @Override
		    public int compare(Object o1, Object o2) {
		        return 0;
		    }
		});
		table.setRowSorter(sorter);
		
		JButton btnNewButton = new JButton("Torna alla home");
		getContentPane().add(btnNewButton);
	}

}