package guiV2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.Calciatore;
import logics.LogicsHome;
import logics.LogicsHomeImpl;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Archivio extends Base {

	private JTable table;
	
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
	public Archivio() throws FileNotFoundException, ClassNotFoundException, IOException {
		
		TableModel tm= new DefaultTableModel(new String[] {"RUOLO","GIOCATORE","RATING","ATT","CEN","DIF"},0);
				
		LogicsHome log=new LogicsHomeImpl("2022-2023");
		log.loadStagione("2022-2023");
		List<Calciatore> li= log.getLi();
		ExtractData ex =new ExtractDataImpl(li);
		li=ex.getListOrdered(c->-c.getRating().getX());
		li.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toVector()));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.setPreferredSize(new Dimension(450, 640));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,40));
		panel.setBackground(getForeground());
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnHome = new JButton("HOME");

		btnHome.setBounds(10, 8, 70, 28);
		btnHome.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
		btnHome.setBackground(Color.white);
		btnHome.setRolloverEnabled(true);
		btnHome.setForeground(Color.BLUE);
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				Home home=new Home();
				getContentPane().add(home.getPanel());
				validate();
				contentPane.updateUI();
			}
		});
		panel.add(btnHome);
		
		JLabel lblNewLabel = new JLabel("2022-2023");
		lblNewLabel.setBounds(262, 11, 77, 17);
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.white);
		panel.add(lblNewLabel);
		
		
		
		table= new JTable(tm);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400,550));
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

	public JPanel getPanel() {
		return this.contentPane;
	}
}