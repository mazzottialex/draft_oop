package manageData;

import java.util.List;
import java.util.Optional;

public interface ManageData {
	public List<Calciatore> getCalciatoreBySquadra(String name);
	public Optional<Calciatore> getCalciatoreByName(String name);
	public List<Calciatore> getListaByRuolo(String name);
	public List<Calciatore> getRandomByRuolo(String name, int n);
	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
}
