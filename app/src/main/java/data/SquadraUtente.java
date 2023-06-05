package data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SquadraUtente implements Squadra {
    private final int id;
    private final String nomeSquadra;
    private final String stemma;
    private final Modulo modulo;
    private final List<Calciatore> liCalciatori;
    private List<Calciatore> liTitolari;
    private List<Calciatore> liRiserve;

    public SquadraUtente(final String nomeSquadra, final String stemma, final Modulo modulo, final List<Calciatore> liTitolari, final List < Calciatore > liRiserve) {
        this.id = 0;
        this.nomeSquadra = nomeSquadra;
        this.stemma = stemma;
        this.modulo = modulo;
        this.liTitolari = liTitolari;
        this.liRiserve = liRiserve;
        this.liCalciatori = new ArrayList<>();
        liCalciatori.addAll(liTitolari);
        liCalciatori.addAll(liRiserve);
    }

    @Override
    public String getNomeSquadra() {
        return nomeSquadra;
    }

    @Override
    public String getStemma() {
        return stemma;
    }

    @Override
    public Modulo getModulo() {
        return modulo;
    }

    @Override
    public List < Calciatore > getLiCalciatori() {
        return liCalciatori;
    }

    @Override
    public List<Calciatore> getTitolari() {
        return liTitolari.stream()
            .sorted((c1, c2) -> c2.getRuolo().compareTo(c1.getRuolo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Calciatore> getTitolariDesc() {
        return liTitolari.stream()
            .sorted((c1, c2) -> c1.getRuolo().compareTo(c2.getRuolo()))
            .collect(Collectors.toList());
    }

    @Override
    public List < Calciatore > getRiserve() {
        return this.liRiserve;
    }

    @Override
    public void setTitolari(final List<Calciatore> liTitolari) {
        this.liTitolari = liTitolari;
    }

    @Override
    public void setRiserve(final List<Calciatore> liRiserve) {
        this.liRiserve = liRiserve;
    }

    @Override
    public int getValutazione() {
        return (int) Math.floor(
            liTitolari.stream()
            .map(c -> c.getRating()
                .getX())
            .mapToDouble(c -> c)
            .average()
            .orElse(0));
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Calciatore getCalciatoreById(final int id) {
        Calciatore c = null;
        for (Calciatore calciatore: liCalciatori) {
            if (calciatore.getId() == id) {
                c = calciatore;
            }
        }
        return c;
    }

    @Override
    public Calciatore getPortiereTit() {
        Calciatore portiere = null;
        for (Calciatore calciatore: getTitolari()) {
            if (calciatore.getRuolo().equals("P")) {
                portiere = calciatore;
            }
        }
        return portiere;
    }
    @Override
    public String toString() {
        return "SquadraAvversaria [nomeSquadra=" + nomeSquadra + "]";
    }
}
