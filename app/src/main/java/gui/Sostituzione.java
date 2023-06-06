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

import data.Calciatore;
import data.Squadra;
import logics.LogicsSostituzione;
import logics.LogicsSostituzioneImpl;

/**
 * Represents a GUI for substituting players.
 */
public class Sostituzione extends Base {
    /**
     * 
     */
    private static final long serialVersionUID = 5244133982320404420L;
    private final List<String> ruoli = List.of("P", "D", "C", "A");
    private JPanel panelSquadra = new JPanel();
    private LogicsSostituzione logics;
    private static JPanel panelTit;
    private static JPanel panelRis;
    private int riserve;
    private static final int RISERVE = 7;
    private static final int INSETS_5 = 5;
    private static final int GRID_5 = 5;
    private static final int GRID_6 = 6;
    private static final int GRID_7 = 7;

    /**
     * Creates a new instance of the {@code Sostituzione} class.
     *
     * @param squadra The team for which substitutions are being made.
     * @param superGui The {@code Partita} instance.
     * @param cambiFatti The number of substitutions already made.
     */
    public Sostituzione(final Squadra squadra, final Partita superGui, final int cambiFatti) {
        logics = new LogicsSostituzioneImpl(squadra, this);
        this.riserve = RISERVE - cambiFatti;
        panelTit = null;
        panelRis = null;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS_5, INSETS_5, 2, 2);
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        panelSquadra.setBackground(getForeground());

        JLabel lblNomeSquadra = new JLabel("Titolari");
        lblNomeSquadra.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        lblNomeSquadra.setForeground(Color.white);
        panelSquadra.add(lblNomeSquadra);

        gbc.gridy = 0;
        contentPane.add(panelSquadra, gbc);

        //titolari
        JPanel panelPosizione = new JPanel();
        panelPosizione.setLayout(layout);
        int count = 0;
        for (int i = 0; i < ruoli.size(); i++) {
            panelPosizione = new JPanel();
            for (int j = 0; j < squadra.getModulo().getN(ruoli.get(i)); j++) {
                Calciatore c = squadra.getTitolari().get(count);
                JPanel panel = (utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        if (panelTit != null) {
                            panelTit.setBackground(null);
                        }
                        panelTit = panel;
                        panelTit.setBackground(Color.YELLOW);
                        logics.selezTit(c);
                    }
                });
                panelPosizione.add(panel);
                count++;
            }
            gbc.gridy = i + 1;
            contentPane.add(panelPosizione, gbc);
        }
        JLabel lblPanchina = new JLabel("Riserve");
        lblPanchina.setForeground(Color.white);
        lblPanchina.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy = GRID_5;
        contentPane.add(lblPanchina, gbc);
        gbc.insets = new Insets(INSETS_5, INSETS_5, INSETS_5, INSETS_5);

        //panchinari
        panelPosizione = new JPanel();
        panelPosizione.setLayout(layout);
        for (int j = 0; j < riserve; j++) {
            Calciatore c = squadra.getRiserve().get(j);
            JPanel panel = (utilsGUI.getPanelCalciatore(c.getNominativo(), c.getRating().getX(), c.getRuolo(), true));
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (panelRis != null) {
                        panelRis.setBackground(null);
                    }
                    panelRis = panel;
                    panelRis.setBackground(Color.YELLOW);
                    logics.selezRis(c);
                }
            });
            panelPosizione.add(panel);
        }
        gbc.gridy = GRID_6;
        contentPane.add(panelPosizione, gbc);

        JButton sostituisci = new JButton("Sostitutisci");
        sostituisci.addActionListener(e -> {
            if (panelTit == null || panelRis == null) {
                JOptionPane.showMessageDialog(null, "Bisogna selezionare due giocatori: uno tra i titolari e uno tra le riserve, che devono avere lo stesso ruolo");
        	    if (panelTit != null) {
                    panelTit.setBackground(null);
                }
        	    if (panelRis != null) {
                    panelRis.setBackground(null);
                }
            } else {
                logics.sub(panelTit.getParent(), panelRis.getParent(), panelTit, panelRis);
            }
            panelTit = null;
            panelRis = null;
        });
        gbc.gridy = GRID_7;
        contentPane.add(sostituisci, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Closes the {@code Sostituzione} window.
     */
    public void chiudi() {
        this.dispose();
    }

    /**
     * Retrieves the {@code LogicsSostituzione} instance associated with the {@code Sostituzione} object.
     *
     * @return The {@code LogicsSostituzione} instance.
     */
    public LogicsSostituzione getLogics() {
        return logics;
    }
}
