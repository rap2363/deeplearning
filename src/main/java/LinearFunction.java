import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

/**
 * Implement a simple linear function with weights s.t. f(x) = w^x
 */
@Immutable
public class LinearFunction implements Function<Vector, Double> {
    private final Vector weights;

    public LinearFunction(final Vector weights) {
        this.weights = weights;
    }

    @Override
    public Double apply(final Vector v) {
        return Vector.dot(weights, v);
    }
}
