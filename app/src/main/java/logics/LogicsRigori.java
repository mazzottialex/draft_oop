package logics;

import java.util.ArrayList;
import java.util.Map;

import data.Calciatore;
import data.Squadra;

public interface LogicsRigori {
	public ArrayList<Map<Calciatore, String>> compute();

	int getGol1();

	int getGol2();

	public Squadra getWinner();
}
