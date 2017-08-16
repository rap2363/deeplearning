import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTest {
    @Test
    public void testVectorCreation() {
        final Vector v = new Vector(1, 3, 5);
        assertEquals(1, v.get(0), 1e-10);
        assertEquals(3, v.get(1), 1e-10);
        assertEquals(5, v.get(2), 1e-10);
    }

    @Test
    public void testVectorAddition() {
        final Vector v1 = new Vector(1, 2, 3);
        final Vector v2 = new Vector(5, 6, 7);
        final Vector v = Vector.add(v1, v2);

        assertEquals(6, v.get(0), 1e-10);
        assertEquals(8, v.get(1), 1e-10);
        assertEquals(10, v.get(2), 1e-10);
    }

    @Test
    public void testVectorDotProduct() {
        final Vector v1 = new Vector(1, -3, 5);
        final Vector v2 = new Vector(-1, -2, -1);

        assertEquals(0, Vector.dot(v1, v2), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingTwoDifferentlySizedVectorsThrows() {
        Vector.add(new Vector(1, 2), new Vector(3, 2, 4));
    }
}
