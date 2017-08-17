import com.google.common.base.Preconditions;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

/**
 * One of the building blocks of a neural network, a Neuron is an immutable structure correlated with an activation
 * function and can be evaluated for some input weights and an input vector.
 */
@Immutable
public final class Neuron {
    public static final Neuron BIAS = new Neuron(RealFunction.UNITY);

    private final RealFunction activationFunction;

    public Neuron(final RealFunction function) {
        this.activationFunction = function;
    }

    public double evaluate(final double input) {
        return this.activationFunction.evaluateAt(input);
    }

    public static List<Neuron> createLayerWithActivationFunction(final int size,
                                                                 final RealFunction function) {
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
