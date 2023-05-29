package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.SquadraAvversaria;

public class LogicsRigoriImpl implements LogicsRigori {
	private SquadraAvversaria s1;
	private SquadraAvversaria s2;
	private Iterator<Calciatore> shooterIterator1;
	private Iterator<Calciatore> shooterIterator2;
	private int gol1;
	private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private boolean continua;
    private ArrayList<Map<Calciatore, String>> list;
    private Map<Calciatore, String> map1;
	private Map<Calciatore, String> map2;
	
	public LogicsRigoriImpl(SquadraAvversaria s1, SquadraAvversaria s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.shooterIterator1 = backIterator(s1.getTitolari());
		this.shooterIterator2 = backIterator(s2.getTitolari());
		this.gol1 = 0;
		this.gol2 = 0;
		this.tiri1 = 0;
		this.tiri2 = 0;
		this.totTiri = 10;
		this.continua = true;
		this.list = new ArrayList<>();
		this.map1 = new HashMap<>();
		this.map2 = new HashMap<>();
	}
	
	@Override
	public ArrayList<Map<Calciatore, String>> compute() {
		while (continua) {
			if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2) || ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
                continua = false;
			} else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                if ((tiri1 + tiri2) % 2 == 0) {
                	if (!shooterIterator1.hasNext()) {
                		shooterIterator1 = backIterator(s1.getTitolari());
                    }
                    if (shooterIterator1.hasNext()) {
                    	Calciatore tiratore = shooterIterator1.next();
                    	String res = rigore(tiratore, s2);
                    	map1.put(tiratore, res);
                        if (res.equals("Gol")) {
                        	gol1++;
                        }
                        tiri1++;
                    }
                } else {
                	if (!shooterIterator2.hasNext()) {
                		shooterIterator2 = backIterator(s2.getTitolari());
                    }
                    if (shooterIterator2.hasNext()) {
                    	Calciatore tiratore = shooterIterator2.next();
                        String res = rigore(tiratore, s1);
                        map2.put(tiratore, res);
                        if (res.equals("Gol")) {
                        	gol2++;
                        }
                        tiri2++;
                    }
                }
			}
		}
		list.add(map1);
		list.add(map2);
		return list;
	}
	
	private Iterator<Calciatore> backIterator(List<Calciatore> list) {
    	List<Calciatore> backList = new ArrayList<>();
    	for (int i = list.size() - 1; i >= 0; i--) {
    		backList.add(list.get(i));
        }
		return backList.iterator();
    }
	
	private String rigore(Calciatore tiratore, SquadraAvversaria dif) {
        double tirRating = tiratore.getRating().getY().getX() * (0.8 + new Random().nextDouble(0.4));

        double porRating = dif.getPortiereTit().getRating().getY().getZ() * (0.8 + new Random().nextDouble(0.4));

        double modPorRating = porRating * 0.75 /*costante rigori fatti nei shootout*/;
        if (tirRating > modPorRating) {
            return "Gol";
        } else {
            return "Sbagliato";
        }
    }
	
	@Override
	public int getGol1() {
		return gol1;
	}

	@Override
	public int getGol2() {
		return gol2;
	}
}
