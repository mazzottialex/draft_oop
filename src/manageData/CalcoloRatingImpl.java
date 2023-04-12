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
		
		int ratGol=fun.Logaritmica(calc.getGol(), ex.getTopByAttribute(c->c.getGol()), 50, 100);
		int ratTiri=fun.Logaritmica(calc.getTiri(), ex.getTopByAttribute(c->c.getTiri()), 50, 100);
		int ratDribl=fun.Logaritmica(calc.getDribling(), ex.getTopByAttribute(c->c.getDribling()), 50, 90);
		int ratAss=fun.Logaritmica(calc.getAssist(), ex.getTopByAttribute(c->c.getAssist()), 50, 90);
		int ratPassChiave=fun.Logaritmica(calc.getPassaggiChiave(), ex.getTopByAttribute(c->c.getPassaggiChiave()), 50, 90);
		
		int ratA=(ratAss*2+ratDribl+ratGol*6+ratPassChiave+ratTiri*2)/12;
		
		int ratPassaggi=fun.Logaritmica(calc.getPassaggi(), ex.getTopByAttribute(c->c.getPassaggi()), 50, 95);
		int ratRubati=fun.Logaritmica(calc.getRubati(), ex.getTopByAttribute(c->c.getRubati()), 50, 95);
		int ratTackle=fun.Logaritmica(calc.getTackle(), ex.getTopByAttribute(c->c.getTackle()), 50, 95);
		int ratCS=fun.Logaritmica(calc.getCleanSheet(), ex.getTopByAttribute(c->c.getCleanSheet()), 50, 82);
		
		int ratD=(ratPassaggi+ratRubati+ratTackle+ratCS)/4;

		int ratC=(ratA+ratD)/2;
		
		int ratMin=fun.Logaritmica(calc.getMinuti(), ex.getTopByAttribute(c->c.getMinuti()), 50, 95);
		
		int rat=50;

		if(ruolo.equals("A"))
			rat=(int) Math.ceil(0.9*ratA+0.1*ratD);
		else if(ruolo.equals("C"))
			rat=(int) Math.ceil(0.5*ratA+0.5*ratD);
		else if(ruolo.equals("D"))
			rat=(int) Math.ceil(0.1*ratA+0.9*ratD);
		else if(ruolo.equals("P"))
		{
			int ratParate=fun.Logaritmica(calc.getParate(), ex.getTopByAttribute(c->c.getParate()), 50, 92);
			ratD=(ratParate+ratCS)/2;
			rat=ratD;
		}
		return new Pair<Integer, Triple<Integer,Integer,Integer>>((int)(0.8*rat+0.2*ratMin), new Triple<>(ratA, ratC, ratD));
	}

}
