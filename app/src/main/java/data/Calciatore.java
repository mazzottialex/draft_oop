package data;
import java.io.Serializable;
import java.util.Vector;
import utils.Pair;
import utils.Triple;
public class Calciatore implements Serializable {
    private static final long serialVersionUID = -557391519003956401L; //per serializzazione
    private final int id;
    private final String nominativo;
    private final String ruolo;
    private final String squadra;
    private final int pg; //partite giocate
    private final int minuti;
    private final int gol;
    private final int tiri;
    private final int dribling;
    private final int assist;
    private final int passaggi;
    private final int passaggiChiave;
    private final int ammonizioni;
    private final int espulsioni;
    private final int rubati;
    private final int tackle;
    private final int cleanSheet;
    private final int parate;
    private Pair<Integer, Integer> ratingCartellino;
    private Pair<Integer, Triple<Integer, Integer, Integer>> rating; //VAL, A-C-D
    public Calciatore(final int id, final String nominativo, final String ruolo, final String squadra,
    		final int pg, final int minuti, final int gol, final int tiri, final int dribling, final int assist,
    		final int passaggi, final int passaggiChiave, final int ammonizioni, final int espulsioni, final int rubati,
    		final int tackle, final int cleanSheet, final int parate) {
        this.id = id;
        this.nominativo = nominativo;
        this.ruolo = ruolo;
        this.squadra = squadra;
        this.pg = pg;
        this.minuti = minuti;
        this.gol = gol;
        this.tiri = tiri;
        this.dribling = dribling;
        this.assist = assist;
        this.passaggi = passaggi;
        this.passaggiChiave = passaggiChiave;
        this.ammonizioni = ammonizioni;
        this.espulsioni = espulsioni;
        this.rubati = rubati;
        this.tackle = tackle;
        this.cleanSheet = cleanSheet;
        this.parate = parate;
        this.rating = null;
        this.ratingCartellino = null;
    }
    public int getId() {
        return id;
    }
    public String getNominativo() {
        return nominativo;
    }
    public String getRuolo() {
        return ruolo;
    }
    public String getSquadra() {
        return squadra;
    }
    public int getPg() {
        return pg;
    }
    public int getMinuti() {
        return minuti;
    }
    public int getGol() {
        return gol;
    }
    public int getTiri() {
        return tiri;
    }
    public int getDribling() {
        return dribling;
    }
    public int getAssist() {
        return assist;
    }
    public int getPassaggi() {
        return passaggi;
    }
    public int getPassaggiChiave() {
        return passaggiChiave;
    }
    public int getAmmonizioni() {
        return ammonizioni;
    }
    public int getEspulsioni() {
        return espulsioni;
    }
    public int getRubati() {
        return rubati;
    }
    public int getTackle() {
        return tackle;
    }
    public int getCleanSheet() {
        return cleanSheet;
    }
    public int getParate() {
        return parate;
    }
    public Pair<Integer, Integer> getRatingCartellino() {
        return ratingCartellino;
    }
    public Pair<Integer, Triple<Integer, Integer, Integer>> getRating() {
        return rating;
    }
    public void setRatingCartellino(final Pair<Integer, Integer> ratingCartellino) {
        this.ratingCartellino = ratingCartellino;
    }
    public void setRating(final Pair<Integer, Triple<Integer, Integer, Integer>> rating) {
        this.rating = rating;
    }
    public Vector<?> toVector() {
        Vector<Object> v = new Vector<>();
        v.add(ruolo);
        v.add(nominativo);
        v.add(rating.getX());
        v.add(rating.getY().getX());
        v.add(rating.getY().getY());
        v.add(rating.getY().getZ());
        return v;
    }
}
