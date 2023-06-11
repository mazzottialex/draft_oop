package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.LogicsSubstitution;
import controller.LogicsSubstitutionImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.data.Player;
import model.data.Team;

/**
 * Represents a GUI for substituting players.
 */
public class Substitution extends Base {
    private static final long serialVersionUID = 5244133982320404420L;
    private final List<String> roles = List.of("P", "D", "C", "A");
    private final JPanel panelTeam = new JPanel();
    private transient LogicsSubstitution logics;
    private final Team team;
    private final Match matchGui;
    private JPanel panelStarters;
    private JPanel panelSubstitutes;
    private final int substitutes;
    private static final int SUBSTITUTES = 7;
    private static final int INSETS_5 = 5;
    private static final int GRID_5 = 5;
    private static final int GRID_6 = 6;
    private static final int GRID_7 = 7;
    private static final double MIN_W = 0.45;
    private static final double MIN_H = 0.1;
    private static final double MAX_W = 0.4;
    private static final double MAX_H = 0.8;

    /**
     * Creates a new instance of the {@code Substitution} class.
     *
     * @param team The team for which substitutions are being made.
     * @param matchGui The {@code Match} instance.
     * @param substitutionsMade The number of substitutions already made.
     */
    @SuppressFBWarnings("")
    public Substitution(final Team team, final Match matchGui, final int substitutionsMade) {
        setBounds(UtilsGUI.getWidth(MIN_W), UtilsGUI.getHeight(MIN_H), UtilsGUI.getWidth(MAX_W), UtilsGUI.getHeight(MAX_H));
        this.team = team;
        this.matchGui = matchGui;
        this.substitutes = SUBSTITUTES - substitutionsMade;
        this.initialize();
    }

    private void initialize() {
        logics = new LogicsSubstitutionImpl(team, this);
        panelStarters = null;
        panelSubstitutes = null;
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS_5, INSETS_5, 2, 2);
        final GridBagLayout layout = new GridBagLayout();
        super.getPanel().setLayout(layout);
        panelTeam.setBackground(super.getForeground());

        final JLabel labelTeamName = new JLabel("Titolari");
        labelTeamName.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
        labelTeamName.setForeground(Color.white);
        panelTeam.add(labelTeamName);

        gbc.gridy = 0;
        getPanel().add(panelTeam, gbc);

        // titolari
        JPanel panelPosition;
        int count = 0;
        for (int i = 0; i < roles.size(); i++) {
            panelPosition = new JPanel();
            for (int j = 0; j < team.getModule().getPlayersNumberByRole(roles.get(i)); j++) {
                final Player p = team.getStarting().get(count);
                final JPanel panel = UtilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true);
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        if (panelStarters != null) {
                            panelStarters.setBackground(null);
                        }
                        panelStarters = panel;
                        panelStarters.setBackground(Color.YELLOW);
                        logics.selectStarter(p);
                    }
                });
                panelPosition.add(panel);
                count++;
            }
            gbc.gridy = i + 1;
            super.getPanel().add(panelPosition, gbc);
        }

        final JLabel labelBench = new JLabel("Riserve");
        labelBench.setForeground(Color.white);
        labelBench.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 16));
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = GRID_5;
        super.getPanel().add(labelBench, gbc);
        gbc.insets = new Insets(INSETS_5, INSETS_5, INSETS_5, INSETS_5);

        // panchinari
        panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        for (int j = 0; j < substitutes; j++) {
            final Player p = team.getSubstitution().get(j);
            final JPanel panel = UtilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (panelSubstitutes != null) {
                        panelSubstitutes.setBackground(null);
                    }
                    panelSubstitutes = panel;
                    panelSubstitutes.setBackground(Color.YELLOW);
                    logics.selectSubstitute(p);
                }
            });
            panelPosition.add(panel);
        }
        gbc.gridy = GRID_6;
        super.getPanel().add(panelPosition, gbc);

        final JButton makeSubButton = new JButton("Sostitutisci");
        makeSubButton.addActionListener(e -> {
            if (panelStarters == null || panelSubstitutes == null) {
                JOptionPane.showMessageDialog(null, "Bisogna selezionare due giocatori: "
                        + "uno tra i titolari e uno tra le riserve, che devono avere lo stesso ruolo");
                if (panelStarters != null) {
                    panelStarters.setBackground(null);
                }
                if (panelSubstitutes != null) {
                    panelSubstitutes.setBackground(null);
                }
            } else {
                logics.sub(panelStarters.getParent(), panelSubstitutes.getParent(), panelStarters, panelSubstitutes);
                if (logics.done()) {
                      matchGui.addSub();
                }
            }
            panelStarters = null;
            panelSubstitutes = null;
        });
        gbc.gridy = GRID_7;
        super.getPanel().add(makeSubButton, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Closes the {@code Substitution} window.
     */
    public void chiudi() {
        this.dispose();
    }

    /**
     * Retrieves the {@code LogicsSubstitution} instance associated with the {@code Substitution} object.
     *
     * @return The {@code LogicsSubstitution} instance.
     */
    public LogicsSubstitution getLogics() {
        return logics;
    }
}
