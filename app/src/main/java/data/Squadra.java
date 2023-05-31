package data;

import java.util.List;
import java.util.Optional;

public interface Squadra {
	public List<Calciatore> getTitolari();
	public List<Calciatore> getRiserve();
	public String getNomeSquadra();
	public String getStemma();
	public int getValutazione();
	public Modulo getModulo();
	public int getId();
	public List<Calciatore> getLiCalciatori();
	public Calciatore getCalciatoreById(int id);
	public Calciatore getPortiereTit();
	public void setTitolari(List<Calciatore> liTitolari);
	public void setRiserve(List<Calciatore> liRiserve);
	List<Calciatore> getTitolariDesc();
}
