package manageData;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import data.Player;
import data.Module;
public interface ExtractData {
	public List<Player> getPlayerByTeam(String name);
	public Optional<Player> getPlayerByName(String name);
	public List<Player> getListByRole(String role);
	public List<Player> getRandomByRole(String name, int n);
	public List<Player> getLi();
	public int getTopByAttribute(Function<Player, Integer> attr);
	public int getTopByAttribute(Function<Player, Integer> f1, Function<Player, Integer> f2);
	public List<Player> getListOrdered(Function<Player, Integer> attr);
	public List<Player> getStartersByTeamByRole(String team, String role, Module module);
	public List<Player> getSubstitutesByTeamByRole(String team, String role, Module module);
	public List<Player> getStarters(String team, Module module);
	public List<Player> getSubstitutes(String team, Module module);
	public List<String> getPlayersNames(String team);
	public List<String> getStartersNames(String team, Module module);
	public List<String> getSubtitutesNames(String team, Module module);
	public List<?> getListPlayersNamePositionRatingByTeam(String team, Module module);
	public List<Player> getRandom(int nA, int nC, int nD, int nP);
	public Optional<Player> getPlayerById(int id);
}
