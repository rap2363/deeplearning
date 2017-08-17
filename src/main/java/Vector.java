import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;
import java.util.Iterator;

/**
 * A class to represent an N-dimensional vector of real values (x1, ..., xM).
 */
@Immutable
public final class Vector implements Iterable<Double> {
    private final double[] values;

    private Vector() {
        // This shouldn't be called
        throw new UnsupportedOperationException();
    }

    public Vector(final double... values) {
        this.values = values;
    }

    /**
     * Return the element of this vector at index
     *
     * @param index
     * @return
     */
    public double get(final int index) {
        Preconditions.checkArgument(index < this.size());
        return this.values[index];
    }

    /**
     * Return the size of this vector
     *
     * @return
     */
    public int size() {
        return this.values.length;
    }

    /**
     * Return the element-by-element addition of two vectors
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Vector add(final Vector v1, final Vector v2) {
        Preconditions.checkArgument(v1.size() == v2.size());
        final double[] summedValues = new double[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            summedValues[i] = v1.get(i) + v2.get(i);
        }

        return new Vector(summedValues);
    }

    /**
     * Concatenate two vectors together to create a new vector with v1.size() + v2.size() total elements with the order
     * v = [v1 | v2]
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Vector concatenate(final Vector v1, final Vector v2) {
        final double[] concatenatedValues = new double[v1.size() + v2.size()];
        for (int i = 0; i < v1.size(); i++) {
            concatenatedValues[i] = v1.get(i);
        }
        for (int i = 0; i < v2.size(); i++) {
            concatenatedValues[i + v1.size()] = v2.get(i);
        }

        return new Vector(concatenatedValues);
    }

    /**
     * Syntactic sugar
     *
     * @param vector
     * @param value
     * @return
     */
    public static Vector concatenate(final Vector vector, final double value) {
        return Vector.concatenate(vector, new Vector(value));
    }

    /**
     * Syntactic sugar
     *
     * @param vector
     * @param value
     * @return
     */
    public static Vector concatenate(final double value, final Vector vector) {
        return Vector.concatenate(new Vector(value), vector);
    }

    /**
     * Return the dot product of two vectors
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double dot(final Vector v1, final Vector v2) {
        Preconditions.checkArgument(v1.size() == v2.size());
        double totalSum = 0d;
        for (int i = 0; i < v1.size(); i++) {
            totalSum += v1.get(i) * v2.get(i);
        }
        return totalSum;
    }

    @Override
    public Iterator<Double> iterator() {
        return new VectorIterator();
    }

    private class VectorIterator implements Iterator<Double> {
        int index;

        public VectorIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < Vector.this.size();
        }

        @Override
        public Double next() {
            return Vector.this.get(index++);
        }
    }
}
