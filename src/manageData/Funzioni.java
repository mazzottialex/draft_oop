package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Funzioni {
	private final int n;
	private ExtractData ex;

	public Funzioni() throws FileNotFoundException, ClassNotFoundException, IOException {
		ex= new ExtractDataImpl();
		this.n=100;
	}
	public Funzioni(int n) {
		this.n=n;
	}
	public int Lineare(int x, int min, int max) {
		return (max-min)/n*x+min;
	}
	public int Logaritmica(int x, int top, int min, int max) {
		x=n*x/top;
		return (int) Math.round(((max-min)/Math.log(n+1)*Math.log(x+1)+min));
	}
	public int Esponenziale(int x, int min, int max) {
		return (int) Math.round(Math.exp(Math.log(((max-min)+1)*x)/n+(max-min)-1));
	}
}
