package gui;


import data.Player;
import data.Team;
import logics.LogicsMatch;
import logics.LogicsMatchImpl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Represents a football match GUI.
 */
public class Match extends Base {
    private static final long serialVersionUID = 3533149128342164934L;
    private JPanel contentPane = new JPanel();
    private JProgressBar progressBar;
    private JLabel labelNameTeam1;
    private JLabel labelScoreTeam1;
    private JLabel labelReportTeam1;
    private JLabel labelNameTeam2;
    private JLabel labelScoreTeam2;
    private JLabel labelReportTeam2;
    private JButton startStopButton;
    private JButton subsButton;
    private JButton goAheadButton;
    private JPanel panel;
    private LogicsMatch logics;
    private Team t1;
    private Team t2;
    private boolean isRunning;
    private boolean changeResult;
    private int fullTime;
    private Team winner;
    private ArrayList<Player> report1;
    private ArrayList<Player> report2;
    private boolean shootsout;
    private Shootout shootsoutGui;
    private String string1 = "";
    private String string2 = "";
    private String htmlOpen = "<html>";
    private String htmlClose = "</html>";
    private int substitutions;
    private Substitution subGui;
    private Match match;
    private int score1;
    private int score2;
    private static final int HALF_REG_TIME = 45;
    private static final int IPADX_CENTER = 50;
    private static final int GRID_5 = 5;
    private static final int END_REG_TIME = 90;
    private static final int END_EXTRA_TIME = 120;
    private static final int HALF_EXTRA_TIME = 105;

