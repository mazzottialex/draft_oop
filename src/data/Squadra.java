package data;

import java.util.List;

public interface Squadra {
	public List<Calciatore> getTitolari();
	public List<Calciatore> getRiserve();
	public String getNomeSquadra();
	public String getStemma();
	public int getValutazione();
	public Modulo getModulo();
}
