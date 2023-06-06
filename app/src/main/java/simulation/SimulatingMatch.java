package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import data.Team;

public interface SimulatingMatch {
    double defensivePerformance(Team team)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    double scoringAbility(Team team)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    double offensivePerformance(Team team)
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    Map<Team, Integer> result()
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    Map<Team, Integer> resultExtra()
    		throws FileNotFoundException, ClassNotFoundException, IOException;

    Map<Team, Integer> resultSub(int minute)
    		throws FileNotFoundException, ClassNotFoundException, IOException;
}
