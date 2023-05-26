package logics;

import java.util.List;

import data.Calciatore;
import data.SquadraAvversaria;

public class LogicsSostituzioneImpl implements LogicsSostituzione{
	private List<Calciatore> titolari;
	private List<Calciatore> riserve;
	
	public LogicsSostituzioneImpl(SquadraAvversaria s) {
		this.titolari = s.getTitolari();
		this.riserve = s.getRiserve();
	}
	
	@Override
	public List<Calciatore> getTitolari() {
		return titolari;
	}
	
	@Override
	public List<Calciatore> getRiserve() {
		return riserve;
	}
	
}
