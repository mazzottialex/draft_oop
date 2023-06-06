package utils;
import java.io.Serializable;
/*
 * A standard generic Triplr<X,Y,Z>, with getters, hashCode, equals, and toString well implemented. 
 */
public class Triple<X,Y,Z> implements Serializable {
    private final X x;
    private final Y y;
    private final Z z;
    public Triple(X x, Y y, Z z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public X getX() {
        return x;
    }
    public Y getY() {
        return y;
    }
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triple other = (Triple) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        if (z == null) {
            if (other.z != null)
                return false;
        } else if (!z.equals(other.z))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Triple [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
