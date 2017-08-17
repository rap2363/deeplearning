package org.rparanjpe.deeplearning;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.rparanjpe.deeplearning.math.Tensor;
import org.rparanjpe.deeplearning.math.Vector;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

/**
 * A NeuralNetwork is built up of multiple layers of neurons with zero or more hidden layers, and an
 * output layer.
 */
@Immutable
public final class NeuralNetwork {
    private final List<List<Neuron>> hiddenLayers;
    private final List<Neuron> outputLayer;
    private final List<List<Neuron>> allLayers;

    private NeuralNetwork(final List<List<Neuron>> hiddenLayers,
                          final List<Neuron> outputLayer) {
        this.hiddenLayers = hiddenLayers;
        this.outputLayer = outputLayer;
        this.allLayers = new ArrayList<>();
        this.allLayers.addAll(hiddenLayers);
        this.allLayers.add(outputLayer);
    }


    /**
     * Evaluate the neural network for a given weight tensor and an input vector.
     *
     * @param weightTensor
     * @param inputs
     * @return
     */
    public Vector evaluate(final Tensor weightTensor,
                           final Vector inputs) {
        Preconditions.checkArgument(checkWeightTensor(weightTensor, inputs));
        Vector result = inputs;
        for (int i = 0; i < this.allLayers.size(); i++) {
            final List<Neuron> layer = this.allLayers.get(i);
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
    protected boolean checkWeightTensor(final Tensor weightTensor,
                                        final Vector inputs) {
        if (weightTensor.size() != this.allLayers.size()) {
            return false;
        }

        if (weightTensor.get(0).numCols() != inputs.size()) {
            return false;
        }

        if (weightTensor.get(0).numRows() != this.allLayers.get(0).size()) {
            return false;
        }

        for (int l = 1; l < this.allLayers.size(); l++) {
            if (weightTensor.get(l).numCols() != this.allLayers.get(l - 1).size()) {
                return false;
            }

            if (weightTensor.get(l).numRows() != this.allLayers.get(l).size()) {
                return false;
            }
        }

        return true;
    }

    public static class Builder {
        private List<List<Neuron>> hiddenLayers;
        private List<Neuron> outputLayer;

        public Builder() {
            this.hiddenLayers = new ArrayList<>();
        }

        public Builder addHiddenLayer(final List<Neuron> hiddenLayer) {
            this.hiddenLayers.add(hiddenLayer);
            return this;
        }

        public Builder setOutputLayer(final List<Neuron> outputLayer) {
            this.outputLayer = outputLayer;
            return this;
        }

        public NeuralNetwork build() {
            Preconditions.checkNotNull(this.outputLayer);
            Preconditions.checkArgument(this.outputLayer.size() > 0);
            return new NeuralNetwork(this.hiddenLayers, this.outputLayer);
        }
    }
}
