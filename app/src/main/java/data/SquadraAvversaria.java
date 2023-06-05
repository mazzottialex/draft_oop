package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import manageData.ExtractData;
import manageData.ExtractDataImpl;

/** 
 * This class represents an opposing team and implements the {@code Squadra} interface.
 * Each opposing team has an ID, a name, an emblem, a formation, a list of starters,
 * a list of substitutes, and a list of all players for a particular season.
 *
 */
public class SquadraAvversaria implements Squadra {
    private static final long serialVersionUID = 1L;
	private int id;
    private String nomeSquadra;
    private final String stemma;
    private Modulo modulo;
    private List<Calciatore> liTitolari = new ArrayList<>();
    private List<Calciatore> liRiserve = new ArrayList<>();
    private List<Calciatore> liCalciatori;


    /**
     * Constructor for the {@code SquadraAvversaria} class.
     * 
     * @param id            The ID of the team.
     * @param nomeSquadra   The name of the team.
     * @param modulo        The formation of the team.
     * @param li            The list of players for a particular season.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public SquadraAvversaria(final int id, final String nomeSquadra, final Modulo modulo, final List<Calciatore> li)
    throws FileNotFoundException, IOException, ClassNotFoundException {
        this.id = id;
        this.nomeSquadra = nomeSquadra;
        this.modulo = modulo;
        this.liCalciatori = li;
        ExtractData ed = new ExtractDataImpl(li);
        this.liTitolari = ed.getTitolari(nomeSquadra, modulo);
        this.liRiserve = ed.getRiserve(nomeSquadra, modulo);
        this.stemma = this.setStemma();
    }

    /**
     * Sets the emblem for the opposing team.
     *
     * @return The emblem filename.
     */
    private String setStemma() {
        final List<String> liStemmi = List.of("arancione.png", "azzurro.png",
            "bianco.png", "blu.png", "giallo.png", "nero.png", "rosso.png",
            "verde.png", "viola.png");
        Random rnd = new Random();
        int pos = rnd.nextInt(liStemmi.size());
        return liStemmi.get(pos);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNomeSquadra() {
        return nomeSquadra;
    }

    @Override
    public Modulo getModulo() {
        return modulo;
    }

    @Override
    public List<Calciatore> getTitolari() {
        return liTitolari;
    }

    @Override
    public List<Calciatore> getTitolariDesc() {
        return liTitolari.stream()
            .sorted((c1, c2) -> c1.getRuolo().compareTo(c2.getRuolo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Calciatore> getRiserve() {
        return liRiserve;
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
    public List<Calciatore> getLiCalciatori() {
        return liCalciatori;
    }

    @Override
    public String getStemma() {
        return this.stemma;
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
        return nomeSquadra;
    }

}
