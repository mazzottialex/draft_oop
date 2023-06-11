package utils;
import java.io.Serializable;

/**
 * A standard generic Pair<X, Y> class that represents a pair of values.
 *
 * @param <X> the type of the first value in the pair
 * @param <Y> the type of the second value in the pair
 */
public final class Pair<X, Y> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final X x;
    private final Y y;

    /**
     * Constructs a Pair object with the specified values.
     *
     * @param x the first value of the pair
     * @param y the second value of the pair
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first value of the pair.
     *
     * @return the first value
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the second value of the pair.
     *
     * @return the second value
     */
    public Y getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
