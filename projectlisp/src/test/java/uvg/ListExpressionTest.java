package uvg;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class ListExpressionTest {

    @Test
    void testEvaluateArithmetic() {
        Environment env = new Environment();
        
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("+"),
            new NumberExpression(1),
            new NumberExpression(2),
            new NumberExpression(3)
        ));
        
        assertEquals(6, expr.evaluate(env));
    }
    
    @Test
    void testEvaluateSetq() {
        Environment env = new Environment();
        
        ListExpression setq = new ListExpression(Arrays.asList(
            new SymbolExpression("setq"),
            new SymbolExpression("x"),
            new NumberExpression(10)
        ));
        
        assertEquals(10, setq.evaluate(env));
        assertEquals(10, env.lookup("x"));
    }
    
    @Test
    void testEvaluateIf() {
        Environment env = new Environment();
        
        ListExpression ifTrue = new ListExpression(Arrays.asList(
            new SymbolExpression("if"),
            new ListExpression(Arrays.asList(
                new SymbolExpression(">"),
                new NumberExpression(5),
                new NumberExpression(3)
            )),
            new NumberExpression(1),
            new NumberExpression(0)
        ));
        
        assertEquals(1, ifTrue.evaluate(env));
        
        ListExpression ifFalse = new ListExpression(Arrays.asList(
            new SymbolExpression("if"),
            new ListExpression(Arrays.asList(
                new SymbolExpression("<"),
                new NumberExpression(5),
                new NumberExpression(3)
            )),
            new NumberExpression(1),
            new NumberExpression(0)
        ));
        
        assertEquals(0, ifFalse.evaluate(env));
    }
    
    @Test
    void testEvaluateCond() {
        Environment env = new Environment();
        
        ListExpression cond = new ListExpression(Arrays.asList(
            new SymbolExpression("cond"),
            new ListExpression(Arrays.asList(
                new ListExpression(Arrays.asList(
                    new SymbolExpression("<"),
                    new NumberExpression(5),
                    new NumberExpression(3)
                )),
                new NumberExpression(1)
            )),
            new ListExpression(Arrays.asList(
                new ListExpression(Arrays.asList(
                    new SymbolExpression(">"),
                    new NumberExpression(5),
                    new NumberExpression(3)
                )),
                new NumberExpression(2)
            )),
            new ListExpression(Arrays.asList(
                new SymbolExpression("t"),
                new NumberExpression(3)
            ))
        ));
        
        assertEquals(2, cond.evaluate(env));
    }
    
    @Test
    void testEvaluateDefun() {
        Environment env = new Environment();
        
        // Define una función que duplica su argumento
        ListExpression defun = new ListExpression(Arrays.asList(
            new SymbolExpression("defun"),
            new SymbolExpression("doble"),
            new ListExpression(Arrays.asList(
                new SymbolExpression("n")
            )),
            new ListExpression(Arrays.asList(
                new SymbolExpression("*"),
                new SymbolExpression("n"),
                new NumberExpression(2)
            ))
        ));
        
        defun.evaluate(env);
        
        // Ahora usamos la función
        ListExpression call = new ListExpression(Arrays.asList(
            new SymbolExpression("doble"),
            new NumberExpression(5)
        ));
        
        assertEquals(10, call.evaluate(env));
    }
    
    @Test
    void testEvaluateRecursiveFunction() {
        Environment env = new Environment();
        
        // Definir función factorial
        ListExpression defun = new ListExpression(Arrays.asList(
            new SymbolExpression("defun"),
            new SymbolExpression("factorial"),
            new ListExpression(Arrays.asList(
                new SymbolExpression("n")
            )),
            new ListExpression(Arrays.asList(
                new SymbolExpression("if"),
                new ListExpression(Arrays.asList(
                    new SymbolExpression("="),
                    new SymbolExpression("n"),
                    new NumberExpression(0)
                )),
                new NumberExpression(1),
                new ListExpression(Arrays.asList(
                    new SymbolExpression("*"),
                    new SymbolExpression("n"),
                    new ListExpression(Arrays.asList(
                        new SymbolExpression("factorial"),
                        new ListExpression(Arrays.asList(
                            new SymbolExpression("-"),
                            new SymbolExpression("n"),
                            new NumberExpression(1)
                        ))
                    ))
                ))
            ))
        ));
        
        defun.evaluate(env);
        
        // Calcular factorial de 5
        ListExpression call = new ListExpression(Arrays.asList(
            new SymbolExpression("factorial"),
            new NumberExpression(5)
        ));
        
        assertEquals(120, call.evaluate(env));
    }
}