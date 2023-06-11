package model.managedata;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.data.Module;
import model.data.Player;

/**
 * This class implements the {@code ExtractData} interface and provides methods to extract and manipulate player data.
 */
public final class ExtractDataImpl implements ExtractData {

    private static final long serialVersionUID = 1L;
    private final List<Player> li;
    private static final int PARAMETER = 10;
    private static final int TOP = 100;
    private Random random = new Random();

    /**
     * Constructs an {@code ExtractDataImpl} object with the given list of players.
     *
     * @param li the list of players
     */
    public ExtractDataImpl(final List<Player> li) {
        this.li = new ArrayList<>(li);
    }

    @Override
    public List<Player> getPlayerByTeam(final String team) {
        return li.stream()
            .filter(p -> p.getTeam().equals(team))
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getLi() {
        return new ArrayList<>(li);
    }

    @Override
    public Optional<Player> getPlayerByName(final String name) {
        return li.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst();
    }

    //RUOLI: P, D, C, A
    @Override
    public List<Player> getListByPos(final String pos) {
        return li.stream()
            .filter(p -> p.getPos().equals(pos))
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getRandomByPos(final String pos, final int n) {
    	final List<Player> listRole = getListByPos(pos);
        final Random rnd = new Random();
        final Set<Integer> position = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num;
            do {
                num = rnd.nextInt(listRole.size());
            } while (position.contains(num));
            position.add(num);
        }
        return position.stream()
            .map(p -> listRole.get(p))
            .collect(Collectors.toList());
    }

    @Override
    public int getTopByAttribute(final Function<Player, Integer> attr) {
        return li.stream()
            .map(p -> attr.apply(p))
            .max((p1, p2) -> p1 - p2)
            .orElse(0);
    }

    @Override
    public int getTopByAttribute(final Function<Player, Integer> f1, 
            final Function<Player, Integer> f2) {
        return li.stream()
            .map(p -> {
                if (f1.apply(p) != 0 && f2.apply(p) > TOP) {
                    return f2.apply(p) / f1.apply(p);
                } else {
                    return TOP;
                }
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
    public List<Player> getStartingByTeamByPos(final String team, 
            final String pos, final Module module) {
        int n = 1;
        switch (pos) {
        case "D":
            n = module.getNumDef();
            break;
        case "C":
            n = module.getNumMid();
            break;
        case "A":
            n = module.getNumFor();
            break;
        default:
            break;
    }
    return getPlayerByTeam(team).stream()
            .filter(p -> p.getPos().equals(pos))
            .sorted(Comparator.comparingInt(p -> ((Player) p).getRating().getX()).reversed())
            .limit(n)
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getSubstitutionByTeamByPos(final String team, 
            final String pos, final Module module) {
        int n = 2;
        int m = 1;
        switch (pos) {
        case "P":
            n = 1;
            break;
        case "D":
            n = module.getNumDef();
            m = module.getNumDef();
            break;
        case "C":
            n = module.getNumMid();
            m = module.getNumMid();
            break;
        case "A":
            n = module.getNumFor();
            m = module.getNumFor();
            break;
        default:
            break;
    }
    return getPlayerByTeam(team).stream()
            .filter(p -> p.getPos().equals(pos))
            .sorted(Comparator.comparingInt(p -> ((Player) p).getRating().getX()).reversed())
            .skip(m)
            .limit(n)
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getStarting(final String team, final Module module) {
        final List<Player> lSt = new ArrayList<>();
        lSt.addAll(getStartingByTeamByPos(team, "P", module));
        lSt.addAll(getStartingByTeamByPos(team, "D", module));
        lSt.addAll(getStartingByTeamByPos(team, "C", module));
        lSt.addAll(getStartingByTeamByPos(team, "A", module));
        return lSt;
    }

    @Override
    public List<Player> getSubstitution(final String team, final Module module) {
        final List<Player> lSub = new ArrayList<>();
        lSub.addAll(getSubstitutionByTeamByPos(team, "P", module));
        lSub.addAll(getSubstitutionByTeamByPos(team, "D", module));
        lSub.addAll(getSubstitutionByTeamByPos(team, "C", module));
        lSub.addAll(getSubstitutionByTeamByPos(team, "A", module));
        return lSub;
    }

    @Override
    public List<String> getPlayerName(final String team) {
        return getPlayerByTeam(team)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getNameStarting(final String team, final Module module) {
        return getStarting(team, module)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getNameSubstitution(final String team, final Module module) {
        return getSubstitution(team, module)
            .stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
    }

    @Override
    public List<?> getStartersRoleNameRating(final String team, final Module module) {
        return getStarting(team, module)
            .stream()
            .map(p -> p.toList())
            .collect(Collectors.toList());
    }

    @Override
    public List<Player> getRandom(final int nA, final int nC, final int nD, final int nP) {
        final List<Player> li = new ArrayList<>();
        li.addAll(getRandomByPos("A", nA));
        li.addAll(getRandomByPos("C", nC));
        li.addAll(getRandomByPos("D", nD));
        li.addAll(getRandomByPos("P", nP));
        Player p;
        for (int i = 0; i < PARAMETER / 2; i++) {
            do {
                p = getRandomByPos("P", 1).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < PARAMETER; i++) {
            do {
                p = getRandomByPos("D", 2).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < PARAMETER; i++) {
            do {
                p = getRandomByPos("C", 2).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        for (int i = 0; i < PARAMETER; i++) {
            do {
                p = getRandomByPos("A", 2).get(0);
            } while (li.contains(p));
            li.add(p);
        }
        return li;
    }

    @Override
    public Optional<Player> getPlayerById(final int id) {
        return li.stream()
            .filter(p -> p.getId() == id)
            .findFirst();
    }

    @Override
    public List<String> findTeams() {
        return li.stream()
                .map(p -> p.getTeam())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Module selectModule() {
    	final List<Module> module = new ArrayList<>(List.of(Module.values()));
        return module.get(random.nextInt(module.size()));
    }
}
