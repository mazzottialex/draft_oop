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
import data.Player;
import logics.LogicsArchive;
import logics.LogicsArchiveImpl;
import java.awt.FlowLayout;

public class Archive extends Base {
    private static final long serialVersionUID = 1L;
	private JTable table;
    private final LogicsArchive log;
    private final TableModel tm = new DefaultTableModel(new String[] {
            "POSITION",
            "PLAYERS",
            "RATING",
            "ATT",
            "MID",
            "DEF"
        }, 0);
    public Archive(List<Player> li, final String season, final Boolean online)
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        log = new LogicsArchiveImpl(season, online);
        List<Player> liOrdered = log.liOrdered(li);
        liOrdered.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toVector()));
        getPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getPanel().setPreferredSize(new Dimension(450, 640));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 40));
        panel.setBackground(getForeground());
        getPanel().add(panel);
        panel.setLayout(null);
        JButton btnHome = utilsGUI.standardButton("Home");
        btnHome.setBounds(10, 8, 80, 28);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new Home(log.getSeason(), log.getOnline(), false));
            }
        });
        panel.add(btnHome);
        JLabel lblNewLabel = new JLabel(log.getSeason());
        lblNewLabel.setBounds(262, 11, 77, 17);
        lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        lblNewLabel.setForeground(Color.white);
        panel.add(lblNewLabel);
        table = new JTable(tm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(420, 580));
        getPanel().add(scrollPane);
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
