package org.rparanjpe.deeplearning.math;

import org.junit.Test;
import org.rparanjpe.deeplearning.math.Tensor;

public class TensorTest {
    @Test(expected = IllegalArgumentException.class)
    public void testBuildingTensorWithNoMatricesThrows() {
        new Tensor.Builder().build();
    }
}
