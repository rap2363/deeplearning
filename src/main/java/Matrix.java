import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * A matrix is comprised of an array of an array of real numbers. It is built up using the Builder row by row.
 *
 * One strong assumption is that a matrix's number of column elements is the same in each row. We throw an exception
 * if we try to add a row without the correct number of elements in the Builder.
 */
public final class Matrix {
    private final double[][] matrix;

    private Matrix(final List<double[]> rowList) {
        this.matrix = new double[rowList.size()][rowList.get(0).length];
        for (int i = 0; i < rowList.size(); i++) {
            this.matrix[i] = rowList.get(i);
        }
    }

    /**
     * Obtain the element at (row, col)
     *
     * @param row
     * @param col
     * @return
     */
    public double get(final int row, final int col) {
        Preconditions.checkArgument(row >= 0 && row < matrix.length);
        Preconditions.checkArgument(col >= 0 && col < matrix[0].length);
        return matrix[row][col];
    }

    /**
     * Return the number of rows
     *
     * @return
     */
    public int numRows() {
        return this.matrix.length;
    }

    /**
     * Return the number of columns.
     *
     * @return
     */
    public int numCols() {
        return this.matrix[0].length;
    }

    /**
     * Replace the element at (row, col) with value
     *
     * @param row
     * @param col
     * @return
     */
    public void replace(final int row, final int col, final double value) {
        Preconditions.checkArgument(row >= 0 && row < matrix.length);
        Preconditions.checkArgument(col >= 0 && col < matrix[0].length);
        matrix[row][col] = value;
    }

    /**
     * Return the output of the matrix multiplication M*v.
     *
     * @param v
     * @return
     */
    public Vector dot(final Vector v) {
        Preconditions.checkArgument(matrix[0].length == v.size(),
                "Number of columns in matrix must equal size of vector!");
        final double[] resultValues = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            final Vector rowVector = new Vector(matrix[row]);
            resultValues[row] = Vector.dot(rowVector, v);
        }
        return new Vector(resultValues);
    }

    public static class Builder {
        private List<double[]> rows;

        public Builder() {
            this.rows = new ArrayList<>();
        }

        public Builder addRow(final double... values) {
            this.rows.add(values);
            Preconditions.checkArgument(rows.get(0).length == values.length,
                    "Each added row must have the same number of elements");
            return this;
        }

        public Matrix build() {
            Preconditions.checkArgument(this.rows.size() > 0, "Matrix must have at least one row!");
            return new Matrix(this.rows);
        }
    }
}
