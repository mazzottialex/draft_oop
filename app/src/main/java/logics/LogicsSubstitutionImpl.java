package logics;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Player;
import data.Team;
import gui.Substitution;

/**
 * Implementation of the {@code LogicsSubstitution} interface for managing player substitutions.
 */
public final class LogicsSubstitutionImpl implements LogicsSubstitution {
    private List<Player> starters;
    private List<Player> substitutes;
    private Player subOn;
    private Player subOff;
    private Team t;
    private Substitution gui;
    private boolean done;

    /**
     * Constructs a new instance of {@code LogicsSubstitutionImpl}.
     *
     * @param t   the {@code Team} instance
     * @param gui the GUI instance for player substitutions
     */
    public LogicsSubstitutionImpl(final Team t, final Substitution gui) {
        starters = new ArrayList<>(t.getStarting());
        substitutes = new ArrayList<>(t.getSubstitution());
        subOn = null;
        subOff = null;
        this.t = t;
        this.gui = gui;
        done = false;
        
    }

    @Override
    public void closeGui() {
        gui.chiudi();
    }

    @Override
    public List<Player> getStarters() {
        return starters;
    }

    @Override
    public List<Player> getSubstitutes() {
        return substitutes;
    }

    @Override
    public void selectStarter(final Player p) {
        subOff = p;
    }

    @Override
    public void selectSubstitute(final Player p) {
        subOn = p;
    }

    @Override
    public void sub(final Container parent1, final Container parent2, final Component component1, final Component component2) {
        if (subOff.getPos().equals(subOn.getPos())) {
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
                int indexEsce = starters.indexOf(subOff);
                starters.set(indexEsce, subOn);
                substitutes.remove(subOn);
                t.setStarting(starters);
                t.setSubstitution(substitutes);
                done = true;
                JOptionPane.showMessageDialog(null, "Sostituzione effettuata");
                closeGui();
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

    @Override
    public boolean done() {
        return done;
    }
}
