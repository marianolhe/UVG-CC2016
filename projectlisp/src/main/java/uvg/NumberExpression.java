package uvg;

/**
 * Expresión que representa un valor numérico.
 */
public class NumberExpression extends Expression {
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}