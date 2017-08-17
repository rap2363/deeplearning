/**
 * One-line interface to capture the behavior of a function which evaluates a vector and returns a real number. By
 * default, evaluateAt(double) is evaluated as a 1-D vector.
 */
public interface RealFunction {
    double evaluateAt(Vector vector);

    default double evaluateAt(double value) {
        return this.evaluateAt(new Vector(value));
    }
}
