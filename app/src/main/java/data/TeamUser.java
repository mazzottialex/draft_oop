package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TeamUser implements Team, Serializable {

	
	private static final long serialVersionUID = 1L;
    private final int id;
    private final String nomeSquadra;
    private final String stemma;
    private final Modulo modulo;
    private final List<Player> liCalciatori;
    private List<Player> liTitolari;
    private List<Player> liRiserve;


    public TeamUser(final String nomeSquadra, final String stemma, final Modulo modulo,
        final List<Player> liTitolari, final List<Player> liRiserve) {
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
    public List<Player> getLiCalciatori() {
        return liCalciatori;
    }

    @Override
    public List<Player> getTitolari() {
        return liTitolari.stream()
            .sorted((c1, c2) -> c2.getRuolo().compareTo(c1.getRuolo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getTitolariDesc() {
        return liTitolari.stream()
            .sorted((c1, c2) -> c1.getRuolo().compareTo(c2.getRuolo()))
            .collect(Collectors.toList());
    }

    @Override
    public List < Player > getRiserve() {
        return this.liRiserve;
    }

    @Override
    public void setTitolari(final List<Player> liTitolari) {
        this.liTitolari = liTitolari;
    }

    @Override
    public void setRiserve(final List<Player> liRiserve) {
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
    public Player getCalciatoreById(final int id) {
        Player c = null;
        for (Player calciatore: liCalciatori) {
            if (calciatore.getId() == id) {
                c = calciatore;
            }
        }
        return c;
    }

    @Override
    public Player getPortiereTit() {
        Player portiere = null;
        for (Player calciatore: getTitolari()) {
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

