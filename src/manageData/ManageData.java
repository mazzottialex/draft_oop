package manageData;

import java.util.List;
import java.util.Optional;

public interface ManageData {
	public Optional<Calciatore> getCalciatore(int n);
	public Optional<Calciatore> getCalciatoreByName(String name);
	public List<Calciatore> getListaByRuolo(String name);
}
