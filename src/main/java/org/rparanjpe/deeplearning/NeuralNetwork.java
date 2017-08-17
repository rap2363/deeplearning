package org.rparanjpe.deeplearning;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.rparanjpe.deeplearning.math.Tensor;
import org.rparanjpe.deeplearning.math.Vector;

import java.util.List;
import java.util.function.Function;

/**
 * A NeuralNetwork is composed of a weight tensor and a NeuralLayers structure. It can be thought of as a Vector
 * function (mapping a vector of inputs to a vector of outputs), by using the weight tensor and neuron activation
 * functions.
 */
@Immutable
public class NeuralNetwork implements Function<Vector, Vector> {
    private final NeuralLayers neuralLayers;
    private final Tensor weightTensor;

    public NeuralNetwork(final NeuralLayers neuralLayers,
                         final Tensor weightTensor) {
        this.neuralLayers = Preconditions.checkNotNull(neuralLayers);
        this.weightTensor = Preconditions.checkNotNull(weightTensor);
        Preconditions.checkArgument(checkWeightTensor(weightTensor));
    }

    /**
     * Evaluate the neural network for an input vector
     * @param inputs
     * @return
     */
    @Override
    public Vector apply(final Vector inputs) {
        Preconditions.checkArgument(weightTensor.get(0).numCols() == inputs.size());
        Vector result = inputs;
        for (int i = 0; i < this.neuralLayers.size(); i++) {
            final List<Neuron> layer = this.neuralLayers.get(i);
            result = Neuron.evaluateLayer(layer, weightTensor.get(i).dot(result));
        }
        return result;
    }

    /**
     * A helper function to ensure the weightTensor is the correct dimensions for evaluating this neural network.
     *
     * @param weightTensor
     * @return
     */
    @VisibleForTesting
    protected boolean checkWeightTensor(final Tensor weightTensor) {
        if (weightTensor.size() != this.neuralLayers.size()) {
            return false;
        }

        if (weightTensor.get(0).numRows() != this.neuralLayers.get(0).size()) {
            return false;
        }

        for (int l = 1; l < this.neuralLayers.size(); l++) {
            if (weightTensor.get(l).numCols() != this.neuralLayers.get(l - 1).size()) {
                return false;
            }

            if (weightTensor.get(l).numRows() != this.neuralLayers.get(l).size()) {
                return false;
            }
        }

        return true;
    }
}
