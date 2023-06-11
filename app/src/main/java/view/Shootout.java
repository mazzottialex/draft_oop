package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LogicsShootout;
import controller.LogicsShootoutImpl;
import model.data.Player;
import model.data.Team;
import utils.Pair;

/**
 * Represents a penalty shoot-out GUI.
 */
public class Shootout extends Base implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5140476454072046580L;
    private Team t1;
    private Team t2;
    private JLabel results1;
    private JLabel results2;
    private JLabel finalScore;
    private int goal1;
    private int goal2;
    private int shoots1;
    private int shoots2;
    private int totShoots;
    private Team winner;
    private JButton closeButton;
    private final LogicsShootout logics;
    private Map<Integer, Pair<Player, String>> shoootout1;
    private Map<Integer, Pair<Player, String>> shootout2;
    private JPanel panel;
    private String string1;
    private String string2;
    private Match match;
    private static final int IPADX_CENTER = 50;
    private static final double MAX_W = 0.45;
    private static final double MIN_H = 0.1;
    private static final double MIN_W = 0.2;
    private static final double MAX_H = 0.3;

    /**
     * Creates a new instance of the {@code Shootout} class.
     *
     * @param t1 The first team in the penalty shoot-out.
     * @param t2 The second team in the penalty shoot-out.
     * @param match The {@code Match} instance.
     */
    public Shootout(final Team t1, final Team t2, final Match match) {
        setBounds(UtilsGUI.getWidth(MAX_W), UtilsGUI.getHeight(MIN_H), UtilsGUI.getWidth(MIN_W), UtilsGUI.getHeight(MAX_H));
        this.t1 = t1;
        this.t2 = t2;
        this.match = match;
        goal1 = 0;
        goal2 = 0;
        shoots1 = 0;
        shoots2 = 0;
        totShoots = 0;
        logics = new LogicsShootoutImpl(t1, t2);
        shoootout1 = logics.compute().get(0);
        shootout2 = logics.compute().get(1);
        string1 = "";
        string2 = "";

        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel(t1.getTeamName(), SwingConstants.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel(t2.getTeamName(), SwingConstants.LEFT), gbc);

        results1 = new JLabel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        //        gbc.ipady = 200;
        panel.add(results1, gbc);

        results2 = new JLabel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        //        gbc.ipady = 200;
        panel.add(results2, gbc);

        finalScore = new JLabel();
        finalScore.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(finalScore, gbc);

        JButton startButton = new JButton("inizia");
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            start();
        });

        closeButton = new JButton("chiudi");
        closeButton.addActionListener(e -> {
            dispose();
            match.setWinnerSh(winner, goal1, goal2);
        });
        closeButton.setEnabled(false);

        add(startButton, BorderLayout.NORTH);
        add(closeButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                dispose();
                match.setWinnerSh(winner, goal1, goal2);
            }
        };
        addWindowListener(windowListener);
    }

    /**
     * Starts the penalty shoot-out.
     */
    private void start() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!shoootout1.containsKey(shoots1) && !shootout2.containsKey(shoots2)) {
                    closeButton.setEnabled(true);
                    finalScore.setText(goal1 + " - " + goal2);
                    setWinner(goal1, goal2);
                    match.setWinnerSh(winner, goal1, goal2);
                    JOptionPane.showMessageDialog(null, "Tiri di rigore terminati. Squadra vincente: " + getWinnerName());
                    timer.cancel();
                } else {
                    if (totShoots % 2 == 0) {
                        if (shoootout1.containsKey(shoots1)) {
                            String res = shoootout1.get(shoots1).getY();
                            String shooter = shoootout1.get(shoots1).getX().getName();
                            String resultLabel = shooter + ": " + res;
                            string1 = string1 + resultLabel + "<br>";
                            results1.setText("<html>" + string1 + "</html>");
                            if (res.equals("Gol")) {
                                goal1++;
                            }
                            shoots1++;
                        }
                    } else {
                        if (shootout2.containsKey(shoots2)) {
                            String res = shootout2.get(shoots2).getY();
                            String shooter = shootout2.get(shoots2).getX().getName();
                            String resultLabel = shooter + ": " + res;
                            string2 = string2 + resultLabel + "<br>";
                            results2.setText("<html>" + string2 + "</html>");
                            if (res.equals("Gol")) {
                                goal2++;
                            }
                            shoots2++;
                        }
                    }
                    totShoots = shoots1 + shoots2;
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    /**
     * Retrieves the name of the winning team.
     *
     * @return The name of the winning team.
     */
    private String getWinnerName() {
        return winner.getTeamName();
    }

    /**
     * Retrieves the winning team.
     *
     * @return The winning team.
     */
    public Team getWinner() {
        return winner;
    }

    /**
     * Sets the winning team based on the number of goals scored.
     *
     * @param gol1 The number of goals scored by the first team.
     * @param gol2 The number of goals scored by the second team.
     */
    public void setWinner(final int gol1, final int gol2) {
        if (gol1 > gol2) {
            winner = t1;
        } else {
            winner = t2;
        }
    }

    /**
     * Create the GUI and show it.
     */
    public void createAndShowGUI() {
        add(panel);
        setVisible(true);
    }
}
