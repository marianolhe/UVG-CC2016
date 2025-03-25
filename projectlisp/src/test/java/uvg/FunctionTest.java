package uvg;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @Test
    void testFunctionApplication() {
        Environment env = new Environment();
        List<String> params = Collections.singletonList("n");
        Expression body = new ListExpression(Arrays.asList(
            new SymbolExpression("*"),
            new SymbolExpression("n"),
            new NumberExpression(2)
        ));
        
        Function doubleFunc = new Function(params, body, env);
        
        assertEquals(10, doubleFunc.apply(Collections.singletonList(5)));
    }
    
    @Test
    void testFunctionWithCapturedEnvironment() {
        Environment env = new Environment();
        env.define("factor", 3);
        
        List<String> params = Collections.singletonList("n");
        Expression body = new ListExpression(Arrays.asList(
            new SymbolExpression("*"),
            new SymbolExpression("n"),
            new SymbolExpression("factor")
        ));
        
        Function tripleFunc = new Function(params, body, env);
        
        assertEquals(15, tripleFunc.apply(Collections.singletonList(5)));
    }
    
    @Test
    void testFunctionWrongArgumentCount() {
        Environment env = new Environment();
        List<String> params = Arrays.asList("a", "b");
        Expression body = new NumberExpression(1); // Cuerpo simple para la prueba
        
        Function func = new Function(params, body, env);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            func.apply(Collections.singletonList(5)); // Solo un argumento para 2 parámetros
        });
        
        assertTrue(exception.getMessage().contains("esperaban 2"));
    }
}