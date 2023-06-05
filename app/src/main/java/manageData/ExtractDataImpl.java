package manageData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import data.Player;
import data.Modulo;
public class ExtractDataImpl implements ExtractData {
    private final List<Player> li;
    public ExtractDataImpl(final List<Player> li)
    		throws FileNotFoundException, IOException, ClassNotFoundException {
        this.li = li;
    }
    @Override
    public List<Player> getCalciatoreBySquadra(final String squadra) {
        return li.stream()
            .filter(c -> c.getTeam().equals(squadra))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getLi() {
        return li;
    }
    @Override
    public Optional<Player> getCalciatoreByName(final String name) {
        return li.stream()
            .filter(c -> c.getName().equals(name))
            .findFirst();
    }
    //RUOLI: P, D, C, A
    @Override
    public List<Player> getListaByRuolo(final String ruolo) {
        return li.stream()
            .filter(c -> c.getPos().equals(ruolo))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getRandomByRuolo(final String ruolo, int n) {
        List<Player> listaRuolo = getListaByRuolo(ruolo);
        Random rnd = new Random();
        Set<Integer> posizioni = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num;
            do {
                num = rnd.nextInt(listaRuolo.size());
            } while (posizioni.contains(num));
            posizioni.add(num);
        }
        return posizioni.stream()
            .map(p -> listaRuolo.get(p))
            .collect(Collectors.toList());
    }

    @Override
    public int getTopByAttribute(final Function <Player, Integer> attr) {
        return li.stream()
            .map(c -> attr.apply(c))
            .max((c1, c2) -> c1 - c2)
            .orElse(0);
    }
    //per ammonizioni e espulsioni
    @Override
    public int getTopByAttribute(final Function<Player, Integer> f1, 
    		final Function <Player, Integer> f2) {
        return li.stream()
            .map(c -> {
                if (f1.apply(c) != 0 && f2.apply(c) > 100) {
                    return f2.apply(c) / f1.apply(c);
                } else
                    return 100;
            })
            .max((c1, c2) -> c1 - c2)
            .orElse(0);
    }
    @Override
    public List<Player> getListOrdered(final Function<Player, Integer> attr) {
        return li.stream()
            .sorted((c1, c2) -> attr.apply(c1) - attr.apply(c2))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getTitolariBySquadraByRuolo(final String squadra, 
    		final String ruolo, final Modulo modulo) {
        int n = 1;
        switch (ruolo) {
            case "D":
                n = modulo.getNumDif();
                break;
            case "C":
                n = modulo.getNumCen();
                break;
            case "A":
                n = modulo.getNumAtt();
                break;
            default: //compreso il case "P" perché per il ruolo portiere n=1
                break;
        }
        final List <Player> lista = getCalciatoreBySquadra(squadra).stream()
            .filter(c -> c.getPos().equals(ruolo))
            .sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
            .limit(n)
            .collect(Collectors.toList());
        return lista;
    }

    @Override
    public List<Player> getRiserveBySquadraByRuolo(final String squadra, 
    		final String ruolo, final Modulo modulo) {
        int n = 2;
        int m = 1;
        switch (ruolo) {
            case "P":
                n = 1;
                break;
            case "D":
                m = modulo.getNumDif();
                break;
            case "C":
                m = modulo.getNumCen();
                break;
            case "A":
                m = modulo.getNumAtt();
                break;
            default:
                break;
        }
        List<Player> lista = getCalciatoreBySquadra(squadra).stream()
            .filter(c -> c.getPos().equals(ruolo))
            .sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
            .skip(m)
            .limit(n)
            .collect(Collectors.toList());
        return lista;
    }

    @Override
    public List<Player> getTitolari(final String squadra, final Modulo modulo) {
    	final List<Player> lt = new ArrayList<>();
        lt.addAll(getTitolariBySquadraByRuolo(squadra, "P", modulo));
        lt.addAll(getTitolariBySquadraByRuolo(squadra, "D", modulo));
        lt.addAll(getTitolariBySquadraByRuolo(squadra, "C", modulo));
        lt.addAll(getTitolariBySquadraByRuolo(squadra, "A", modulo));
        return lt;
    }

    @Override
    public List<Player> getRiserve(final String squadra, final Modulo modulo) {
        final List<Player> lr = new ArrayList<>();
        lr.addAll(getRiserveBySquadraByRuolo(squadra, "P", modulo));
        lr.addAll(getRiserveBySquadraByRuolo(squadra, "D", modulo));
        lr.addAll(getRiserveBySquadraByRuolo(squadra, "C", modulo));
        lr.addAll(getRiserveBySquadraByRuolo(squadra, "A", modulo));
        return lr;
    }

    public List<String> getNomeCalciatori(final String squadra) {
        return getCalciatoreBySquadra(squadra)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    public List<String> getNomeTitolari(final String squadra, final Modulo modulo) {
        return getTitolari(squadra, modulo)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    public List<String> getNomeRiserve(final String squadra, final Modulo modulo) {
        return getRiserve(squadra, modulo)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    // ruolo, nome, rating
    public List<?> tsr(final String squadra, final Modulo modulo) {
        return getTitolari(squadra, modulo)
            .stream()
            .map(c -> c.toVector())
            .collect(Collectors.toList());
    }

    public List<Player> getRandom(final int nA, final int nC, final int nD, final int nP) {
    	final List<Player> li = new ArrayList<>();
        li.addAll(getRandomByRuolo("A", nA));
        li.addAll(getRandomByRuolo("C", nC));
        li.addAll(getRandomByRuolo("D", nD));
        li.addAll(getRandomByRuolo("P", nP));
        Player c;
        for (int i = 0; i < 5; i++) {
            do {
                c = getRandomByRuolo("P", 1).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByRuolo("D", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByRuolo("C", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByRuolo("A", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        return li;
    }
    @Override
    public Optional<Player> getCalciatoreById(final int id) {
        return li.stream()
            .filter(c -> c.getId() == id)
            .findFirst();
    }
}
