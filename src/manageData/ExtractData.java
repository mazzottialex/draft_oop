package manageData;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import data.Calciatore;
import data.Modulo;

public interface ExtractData {
	public List<Calciatore> getCalciatoreBySquadra(String name);
	public Optional<Calciatore> getCalciatoreByName(String name);
	public List<Calciatore> getListaByRuolo(String ruolo);
	public List<Calciatore> getRandomByRuolo(String name, int n);
	public List<Calciatore> getLi();
	public int getTopByAttribute(Function<Calciatore, Integer> attr);
	public int getTopByAttribute(Function<Calciatore, Integer> f1, Function<Calciatore, Integer> f2);
	public List<Calciatore> getListOrdered(Function<Calciatore, Integer> attr);
	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
	public List<Calciatore> getTitolari(String squadra, Modulo modulo);
	public List<Calciatore> getRiserve(String squadra, Modulo modulo);
	public List<String> getNomeCalciatori(String squadra);
	public List<String> getNomeTitolari(String squadra, Modulo modulo);
	public List<String> getNomeRiserve(String squadra, Modulo modulo);
	public List<?> tsr(String squadra, Modulo modulo);
}
