package manageData;



public class Funzioni {
	private final int min;
	private final int max;
	private final int n;
	public Funzioni() {
		this.min=60;
		this.max=100;
		this.n=100;
	}
	public Funzioni(int min, int max, int n) {
		this.min=min;
		this.max=max;
		this.n=n;
	}
	public int Lineare(int x) {
		return (max-min)/n*x+min;
	}
	public int Logaritmica(int x) {
		return (int) Math.round(((max-min)/Math.log(n+1)*Math.log(x+1)+min));
	}
	public int Esponenziale(int x) {
		return (int) Math.round(Math.exp(Math.log(((max-min)+1)*x)/n+(max-min)-1));
	}
}
