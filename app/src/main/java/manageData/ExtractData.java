package manageData;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import data.Player;
import data.Module;
public interface ExtractData {
	public List<Player> getCalciatoreBySquadra(String name);
	public Optional<Player> getCalciatoreByName(String name);
	public List<Player> getListaByRuolo(String ruolo);
	public List<Player> getRandomByRuolo(String name, int n);
	public List<Player> getLi();
	public int getTopByAttribute(Function<Player, Integer> attr);
	public int getTopByAttribute(Function<Player, Integer> f1, Function<Player, Integer> f2);
	public List<Player> getListOrdered(Function<Player, Integer> attr);
	public List<Player> getTitolariBySquadraByRuolo(String squadra, String ruolo, Module modulo);
	public List<Player> getRiserveBySquadraByRuolo(String squadra, String ruolo, Module modulo);
	public List<Player> getTitolari(String squadra, Module modulo);
	public List<Player> getRiserve(String squadra, Module modulo);
	public List<String> getNomeCalciatori(String squadra);
	public List<String> getNomeTitolari(String squadra, Module modulo);
	public List<String> getNomeRiserve(String squadra, Module modulo);
	public List<?> tsr(String squadra, Module modulo);
	public List<Player> getRandom(int nA, int nC, int nD, int nP);
	public Optional<Player> getCalciatoreById(int id);
}
