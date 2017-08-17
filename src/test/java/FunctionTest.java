import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    @Test
    public void testLinearFunction() {
        final VectorFunction linearFunction = new LinearFunction(new Vector(1, -1, 2));
        assertEquals(9, linearFunction.evaluateAt(new Vector(3, 4, 5)), 1e-10);
    }

    @Test
    public void testSigmoidFunction() {
        final RealFunction sigmoidFunction = SigmoidFunction.of();
        final double value = 3.4;
        assertEquals(1 / (1 + Math.exp(-value)), sigmoidFunction.evaluateAt(value), 1e-10);
    }
}
