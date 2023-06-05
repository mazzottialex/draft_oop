package logics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Player;
import data.Team;
import gui.Sostituzione;

/**
 * Implementation of the {@code LogicsSostituzione} interface for managing player substitutions.
 */
public class LogicsSostituzioneImpl implements LogicsSostituzione {
    private List<Player> titolari;
    private List<Player> riserve;
    private Player entra;
    private Player esce;
    private Team s;
    private Sostituzione gui;

    /**
     * Constructs a new instance of {@code LogicsSostituzioneImpl}.
     *
     * @param s   the squadra (team) instance
     * @param gui the GUI instance for player substitutions
     */
    public LogicsSostituzioneImpl(final Team s, final Sostituzione gui) {
        this.titolari = new ArrayList<>(s.getTitolari());
        this.riserve = new ArrayList<>(s.getRiserve());
        this.entra = null;
        this.esce = null;
        this.s = s;
        this.gui = gui;
    }

    @Override
    public void chiudiGUI() {
        gui.chiudi();
    }

    @Override
    public List<Player> getTitolari() {
        return titolari;
    }

    @Override
    public List<Player> getRiserve() {
        return riserve;
    }

    @Override
    public void selezTit(final Player c) {
        esce = c;
    }

    @Override
    public void selezRis(final Player c) {
        entra = c;
    }

    @Override
    public void sub(final Container parent1, final Container parent2, final Component component1, final Component component2) {
        if (esce.getRuolo().equals(entra.getRuolo())) {
            int index1 = getComponentIndex(parent1, component1);
            int index2 = getComponentIndex(parent2, component2);
            if (index1 != -1 && index2 != -1) {
                parent1.remove(component1);
                parent2.remove(component2);
                parent1.add(component2, index1);
                parent1.revalidate();
                parent1.repaint();
                parent2.revalidate();
                parent2.repaint();
                // Resetta la selezione dei pannelli
                component1.setBackground(null);
                component2.setBackground(null);
                int indexEsce = titolari.indexOf(esce);
                titolari.set(indexEsce, entra);
                riserve.remove(entra);
                s.setTitolari(titolari);
                s.setRiserve(riserve);
                JOptionPane.showMessageDialog(null, "Sostituzione effettuata");
                chiudiGUI();
            }
        } else {
            JOptionPane.showMessageDialog(null, "I due giocatori selezionati devono avere lo stesso ruolo");
            component1.setBackground(null);
            component2.setBackground(null);
        }
    }

    /**
     * Retrieves the index of a component within a container.
     *
     * @param parent    the parent container
     * @param component the component to find the index of
     * @return the index of the component, or -1 if not found
     */
    private static int getComponentIndex(final Container parent, final Component component) {
        for (int i = 0; i < parent.getComponentCount(); i++) {
            if (parent.getComponent(i) == component) {
                return i;
            }
        }
        return -1;
    }

}
