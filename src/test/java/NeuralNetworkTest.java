import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class NeuralNetworkTest {
    @Test
    public void testSparseAutoEncoderNetworkEvaluation() {
        final NeuralNetwork autoEncoderNetwork = new NeuralNetwork.Builder()
                .addHiddenLayer(Neuron.createLayerWithActivationFunction(2, RealFunction.IDENTITY))
                .setOutputLayer(Neuron.createLayerWithActivationFunction(3, SigmoidFunction.of()))
                .build();
        final int a = 1;
        final int b = 5;
        final int c = 3;
        final int d = 4;
        final int e = 4;
        final int f = -2;
        final int g = 2;
        final int h = 3;
        final int i = 2;
        final int j = -4;
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
        assertEquals(SigmoidFunction.of().evaluateAt((a + b) * e + (c + d) * f), outputs.get(0), 1e-10);
        assertEquals(SigmoidFunction.of().evaluateAt((a + b) * g + (c + d) * h), outputs.get(1), 1e-10);
        assertEquals(SigmoidFunction.of().evaluateAt((a + b) * i + (c + d) * j), outputs.get(2), 1e-10);
    }
}
