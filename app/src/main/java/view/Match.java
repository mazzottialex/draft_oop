package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.LogicsMatch;
import controller.LogicsMatchImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.data.Player;
import model.data.Team;
import model.data.TeamUser;

/**
 * Represents a football match GUI.
 */
public class Match extends Base implements Serializable {
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
    private transient LogicsMatch logics;
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
    private static final int IPADX_CENTER = 50;
    private static final int GRID_5 = 5;
    private static final int END_REG_TIME = 90;
    private static final int END_EXTRA_TIME = 120;
    private static final double MAX_MIN_W = 0.2;
    private static final double MIN_H = 0.1;
    private static final double MAX_H = 0.3;

    /**
     * Creates a new instance of the {@code Match} class.
     *
     * @param t1 The first team.
     * @param t2 The second team.
     * @throws FileNotFoundException If a file is not found.
     * @throws ClassNotFoundException If a class is not found.
     * @throws IOException If an I/O error occurs.
     */
    @SuppressFBWarnings("EI2")
    public Match(final Team t1, final Team t2) throws FileNotFoundException, ClassNotFoundException, IOException {
        setBounds(UtilsGUI.getWidth(MAX_MIN_W), UtilsGUI.getHeight(MIN_H), UtilsGUI.getWidth(MAX_MIN_W),
                UtilsGUI.getHeight(MAX_H));
        this.t1 = new TeamUser(t1.getTeamName(), t1.getLogo(), t1.getModule(), t1.getStarting(), t1.getSubstitution());
        this.t2 = new TeamUser(t2.getTeamName(), t2.getLogo(), t2.getModule(), t2.getStarting(), t2.getSubstitution());
        match = this;

        // Define the panel to hold the components
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //define components
        score1 = 0;
        score2 = 0;
        labelNameTeam1 = new JLabel(t1.getTeamName(), SwingConstants.RIGHT);
        labelScoreTeam1 = new JLabel(String.valueOf(score1), SwingConstants.RIGHT);
        labelScoreTeam1.setVerticalAlignment(SwingConstants.TOP);
        labelReportTeam1 = new JLabel("", SwingConstants.RIGHT);
        labelReportTeam1.setVerticalAlignment(SwingConstants.TOP);
        labelNameTeam2 = new JLabel(t2.getTeamName(), SwingConstants.LEFT);
        labelScoreTeam2 = new JLabel(String.valueOf(score2), SwingConstants.LEFT);
        labelScoreTeam2.setVerticalAlignment(SwingConstants.TOP);
        labelReportTeam2 = new JLabel("", SwingConstants.LEFT);
        labelReportTeam2.setVerticalAlignment(SwingConstants.TOP);
        startStopButton = new JButton("Play");
        subsButton = new JButton("Subs");
        subsButton.setEnabled(false);
        goAheadButton = new JButton("Avanti");
        goAheadButton.setEnabled(false);
        progressBar = new JProgressBar(0, 90);
        progressBar.setStringPainted(true);
        progressBar.setString("Minuto 0°");
        changeResult = false;
        fullTime = END_REG_TIME;
        winner = null;
        report1 = new ArrayList<>();
        report2 = new ArrayList<>();
        shootsout = false;
        substitutions = 0;
        shootsoutGui = null;
        subGui = null;

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
                subGui = new Substitution(t1, match, substitutions);
                subGui.setVisible(true);
            }
        });

        goAheadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                stopProgress();
                winner = t2;
                score1 = 0;
                score2 = 3;
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Adds a substitution count disable substitutions button.
     * @throws Exception 
     */
    public void addSub() throws Exception {
        int s = t1.getSubstitution().size();
        updateTeam();
        if (s == t1.getSubstitution().size()) {
            substitutions++;
        }
        if (substitutions == 3) {
        	Container cont = subsButton.getParent();
        	if (cont instanceof JPanel) {
        	    JPanel panel = (JPanel) cont;
        	    subsButton.setEnabled(false);
                panel.revalidate();
                panel.repaint();
        	} else {
                throw new Exception();
            }
        }
    }

    /**
     * Updates the team's line-up based on the performed substitution.
     */
    public void updateTeam() {
        t1.setStarting(subGui.getLogics().getStarters());
        t1.setSubstitution(subGui.getLogics().getSubstitutes());
        changeResult = false;
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
                        logics = new LogicsMatchImpl(t1, t2);
                        logics.scorers(progressBar.getValue());
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
//                  labelScoreTeam1.setText("2"); score1 = 2;
//                  labelScoreTeam2.setText("2"); score2 = 2;
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
                    //labelScoreTeam1.setText("2"); score1 = 2;
                    //labelScoreTeam2.setText("2"); score2 = 2;
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
    public void setWinnerSh(final Team team, final int goal1, final int goal2) {
        winner = new TeamUser(team.getTeamName(), team.getLogo(), team.getModule(), team.getStarting(), team.getSubstitution());
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
    private void changeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
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
        Team copiaTeam=new TeamUser(winner.getTeamName(), winner.getLogo(), winner.getModule(), winner.getStarting(), winner.getStartingDesc());
    	return copiaTeam;
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
