import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
    @Test
    public void testLinearFunction() {
        final Function<Vector, Double> linearFunction = new LinearFunction(new Vector(1, -1, 2));
        assertEquals(9, linearFunction.apply(new Vector(3, 4, 5)), 1e-10);
    }

    @Test
    public void testSigmoidFunction() {
        final Function<Double, Double> sigmoidFunction = SigmoidFunction.of();
        final double value = 3.4;
        assertEquals(1 / (1 + Math.exp(-value)), sigmoidFunction.apply(value), 1e-10);
    }

    @Test
    public void testCompositeVectorFunction() {
        final Function<Vector, Double> compositeVectorFunction = new CompositeVectorFunction(
                new LinearFunction(new Vector(1, 1)),
                (Double value) -> value*value,
                (Double value) -> value + 1,
                (Double value) -> value * 2);

        assertEquals(34, compositeVectorFunction.apply(new Vector(-1, 5)), 1e-10);
    }
}
