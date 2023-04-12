package manageData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Funzioni {
	private final int n;

	public Funzioni() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.n=100;
	}
	public Funzioni(int n) {
		this.n=n;
	}
	public int Lineare(int x, int top, int min, int max) {
		x=n*x/top;
		return (max-min)/n*x+min;
	}
	
	//parametro a, velocità funzione logaritmica, con a piccolo (es: 0,1) lenta, con a grande (es: 100) veloce
	public int Logaritmica(int x, int top, int min, int max) { 
		double a=0.3;
		x=n*x/top;
		return (int) Math.round(((max-min)/Math.log(a*n+1)*Math.log(a*x+1)+min));
	}
	public int Logaritmica(int x, int top, double a, int min, int max) { 
		x=n*x/top;
		return (int) Math.round(((max-min)/Math.log(a*n+1)*Math.log(a*x+1)+min));
	}
	public int Esponenziale(int x, int top, int min, int max) {
		x=n*x/top;
		return (int) Math.round(Math.exp(Math.log(((max-min)+1)*x)/n+(max-min)-1));
	}
}
