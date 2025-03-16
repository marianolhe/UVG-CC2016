package uvg;

import java.util.List;

/**
 * Clase principal para evaluar expresiones en el intérprete Lisp.
 */
public class Evaluator {
    private Parser parser;
    private Environment globalEnv;

    public Evaluator(Parser parser) {
        this.parser = parser;
        this.globalEnv = new Environment();
    }

    public Object evaluate() {
        Expression expression = parser.parse();
        return expression.evaluate(globalEnv);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        String input = "(+ 1 2 3)";
        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.Tokenize();
        Parser parser = new Parser(tokens);
        Evaluator evaluator = new Evaluator(parser);

        Object result = evaluator.evaluate();
        System.out.println("Resultado: " + result); // Debería imprimir "Resultado: 6"
        
        // Otros ejemplos
        evaluateExample("(setq x 10)");
        evaluateExample("(* x 2)");
        evaluateExample("(defun cuadrado (n) (* n n))");
        evaluateExample("(cuadrado 5)");
        evaluateExample("(if (> 5 3) 'verdadero 'falso)");
        evaluateExample("(cond ((< 5 3) 'menor) ((> 5 3) 'mayor) (t 'igual))");
    }
    
    private static void evaluateExample(String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.Tokenize();
        Parser parser = new Parser(tokens);
        Evaluator evaluator = new Evaluator(parser);
        
        Object result = evaluator.evaluate();
        System.out.println("Expresión: " + input);
        System.out.println("Resultado: " + result);
        System.out.println();
    }
}