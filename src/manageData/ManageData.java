package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ManageData {
	public List<Calciatore> getLi();
	public void LoadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	public void DownloadData() throws FileNotFoundException, ClassNotFoundException, IOException;
	/*
	public List<Calciatore> getCalciatoreBySquadra(String name);
	public Optional<Calciatore> getCalciatoreByName(String name);
	public List<Calciatore> getListaByRuolo(String name);
	public List<Calciatore> getRandomByRuolo(String name, int n);
	public List<Calciatore> getTitolariBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
	public List<Calciatore> getRiserveBySquadraByRuolo(String squadra, String ruolo, Modulo modulo);
	*/
}
