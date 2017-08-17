package org.rparanjpe.deeplearning.math;

import org.junit.Test;

public class TensorTest {
    @Test(expected = IllegalArgumentException.class)
    public void testBuildingTensorWithNoMatricesThrows() {
        new Tensor.Builder().build();
    }
}
