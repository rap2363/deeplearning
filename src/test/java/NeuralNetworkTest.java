import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class NeuralNetworkTest {
    @Test
    public void testSparseAutoEncoderNetworkEvaluation() {
        final NeuralNetwork autoEncoderNetwork = new NeuralNetwork.Builder()
                .addHiddenLayer(Neuron.createLayerWithActivationFunction(2, Function.identity()))
                .setOutputLayer(Neuron.createLayerWithActivationFunction(3, SigmoidFunction.of()))
                .build();
        final double a = 1;
        final double b = 5;
        final double c = 3;
        final double d = 4;
        final double e = 4;
        final double f = -2;
        final double g = 2;
        final double h = 3;
        final double i = 2;
        final double j = -4;
        final Tensor weightTensor = new Tensor.Builder()
                .addMatrix(new Matrix.Builder()
                        .addRow(a, b)
                        .addRow(c, d)
                        .build())
                .addMatrix(new Matrix.Builder()
                        .addRow(e, f)
                        .addRow(g, h)
                        .addRow(i, j)
                        .build())
                .build();
        final Vector inputs = new Vector(1, 1);
        final Vector outputs = autoEncoderNetwork.evaluate(weightTensor, inputs);
        assertThat(outputs.size(), is(3));
        assertEquals(SigmoidFunction.of().apply((a + b) * e + (c + d) * f), outputs.get(0), 1e-10);
        assertEquals(SigmoidFunction.of().apply((a + b) * g + (c + d) * h), outputs.get(1), 1e-10);
        assertEquals(SigmoidFunction.of().apply((a + b) * i + (c + d) * j), outputs.get(2), 1e-10);
    }
}
