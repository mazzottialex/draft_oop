package logics;

import java.util.List;

import data.Calciatore;

public interface LogicsHome {
	public List<String> getStagioni();
	public Boolean loadStagione(String stagione);
	public Boolean downloadStagione(String stagione);
	public List<Calciatore> geLi();
}
