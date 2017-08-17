import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;

/**
 * A CompositeFunction is composed of multiple RealFunctions and one VectorFunction. Specifically:
 * RealFunction f = new CompositeFunction(f1, f2, f3);
 *
 * implies f(x) = f3(f2(f1(x)))
 *
 */
@Immutable
public class CompositeVectorFunction implements VectorFunction {
    private final VectorFunction vectorFunction;
    private final RealFunction[] realFunctions;

    public CompositeVectorFunction(final VectorFunction vectorFunction,
                                   final RealFunction... realFunctions) {
        this.vectorFunction = Preconditions.checkNotNull(vectorFunction);
        this.realFunctions = Preconditions.checkNotNull(realFunctions);
    }

    /**
     * Evaluate the vector by applying the vectorFunction, then the realFunctions in the order they were provided.
     *
     * @param vector
     * @return
     */
    public double evaluateAt(final Vector vector) {
        double value = this.vectorFunction.evaluateAt(vector);
        for (final RealFunction realFunction : realFunctions) {
            value = realFunction.evaluateAt(value);
        }
        return value;
    }
}
