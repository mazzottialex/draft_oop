package gui;

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

import data.Player;
import data.Team;
import logics.LogicsSubstitution;
import logics.LogicsSubstitutionImpl;

/**
 * Represents a GUI for substituting players.
 */
public class Substitution extends Base {
    /**
     * 
     */
    private static final long serialVersionUID = 5244133982320404420L;
    private final List<String> roles = List.of("P", "D", "C", "A");
    private JPanel panelTeam = new JPanel();
    private LogicsSubstitution logics;
    private static JPanel panelStarters;
    private static JPanel panelSubstitutes;
    private int substitutes;
    private static final int SUBSTITUTES = 7;
    private static final int INSETS_5 = 5;
    private static final int GRID_5 = 5;
    private static final int GRID_6 = 6;
    private static final int GRID_7 = 7;

    /**
     * Creates a new instance of the {@code Substitution} class.
     *
     * @param team The team for which substitutions are being made.
     * @param matchGui The {@code Match} instance.
     * @param substitutionsMade The number of substitutions already made.
     */
    public Substitution(final Team team, final Match matchGui, final int substitutionsMade) {
        logics = new LogicsSubstitutionImpl(team, this);
        this.substitutes = SUBSTITUTES - substitutionsMade;
        panelStarters = null;
        panelSubstitutes = null;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS_5, INSETS_5, 2, 2);
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        panelTeam.setBackground(getForeground());

        JLabel labelTeamName = new JLabel("Titolari");
        labelTeamName.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        labelTeamName.setForeground(Color.white);
        panelTeam.add(labelTeamName);

        gbc.gridy = 0;
        contentPane.add(panelTeam, gbc);

        //titolari
        JPanel panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        int count = 0;
        for (int i = 0; i < roles.size(); i++) {
            panelPosition = new JPanel();
            for (int j = 0; j < team.getModule().getN(roles.get(i)); j++) {
                Player p = team.getStarting().get(count);
                JPanel panel = (utilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true));
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
            contentPane.add(panelPosition, gbc);
        }
        JLabel labelBench = new JLabel("Riserve");
        labelBench.setForeground(Color.white);
        labelBench.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = GRID_5;
        contentPane.add(labelBench, gbc);
        gbc.insets = new Insets(INSETS_5, INSETS_5, INSETS_5, INSETS_5);

        //panchinari
        panelPosition = new JPanel();
        panelPosition.setLayout(layout);
        for (int j = 0; j < substitutes; j++) {
            Player p = team.getSubstitution().get(j);
            JPanel panel = (utilsGUI.getPanelCalciatore(p.getName(), p.getRating().getX(), p.getPos(), true));
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
        contentPane.add(panelPosition, gbc);

        JButton makeSubButton = new JButton("Sostitutisci");
        makeSubButton.addActionListener(e -> {
            if (panelStarters == null || panelSubstitutes == null) {
                JOptionPane.showMessageDialog(null, "Bisogna selezionare due giocatori: uno tra i titolari e uno tra le riserve, che devono avere lo stesso ruolo");
        	    if (panelStarters != null) {
                    panelStarters.setBackground(null);
                }
        	    if (panelSubstitutes != null) {
                    panelSubstitutes.setBackground(null);
                }
            } else {
                logics.sub(panelStarters.getParent(), panelSubstitutes.getParent(), panelStarters, panelSubstitutes);
            }
            panelStarters = null;
            panelSubstitutes = null;
        });
        gbc.gridy = GRID_7;
        contentPane.add(makeSubButton, gbc);

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
