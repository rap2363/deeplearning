import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    @Test
    public void testLinearFunction() {
        final RealFunction linearFunction = new LinearFunction(new Vector(1, -1, 2));
        assertEquals(9, linearFunction.evaluateAt(new Vector(3, 4, 5)), 1e-10);
    }

    @Test
    public void testSigmoidFunction() {
        final ActivationFunction sigmoidFunction = new SigmoidFunction(new Vector(1, 2));
        assertEquals(1 / (1 + Math.exp(-3)), sigmoidFunction.evaluateAt(new Vector(1, 1)), 1e-10);
    }
}
