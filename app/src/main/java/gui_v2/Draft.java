package gui_v2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import data.Player;
import data.Modulo;
import data.Team;
import data.TeamUser;
import gui.Base;
import gui.ViewTeam;
import gui.utilsGUI;
import manageData.ExtractData;
import manageData.ExtractDataImpl;
import manageData.LogicsFile;
import manageData.LogicsFileImpl;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Draft extends Base {
    private List<Player> liGiocatori;
    private final List<String> ruoli = List.of("A", "C", "D", "P");
    private final int nDraft = 5;
    private final List<Player> liTitolari = new ArrayList<>();
    private final List<Player> liRiserve = new ArrayList<>();
    public Draft(final List<Player> li, final Modulo mod, final String nomeSquadra, final String stemma) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 2, 2);
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        List<JButton> liBtn = new ArrayList<>();
        ExtractData ex;
        try {
            ex = new ExtractDataImpl(li);
            liGiocatori = ex.getRandom(mod.getNumAtt() * nDraft,
            		mod.getNumCen() * nDraft, mod.getNumDif() * nDraft, nDraft);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        JPanel panelGiocatore;
        JPanel panelPosizione;
        JButton btnProsegui = utilsGUI.standardButton("Prosegui");
        btnProsegui.setVisible(false);
        //titolari
        for (int i = 0; i < ruoli.size(); i++) {
            panelPosizione = new JPanel();
            for (int j = 0; j < mod.getN(ruoli.get(i)); j++) {
                panelPosizione.setLayout(layout);
                panelGiocatore = new JPanel();
                panelGiocatore.setLayout(layout);
                JButton btnScegli = new JButton("Scegli");
                btnScegli.setPreferredSize(new Dimension(100, 50));
                liBtn.add(btnScegli);
                btnScegli.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        JButton btn = (JButton) e.getSource();
                        JPanel panel = (JPanel) btn.getParent();
                        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
                        int index = liBtn.indexOf(btn);
                        DialogScelta dialog;
                        try {
                            dialog = new DialogScelta(parent, true,
                            		liGiocatori.subList(nDraft * index, nDraft * (index + 1)), 
                            		liGiocatori.subList(nDraft * index, nDraft * (index + 1)).get(0).getPos());
                            dialog.setVisible(true);
                            Player c = dialog.getCalciatore();
                            if (c != null) {
                                panel.remove(btn);
                                liTitolari.add(c);
                                panel.add(utilsGUI.getPanelCalciatore(c.getName(), 
                                		c.getRating().getX(), c.getPos(), true));
                                if (liTitolari.size() >= 11 && liRiserve.size() >= 7) {
                                    btnProsegui.setVisible(true);
                                }
                            }
                        } catch (ClassNotFoundException | IOException e1) {
                            e1.printStackTrace();
                        }
                        panel.revalidate();
                        panel.repaint();
                    }
                });
                panelGiocatore.add(btnScegli);
                panelPosizione.add(btnScegli);
            }
            gbc.gridy = i;
            contentPane.add(panelPosizione, gbc);
        }
        JLabel lblPanchina = new JLabel("PANCHINA");
        lblPanchina.setForeground(Color.white);
        lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridy = 4;
        contentPane.add(lblPanchina, gbc);
        gbc.insets = new Insets(10, 10, 10, 10);
        //panchinari
        panelPosizione = new JPanel();
        for (int j = 0; j < 7; j++) {
            panelPosizione.setLayout(layout);
            panelGiocatore = new JPanel();
            panelGiocatore.setLayout(layout);
            JButton btnScegli = new JButton("Scegli");
            btnScegli.setPreferredSize(new Dimension(100, 50));
            liBtn.add(btnScegli);
            btnScegli.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    JPanel panel = (JPanel) btn.getParent();
                    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(btn);
                    int index = liBtn.indexOf(btn);
                    DialogScelta dialog;
                    try {
                        dialog = new DialogScelta(parent, true,
                        liGiocatori.subList(nDraft * index, nDraft * (index + 1)),
                        liGiocatori.subList(nDraft * index, nDraft * (index + 1)).get(0).getPos());
                        dialog.setVisible(true);
                        Player c = dialog.getCalciatore();
                        if (c != null) {
                            panel.remove(btn);
                            liRiserve.add(c);
                            panel.add(utilsGUI.getPanelCalciatore(c.getName(), 
                            c.getRating().getX(), c.getPos(), false));
                            if (liTitolari.size() >= 11 && liRiserve.size() >= 7) {
                                btnProsegui.setVisible(true);
                            }
                        }
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    panel.revalidate();
                    panel.repaint();
                }
            });
            panelGiocatore.add(btnScegli);
            if (j < 4) {
                gbc.gridy = 0;
            }
            else {
                gbc.gridy = 1;
            }
            panelPosizione.add(panelGiocatore, gbc);
            gbc.gridy = 5;
            contentPane.add(panelPosizione, gbc);
        }
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = 6;
        contentPane.add(btnProsegui, gbc);
        btnProsegui.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        btnProsegui.setBackground(Color.white);
        btnProsegui.setRolloverEnabled(true);
        btnProsegui.setForeground(Color.BLUE);
        btnProsegui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Team squadra = new TeamUser(nomeSquadra, stemma, mod, liTitolari, liRiserve);
                LogicsFile logFile = new LogicsFileImpl();
                logFile.SaveStorico(squadra);
               // changeJPanel(new ViewTeam(squadra, li));
            }
        });
    }
}
