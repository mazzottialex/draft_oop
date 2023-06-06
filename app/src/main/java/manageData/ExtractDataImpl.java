package manageData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import data.Player;
import data.Module;
public class ExtractDataImpl implements ExtractData {
    private final List<Player> li;
    public ExtractDataImpl(final List<Player> li) {
        this.li = li;
    }
    @Override
    public List<Player> getPlayerByTeam(final String team) {
        return li.stream()
            .filter(c -> c.getTeam().equals(team))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getLi() {
        return li;
    }
    @Override
    public Optional<Player> getPlayerByName(final String name) {
        return li.stream()
            .filter(c -> c.getName().equals(name))
            .findFirst();
    }
    //RUOLI: P, D, C, A
    @Override
    public List<Player> getListByPos(final String pos) {
        return li.stream()
            .filter(c -> c.getPos().equals(pos))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getRandomByPos(final String pos, int n) {
        List<Player> listaRuolo = getListByPos(pos);
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
    public List<Player> getStartingByTeamByPos(final String squadra, 
    		final String ruolo, final Module modulo) {
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
        final List <Player> lista = getPlayerByTeam(squadra).stream()
            .filter(c -> c.getPos().equals(ruolo))
            .sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
            .limit(n)
            .collect(Collectors.toList());
        return lista;
    }

    @Override
    public List<Player> getSubstitutionByTeamByPos(final String team, 
    		final String pos, final Module modulo) {
        int n = 2;
        int m = 1;
        switch (pos) {
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
        List<Player> lista = getPlayerByTeam(team).stream()
            .filter(c -> c.getPos().equals(pos))
            .sorted((c1, c2) -> c2.getRating().getX() - c1.getRating().getX())
            .skip(m)
            .limit(n)
            .collect(Collectors.toList());
        return lista;
    }

    @Override
    public List<Player> getStarting(final String team, final Module modulo) {
    	final List<Player> lt = new ArrayList<>();
        lt.addAll(getStartingByTeamByPos(team, "P", modulo));
        lt.addAll(getStartingByTeamByPos(team, "D", modulo));
        lt.addAll(getStartingByTeamByPos(team, "C", modulo));
        lt.addAll(getStartingByTeamByPos(team, "A", modulo));
        return lt;
    }

    @Override
    public List<Player> getSubstitution(final String team, final Module modulo) {
        final List<Player> lr = new ArrayList<>();
        lr.addAll(getSubstitutionByTeamByPos(team, "P", modulo));
        lr.addAll(getSubstitutionByTeamByPos(team, "D", modulo));
        lr.addAll(getSubstitutionByTeamByPos(team, "C", modulo));
        lr.addAll(getSubstitutionByTeamByPos(team, "A", modulo));
        return lr;
    }

    public List<String> getPlayerName(final String team) {
        return getPlayerByTeam(team)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    public List<String> getNameStarting(final String team, final Module modulo) {
        return getStarting(team, modulo)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    public List<String> getNameSubstitution(final String team, final Module modulo) {
        return getSubstitution(team, modulo)
            .stream()
            .map(c -> c.getName())
            .collect(Collectors.toList());
    }

    // ruolo, nome, rating
    public List<?> tsr(final String team, final Module modulo) {
        return getStarting(team, modulo)
            .stream()
            .map(c -> c.toVector())
            .collect(Collectors.toList());
    }

    public List<Player> getRandom(final int nA, final int nC, final int nD, final int nP) {
    	final List<Player> li = new ArrayList<>();
        li.addAll(getRandomByPos("A", nA));
        li.addAll(getRandomByPos("C", nC));
        li.addAll(getRandomByPos("D", nD));
        li.addAll(getRandomByPos("P", nP));
        Player c;
        for (int i = 0; i < 5; i++) {
            do {
                c = getRandomByPos("P", 1).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByPos("D", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByPos("C", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        for (int i = 0; i < 10; i++) {
            do {
                c = getRandomByPos("A", 2).get(0);
            } while (li.contains(c));
            li.add(c);
        }
        return li;
    }
    @Override
    public Optional<Player> getPlayerById(final int id) {
        return li.stream()
            .filter(c -> c.getId() == id)
            .findFirst();
    }
}
