package uvg;

/**
 * Una expresión que representa un símbolo/identificador.
 */
public class SymbolExpression extends Expression {
    private final String name;

    public SymbolExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(Environment env) {
        return env.lookup(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}