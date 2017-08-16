import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {

    @Test
    public void testSumOfSquaresFunction() {
        final RealFunction sumOfSquares = new SumOfSquares();
        assertEquals(30, sumOfSquares.evaluateAt(new Vector(1, 2, 3, 4)), 1e-10);
    }

    @Test
    public void testLinearFunction() {
        final RealFunction linearFunction = new LinearFunction(new Vector(1, -1, 2));
        assertEquals(9, linearFunction.evaluateAt(new Vector(3, 4, 5)), 1e-10);
    }

    private class SumOfSquares implements ActivationFunction {

        @Override
        public double evaluateAt(final Vector v) {
            double sum = 0;
            for (double value : v) {
                sum += value * value;
            }
            return sum;
        }
    }
}
