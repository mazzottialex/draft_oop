package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.exec.ExecuteException;

import controller.LogicsHistory;
import controller.LogicsHistoryImpl;
import model.data.Team;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Represents a GUI for History of teams created.
 */
public class History extends Base {
    private static final long serialVersionUID = 1L;
    private final transient LogicsHistory log;
    private static final int PANEL_DIM = 50;
    private static final Color COLOR_PANEL = new Color(0, 64, 128);
    private static final int SCALED_IMAGE = 55;
    private static final Insets INSETS_1 = new Insets(5, 0, 5, 25);
    private static final Insets INSETS_2 = new Insets(2, 25, 2, 25);
    private static final Logger LOG = LoggerFactory.getLogger(History.class);
    /**
     * Constructs a new History object.
     * @param season the season selected
     * @param online the online status
     */
    public History(final String season, final Boolean online) {
        log = new LogicsHistoryImpl(season, online);
        final GridBagConstraints gbc = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        super.getPanel().setLayout(new BorderLayout());
        final JButton btnHome = UtilsGUI.standardButton("Home");
        final JPanel panelBtn = new JPanel();
        panelBtn.setBackground(super.getForeground());
        panelBtn.setPreferredSize(new Dimension(PANEL_DIM, PANEL_DIM));
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
        panelBtn.add(btnHome);
        super.getPanel().add(panelBtn, BorderLayout.NORTH);
        final JPanel panelLi = new JPanel();
        panelLi.setLayout(layout);
        panelLi.setBackground(COLOR_PANEL);
        int count = 0;
        for (final Team team : log.getLiTeam()) {
            final JPanel panelTeam = new JPanel();
            panelTeam.setBackground(super.getForeground());
            final JLabel lblLogo = new JLabel();
            ImageIcon img = new ImageIcon(team.getLogo());
            final Image image = img.getImage();
            final Image newImg = image.getScaledInstance(SCALED_IMAGE, SCALED_IMAGE,  Image.SCALE_SMOOTH);
            img = new ImageIcon(newImg);
            lblLogo.setBackground(Color.white);
            lblLogo.setIcon(img);
            lblLogo.setBorder(new EmptyBorder(INSETS_1));
            final JLabel lblNomeSquadra = new JLabel(team.getTeamName());
            lblNomeSquadra.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
            lblNomeSquadra.setForeground(Color.white);
            panelTeam.add(lblNomeSquadra);
            final JLabel lblRating = new JLabel("Valutazione: " + team.getRating());
            lblRating.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
            lblRating.setForeground(Color.white);
            lblRating.setBorder(new EmptyBorder(INSETS_2));
            panelTeam.add(lblRating);
            panelTeam.add(lblRating);
            final JButton btnVedi = UtilsGUI.standardButton("Vedi");
            btnVedi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    try {
                        changeJPanel(new ViewTeam(team, log.getSeason(), log.getOnline()));
                    } catch (ExecuteException e1) {
                        LOG.error("Error", e1);
                    }
                }
            });
            panelTeam.add(btnVedi);
            gbc.gridy = count;
            panelLi.add(panelTeam, gbc);
            count++;
        }
        final JScrollPane scrollPane = new JScrollPane(panelLi);
        super.getPanel().add(scrollPane, BorderLayout.CENTER);
    }
}
