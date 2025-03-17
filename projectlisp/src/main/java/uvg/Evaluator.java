package uvg;

import java.util.List;

/**
 * Clase principal para evaluar expresiones en el intérprete Lisp.
 */
public class Evaluator {
    private Parser parser; // El analizador que convierte el texto en una lista de tokens
    private Environment globalEnv; // El entorno global donde se guardan las variables y funciones

    /**
     * Constructor de la clase Evaluator.
     * @param parser El analizador (parser) que se usa para convertir el código en tokens.
     */    
    public Evaluator(Parser parser) {
        this.parser = parser;
        this.globalEnv = new Environment();
    }

    /**
     * Evalúa (ejecuta) la expresión que se obtiene al analizar el código de entrada.
     * @return El resultado de evaluar la expresión.
     */
    public Object evaluate() {
        // Convierte el código en una expresión y la evalúa en el entorno global
        Expression expression = parser.parse();
        return expression.evaluate(globalEnv);
    }

    /**
     * Método principal que se ejecuta al iniciar el programa.
     * Aquí se muestran algunos ejemplos de expresiones que se pueden evaluar.
     */    
    public static void main(String[] args) {
        // Ejemplo de uso del evaluador con una expresión simple
        String input = "(+ 1 2 3)";
        Tokenizer tokenizer = new Tokenizer(input); // Convierte el texto en tokens
        List<Token> tokens = tokenizer.Tokenize(); // Obtiene los tokens
        Parser parser = new Parser(tokens); // Analiza los tokens
        Evaluator evaluator = new Evaluator(parser); // Crea el evaluador

        // Evalúa la expresión y muestra el resultado
        Object result = evaluator.evaluate();
        System.out.println("Resultado: " + result); // Debería imprimir "Resultado: 6"
        
        // Ejemplos adicionales de uso del evaluador
        evaluateExample("(setq x 10)");
        evaluateExample("(* x 2)");
        evaluateExample("(defun cuadrado (n) (* n n))");
        evaluateExample("(cuadrado 5)");
        evaluateExample("(if (> 5 3) 'verdadero 'falso)");
        evaluateExample("(cond ((< 5 3) 'menor) ((> 5 3) 'mayor) (t 'igual))");
    }
      
    /**
     * Evalúa una expresión y muestra el resultado de manera más clara.
     * @param input El código que se quiere evaluar.
     */    
    private static void evaluateExample(String input) {
        // Convierte el código en tokens, luego lo analiza y lo evalúa
        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.Tokenize();
        Parser parser = new Parser(tokens);
        Evaluator evaluator = new Evaluator(parser);
        
        // Evalúa la expresión y muestra el resultado
        Object result = evaluator.evaluate();
        System.out.println("Expresión: " + input);
        System.out.println("Resultado: " + result);
        System.out.println();
    }
}