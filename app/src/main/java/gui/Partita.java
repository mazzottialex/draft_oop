package gui;


import data.Calciatore;
import data.Squadra;
import logics.LogicsPartita;
import logics.LogicsPartitaImpl;

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
public class Partita extends Base {
    private static final long serialVersionUID = 3533149128342164934L;
    private JPanel contentPane = new JPanel();
    private JProgressBar progressBar;
    private JLabel jlNomeSq1;
    private JLabel jlScoreSq1;
    private JLabel jlTabSq1;
    private JLabel jlNomeSq2;
    private JLabel jlScoreSq2;
    private JLabel jlTabSq2;
    private JButton startStop;
    private JButton jbSubs;
    private JButton next;
    private JPanel panel;
    private LogicsPartita logics;
    private Squadra s1;
    private Squadra s2;
    private boolean isRunning;
    private boolean ris;
    private int fineTempo;
    private Squadra winner;
    private ArrayList<Calciatore> tab1;
    private ArrayList<Calciatore> tab2;
    private boolean rigori;
    private Rigori guiRigori;
    private String string1 = "";
    private String string2 = "";
    private String apri = "<html>";
    private String chiudi = "</html>";
    private int cambi;
    private Sostituzione sub;
    private Partita partita;
    private int score1;
    private int score2;
    private static final int HALF_REG_TIME = 45;
    private static final int IPADX_CENTER = 50;
    private static final int GRID_5 = 5;
    private static final int END_REG_TIME = 90;
    private static final int END_SUPPL_TIME = 120;
    private static final int HALF_SUPPL_TIME = 105;

