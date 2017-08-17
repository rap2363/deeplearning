package org.rparanjpe.deeplearning;

import com.google.common.base.Preconditions;
import org.rparanjpe.deeplearning.math.Vector;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * One of the building blocks of a neural network, a Neuron is an immutable structure correlated with an activation
 * function and can be evaluated for some input weights and an input vector.
 */
@Immutable
public final class Neuron {
    public static final Neuron BIAS = new Neuron((Double v) -> 1d);

    private final Function<Double, Double> activationFunction;

    public Neuron(final Function<Double, Double> function) {
        this.activationFunction = function;
    }

    public double evaluate(final double input) {
        return this.activationFunction.apply(input);
    }

    public static List<Neuron> createLayerWithActivationFunction(final int size,
                                                                 final Function<Double, Double> function) {
        final List<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            neurons.add(new Neuron(function));
        }
        return neurons;
    }

    /**
     * Helper function to evaluate a layer of neurons element-wise.
     *
     * @param layer
     * @param inputs
     * @return
     */
    public static Vector evaluateLayer(final List<Neuron> layer,
                                       final Vector inputs) {
        Preconditions.checkArgument(layer.size() == inputs.size());

        final double[] results = new double[layer.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = layer.get(i).evaluate(inputs.get(i));
        }
        return new Vector(results);
    }

}
