package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Calciatore;
import data.Squadra;
import logics.LogicsRigori;
import logics.LogicsRigoriImpl;
import utils.Pair;

/**
 * Represents a penalty shoot-out GUI.
 */
public class Rigori extends Base {
    /**
     * 
     */
    private static final long serialVersionUID = 5140476454072046580L;
    private Squadra s1;
    private Squadra s2;
    private JLabel results1;
    private JLabel results2;
    private JLabel result;
    private int gol1;
    private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private Squadra winner;
    private JButton chiudi;
    private LogicsRigori logics;
    private Map<Integer, Pair<Calciatore, String>> rig1;
    private Map<Integer, Pair<Calciatore, String>> rig2;
    private JPanel panel;
    private String str1;
    private String str2;
    private Partita partita;
    private static final int IPADX_CENTER = 50;

    /**
     * Creates a new instance of the `Rigori` class.
     *
     * @param s1 The first team in the penalty shoot-out.
     * @param s2 The second team in the penalty shoot-out.
     * @param partita The `Partita` instance.
     */
    public Rigori(final Squadra s1, final Squadra s2, final Partita partita) {
        this.s1 = s1;
        this.s2 = s2;
        this.partita = partita;
        gol1 = 0;
        gol2 = 0;
        tiri1 = 0;
        tiri2 = 0;
        totTiri = 0;
        logics = new LogicsRigoriImpl(s1, s2);
        rig1 = logics.compute().get(0);
        rig2 = logics.compute().get(1);
        str1 = "";
        str2 = "";

        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel(s1.getNomeSquadra(), SwingConstants.RIGHT), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = IPADX_CENTER;
        panel.add(new JLabel("vs", SwingConstants.CENTER), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel(s2.getNomeSquadra(), SwingConstants.LEFT), gbc);

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

        result = new JLabel();
        result.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(result, gbc);

        JButton inizia = new JButton("inizia");
        inizia.addActionListener(e -> {
            inizia.setEnabled(false);
            start();
        });

        chiudi = new JButton("chiudi");
        chiudi.addActionListener(e -> {
            dispose();
            partita.setWinnerR(winner, gol1, gol2);
        });
        chiudi.setEnabled(false);

        add(inizia, BorderLayout.NORTH);
        add(chiudi, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                dispose();
                partita.setWinnerR(winner, gol1, gol2);
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
                if (!rig1.containsKey(tiri1) && !rig2.containsKey(tiri2)) {
                    chiudi.setEnabled(true);
                    result.setText(gol1 + " - " + gol2);
                    setWinner(gol1, gol2);
                    partita.setWinnerR(winner, gol1, gol2);
                    JOptionPane.showMessageDialog(null, "Tiri di rigore terminati. Squadra vincente: " + getNomeWinner());
                    timer.cancel();
                } else {
                    if (totTiri % 2 == 0) {
                        if (rig1.containsKey(tiri1)) {
                            String res = rig1.get(tiri1).getY();
                            String tiratore = rig1.get(tiri1).getX().getNominativo();
                            String resultLabel = tiratore + ": " + res;
                            str1 = str1 + resultLabel + "<br>";
                            results1.setText("<html>" + str1 + "</html>");
                            if (res.equals("Gol")) {
                                gol1++;
                            }
                            tiri1++;
                        }
                    } else {
                        if (rig2.containsKey(tiri2)) {
                            String res = rig2.get(tiri2).getY();
                            String tiratore = rig2.get(tiri2).getX().getNominativo();
                            String resultLabel = tiratore + ": " + res;
                            str2 = str2 + resultLabel + "<br>";
                            results2.setText("<html>" + str2 + "</html>");
                            if (res.equals("Gol")) {
                                gol2++;
                            }
                            tiri2++;
                        }
                    }
                    totTiri = tiri1 + tiri2;
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
    private String getNomeWinner() {
        return winner.getNomeSquadra();
    }

    /**
     * Retrieves the winning team.
     *
     * @return The winning team.
     */
    public Squadra getWinner() {
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
            winner = s1;
        } else {
            winner = s2;
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