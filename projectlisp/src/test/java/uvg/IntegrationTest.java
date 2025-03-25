package uvg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private Environment env;
    
    @BeforeEach
    void setUp() {
        env = new Environment();
    }
    
    private Object evaluateExpression(String expr) {
        Tokenizer tokenizer = new Tokenizer(expr);
        Parser parser = new Parser(tokenizer.Tokenize());
        Expression expression = parser.parse();
        return expression.evaluate(env);
    }

    @Test
    void testIntegrationArithmetic() {
        assertEquals(6, evaluateExpression("(+ 1 2 3)"));
        assertEquals(5, evaluateExpression("(- 10 3 2)"));
        assertEquals(24, evaluateExpression("(* 2 3 4)"));
        assertEquals(5, evaluateExpression("(/ 20 2 2)"));
    }
    
    @Test
    void testIntegrationNestedArithmetic() {
        assertEquals(20, evaluateExpression("(* 4 (+ 2 3))"));
        assertEquals(14, evaluateExpression("(+ 2 (* 3 4))"));
    }
    
    @Test
    void testIntegrationVariables() {
        evaluateExpression("(setq x 10)");
        assertEquals(10, evaluateExpression("x"));
        
        evaluateExpression("(setq y (+ x 5))");
        assertEquals(15, evaluateExpression("y"));
    }
    
    @Test
    void testIntegrationConditionals() {
        assertEquals(true, evaluateExpression("(> 5 3)"));
        assertEquals(false, evaluateExpression("(< 5 3)"));
        
        assertEquals(10, evaluateExpression("(if (> 5 3) 10 20)"));
        assertEquals(20, evaluateExpression("(if (< 5 3) 10 20)"));
    }
    
    @Test
    void testIntegrationFunctions() {
        // Definir función
        evaluateExpression("(defun square (n) (* n n))");
        
        // Utilizar función
        assertEquals(25, evaluateExpression("(square 5)"));
        
        // Función con múltiples argumentos
        evaluateExpression("(defun sum-of-squares (a b) (+ (square a) (square b)))");
        assertEquals(41, evaluateExpression("(sum-of-squares 4 5)"));
    }
    
    @Test
    void testIntegrationRecursion() {
        evaluateExpression("(defun factorial (n) (if (= n 0) 1 (* n (factorial (- n 1)))))");
        assertEquals(120, evaluateExpression("(factorial 5)"));
        
        evaluateExpression("(defun fibonacci (n) (if (< n 2) n (+ (fibonacci (- n 1)) (fibonacci (- n 2)))))");
        assertEquals(5, evaluateExpression("(fibonacci 5)"));
    }
    
    @Test
    void testIntegrationComplexProgram() {
        // Programa más complejo con múltiples definiciones y cálculos
        evaluateExpression("(setq pi 3.14159)");
        evaluateExpression("(defun circle-area (radius) (* pi (* radius radius)))");
        evaluateExpression("(defun cylinder-volume (radius height) (* height (circle-area radius)))");
        
        // Verificar resultado del cálculo complejo
        Object result = evaluateExpression("(cylinder-volume 3 10)");
        assertTrue(result instanceof Number);
        
        double numericResult = ((Number) result).doubleValue();
        assertTrue(numericResult > 282 && numericResult < 283, 
                  "El volumen del cilindro debe ser aproximadamente 282.7431");
    }
}