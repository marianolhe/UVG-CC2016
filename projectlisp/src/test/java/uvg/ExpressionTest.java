package uvg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void testNumberExpression() {
        Environment env = new Environment();
        NumberExpression num = new NumberExpression(42);
        
        assertEquals(42, num.evaluate(env));
        assertEquals("42", num.toString());
    }
    
    @Test
    void testSymbolExpression() {
        Environment env = new Environment();
        env.define("x", 10);
        
        SymbolExpression symbol = new SymbolExpression("x");
        assertEquals(10, symbol.evaluate(env));
        assertEquals("x", symbol.getName());
        assertEquals("x", symbol.toString());
    }
    
    @Test
    void testSymbolExpressionNotFound() {
        Environment env = new Environment();
        SymbolExpression symbol = new SymbolExpression("undefinedVar");
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            symbol.evaluate(env);
        });
        
        assertTrue(exception.getMessage().contains("no definida"));
    }
}