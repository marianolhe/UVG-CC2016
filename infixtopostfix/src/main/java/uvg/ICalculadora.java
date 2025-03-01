package uvg; 

/**
 * Interfaz para una calculadora que evalúa expresiones.
 */

public interface ICalculadora {
    
    /**
     * Evalúa una expresión dada.
     * 
     * Precondiciones:
     * - La expresión debe estar en un formato válido.
     * 
     * Postcondiciones:
     * - Devuelve el resultado de la expresión evaluada como un entero.
     * 
     * @param expression La expresión a evaluar.
     * @return El resultado de la evaluación.
     * @throws Exception si la evaluación falla.

     */
    int evaluate(String expression) throws Exception;
}
