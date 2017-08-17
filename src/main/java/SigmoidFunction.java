import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

/**
 * The sigmoid function: f(a) = 1 / (1 + exp(-a))
 * This function is stateless,immutable, and needs no members, so it is instantiated as a singleton
 */
@Immutable
public final class SigmoidFunction implements Function<Double, Double> {
    private static final SigmoidFunction INSTANCE = new SigmoidFunction();

    private SigmoidFunction() {
        // This is only called once by the static instance.
    }

    public static SigmoidFunction of() {
        return INSTANCE;
    }

    @Override
    public Double apply(final Double value) {
        return 1 / (1 + Math.exp(-value));
    }
}
