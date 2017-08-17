package org.rparanjpe.deeplearning.functions;

import com.google.common.base.Preconditions;
import org.rparanjpe.deeplearning.math.Vector;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;
import java.util.function.Function;

/**
 * A CompositeFunction is composed of multiple Function<Double, Double> (the real org.rparanjpe.deeplearning.functions f1, f2, ...) and one
 * Function<org.rparanjpe.deeplearning.math.Vector, Double> (the vector function v).
 *
 * The order of application is: f3(f2(f1(v(x))))
 *
 */
@Immutable
public class CompositeVectorFunction implements Function<Vector, Double> {
    private final Function<Vector, Double> vectorFunction;
    private final Function<Double, Double>[] realFunctions;

    public CompositeVectorFunction(final Function<Vector, Double> vectorFunction,
                                   final Function<Double, Double>... realFunctions) {
        this.vectorFunction = Preconditions.checkNotNull(vectorFunction);
        this.realFunctions = Preconditions.checkNotNull(realFunctions);
    }

    /**
     * Evaluate the vector by applying the vectorFunction, then the realFunctions in the order they were provided.
     *
     * @param vector
     * @return
     */
    public Double apply(final Vector vector) {
        return Arrays.stream(realFunctions)
                .reduce(Function.identity(), Function::andThen)
                .apply(this.vectorFunction.apply(vector));
    }
}
