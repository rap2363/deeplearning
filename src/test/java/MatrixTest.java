import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void testMatrixMultiplication() {
        final Matrix m = new Matrix.Builder()
                .addRow(1, 2, 3)
                .addRow(4, 5, 6)
                .build();
        final Vector v = m.dot(new Vector(1, -1, 1));
        assertEquals(2, v.size());
        assertEquals(2, v.get(0), 1e-10);
        assertEquals(5, v.get(1), 1e-10);
    }

    @Test
    public void testMatrixMultiplicationWithWrongSizeVectorThrows() {
        boolean exceptionThrown = false;
        try {
            final Matrix m = new Matrix.Builder()
                    .addRow(1, 2, 3)
                    .addRow(4, 5, 6)
                    .build();
            m.dot(new Vector(1, 2));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Number of columns in matrix must equal size of vector!"));
            exceptionThrown = true;
        }
        assertThat(exceptionThrown, is(true));
    }

    @Test
    public void testZeroRowsThrows() {
        boolean exceptionThrown = false;
        try {
            new Matrix.Builder().build();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Matrix must have at least one row!"));
            exceptionThrown = true;
        }
        assertThat(exceptionThrown, is(true));
    }

    @Test
    public void testUnevenRowsThrows() {
        boolean exceptionThrown = false;
        try {
            new Matrix.Builder().addRow(0, 1).addRow(1, 2, 3).build();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Each added row must have the same number of elements"));
            exceptionThrown = true;
        }
        assertThat(exceptionThrown, is(true));
    }
}
