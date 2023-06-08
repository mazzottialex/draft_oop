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
/**
 * Represents a GUI for Archive of players list.
 */
public class Archive extends Base {
    private static final long serialVersionUID = 1L;
    private final JTable table;
    private final LogicsArchive log;
    private final TableModel tm = new DefaultTableModel(new String[] {
            "POSITION",
            "PLAYERS",
            "RATING",
            "ATT",
            "MID",
            "DEF"
        }, 0);
    private static final int PANEL_WIDTH = 450;
    private static final int PANEL_HEIGHT = 640;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 28;
    private static final int LABEL_X = 262;
    private static final int LABEL_Y = 11;
    private static final int LABEL_WIDTH = 77;
    private static final int LABEL_HEIGHT = 17;
    private static final int TABLE_WIDTH = 420;
    private static final int TABLE_HEIGHT = 580;
    private static final int GAP = 5;
    private static final int PANEL_WIDTH2 = 400;
    private static final int PANEL_HEIGHT2 = 40;
    private static final int FONT_DIM = 14;

    /**
     * Constructs a new Archive object.
     * 
     * @param li      the list of players
     * @param season  the season
     * @param online  indicates whether it is an online archive
     * @throws FileNotFoundException    if a file is not found
     * @throws ClassNotFoundException   if a class is not found
     * @throws IOException               if an I/O error occurs
     */
    public Archive(final List<Player> li, final String season, final Boolean online)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        log = new LogicsArchiveImpl(season, online);
        List<Player> liOrdered = log.liOrdered(li);
        liOrdered.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toVector()));
        getPanel().setLayout(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
        getPanel().setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH2, PANEL_HEIGHT2));
        panel.setBackground(getForeground());
        getPanel().add(panel);
        panel.setLayout(null);
        JButton btnHome = UtilsGUI.standardButton("Home");
        btnHome.setBounds(10, 8, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new Home(log.getSeason(), log.getOnline(), false));
            }
        });
        panel.add(btnHome);
        JLabel lblNewLabel = new JLabel(log.getSeason());
        lblNewLabel.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, FONT_DIM));
        lblNewLabel.setForeground(Color.white);
        panel.add(lblNewLabel);
        table = new JTable(tm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        getPanel().add(scrollPane);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
        sorter.setComparator(0, new Comparator<Object>() {
            @Override
            public int compare(final Object o1, final Object o2) {
                return 0;
            }
        });
        table.setRowSorter(sorter);
    }
}
