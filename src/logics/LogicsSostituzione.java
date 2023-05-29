package logics;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

import data.Calciatore;

public interface LogicsSostituzione {
	public List<Calciatore> getTitolari();
	public List<Calciatore> getRiserve();
	public void selezTit(Calciatore c);
	public void selezRis(Calciatore c);
	public void sub(Container parent1, Container parent2, Component component1, Component component2);
}
