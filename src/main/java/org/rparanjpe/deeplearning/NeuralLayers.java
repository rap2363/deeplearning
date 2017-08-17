package org.rparanjpe.deeplearning;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.rparanjpe.deeplearning.math.Tensor;
import org.rparanjpe.deeplearning.math.Vector;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

/**
 * NeuralLayers are built up of multiple layers of neurons with zero or more hidden layers, and an
 * output layer.
 */
@Immutable
public final class NeuralLayers {
    private final List<List<Neuron>> hiddenLayers;
    private final List<Neuron> outputLayer;

    private NeuralLayers(final List<List<Neuron>> hiddenLayers,
                          final List<Neuron> outputLayer) {
        this.hiddenLayers = hiddenLayers;
        this.outputLayer = outputLayer;
    }

    /**
     * Return the number of layers (i.e. the number of hidden layers + the one output layer).
     *
     * @return
     */
    public int size() {
        return this.hiddenLayers.size() + 1;
    }

    /**
     * Retrieve a neural layer by an index.
     *
     * @param index
     * @return
     */
    public List<Neuron> get(final int index) {
        Preconditions.checkArgument(index >= 0 && index <= this.hiddenLayers.size());
        if (index < this.hiddenLayers.size()) {
            return this.hiddenLayers.get(index);
        } else {
            return this.outputLayer;
        }
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

        public NeuralLayers build() {
            Preconditions.checkNotNull(this.outputLayer);
            Preconditions.checkArgument(this.outputLayer.size() > 0);
            return new NeuralLayers(this.hiddenLayers, this.outputLayer);
        }
    }
}
