package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.slf4j.LoggerFactory;

import controller.LogicsShootoutImpl;
import controller.LogicsTournament;
import controller.LogicsTournamentImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
//import io.opentelemetry.api.logs.Logger;
import model.data.Player;
import model.data.Team;
import utils.RoundedBorder;

/**
 * The GUI where the tournament is played.
 * 
 * @author Davide Braccini
 *
 */
public class Tournament extends Base {

    private static final long serialVersionUID = 1L;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Tournament.class);
    private static final String BAR = " - ";
    private static final int NUM_TURN_1 = 16;
    private static final int NUM_TURN_2 = 8;
    private static final int NUM_TURN_3 = 4;
    private static final int NUM_TURN_4 = 2;
    private static final int BORDER_R_P0 = 5;
    private static final int BORDER_R_P3 = 40;
    private static final int GAP_P3 = 140;
    private static final int BORDER_R_P2 = 180;
    private static final int GAP_P2 = 300;
    private static final int BORDER_R_P1 = 450;
    private static final int BORDER_T = 20;
    private static final int NUM_TEXT_SINGLE = 9;
    private static final int NUM_TEXT_DOUBLE = 20;
    private static final int ROUND = 20;
    private static final double MIN_W_H = 0.1;
    private static final double MAX_W_H = 0.8;
    private final LogicsTournament logTor;
    private final JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final GridLayout gl = new GridLayout(6, 1);
    private final JPanel panelCenter = new JPanel(gl);
    private final JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final GridLayout g0 = new GridLayout(1, 1);
    private final GridLayout g1 = new GridLayout(1, 1);
    private final GridLayout g2 = new GridLayout(1, NUM_TURN_4);
    private final GridLayout g3 = new GridLayout(1, NUM_TURN_3);
    private final GridLayout g4 = new GridLayout(1, NUM_TURN_2);
    private final GridLayout g5 = new GridLayout(1, NUM_TURN_1);
    private final JPanel p0 = new JPanel(g0); // Panel a riga 0 (squadra vincitrice)
    private final JPanel p1 = new JPanel(g1); // Panel a riga 1 --> 2 squadre --> 1 partita
    private final JPanel p2 = new JPanel(g2); // Panel a riga 2 --> 4 squadre --> 2 partite
    private final JPanel p3 = new JPanel(g3); // Panel a riga 3 --> 8 squadre ---> 4 partite
    private final JPanel p4 = new JPanel(g4); // Panel a riga 4 --> 16 squadre --> 8 partite
    private final JPanel p5 = new JPanel(g5); // Panel a riga 5 --> 16 squadre
    //private final MyJLabel[] labelsp5 = new MyJLabel[NUM_TURN_1];
    private final MyJLabel[] labelsp4 = new MyJLabel[NUM_TURN_2];
    private final MyJLabel[] labelsp3 = new MyJLabel[NUM_TURN_3];
    private final MyJLabel[] labelsp2 = new MyJLabel[NUM_TURN_4];
    private MyJLabel labelp1 = new MyJLabel();
    private MyJLabel labelp0 = new MyJLabel();
    private List<Team> listAvversarie;
    private int risSquadraUte;
    private int risSquadraAvv;
    // boolean eliminatedThisTurn = false;
    private final JButton buttonSimula;
    private final Color panelColor = new Color(0, 64, 128);
    private int r1;
    private int r2;

    /**
     * Constructor of Tournament, add the necessary graphics components.
     * 
     * @param squadra the user's team
     * @param li      the list of all the players in Serie A
     * @throws FileNotFoundException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException an I/O error occurs
     */
    @SuppressFBWarnings("MC")
    public Tournament(final Team squadra, final List<Player> li)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        this.logTor = new LogicsTournamentImpl(squadra, li);
        this.listAvversarie = logTor.getListAvversari();
        getPanel().setLayout(new BorderLayout());
        // Aggiungo il bottone Simula nel panelSud
        buttonSimula = new JButton("Simula");
        buttonSimula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // eliminatedThisTurn = false;
                logTor.setElimThisTurn(false);
                if (!logTor.isEliminated()) {
                    Match p;
                    try {
                        p = new Match(logTor.getMiaSquadra(), logTor.getListAvversari().get(0));
                        buttonSimula.setVisible(false);
                        p.createAndShowGUI();
                        p.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(final WindowEvent e) {
                                buttonSimula.setVisible(true);
                                if (p.getWinner().getId() != 0) {
                                    logTor.setEliminated(true);
                                    logTor.setSquadraAvv(logTor.getListAvversari().get(0));
                                    // eliminatedThisTurn = true;
                                    logTor.setElimThisTurn(true);
                                }
                                risSquadraUte = p.getGoalT1();
                                risSquadraAvv = p.getGoalT2();
                                try {
                                    logTor.simulaMatch();
                                    createLevel();
                                } catch (ClassNotFoundException | IOException e1) {
                                    LOG.error("Error", e1);
                                }
                            }
                        });
                    } catch (ClassNotFoundException | IOException e1) {
                        LOG.error("Error", e1);
                    }
                } else {
                    try {
                        logTor.simulaMatch();
                        createLevel();
                    } catch (ClassNotFoundException | IOException e1) {
                        LOG.error("Error", e1);
                    }
                }
                // buttonSimula.setVisible(true);
            }
        });
        this.panelSud.add(buttonSimula);
        // Mi occupo del PanelCenter
        p5.setBorder(new EmptyBorder(BORDER_T, BORDER_R_P0, BORDER_T, BORDER_R_P0));
        g5.setHgap(BORDER_R_P0);
        p4.setBorder(new EmptyBorder(BORDER_T, BORDER_T, BORDER_T, BORDER_T));
        g4.setHgap(BORDER_R_P3);
        p3.setBorder(new EmptyBorder(BORDER_T, BORDER_R_P3, BORDER_T, BORDER_R_P3));
        g3.setHgap(GAP_P3);
        p2.setBorder(new EmptyBorder(BORDER_T, BORDER_R_P2, BORDER_T, BORDER_R_P2));
        g2.setHgap(GAP_P2);
        p1.setBorder(new EmptyBorder(BORDER_T, BORDER_R_P1, BORDER_T, BORDER_R_P1));
        p0.setBorder(new EmptyBorder(BORDER_T, BORDER_R_P1, BORDER_T, BORDER_R_P1));
        // Aggiungo le varie squadre nel panel5 (la prima è sempre quella dell'utente)
        final MyJLabel[] labelsp5 = new MyJLabel[NUM_TURN_1];
        labelsp5[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName());
        labelsp5[0].setText(textFormat(NUM_TEXT_SINGLE, labelsp5[0].getText()));
        this.p5.add(labelsp5[0]);
        // Collections.shuffle(this.logTor.getListAvversari());
        for (int i = 0; i < this.logTor.getListAvversari().size(); i++) {
            labelsp5[i + 1] = new MyJLabel(this.logTor.getListAvversari().get(i).getTeamName());
            this.p5.add(labelsp5[i + 1]);
        }
        // System.out.println(this.logTor.getListAvversari().size());
        int cont = 1;
        this.labelsp4[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + BAR
                + this.logTor.getListAvversari().get(0).getTeamName());
        this.labelsp4[0].setText(textFormat(NUM_TEXT_DOUBLE, this.labelsp4[0].getText()));
        this.p4.add(this.labelsp4[0]);
        for (int i = 1; i < this.logTor.getListAvversari().size() - 1; i = i + 2, cont++) {
            this.labelsp4[cont] = new MyJLabel(this.logTor.getListAvversari().get(i).getTeamName() + BAR
                    + this.logTor.getListAvversari().get(i + 1).getTeamName());
            this.p4.add(this.labelsp4[cont]);
        }
        p0.setBackground(this.panelColor);
        p1.setBackground(this.panelColor);
        p2.setBackground(this.panelColor);
        p3.setBackground(this.panelColor);
        p4.setBackground(this.panelColor);
        p5.setBackground(this.panelColor);
        this.panelCenter.add(p0);
        this.panelCenter.add(p1);
        this.panelCenter.add(p2);
        this.panelCenter.add(p3);
        this.panelCenter.add(p4);
        this.panelCenter.add(p5);
        this.panelSud.setBackground(this.panelColor);
        this.panelCenter.setBackground(this.panelColor);
        this.panelNord.setBackground(Color.white);
        // this.panelNord.setBounds(200, 300, 200, 300);
        this.getPanel().add(panelSud, BorderLayout.SOUTH);
        this.getPanel().add(panelCenter, BorderLayout.CENTER);
        this.getPanel().add(panelNord, BorderLayout.NORTH);
    }

    private void createLevel() {
        setBounds(UtilsGUI.getWidth(MIN_W_H), UtilsGUI.getHeight(MIN_W_H), UtilsGUI.getWidth(MAX_W_H),
                UtilsGUI.getHeight(MAX_W_H));
        final int numSquadre = logTor.getNumSquadre();
        switch (numSquadre) {
        case 8:
            this.p4.removeAll();
            this.p4.repaint();
            // mettere il risultato della partita della squadra utente (prima da fare in
            // Logic)
            // metto i risultati nelle partite che si svolgono
            int cont = 1;
            this.labelsp4[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + " " + this.risSquadraUte
                    + " " + BAR + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getTeamName());
            this.labelsp4[0].setText(textFormat(NUM_TEXT_DOUBLE, this.labelsp4[0].getText()));
            this.p4.add(this.labelsp4[0]);
            for (int i = 1; i < this.listAvversarie.size() - 1; i = i + 2, cont++) {
                final String squad1 = this.listAvversarie.get(i).getTeamName();
                final var ris1 = this.logTor.getRisultati().get(squad1);
                final String squad2 = this.listAvversarie.get(i + 1).getTeamName();
                final var ris2 = this.logTor.getRisultati().get(squad2);
                this.labelsp4[cont] = new MyJLabel(squad1 + " " + ris1 + " " + BAR + " " + ris2 + " " + squad2);
                this.p4.add(this.labelsp4[cont]);
            }
            // ... creo il panel 3 con le squadre che hanno vinto
            if (!logTor.isEliminated()) {
                this.labelsp3[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                // this.labelsp3[0].setText(textFormat(14, this.labelsp3[0].getText()));
                this.p3.add(this.labelsp3[0]);
            } else {
                this.labelsp3[0] = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                this.p3.add(this.labelsp3[0]);
            }
            cont = 1;
            for (int i = 1; i < this.logTor.getListAvversari().size() - 1; i = i + 2, cont++) {
                this.labelsp3[cont] = new MyJLabel(this.logTor.getListAvversari().get(i).getTeamName() + BAR
                        + this.logTor.getListAvversari().get(i + 1).getTeamName());
                this.p3.add(this.labelsp3[cont]);
            }
            this.p3.validate();
            this.p4.validate();
            this.panelCenter.validate();
            this.listAvversarie = logTor.getListAvversari();
            break;
        case 4:
            this.p3.removeAll();
            this.p3.repaint();
            // mettere il risultato della partita della squadra utente
            // metto i risultati nelle partite che si svolgono
            if (!logTor.isEliminated() || this.logTor.isElimThisTurn()) {
                this.labelsp3[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + " " + this.risSquadraUte
                        + " " + BAR + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getTeamName());
                this.p3.add(this.labelsp3[0]);
            } else {
                final int ris1 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getTeamName());
                final int ris2 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getTeamName());
                this.labelsp3[0] = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + " " + ris1 + " " + BAR
                        + " " + ris2 + " " + this.listAvversarie.get(0).getTeamName());
                this.p3.add(this.labelsp3[0]);
                r1 = ris1;
                r2 = ris2;
                setOppTeam();
            }
            int cont1 = 1;
            for (int i = 1; i < this.listAvversarie.size() - 1; i = i + 2, cont1++) {
                final String squad1 = this.listAvversarie.get(i).getTeamName();
                final var ris1 = this.logTor.getRisultati().get(squad1);
                final String squad2 = this.listAvversarie.get(i + 1).getTeamName();
                final var ris2 = this.logTor.getRisultati().get(squad2);
                this.labelsp3[cont1] = new MyJLabel(squad1 + " " + ris1 + " " + BAR + " " + ris2 + " " + squad2);
                this.p3.add(this.labelsp3[cont1]);
            }
            // ... creo il panel 2 con le squadre che hanno vinto
            // (ora faccio finta che vinca sempre la squadra dell'utente poi dovrò cambiare)
            if (!logTor.isEliminated()) {
                this.labelsp2[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                this.p2.add(this.labelsp2[0]);
            } else {
                this.labelsp2[0] = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                this.p2.add(this.labelsp2[0]);
            }
            this.labelsp2[1] = new MyJLabel(this.logTor.getListAvversari().get(1).getTeamName() + BAR
                    + this.logTor.getListAvversari().get(2).getTeamName());
            this.p2.add(this.labelsp2[1]);
            this.logTor.clearRisMatch();
            this.p2.validate();
            this.p3.validate();
            this.panelCenter.validate();
            this.listAvversarie = logTor.getListAvversari();
            break;
        case 2:
            this.p2.removeAll();
            this.p2.repaint();
            // metto i risultati nel panel 2 (ora non ho quelli della squadraUtente)
            if (!logTor.isEliminated() || this.logTor.isElimThisTurn()) {
                this.labelsp2[0] = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + " " + this.risSquadraUte
                        + " " + BAR + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getTeamName());
                this.p2.add(this.labelsp2[0]);
            } else {
                final int ris1 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getTeamName());
                final int ris2 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getTeamName());
                this.labelsp2[0] = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + " " + ris1 + " " + BAR
                        + " " + ris2 + " " + this.listAvversarie.get(0).getTeamName());
                this.p2.add(this.labelsp2[0]);
                r1 = ris1;
                r2 = ris2;
                setOppTeam();
            }
            final String squad1 = this.listAvversarie.get(1).getTeamName();
            final var ris1 = this.logTor.getRisultati().get(squad1);
            final String squad2 = this.listAvversarie.get(2).getTeamName();
            final var ris2 = this.logTor.getRisultati().get(squad2);
            this.labelsp2[1] = new MyJLabel(squad1 + " " + ris1 + " " + BAR + " " + ris2 + " " + squad2);
            this.p2.add(this.labelsp2[1]);
            // aggiungo la nuova partita ...
            if (!logTor.isEliminated()) {
                this.labelp1 = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                this.p1.add(this.labelp1);
            } else {
                this.labelp1 = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + BAR
                        + this.logTor.getListAvversari().get(0).getTeamName());
                this.p1.add(this.labelp1);
            }
            this.logTor.clearRisMatch();
            this.p1.validate();
            this.p2.validate();
            this.panelCenter.validate();
            this.listAvversarie = logTor.getListAvversari();
            break;
        case 1:
            this.p1.removeAll();
            this.p1.repaint();
            // metto i risultati nel panel 1
            if (!logTor.isEliminated() || this.logTor.isElimThisTurn()) {
                this.labelp1 = new MyJLabel(this.logTor.getMiaSquadra().getTeamName() + " " + this.risSquadraUte
                        + " " + BAR + " " + this.risSquadraAvv + " " + this.listAvversarie.get(0).getTeamName());
                this.p1.add(this.labelp1);
            } else {
                final int ris11 = this.logTor.getRisMatch().get(this.logTor.getSquadraAvv().getTeamName());
                final int ris21 = this.logTor.getRisMatch().get(this.listAvversarie.get(0).getTeamName());
                this.labelp1 = new MyJLabel(this.logTor.getSquadraAvv().getTeamName() + " " + ris11 + " " + BAR
                        + " " + ris21 + " " + this.listAvversarie.get(0).getTeamName());
                this.p1.add(this.labelp1);
            }
            String winner;
            if (this.logTor.isElimThisTurn()) {
                this.labelp0 = new MyJLabel(this.logTor.getSquadraAvv().getTeamName());
                winner = this.logTor.getSquadraAvv().getTeamName();
            } else if (!logTor.isEliminated()) {
                this.labelp0 = new MyJLabel(this.logTor.getMiaSquadra().getTeamName());
                winner = this.logTor.getMiaSquadra().getTeamName();
            } else {
                this.labelp0 = new MyJLabel(this.logTor.getWinner());
                winner = this.logTor.getWinner();
            }
            this.p0.add(this.labelp0);
            final JLabel label = new JLabel("The winner is: " + winner + "!");
            label.setBorder(new RoundedBorder(Color.red, ROUND));
            label.setBackground(Color.white);
            label.setForeground(Color.red);
            label.setOpaque(true);
            this.panelNord.add(label);
            buttonSimula.setVisible(false);
            getPanel().validate();
            this.logTor.setNumSquadre(0);
            this.panelNord.validate();
            this.p0.validate();
            this.p1.validate();
            this.panelCenter.validate();
            break;
        default:
            break;
        }
    }

    ///**
     //* Initializes the tournament panel after the object has been fully constructed.
     //* This method should be called after constructing a Tournament object to perform any necessary panel initialization.
     //*/
    //private void initializePanel() {
      //  panel = getPanel();
    //}
    //
    ///**
     //* Retrieves the tournament panel.
     //* Subclasses should override this method to provide a specific tournament panel implementation.
     //*
     //* @return The tournament panel.
     //*/
    //public JPanel getPanel() {
        //return panel;
    //}

    /* metodo che formatta la stringa dei nomi dei giocatori */
    private String textFormat(final int max, final String s) {
        final StringBuilder sb = new StringBuilder();
        if (s.length() > 1) {
            final String label = "<html>";
            for (int i = 0; i < s.length() - 2; i++) {
                sb.append(label);
                sb.append(s.charAt(i));
                if (i % max == 0 && i != 0) {
                    sb.append("<br>");
                }
            }
            sb.append(label + s.charAt(s.length() - 2) + s.charAt(s.length() - 1));
            return sb.toString();
        }
        return s;
    }

    private void setOppTeam() {
        if (r2 > r1) {
            this.logTor.setSquadraAvv(this.listAvversarie.get(0));
        } else if (r1 == r2) {
            // rigori
            final Team vinc = new LogicsShootoutImpl(this.logTor.getSquadraAvv(), this.listAvversarie.get(0))
                    .getWinner();
            if (vinc.getTeamName().equals(this.listAvversarie.get(0).getTeamName())) {
                this.logTor.setSquadraAvv(this.listAvversarie.get(0));
            }
        }
    }

    /**
     * The inner classe that extends JLabel.
     * 
     * @author Davide Braccini
     *
     */
    private static class MyJLabel extends JLabel {
        private static final int ROUND = 20;
        private final Color blu = new Color(0, 64, 128);
        private static final long serialVersionUID = 1L;

        MyJLabel() {
            super();
        }

        MyJLabel(final String text) {
            super(text, SwingConstants.CENTER);
            setBorder(new RoundedBorder(Color.white, ROUND));
            setBackground(blu);
            setForeground(Color.white);
            setOpaque(true);
        }
    }
}
