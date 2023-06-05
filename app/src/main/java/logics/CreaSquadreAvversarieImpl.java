package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import data.Player;
import data.Modulo;
import data.Team;
import data.TeamOpponent;

/**
 * The {@code CreaSquadreAvversarieImpl} class implements the {@code CreaSquadreAvversarie} interface
 * and provides the functionality to create opposing teams.
 */
public class CreaSquadreAvversarieImpl implements CreaSquadreAvversarie {

    private int numero;
    private List<Player> li;
    private List<String> squadre;

    /**
     * Constructs a {@code CreaSquadreAvversarieImpl} object with the specified list of players and team count.
     *
     * @param calciatori the list of players.
     * @param numero the number of teams to create.
     */
    public CreaSquadreAvversarieImpl(final List<Player> calciatori, final int numero) {
        this.numero = numero;
        this.li = calciatori;
        this.squadre = raccogliSquadre(calciatori);
    }

    /**
     * Collects the unique team names from the given list of players.
     *
     * @param calciatori the list of players.
     * @return a list of unique team names.
     */
    public static List<String> raccogliSquadre(final List<Player> calciatori) {
        Set<String> squadreSet = new HashSet<>();
        for (Player calciatore: calciatori) {
            squadreSet.add(calciatore.getSquadra());
        }
        return new ArrayList<>(squadreSet);
    }

    /**
     * Selects random teams from the given list of teams and returns a list of SquadraAvversaria objects.
     *
     * @param squadre the list of teams.
     * @param modulo the team formation.
     * @param numeroSquadre the number of teams to select.
     * @return a list of randomly selected opposing teams.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    public List<Team> selezionaSquadreCasuali(final List<String> squadre, final Modulo modulo, final int numeroSquadre)
    throws FileNotFoundException, ClassNotFoundException, IOException {
        Collections.shuffle(squadre); // Mescola l'ordine delle squadre
        int id = 1;
        List<Team> listSquadre = new ArrayList<>();
        for (String s: squadre) {
            listSquadre.add(new TeamOpponent(id, s, modulo, li));
            id++;
        }
        return listSquadre.subList(0, Math.min(numeroSquadre, squadre.size()));
    }

    /**
     * Selects a random team formation.
     *
     * @return a randomly selected team formation.
     */
    public static Modulo selezionaModulo() {
        List<Modulo> modulo = new ArrayList<>(List.of(Modulo.values()));
        Random random = new Random();
        int index = random.nextInt(modulo.size());
        return modulo.get(index);
    }

    @Override
    public List<Team> getSquadre() throws FileNotFoundException, ClassNotFoundException, IOException {
        return selezionaSquadreCasuali(squadre, selezionaModulo(), numero);
    }
}