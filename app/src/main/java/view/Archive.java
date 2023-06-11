package view;
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

import org.apache.commons.exec.ExecuteException;

import controller.LogicsArchive;
import controller.LogicsArchiveImpl;
import model.data.Player;

import java.awt.FlowLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Represents a GUI for Archive of players list.
 */
public class Archive extends Base {
    private static final long serialVersionUID = 1L;
    private final transient LogicsArchive log;
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
    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 17;
    private static final int GAP = 5;
    private static final int PANEL_WIDTH2 = 400;
    private static final int PANEL_HEIGHT2 = 40;
    private static final int FONT_DIM = 14;
    private static final double WIDTH_SCROLLPANEL = 0.35;
    private static final double HEIGHT_SCROLLPANEL = 0.70;
    private static final Logger LOG = LoggerFactory.getLogger(Archive.class);
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
        final List<Player> liOrdered = log.liOrdered(li);
        liOrdered.stream().forEach(c -> ((DefaultTableModel) tm).addRow(c.toList().toArray()));
        super.getPanel().setLayout(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
        super.getPanel().setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH2, PANEL_HEIGHT2));
        panel.setBackground(super.getForeground());
        super.getPanel().add(panel);
        panel.setLayout(null);
        final JButton btnHome = UtilsGUI.standardButton("Home");
        btnHome.setBounds(10, 8, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    changeJPanel(new Home(log.getSeason(), log.getOnline(), false));
                } catch (ExecuteException e1) {
                    LOG.error("Error", e1);
                }
            }
        });
        panel.add(btnHome);
        final JLabel lblNewLabel = new JLabel(log.getSeason());
        lblNewLabel.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        lblNewLabel.setFont(new Font("Verdana", Font.ROMAN_BASELINE, FONT_DIM));
        lblNewLabel.setForeground(Color.white);
        panel.add(lblNewLabel);
        final JTable table = new JTable(tm);
        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(UtilsGUI.getWidth(WIDTH_SCROLLPANEL), UtilsGUI.getHeight(HEIGHT_SCROLLPANEL)));
        super.getPanel().add(scrollPane);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tm);
        sorter.setComparator(0, new Comparator<Object>() {
            @Override
            public int compare(final Object o1, final Object o2) {
                return 0;
            }
        });
        table.setRowSorter(sorter);
    }
}
