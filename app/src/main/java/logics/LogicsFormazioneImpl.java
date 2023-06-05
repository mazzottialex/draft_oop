package logics;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import data.Modulo;
public class LogicsFormazioneImpl implements LogicsFormazione {
    public LogicsFormazioneImpl() {}
    public List<Modulo> getRandomMod(final int n) {
        final List<Modulo> li = Arrays.asList(Modulo.values());
        final Random rnd = new Random();
        final Set<Integer> posizioni = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num;
            do {
                num = rnd.nextInt(li.size());
            } while (posizioni.contains(num));
            posizioni.add(num);
        }
        return posizioni.stream()
            .map(p -> li.get(p))
            .toList();
    }
}
