package rating;

/**
 * Class that provides various rating functions
 */
public class Function {
    private final int n;
    
    /**
     * Constructs a Function object with a default value of 100
     */
    public Function() {
        this.n = 100;
    }
    
    /**
     * Constructs a Function object with the specified value of n
     *
     * @param n The value of n.
     */
    public Function(final int n) {
        this.n = n;
    }
    
    /**
     * Computes the linear rating for the given value x
     *
     * @param x   The input value
     * @param top The maximum value for x
     * @param min The minimum rating value
     * @param max The maximum rating value
     * @return The computed linear rating
     */
    public int linear(int x, final int top, final int min, final int max) {
        x = n * x / top;
        return (max - min) / n * x + min;
    }
    
    /**
     * Computes the logarithmic rating for the given value x with a default speed parameter
     *
     * @param x   The input value
     * @param top The maximum value for x
     * @param min The minimum rating value
     * @param max The maximum rating value
     * @return The computed logarithmic rating
     */
    public int logarithmic(int x, final int top, final int min, final int max) {       
        //parametro a, velocit� funzione logaritmica, con a piccolo (es: 0.1) lenta, con a grande (es: 100) veloce
    	double a = 0.3;
        x = n * x / top;
        return (int) Math.round(((max - min) / Math.log(a * n + 1) * Math.log(a * x + 1) + min));
    }
    
    /**
     * Computes the logarithmic rating for the given value x with a specified speed parameter.
     *
     * @param x   The input value
     * @param top The maximum value for x
     * @param a   The speed parameter of the logarithmic function
     * @param min The minimum rating value
     * @param max The maximum rating value
     * @return The computed logarithmic rating
     */
    public int logarithmic(int x, final int top, final double a, final int min, final int max) {
        x = n * x / top;
        return (int) Math.round(((max - min) / Math.log(a * n + 1) * Math.log(a * x + 1) + min));
    }
    
    /**
     * Computes the exponential rating for the given value x
     *
     * @param x   The input value
     * @param top The maximum value for x
     * @param min The minimum rating value
     * @param max The maximum rating value
     * @return The computed exponential rating
     */
    public int exponential(int x, final int top, final int min, final int max) {
        x = n * x / top;
        return (int) Math.round(Math.exp(Math.log(((max - min) + 1) * x) / n + (max - min) - 1));
    }
}