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
		//String ruolo=calc.getRuolo();
		int ratGol=fun.Logaritmica(calc.getGol(), ex.getTopGol(), 50, 100);
		int ratTiri=fun.Logaritmica(calc.getTiri(), ex.getTopTiri(), 50, 100);
		int ratDribl=fun.Logaritmica(calc.getDribling(), ex.getTopDribling(), 50, 90);
		int ratAss=fun.Logaritmica(calc.getAssist(), ex.getTopAssist(), 50, 90);
		int ratPassChiave=fun.Logaritmica(calc.getPassaggiChiave(), ex.getTopPassChiave(), 50, 90);
		
		int rat=(ratAss+ratDribl+ratGol*2+ratPassChiave+ratTiri*2)/7;
		
		Pair<Integer, Triple<Integer, Integer, Integer>> rating= new Pair<Integer, Triple<Integer,Integer,Integer>>(rat, null);
		return rating;
	}

}
