package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LogicsHistory;
import controller.LogicsHistoryImpl;
import model.data.Player;
import model.data.Team;

/**
 * Represents a GUI for viewing a Team and its rating.
 */
public class ViewTeam extends Base {
    private static final long serialVersionUID = 1L;
    private final List<String> pos = List.of("A", "C", "D", "P");
    private final transient LogicsHistory log;
    private static final Insets INSETS_1 = new Insets(5, 5, 2, 2);
    private static final Insets INSETS_2 = new Insets(2, 0, 2, 25);
    private static final Insets INSETS_3 = new Insets(2, 25, 2, 0);
    private static final Insets INSETS_4 = new Insets(10, 0, 0, 0);
    private static final Insets INSETS_5 = new Insets(5, 5, 5, 5);
    private static final int SCALE_IMG_1 = 55;
    private static final int GRID_Y = 5;
    private static final int GRID_Y_2 = 6;
    private static final int N_SUB = 7;
    private final JPanel panelTeam = new JPanel();

    /**
     * Constructs a new ViewTeam object.
     * 
     * @param team    team of GUI
     * @param season  the season selected
     * @param online  online status
     */
    public ViewTeam(final Team team, final String season, final Boolean online) {
        log = new LogicsHistoryImpl(season, online);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = INSETS_1;
        GridBagLayout layout = new GridBagLayout();
        super.getPanel().setLayout(layout);
        panelTeam.setBackground(super.getForeground());
        JButton btnArchive = UtilsGUI.standardButton("Archive");
        btnArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new History(log.getSeason(), log.getOnline()));
            }
        });

        panelTeam.add(btnArchive);
        JLabel lblLogo = new JLabel();
        ImageIcon img = new ImageIcon(team.getLogo());
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(SCALE_IMG_1, SCALE_IMG_1, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        lblLogo.setBackground(Color.white);
        lblLogo.setIcon(img);
        lblLogo.setBorder(new EmptyBorder(INSETS_2));
        panelTeam.add(lblLogo);
        JLabel lblTeamName = new JLabel(team.getTeamName());
        lblTeamName.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        lblTeamName.setForeground(Color.white);
        panelTeam.add(lblTeamName);
        JLabel lblRating = new JLabel("Valutazione: " + team.getRating());
        lblRating.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        lblRating.setForeground(Color.white);
        lblRating.setBorder(new EmptyBorder(INSETS_3));
        panelTeam.add(lblRating);
        gbc.gridy = 0;
        super.getPanel().add(panelTeam, gbc);
        JPanel panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        int count = 0;
        for (int i = 0; i < pos.size(); i++) {
            panelPosition = new JPanel();
            for (int j = 0; j < team.getModule().getPlayersNumberByRole(pos.get(i)); j++) {
                Player p = team.getStartingDesc().get(count);
                JPanel panel = UtilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true);
                count++;
                panelPosition.add(panel);
            }
            gbc.gridy = i + 1;
            super.getPanel().add(panelPosition, gbc);
        }
        JLabel lblPanchina = new JLabel("PANCHINA");
        lblPanchina.setForeground(Color.white);
        lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.insets = INSETS_4;
        gbc.gridy = GRID_Y;
        super.getPanel().add(lblPanchina, gbc);
        gbc.insets = INSETS_5;
        // panchinari
        panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        for (int j = 0; j < N_SUB; j++) {
            Player p = team.getSubstitution().get(j);
            if (j < 4) {
                gbc.gridy = 0;
            } else {
                gbc.gridy = 1;
            }
            panelPosition.add(UtilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), false), gbc);
        }
        gbc.gridy = GRID_Y_2;
        super.getPanel().add(panelPosition, gbc);
    }
}
