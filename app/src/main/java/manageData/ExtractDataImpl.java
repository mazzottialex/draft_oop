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
            .filter(p -> p.getTeam().equals(team))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getLi() {
        return li;
    }
    @Override
    public Optional<Player> getPlayerByName(final String name) {
        return li.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst();
    }
    //RUOLI: P, D, C, A
    @Override
    public List<Player> getListByRole(final String role) {
        return li.stream()
            .filter(p -> p.getPos().equals(role))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getRandomByRole(final String role, int n) {
        List<Player> listRoles = getListByRole(role);
        Random rnd = new Random();
        Set<Integer> position = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num;
            do {
                num = rnd.nextInt(listRoles.size());
            } while (position.contains(num));
            position.add(num);
        }
        return position.stream()
            .map(p -> listRoles.get(p))
            .collect(Collectors.toList());
    }

    @Override
    public int getTopByAttribute(final Function <Player, Integer> attr) {
        return li.stream()
            .map(p -> attr.apply(p))
            .max((p1, p2) -> p1 - p2)
            .orElse(0);
    }
    //per ammonizioni e espulsioni
    @Override
    public int getTopByAttribute(final Function<Player, Integer> f1, 
    		final Function <Player, Integer> f2) {
        return li.stream()
            .map(p -> {
                if (f1.apply(p) != 0 && f2.apply(p) > 100) {
                    return f2.apply(p) / f1.apply(p);
                } else
                    return 100;
            })
            .max((p1, p2) -> p1 - p2)
            .orElse(0);
    }
    @Override
    public List<Player> getListOrdered(final Function<Player, Integer> attr) {
        return li.stream()
            .sorted((p1, p2) -> attr.apply(p1) - attr.apply(p2))
            .collect(Collectors.toList());
    }
    @Override
    public List<Player> getStartersByTeamByRole(final String team, 
    		final String role, final Module module) {
        int n = 1;
        switch (role) {
            case "D":
                n = module.getNumDif();
                break;
            case "C":
                n = module.getNumCen();
                break;
            case "A":
                n = module.getNumAtt();
                break;
            default: //compreso il case "P" perché per il ruolo portiere n=1
                break;
        }
        final List <Player> list = getPlayerByTeam(team).stream()
            .filter(p -> p.getPos().equals(role))
            .sorted((p1, p2) -> p2.getRating().getX() - p1.getRating().getX())
            .limit(n)
            .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Player> getSubstitutesByTeamByRole(final String team, 
    		final String role, final Module module) {
        int n = 2;
        int m = 1;
        switch (role) {
            case "P":
                n = 1;
                break;
            case "D":
                m = module.getNumDif();
                break;
            case "C":
                m = module.getNumCen();
                break;
            case "A":
                m = module.getNumAtt();
                break;
            default:
                break;
        }
        List<Player> list = getPlayerByTeam(team).stream()
            .filter(p -> p.getPos().equals(role))
            .sorted((p1, p2) -> p2.getRating().getX() - p1.getRating().getX())
            .skip(m)
            .limit(n)
            .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Player> getStarters(final String team, final Module module) {
    	final List<Player> lSt = new ArrayList<>();
        lSt.addAll(getStartersByTeamByRole(team, "P", module));
        lSt.addAll(getStartersByTeamByRole(team, "D", module));
        lSt.addAll(getStartersByTeamByRole(team, "C", module));
        lSt.addAll(getStartersByTeamByRole(team, "A", module));
        return lSt;
    }

    @Override
    public List<Player> getSubstitutes(final String team, final Module module) {
        final List<Player> lSub = new ArrayList<>();
        lSub.addAll(getSubstitutesByTeamByRole(team, "P", module));
        lSub.addAll(getSubstitutesByTeamByRole(team, "D", module));
        lSub.addAll(getSubstitutesByTeamByRole(team, "C", module));
        lSub.addAll(getSubstitutesByTeamByRole(team, "A", module));
        return lSub;
    }

    public List<String> getPlayersNames(final String team) {
        return getPlayerByTeam(team)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    public List<String> getStartersNames(final String team, final Module module) {
        return getStarters(team, module)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    public List<String> getSubtitutesNames(final String team, final Module module) {
        return getSubstitutes(team, module)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    // ruolo, nome, rating
    public List<?> getListPlayersNamePositionRatingByTeam(final String team, final Module module) {
        return getStarters(team, module)
            .stream()
            .map(c -> c.toVector())
            .collect(Collectors.toList());
    }

    public List<Player> getRandom(final int nA, final int nC, final int nD, final int nP) {
    	final List<Player> li = new ArrayList<>();
        li.addAll(getRandomByRole("A", nA));
        li.addAll(getRandomByRole("C", nC));
        li.addAll(getRandomByRole("D", nD));
        li.addAll(getRandomByRole("P", nP));
        Player p;
        for (int i = 0; i < 5; i++) {
            do {
                p = getRandomByRole("P", 1).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < 10; i++) {
            do {
                p = getRandomByRole("D", 2).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < 10; i++) {
            do {
                p = getRandomByRole("C", 2).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < 10; i++) {
            do {
                p = getRandomByRole("A", 2).get(0);
            } while (li.contains(p));
            li.add(p);
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
