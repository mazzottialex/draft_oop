package utils;
import java.io.Serializable;

/**
 * A standard generic Triple<X, Y, Z>, with getters, hashCode, equals, and toString well implemented.
 * 
 * @param <X> the type of the first value in the pair
 * @param <Y> the type of the second value in the pair
 * @param <Z> the type of the third value in the pair
 */
public class Triple<X, Y, Z> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final X x;
    private final Y y;
    private final Z z;

    /**
     * Constructs a Triple object with the specified values for x, y, and z.
     *
     * @param x the value of the first element
     * @param y the value of the second element
     * @param z the value of the third element
     */
    public Triple(final X x, final Y y, final Z z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns the value of the first element.
     *
     * @return the value of the first element
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the value of the second element.
     *
     * @return the value of the second element
     */
    public Y getY() {
        return y;
    }

    /**
     * Returns the value of the third element.
     *
     * @return the value of the third element
     */
    public Z getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        result = prime * result + ((z == null) ? 0 : z.hashCode());
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
        Triple other = (Triple) obj;
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
        if (z == null) {
            if (other.z != null) {
                return false;
            }
        } else if (!z.equals(other.z)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Triple [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
