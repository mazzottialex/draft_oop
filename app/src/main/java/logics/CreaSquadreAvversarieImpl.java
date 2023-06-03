package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraAvversaria;

public class CreaSquadreAvversarieImpl implements CreaSquadreAvversarie {

    private int numero;
    private List<Calciatore> li;
    private List<String> squadre;

    public CreaSquadreAvversarieImpl(List<Calciatore> calciatori, int numero) {
        this.numero = numero;
        this.li = calciatori;
        this.squadre = raccogliSquadre(calciatori);
    }

    public static List<String> raccogliSquadre(List<Calciatore> calciatori) {
        Set<String> squadreSet = new HashSet<>();
        for (Calciatore calciatore: calciatori) {
            squadreSet.add(calciatore.getSquadra());
        }
        return new ArrayList<>(squadreSet);
    }

    public List<Squadra> selezionaSquadreCasuali(List<String> squadre, Modulo modulo, int numeroSquadre) throws FileNotFoundException, ClassNotFoundException, IOException {
        Collections.shuffle(squadre); // Mescola l'ordine delle squadre
        int id = 0;
        List<Squadra> listSquadre = new ArrayList<>();
        for (String s: squadre) {
            listSquadre.add(new SquadraAvversaria(id, s, modulo, li));
            id++;
        }
        return listSquadre.subList(0, Math.min(numeroSquadre, squadre.size()));
    }

    public static Modulo selezionaModulo() {
        List<Modulo> modulo = new ArrayList<>(List.of(Modulo.values()));
        Random random = new Random();
        int index = random.nextInt(modulo.size());
        return modulo.get(index);
    }

    @Override
    public List<Squadra> getSquadre() throws FileNotFoundException, ClassNotFoundException, IOException {
        return selezionaSquadreCasuali(squadre, selezionaModulo(), numero);
    }
}