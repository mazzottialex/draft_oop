package logics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.Calciatore;
import data.Squadra;
import utils.Pair;

public class LogicsRigoriImpl implements LogicsRigori {
	private Squadra s1;
	private Squadra s2;
//	private Iterator<Calciatore> shooterIterator1;
//	private Iterator<Calciatore> shooterIterator2;
	private List<Calciatore> titolari1;
	private List<Calciatore> titolari2;
	private int gol1;
	private int gol2;
    private int tiri1;
    private int tiri2;
    private int totTiri;
    private boolean continua;
    private ArrayList<Map<Calciatore, String>> list_old;
    private Map<Calciatore, String> map1_old;
	private Map<Calciatore, String> map2_old;
	private Map<Integer, Pair<Calciatore, String>> map1;
	private Map<Integer, Pair<Calciatore, String>> map2;
	private ArrayList<Map<Integer, Pair<Calciatore, String>>> list;
	
	public LogicsRigoriImpl(Squadra s1, Squadra s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
//		this.shooterIterator1 = backIterator(s1.getTitolari());
//		this.shooterIterator2 = backIterator(s2.getTitolari());
		this.titolari1 = s1.getTitolari();
		this.titolari2 = s2.getTitolari();
		this.gol1 = 0;
		this.gol2 = 0;
		this.tiri1 = 0;
		this.tiri2 = 0;
		this.totTiri = 10;
		this.continua = true;
		this.list_old = new ArrayList<>();
		this.list = new ArrayList<>();
		this.map1_old = new HashMap<>();
		this.map2_old = new HashMap<>();
		this.map1 = new HashMap<>();
		this.map2 = new HashMap<>();
	}
	
	@Override
	public ArrayList<Map<Calciatore, String>> compute_old() {
		while (continua) {
			if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2)
					|| ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
                continua = false;
			} else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                if ((tiri1 + tiri2) % 2 == 0) {
                	if (titolari1.isEmpty()) {
                		titolari1 = s1.getTitolari();
                    }
                	Calciatore tiratore = bestRating(titolari1);
                	System.out.print(tiratore.getNominativo() + " " + tiratore.getRating().getY().getX() + " ");
                	titolari1.remove(tiratore);
                	String res = rigore(tiratore, s2);
                	System.out.print(res + " ");
                	map1_old.put(tiratore, res);
                    if (res.equals("Gol")) {
                    	gol1++;
                    }
                    tiri1++;
                } else {
                	if (titolari2.isEmpty()) {
                		titolari2 = s2.getTitolari();
                    }
                	Calciatore tiratore = bestRating(titolari2);
                	System.out.print(tiratore.getNominativo() + " " + tiratore.getRating().getY().getX());
                	titolari2.remove(tiratore);
                    String res = rigore(tiratore, s1);
                    System.out.println(res + " ");
                    map2_old.put(tiratore, res);
                    if (res.equals("Gol")) {
                    	gol2++;
                    }
                    tiri2++;
                }
			}
		}
		titolari1 = s1.getTitolari();
		titolari2 = s2.getTitolari();
		list_old.add(map1_old);
		list_old.add(map2_old);
		return list_old;
	}
	
	@Override
	public ArrayList<Map<Integer, Pair<Calciatore, String>>> compute() {
		while (continua) {
			if ((((tiri1 + tiri2) >= totTiri) && tiri1 == tiri2 && gol1 != gol2)
					|| ((tiri1 + tiri2) < totTiri && ((((totTiri / 2) - tiri1) + gol1) < gol2 || (((totTiri / 2) - tiri2) + gol2) < gol1))) {
                continua = false;
			} else if ((tiri1 + tiri2) < totTiri || tiri1 != tiri2 || (tiri1 == tiri2 && gol1 == gol2)) {
                if ((tiri1 + tiri2) % 2 == 0) {
                	if (titolari1.isEmpty()) {
                		titolari1 = s1.getTitolari();
                    }
                	Calciatore tiratore = bestRating(titolari1);
//                	System.out.print(tiratore.getNominativo() + " " + tiratore.getRating().getY().getX() + " ");
                	titolari1.remove(tiratore);
                	String res = rigore(tiratore, s2);
//                	System.out.print(res + " ");
                	map1.put(tiri1, new Pair<>(tiratore, res));
                    if (res.equals("Gol")) {
                    	gol1++;
                    }
                    tiri1++;
                } else {
                	if (titolari2.isEmpty()) {
                		titolari2 = s2.getTitolari();
                    }
                	Calciatore tiratore = bestRating(titolari2);
//                	System.out.print(tiratore.getNominativo() + " " + tiratore.getRating().getY().getX());
                	titolari2.remove(tiratore);
                    String res = rigore(tiratore, s1);
//                  System.out.println(res + " ");
                    map2.put(tiri2, new Pair<>(tiratore, res));
                    if (res.equals("Gol")) {
                    	gol2++;
                    }
                    tiri2++;
                }
			}
		}
		titolari1 = s1.getTitolari();
		titolari2 = s2.getTitolari();
		list.add(map1);
		list.add(map2);
		return list;
	}
	
	private Calciatore bestRating(List<Calciatore> list) {
		Calciatore best = list.get(list.size() - 1);
//		for (Calciatore calciatore : list) {
//			if (best == null || calciatore.getRating().getY().getX() > best.getRating().getY().getX()) {
//				best = calciatore;
//			}
//		}
//		return best;
//		list.remove(best);
		return best;
	}
	
//	private Iterator<Calciatore> backIterator(List<Calciatore> list) {
//    	List<Calciatore> backList = new ArrayList<>();
//    	for (int i = list.size() - 1; i >= 0; i--) {
//    		backList.add(list.get(i));
//        }
//		return backList.iterator();
//    }
	
	private String rigore(Calciatore tiratore, Squadra dif) {
        double tirRating = tiratore.getRating().getY().getX() * (0.8 + new Random().nextDouble(0.4));

        double porRating = 85/*dif.getPortiereTit().getRating().getY().getZ()*/ * (0.8 + new Random().nextDouble(0.4));

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
	
	@Override
	public Squadra getWinner() {
		if (gol1 > gol2) {
			return s1;
		} else {
			return s2;
		}
    }
}
