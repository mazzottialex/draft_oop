package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.border.Border;
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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600,40));
		panel.setBackground(getForeground());
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{61, 40, 1, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnHome.setBackground(Color.white);
		btnHome.setRolloverEnabled(true);
		btnHome.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.WEST;
		gbc_btnHome.insets = new Insets(0, 0, 0, 5);
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 0;
		panel.add(btnHome, gbc_btnHome);
		
		JLabel lblNewLabel = new JLabel("2022-2023");
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.white);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblEmpty = new JLabel("-----------------");
		lblEmpty.setForeground(getForeground());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblEmpty, gbc_lblNewLabel_1);
		
		
		
		table= new JTable(tm);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(620,600));
		contentPane.add(scrollPane);
		
		TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>(tm);
		sorter.setComparator(0, new Comparator<Object>() {
		    @Override
		    public int compare(Object o1, Object o2) {
		        return 0;
		    }
		});
		table.setRowSorter(sorter);
		
	}

}
