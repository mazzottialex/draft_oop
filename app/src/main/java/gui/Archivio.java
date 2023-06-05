package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import data.Calciatore;
import logics.LogicsArchivio;
import logics.LogicsArchivioImpl;
import java.awt.FlowLayout;

public class Archivio extends Base {
    private JTable table;
    private final LogicsArchivio log;
    private final TableModel tm = new DefaultTableModel(new String[] {
            "RUOLO",
            "GIOCATORE",
            "RATING",
            "ATT",
            "CEN",
            "DIF"
        }, 0);
    public Archivio(List<Calciatore> li, final String stagioneP, final Boolean online)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        this.stagione = stagioneP;
        this.online = online;
        log = new LogicsArchivioImpl(online);
        li = log.liOrdinata(li);
        li.forEach(c -> System.out.println(c.getNominativo() + ";" + c.getRuolo()));
        li.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toVector()));
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.setPreferredSize(new Dimension(450, 640));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 40));
        panel.setBackground(getForeground());
        contentPane.add(panel);
        panel.setLayout(null);
        JButton btnHome = utilsGUI.standardButton("Home");
        btnHome.setBounds(10, 8, 80, 28);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new Home(stagione, online));
            }
        });
        panel.add(btnHome);
        JLabel lblNewLabel = new JLabel(stagione);
        lblNewLabel.setBounds(262, 11, 77, 17);
        lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        lblNewLabel.setForeground(Color.white);
        panel.add(lblNewLabel);
        table = new JTable(tm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 580));
        contentPane.add(scrollPane);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
        sorter.setComparator(0, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        table.setRowSorter(sorter);
    }
}
