package manageData;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import data.Calciatore;

public interface ExtractData {
	public List<Calciatore> getCalciatoreBySquadra(String name);
	public Optional<Calciatore> getCalciatoreByName(String name);
	public List<Calciatore> getListaByRuolo(String name);
	public List<Calciatore> getRandomByRuolo(String name, int n);
	public List<Calciatore> getLi();
	public int getTopByAttribute(Function<Calciatore, Integer> attr);
}
