/**
 * A function which maps the real numbers to the real numbers. Specifically used as an activation function for
 * nodes.
 */
public interface RealFunction {
    RealFunction IDENTITY = new RealFunction() {
        @Override
        public double evaluateAt(double value) {
            return value;
        }
    };

    RealFunction UNITY = new RealFunction() {
        @Override
        public double evaluateAt(double value) {
            return 1d;
        }
    };

    double evaluateAt(double value);
}
