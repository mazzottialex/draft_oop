package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.Pair;
import utils.Triple;

public class CalcoloRatingImpl implements CalcoloRating {

	private Funzioni fun=new Funzioni();
	private ExtractData ex;
	public CalcoloRatingImpl() throws FileNotFoundException, ClassNotFoundException, IOException {
		ex=new ExtractDataImpl();
	}
	@Override
	public Pair<Integer, Triple<Integer, Integer, Integer>> getRating(Calciatore calc) {
		String ruolo=calc.getRuolo();
		
		int ratGol=fun.Logaritmica(calc.getGol(), ex.getTopGol(), 50, 100);
		int ratTiri=fun.Logaritmica(calc.getTiri(), ex.getTopTiri(), 50, 100);
		int ratDribl=fun.Logaritmica(calc.getDribling(), ex.getTopDribling(), 50, 90);
		int ratAss=fun.Logaritmica(calc.getAssist(), ex.getTopAssist(), 50, 90);
		int ratPassChiave=fun.Logaritmica(calc.getPassaggiChiave(), ex.getTopPassChiave(), 50, 90);
		
		int ratA=(ratAss*2+ratDribl+ratGol*5+ratPassChiave+ratTiri)/10;
		
		int ratPassaggi=fun.Logaritmica(calc.getPassaggi(), ex.getTopPassaggi(), 50, 90);
		int ratRubati=fun.Logaritmica(calc.getRubati(), ex.getTopRubati(), 50, 100);
		int ratTackle=fun.Logaritmica(calc.getTackle(), ex.getTopTackle(), 50, 100);
		int ratCS=fun.Logaritmica(calc.getCleanSheet(), ex.getTopCleanSheet(), 50, 80);
		
		int ratD=(ratPassaggi+ratRubati+ratTackle+ratCS)/4;

		int ratC=(ratA+ratD)/2;
		
		int ratMin=fun.Logaritmica(calc.getMinuti(), ex.getTopMinuti(), 50, 95);
		
		int rat=50;

		if(ruolo.equals("A"))
			rat=(int) Math.ceil(0.9*ratA+0.1*ratD);
		else if(ruolo.equals("C"))
			rat=(int) Math.ceil(0.5*ratA+0.5*ratD);
		else if(ruolo.equals("D"))
			rat=(int) Math.ceil(0.1*ratA+0.9*ratD);
		else if(ruolo.equals("P"))
		{
			int ratParate=fun.Logaritmica(calc.getParate(), ex.getCountPortieri(), 50, 86);
			ratD=(ratParate*2+ratCS)/3;
			rat=ratD;
		}
		Pair<Integer, Triple<Integer, Integer, Integer>> rating= new Pair<Integer, Triple<Integer,Integer,Integer>>((int)(0.8*rat+0.2*ratMin), new Triple<>(ratA, ratC, ratD));
		return rating;
	}

}
