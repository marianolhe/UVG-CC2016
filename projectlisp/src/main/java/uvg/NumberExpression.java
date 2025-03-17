package uvg;

/**
 * Representa una expresión numérica que almacena un valor entero.
 * Proporciona métodos para evaluarlo y convertirlo en una cadena.
 */
public class NumberExpression extends Expression {
    private final int value;

    /**
     * Constructor que inicializa la expresión con un valor numérico.
     * 
     * @param value El valor numérico de la expresión.
     */
    public NumberExpression(int value) {
        this.value = value;
    }

    /**
     * Evalúa la expresión numérica en el entorno proporcionado, retornando su valor.
     * 
     * @param env El entorno en el que se evalúa la expresión.
     * @return El valor numérico de la expresión.
     */    
    @Override
    public Object evaluate(Environment env) {
        return value;
    }
    
    /**
     * Representa la expresión numérica como una cadena.
     * 
     * @return La representación en cadena del valor numérico.
     */    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}