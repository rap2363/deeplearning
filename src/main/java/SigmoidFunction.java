import javax.annotation.concurrent.Immutable;

/**
 * The sigmoid function parameterized by w: f(x) = 1 / (1 + exp(-w^x))
 */
@Immutable
public final class SigmoidFunction implements ActivationFunction {
    private final Vector weights;
    public SigmoidFunction(final Vector weights) {
        this.weights = weights;
    }

    @Override
    public double evaluateAt(final Vector v) {
        return 1 / (1 + Math.exp(-Vector.dot(weights, v)));
    }
}
