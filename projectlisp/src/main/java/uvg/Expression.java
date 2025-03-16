package uvg;

/**
 * Clase abstracta base para todas las expresiones.
 */
public abstract class Expression {
    /**
     * Evalúa la expresión en el entorno dado y devuelve el resultado.
     * 
     * @param env El entorno en el que evaluar la expresión
     * @return El resultado de la evaluación
     */
    public abstract Object evaluate(Environment env);
}