    /**
     * Creates a new instance of the {@code Match} class.
     *
     * @param t1 The first team.
     * @param t2 The second team.
     * @throws FileNotFoundException If a file is not found.
     * @throws ClassNotFoundException If a class is not found.
     * @throws IOException If an I/O error occurs.
     */
    public Match(final Team t1, final Team t2) throws FileNotFoundException, ClassNotFoundException, IOException {
        this.t1 = t1;
        this.t2 = t2;
        this.logics = new LogicsMatchImpl(this.t1, this.t2);
        match = this;

        // Define the panel to hold the components
        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //define components
        this.score1 = 0;
        this.score2 = 0;
        this.labelNameTeam1 = new JLabel(t1.getTeamName(), SwingConstants.RIGHT);
        this.labelScoreTeam1 = new JLabel(String.valueOf(score1), SwingConstants.RIGHT);
        this.labelScoreTeam1.setVerticalAlignment(SwingConstants.TOP);
        this.labelReportTeam1 = new JLabel("", SwingConstants.RIGHT);
        this.labelReportTeam1.setVerticalAlignment(SwingConstants.TOP);
        this.labelNameTeam2 = new JLabel(t2.getTeamName(), SwingConstants.LEFT);
        this.labelScoreTeam2 = new JLabel(String.valueOf(score2), SwingConstants.LEFT);
        this.labelScoreTeam2.setVerticalAlignment(SwingConstants.TOP);
        this.labelReportTeam2 = new JLabel("", SwingConstants.LEFT);
        this.labelReportTeam2.setVerticalAlignment(SwingConstants.TOP);
        this.startStopButton = new JButton("Play");
        this.subsButton = new JButton("Subs");
        this.subsButton.setEnabled(false);
        this.goAheadButton = new JButton("Avanti");
        this.goAheadButton.setEnabled(false);
        this.progressBar = new JProgressBar(0, 90);
        this.progressBar.setStringPainted(true);
        this.progressBar.setString("Minuto 0°");
        this.changeResult = false;
        this.fullTime = HALF_REG_TIME;
        this.winner = null;
        this.report1 = new ArrayList<>();
        this.report2 = new ArrayList<>();
        this.shootsout = false;
        this.substitutions = 1;

        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNameTeam1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(labelNameTeam2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelScoreTeam1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("-", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(labelScoreTeam2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        //        gbc.ipady = 200;
        panel.add(labelReportTeam1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        //        gbc.ipady = 200;
        panel.add(labelReportTeam2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.ipady = GRID_5;
        panel.add(progressBar, gbc);

        JPanel southWest = new JPanel();
        southWest.add(subsButton);
        gbc.gridx = 0;
        gbc.gridy = GRID_5;
        gbc.gridwidth = 1;
        gbc.ipady = GRID_5;
        panel.add(southWest, gbc);

        gbc.gridx = 1;
        gbc.gridy = GRID_5;
        gbc.gridwidth = 1;
        gbc.ipady = GRID_5;
        panel.add(startStopButton, gbc);

        JPanel southEast = new JPanel();
        southEast.add(goAheadButton);
        gbc.gridx = 2;
        gbc.gridy = GRID_5;
        panel.add(southEast, gbc);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (isRunning) {
                    stopProgress();
                } else {
                    startProgress();
                }
            }
        });

        subsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stopProgress();
                if (substitutions <= 3) {
                    makeSub();
//                    if (true/*!t1.getStarting().equals(subGui.getLogics().getStarters())*/) {
//                    	System.out.println(substitutions);
//						updateTeam();
//					}
					if (substitutions == 3) {
                        JButton button = (JButton) e.getSource();
                        JPanel panel = (JPanel) button.getParent();
                        button.setEnabled(false);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            }
        });

        goAheadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Initializes the substitution gui {@code Substitution}
     */
    private void makeSub() {
        subGui = new Substitution(t1, this, substitutions);
        subGui.setVisible(true);
    }

    /**
     * Adds a substitution count.
     */
    public void addSub() {
        substitutions++;
        updateTeam();
        changeResult = false;
    }

    /**
     * Updates the team's line-up based on the performed substitution.
     */
    public void updateTeam() {
        t1.setStarting(subGui.getLogics().getStarters());
        t1.setSubstitution(subGui.getLogics().getSubstitutes());
    }

    /**
     * Starts the match progress.
     */
    private void startProgress() {
        startStopButton.setText("Stop");
        isRunning = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!changeResult) {
                    try {
                        logics.scorers(progressBar.getValue());
                        System.out.println("siuuuum");
                        changeResult = true;
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = progressBar.getValue(); i <= fullTime && isRunning; i++) {
                    final int value = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //Make progress.
                            progressBar.setValue(value);
                            progressBar.setString("Minuto " + String.valueOf(value) + "°");
                            // chiama funzione per gol
                            try {
                                changeScore();
                            } catch (ClassNotFoundException | IOException e) {
                                e.printStackTrace();
                            }
                            //Abilita bottone sostituzioni
                            if (progressBar.getValue() > 0 && substitutions < 3) {
                                subsButton.setEnabled(true);
                            }

                            if (shootsout) {
                                winner = shootsoutGui.getWinner();
                            }
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Fine tempi reg
                if (progressBar.getValue() == END_REG_TIME) {
                    //jlScoreSq1.setText("2"); score1 = 2;
                    //jlScoreSq2.setText("2"); score2 = 2;
                    if (score1 != score2) {
                        winner = score1 > score2 ? t1 : t2;
                        JOptionPane.showMessageDialog(null, "Partita finita. Ha vinto " + winner.getTeamName());
                        startStopButton.setEnabled(false);
                        subsButton.setEnabled(false);
                        goAheadButton.setEnabled(true);
                    } else {
                        fullTime = END_EXTRA_TIME;
                        progressBar.setMaximum(fullTime);
                        changeResult = false;
                        JOptionPane.showMessageDialog(null, "Fine tempi regolamentari");
                    }
                }

                //Fine tempi suppl
                if (progressBar.getValue() == END_EXTRA_TIME) {
                    //jlScoreSq1.setText("2"); score1 = 2;
                    //jlScoreSq2.setText("2"); score2 = 2;
                    subsButton.setEnabled(false);
                    if (score1 != score2) {
                        winner = score1 > score2 ? t1 : t2;
                        JOptionPane.showMessageDialog(null, "Partita finita. Ha vinto " + winner.getTeamName());
                        startStopButton.setEnabled(false);
                        goAheadButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fine tempi supplementari. Si va ai calci di rigore");
                        startStopButton.setEnabled(false);
                        shootsout = true;
                        shootsoutGui = new Shootout(t1, t2, match);
                        SwingUtilities.invokeLater(() -> {
                            shootsoutGui.createAndShowGUI();
                        });
                    }
                }

                //Fine 1° tempo
                if (progressBar.getValue() == HALF_REG_TIME) {
                    fullTime = END_REG_TIME;
                    JOptionPane.showMessageDialog(null, "Fine primo tempo");
                }

                //Fine 1° tempo suppl
                if (progressBar.getValue() == HALF_EXTRA_TIME) {
                    fullTime = END_EXTRA_TIME;
                    JOptionPane.showMessageDialog(null, "Fine primo tempo supplementare");
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        startStopButton.setText("Play");
                    }
                });
            }
        });

        thread.start();
    }

    /**
     * Sets the winner and the score, after the penalty shoot-out.
     * Used in the class {@code Shootout}.
     *
     * @param team the winning team.
     * @param goal1 the number of penalties scored by the first team.
     * @param goal2 the number of penalties scored by the second team.
     */
    public void setWinnerR(final Team team, final int goal1, final int goal2) {
        winner = team;
        labelScoreTeam1.setText(score1 + " (" + goal1 + ")");
        labelScoreTeam2.setText(score2 + " (" + goal2 + ")");
        startStopButton.setEnabled(false);
        goAheadButton.setEnabled(true);
    }

    /**
     * Changes the score of the match.
     *
     * @throws FileNotFoundException If a file is not found.
     * @throws ClassNotFoundException If a class is not found during deserialization.
     * @throws IOException If an I/O exception occurs.
     */
    public void changeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
        if (logics.getGoalsMinutes(t1).contains(progressBar.getValue())) {
            report1.add(logics.addScorer(t1));
            Player player = report1.get(report1.size() - 1);
            String owngoal = "";
            if (!t1.getStarting().contains(player)) {
                owngoal = " (AG)";
            }
            string1 = string1 + Integer.toString(progressBar.getValue())
                    + "' Gol: " + player.getName() + owngoal + "<br>";
            labelReportTeam1.setText(htmlOpen + string1 + htmlClose);
            score1++;
            labelScoreTeam1.setText(String.valueOf(score1));
        }
        if (logics.getGoalsMinutes(t2).contains(progressBar.getValue())) {
            report2.add(logics.addScorer(t2));
            Player player = report2.get(report2.size() - 1);
            String owngoal = "";
            if (!t2.getStarting().contains(player)) {
                owngoal = " (AG)";
            }
            string2 = string2 + Integer.toString(progressBar.getValue())
                    + "' Gol: " + player.getName() + owngoal + "<br>";
            labelReportTeam2.setText(htmlOpen + string2 + htmlClose);
            score2++;
            labelScoreTeam2.setText(String.valueOf(score2));
        }
    }

    /**
     * Stops the match progress.
     */
    private void stopProgress() {
        isRunning = false;
        startStopButton.setText("Play");
    }

    /**
     * Retrieves the winning team of the match.
     *
     * @return The winning team.
     */
    public Team getWinner() {
        return winner;
    }

    /**
     * Retrieves the number of goals scored by the first team.
     *
     * @return The number of goals.
     */
    public int getGoalT1() {
        return score1;
    }

    /**
     * Retrieves the number of goals scored by the second team.
     *
     * @return The number of goals.
     */
    public int getGoalT2() {
        return score2;
    }

    /**
     * Create the GUI and show it.
     */
    public void createAndShowGUI() {
        //Create and set up the content pane.
        add(contentPane);
        contentPane.add(panel);

        //Display the window.
        pack();
        setVisible(true);
    }
}