    /**
     * Creates a new instance of the {@code Partita} class.
     *
     * @param s1 The first team.
     * @param s2 The second team.
     * @throws FileNotFoundException If a file is not found.
     * @throws ClassNotFoundException If a class is not found.
     * @throws IOException If an I/O error occurs.
     */
    public Partita(final Squadra s1, final Squadra s2) throws FileNotFoundException, ClassNotFoundException, IOException {
        this.s1 = s1;
        this.s2 = s2;
        this.logics = new LogicsPartitaImpl(this.s1, this.s2);
        partita = this;

        // Define the panel to hold the components
        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //define components
        this.score1 = 0;
        this.score2 = 0;
        this.jlNomeSq1 = new JLabel(s1.getNomeSquadra(), SwingConstants.RIGHT);
        this.jlScoreSq1 = new JLabel(String.valueOf(score1), SwingConstants.RIGHT);
        this.jlScoreSq1.setVerticalAlignment(SwingConstants.TOP);
        this.jlTabSq1 = new JLabel("", SwingConstants.RIGHT);
        this.jlTabSq1.setVerticalAlignment(SwingConstants.TOP);
        this.jlNomeSq2 = new JLabel(s2.getNomeSquadra(), SwingConstants.LEFT);
        this.jlScoreSq2 = new JLabel(String.valueOf(score2), SwingConstants.LEFT);
        this.jlScoreSq2.setVerticalAlignment(SwingConstants.TOP);
        this.jlTabSq2 = new JLabel("", SwingConstants.LEFT);
        this.jlTabSq2.setVerticalAlignment(SwingConstants.TOP);
        this.startStop = new JButton("Play");
        this.jbSubs = new JButton("Subs");
        this.jbSubs.setEnabled(false);
        this.next = new JButton("Avanti");
        this.next.setEnabled(false);
        this.progressBar = new JProgressBar(0, 90);
        this.progressBar.setStringPainted(true);
        this.progressBar.setString("Minuto 0°");
        this.ris = false;
        this.fineTempo = HALF_REG_TIME;
        this.winner = null;
        this.tab1 = new ArrayList<>();
        this.tab2 = new ArrayList<>();
        this.rigori = false;
        this.cambi = 0;

        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(jlNomeSq1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(jlNomeSq2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(jlScoreSq1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("-", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(jlScoreSq2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        //        gbc.ipady = 200;
        panel.add(jlTabSq1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        //        gbc.ipady = 200;
        panel.add(jlTabSq2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.ipady = GRID_5;
        panel.add(progressBar, gbc);

        JPanel southWest = new JPanel();
        southWest.add(jbSubs);
        gbc.gridx = 0;
        gbc.gridy = GRID_5;
        gbc.gridwidth = 1;
        gbc.ipady = GRID_5;
        panel.add(southWest, gbc);

        gbc.gridx = 1;
        gbc.gridy = GRID_5;
        gbc.gridwidth = 1;
        gbc.ipady = GRID_5;
        panel.add(startStop, gbc);

        JPanel southEast = new JPanel();
        southEast.add(next);
        gbc.gridx = 2;
        gbc.gridy = GRID_5;
        panel.add(southEast, gbc);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        startStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (isRunning) {
                    stopProgress();
                } else {
                    startProgress();
                }
            }
        });

        jbSubs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stopProgress();
                if (cambi <= 3) {
                    sost();
                    if (!s1.getTitolari().equals(sub.getLogics().getTitolari())) {
						update();
						addCambio();
						ris = true;
					}
					if (cambi == 3) {
                        JButton button = (JButton) e.getSource();
                        JPanel panel = (JPanel) button.getParent();
                        button.setEnabled(false);
                        panel.revalidate();
                        panel.repaint();
                    }
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Initializes the substitution gui {@code Sostituzione}
     */
    private void sost() {
        sub = new Sostituzione(s1, this, cambi);
        sub.setVisible(true);
    }

    /**
     * Adds a substitution count.
     */
    public void addCambio() {
        cambi++;
    }

    /**
     * Updates the team's line-up based on the performed substitution.
     */
    public void update() {
        s1.setTitolari(sub.getLogics().getTitolari());
        s1.setRiserve(sub.getLogics().getRiserve());
    }

    /**
     * Starts the match progress.
     */
    private void startProgress() {
        startStop.setText("Stop");
        isRunning = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!ris) {
                    try {
                        logics.scorers(progressBar.getValue());
                        ris = true;
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = progressBar.getValue(); i <= fineTempo && isRunning; i++) {
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
                            // chiama funzione per ammonizioni / espulsioni
                            logics.sanctions();
                            //Abilita bottone sostituzioni
                            if (progressBar.getValue() > 0 && cambi < 3) {
                                jbSubs.setEnabled(true);
                            }

                            if (rigori) {
                                winner = guiRigori.getWinner();
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
                        winner = score1 > score2 ? s1 : s2;
                        JOptionPane.showMessageDialog(null, "Partita finita. Ha vinto " + winner.getNomeSquadra());
                        startStop.setEnabled(false);
                        jbSubs.setEnabled(false);
                        next.setEnabled(true);
                    } else {
                        fineTempo = END_SUPPL_TIME;
                        progressBar.setMaximum(fineTempo);
                        ris = false;
                        JOptionPane.showMessageDialog(null, "Fine tempi regolamentari");
                    }
                }

                //Fine tempi suppl
                if (progressBar.getValue() == END_SUPPL_TIME) {
                    //jlScoreSq1.setText("2"); score1 = 2;
                    //jlScoreSq2.setText("2"); score2 = 2;
                    jbSubs.setEnabled(false);
                    if (score1 != score2) {
                        winner = score1 > score2 ? s1 : s2;
                        JOptionPane.showMessageDialog(null, "Partita finita. Ha vinto " + winner.getNomeSquadra());
                        startStop.setEnabled(false);
                        next.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fine tempi supplementari. Si va ai calci di rigore");
                        startStop.setEnabled(false);
                        rigori = true;
                        guiRigori = new Rigori(s1, s2, partita);
                        SwingUtilities.invokeLater(() -> {
                            guiRigori.createAndShowGUI();
                        });
                    }
                }

                //Fine 1° tempo
                if (progressBar.getValue() == HALF_REG_TIME) {
                    fineTempo = END_REG_TIME;
                    JOptionPane.showMessageDialog(null, "Fine primo tempo");
                }

                //Fine 1° tempo suppl
                if (progressBar.getValue() == HALF_SUPPL_TIME) {
                    fineTempo = END_SUPPL_TIME;
                    JOptionPane.showMessageDialog(null, "Fine primo tempo supplementare");
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        startStop.setText("Play");
                    }
                });
            }
        });

        thread.start();
    }

    /**
     * Sets the winner and the score, after the penalty shoot-out.
     * Used in the class {@code Rigori}.
     *
     * @param s the winning team.
     * @param gol1 the number of penalties scored by the first team.
     * @param gol2 the number of penalties scored by the second team.
     */
    public void setWinnerR(final Squadra s, final int gol1, final int gol2) {
        winner = s;
        jlScoreSq1.setText(score1 + " (" + gol1 + ")");
        jlScoreSq2.setText(score2 + " (" + gol2 + ")");
        startStop.setEnabled(false);
        next.setEnabled(true);
    }

    /**
     * Changes the score of the match.
     *
     * @throws FileNotFoundException If a file is not found.
     * @throws ClassNotFoundException If a class is not found during deserialization.
     * @throws IOException If an I/O exception occurs.
     */
    public void changeScore() throws FileNotFoundException, ClassNotFoundException, IOException {
        if (logics.getMinGol(s1).contains(progressBar.getValue())) {
            tab1.add(logics.addScorer(s1));
            Calciatore calciatore = tab1.get(tab1.size() - 1);
            String autogol = "";
            if (!s1.getTitolari().contains(calciatore)) {
                autogol = " (AG)";
            }
            string1 = string1 + Integer.toString(progressBar.getValue())
                    + "' Gol: " + calciatore.getNominativo() + autogol + "<br>";
            jlTabSq1.setText(apri + string1 + chiudi);
            score1++;
            jlScoreSq1.setText(String.valueOf(score1));
        }
        if (logics.getMinGol(s2).contains(progressBar.getValue())) {
            tab2.add(logics.addScorer(s2));
            Calciatore calciatore = tab2.get(tab2.size() - 1);
            String autogol = "";
            if (!s2.getTitolari().contains(calciatore)) {
                autogol = " (AG)";
            }
            string2 = string2 + Integer.toString(progressBar.getValue())
                    + "' Gol: " + calciatore.getNominativo() + autogol + "<br>";
            jlTabSq2.setText(apri + string2 + chiudi);
            score2++;
            jlScoreSq2.setText(String.valueOf(score2));
        }
    }

    /**
     * Stops the match progress.
     */
    private void stopProgress() {
        isRunning = false;
        startStop.setText("Play");
    }

    /**
     * Retrieves the winning team of the match.
     *
     * @return The winning team.
     */
    public Squadra getWinner() {
        return winner;
    }

    /**
     * Retrieves the number of goals scored by the first team.
     *
     * @return The number of goals.
     */
    public int getGolS1() {
        return score1;
    }

    /**
     * Retrieves the number of goals scored by the second team.
     *
     * @return The number of goals.
     */
    public int getGolS2() {
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
