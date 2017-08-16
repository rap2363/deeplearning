import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * A class to represent an N-dimensional vector of real values (x1, ..., xM).
 */
@Immutable
public class Vector {
    private final double[] values;

    public Vector() {
        this.values = new double[0];
    }

    public Vector(final double... values) {
        this.values = values;
    }

    public double get(final int index) {
        Preconditions.checkArgument(index < this.size());
        return this.values[index];
    }

    public int size() {
        return this.values.length;
    }

    public static Vector add(final Vector v1, final Vector v2) {
        Preconditions.checkArgument(v1.size() == v2.size());
        final double[] summedValues = new double[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            summedValues[i] = v1.get(i) + v2.get(i);
        }

        return new Vector(summedValues);
    }
}
