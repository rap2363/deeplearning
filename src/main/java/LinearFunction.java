import javax.annotation.concurrent.Immutable;

/**
 * Implement a simple linear function with weights s.t. f(x) = w^x
 */
@Immutable
public class LinearFunction implements RealFunction {
    private final Vector weights;

    public LinearFunction(final Vector weights) {
        this.weights = weights;
    }

    @Override
    public double evaluateAt(final Vector v) {
        return Vector.dot(weights, v);
    }
}
