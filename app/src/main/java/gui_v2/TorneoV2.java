package gui_v2;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.Pair;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.Player;
import data.Team;
import data.TeamUser;
import gui.Base;
import gui.Match;
import gui.Start;
import gui.utilsGUI;
import logics.CreateOpponentTeams;
import logics.CreateOpponentTeamsImpl;
import logics.LogicsShootout;
import logics.LogicsShootoutImpl;
import simulation.SimulatingMatch;
import simulation.SimulatingMatchImpl;
public class TorneoV2 extends Base {
    private List<Team> turnoDaSimul = new ArrayList<>();
    private List<Pair<Team, Integer>> liRis = new ArrayList<>();
    private final int turni = 3;
    private final int nSquadre = (int) Math.pow(2, turni);
    private int count = 0;
    private TorneoColl tabellone;
    private static final int SIZE_INSETS = 5;
    public TorneoV2(final Team squadra, final List<Player> li) {
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        CreateOpponentTeams creaS = new CreateOpponentTeamsImpl(li, nSquadre - 1);
        List<Team> liSquadre = new ArrayList<>();
        liSquadre.add(squadra);
        try {
            liSquadre.addAll(creaS.getTeams());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        tabellone = new TorneoColl(liSquadre);
        JButton btnSimula = utilsGUI.standardButton("Simula");
        JButton btnHome = utilsGUI.standardButton("Torna alla Home");
        btnHome.setVisible(false);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                changeJPanel(new Start());
            }
        });
        btnSimula.addActionListener(new ActionListener() {
            private List<Team> liSquadreVinc = new ArrayList<>();
            @Override
            public void actionPerformed(final ActionEvent e) {
                turnoDaSimul = tabellone.getLastLi();
                if (turnoDaSimul.size() == 2) {
                    btnSimula.setVisible(false);
                    btnHome.setVisible(true);
                }
                if (turnoDaSimul.size() > 1) {
                    if (turnoDaSimul.get(0) instanceof TeamUser) {
                        JButton btn = (JButton) e.getSource();
                        btn.setEnabled(false);
                        Match partita;
                        try {
                            partita = new Match(turnoDaSimul.get(0), turnoDaSimul.get(1));
                            partita.createAndShowGUI();
                            partita.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(final WindowEvent e) {
                                    btn.setEnabled(true);
                                    liSquadreVinc.add(0, partita.getWinner());
                                    tabellone.addLi(liSquadreVinc);
                                    liSquadreVinc = new ArrayList<>();
                                    liRis.add(0, new Pair<Team, Integer>(turnoDaSimul.get(0), partita.getGoalT1()));
                                    liRis.add(1, new Pair<Team, Integer>(turnoDaSimul.get(1), partita.getGoalT2()));
                                    tabellone.setLiLastRisul(liRis);
                                    liRis = new ArrayList<>();
                                    contentPane.remove(count + 2);
                                    gbc.gridy = turni - count;
                                    contentPane.add(createPaneFase2(tabellone.getLiLastRisul()), gbc);
                                    count++;
                                    gbc.gridy = turni - count;
                                    contentPane.add(createPaneFase(tabellone.getLastLi()), gbc);
                                    contentPane.revalidate();
                                    contentPane.repaint();
                                };
                            });
                        } catch (ClassNotFoundException | IOException e1) {
                            e1.printStackTrace();
                        }
                        for (int i = 0; i < turnoDaSimul.size() - 1; i = i + 2) {
                            if (!(turnoDaSimul.get(i) instanceof TeamUser)) {
                                try {
                                    SimulatingMatch sim = new SimulatingMatchImpl(turnoDaSimul.get(i), turnoDaSimul.get(i + 1));
                                    Iterator<Team> it = sim.result().keySet().iterator();
                                    Team s1 = it.next();
                                    Team s2 = it.next();
                                    int score1 = sim.result().get(s1);
                                    int score2 = sim.result().get(s2);
                                    if (score1 == score2) {
                                        score1 = sim.resultExtra().get(s1);
                                        score2 = sim.resultExtra().get(s2);
                                        if (score1 == score2) {
                                            LogicsShootout rigori = new LogicsShootoutImpl(s1, s2);
                                            liSquadreVinc.add(rigori.getWinner());
                                        } else {
                                            liSquadreVinc.add(score1 > score2 ? s1 : s2);
                                        }
                                    } else {
                                        liSquadreVinc.add(score1 > score2 ? s1 : s2);
                                    }
                                    liRis.add(new Pair<Team, Integer>(s1, score1));
                                    liRis.add(new Pair<Team, Integer>(s2, score2));
                                } catch (ClassNotFoundException | IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < turnoDaSimul.size() - 1; i = i + 2) {
                            if (!(turnoDaSimul.get(i) instanceof TeamUser)) {
                                try {
                                    SimulatingMatch sim = new SimulatingMatchImpl(turnoDaSimul.get(i), turnoDaSimul.get(i + 1));
                                    Iterator<Team> it = sim.result().keySet().iterator();
                                    Team s1 = it.next();
                                    Team s2 = it.next();
                                    int score1 = sim.result().get(s1);
                                    int score2 = sim.result().get(s2);
                                    if (score1 == score2) {
                                        score1 = sim.resultExtra().get(s1);
                                        score2 = sim.resultExtra().get(s2);
                                        if (score1 == score2) {
                                            LogicsShootout rigori = new LogicsShootoutImpl(s1, s2);
                                            liSquadreVinc.add(rigori.getWinner());
                                        } else {
                                            liSquadreVinc.add(score1 > score2 ? s1 : s2);
                                        }
                                    } else {
                                        liSquadreVinc.add(score1 > score2 ? s1 : s2);
                                    }
                                    liRis.add(new Pair<Team, Integer>(s1, score1));
                                    liRis.add(new Pair<Team, Integer>(s2, score2));
                                } catch (ClassNotFoundException | IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                        tabellone.addLi(liSquadreVinc);
                        liSquadreVinc = new ArrayList<>();
                        tabellone.setLiLastRisul(liRis);
                        liRis = new ArrayList<>();
                        contentPane.remove(count + 2);
                        gbc.gridy = turni - count;
                        contentPane.add(createPaneFase2(tabellone.getLiLastRisul()), gbc);
                        count++;
                        gbc.gridy = turni - count;
                        contentPane.add(createPaneFase(tabellone.getLastLi()), gbc);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                }
            }
        });
        gbc.insets = new Insets(SIZE_INSETS, SIZE_INSETS, SIZE_INSETS, SIZE_INSETS);
        gbc.gridy = turni + 2;
        contentPane.add(btnSimula, gbc);
        gbc.gridy = turni + 2;
        contentPane.add(btnHome, gbc);
        gbc.gridy = 0;
        contentPane.add(createPaneFase(tabellone.getLastLi()), gbc);
    }
    private JPanel createPaneFase(final List<Team> li) {
        JPanel panelFase = new JPanel();
        for (int i = 0; i < li.size(); i++) {
            JPanel matchPanel = new JPanel();
            if (tabellone.getLastLi().size() == 1) {
                matchPanel.add(new JLabel("Vincitore: " + li.get(i).getTeamName()));
                JOptionPane.showMessageDialog(null, "Il vincitore del torneo è: "
                + li.get(i).getTeamName(), "Fine torneo", JOptionPane.INFORMATION_MESSAGE);
            } else if (tabellone.getLastLi().size() > 1) {
                matchPanel.add(new JLabel(li.get(i).getTeamName()));
                matchPanel.add(new JLabel("-"));
                i++;
                matchPanel.add(new JLabel(li.get(i).getTeamName()));
            }
            panelFase.add(matchPanel);
        }
        return panelFase;
    }
    private JPanel createPaneFase2(final List<Pair<Team, Integer>> li) {
        JPanel panelFase = new JPanel();
        for (int i = 0; i < li.size(); i = i + 2) {
            int score1 = li.get(i).getY();
            int score2 = li.get(i + 1).getY();
            Team s1 = li.get(i).getX();
            Team s2 = li.get(i + 1).getX();
            JPanel matchPanel = new JPanel();
            JLabel labelSquadra1;
            JLabel labelSquadra2;
            labelSquadra1 = new JLabel(s1.getTeamName() + " " + score1);
            labelSquadra2 = new JLabel(score2 + " " + s2.getTeamName());
            if (score1 > score2) {
                labelSquadra2.setForeground(Color.gray);
            } else {
                labelSquadra1.setForeground(Color.gray);
            }
            matchPanel.add(labelSquadra1);
            matchPanel.add(new JLabel("-"));
            matchPanel.add(labelSquadra2);
            panelFase.add(matchPanel);
        }
        return panelFase;
    }
}
