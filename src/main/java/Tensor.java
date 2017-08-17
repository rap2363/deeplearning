import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * A tensor is an array of matrices. There are no other conditions on the sizes of the matrices.
 */
public final class Tensor {
    private final Matrix[] matrices;

    private Tensor(final List<Matrix> matrices) {
        this.matrices = (Matrix[]) matrices.toArray();
    }

    public Matrix get(final int index) {
        Preconditions.checkArgument(index >= 0 && index < this.matrices.length);
        return this.matrices[index];
    }

    public static class Builder {
        private List<Matrix> matrices;

        public Builder() {
            this.matrices = new ArrayList<>();
        }

        public Builder addMatrix(final Matrix m) {
            this.matrices.add(m);
            return this;
        }

        public Tensor build() {
            Preconditions.checkArgument(this.matrices.size() > 0, "A tensor must have at least one matrix");
            return new Tensor(this.matrices);
        }
    }
}