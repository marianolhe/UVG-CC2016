package uvg;

/**
 * Representa una expresión simbólica en el lenguaje.
 * Un símbolo puede ser un identificador, una variable o un operador que 
 * hace referencia a un valor dentro del entorno de ejecución.
 */
public class SymbolExpression extends Expression {
    private final String name; // Nombre del símbolo


    /**
     * Constructor de SymbolExpression.
     * @param name Nombre del símbolo representado por esta expresión.
     */
    public SymbolExpression(String name) {
        this.name = name;
    }


   /**
     * Obtiene el nombre del símbolo.
     * @return El nombre del símbolo como una cadena de texto.
     */    
    public String getName() {
        return name;
    }

       /**
     * Evalúa la expresión buscando el valor del símbolo en el entorno dado.
     * @param env El entorno donde se busca el valor del símbolo.
     * @return El valor asociado al símbolo en el entorno.
     */
    @Override
    public Object evaluate(Environment env) {
        return env.lookup(name);
    }

    /**
     * Devuelve una representación en cadena del símbolo.
     * @return El nombre del símbolo como texto.
     */    
    @Override
    public String toString() {
        return name;
    }
}