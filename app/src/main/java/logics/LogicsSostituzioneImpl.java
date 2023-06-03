package logics;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Calciatore;
import data.Squadra;
import gui.Sostituzione;

public class LogicsSostituzioneImpl implements LogicsSostituzione {
    private List<Calciatore> titolari;
    private List<Calciatore> riserve;
    private Calciatore entra;
    private Calciatore esce;
    private Squadra s;
    private Sostituzione gui;

    public LogicsSostituzioneImpl(Squadra s, Sostituzione gui) {
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
    public List<Calciatore> getTitolari() {
        return titolari;
    }

    @Override
    public List<Calciatore> getRiserve() {
        return riserve;
    }

    @Override
    public void selezTit(Calciatore c) {
        esce = c;
    }

    @Override
    public void selezRis(Calciatore c) {
        entra = c;
    }

    @Override
    public void sub(Container parent1, Container parent2, Component component1, Component component2) {
        if (entra != null && esce != null) {
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
                    component1 = null;
                    component2 = null;
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
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bisogna selezionare due giocatori: uno tra i titolari e uno tra le riserve, che devono avere lo stesso ruolo");
        }
    }

    private static int getComponentIndex(Container parent, Component component) {
        for (int i = 0; i < parent.getComponentCount(); i++) {
            if (parent.getComponent(i) == component) {
                return i;
            }
        }
        return -1;
    }

}