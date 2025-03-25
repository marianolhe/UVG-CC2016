package uvg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainProjectLispTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testInterpretLisp() throws Exception {
        Environment env = new Environment();
        String input = "(+ 2 3)";

        // Usando reflexión para acceder al método privado
        java.lang.reflect.Method method = MainProjectLisp.class.getDeclaredMethod("interpretLisp", String.class, Environment.class);
        method.setAccessible(true);
        
        LispResult result = (LispResult) method.invoke(null, input, env);
        
        assertNotNull(result.tokens);
        assertNotNull(result.expression);
        assertEquals(5, result.result);
    }
    
    @Test
    void testFormatTokens() throws Exception {
        // Acceso al método privado mediante reflexión
        java.lang.reflect.Method method = MainProjectLisp.class.getDeclaredMethod("formatTokens", java.util.List.class);
        method.setAccessible(true);
        
        Tokenizer tokenizer = new Tokenizer("(+ 1 2 3)");
        java.util.List<Token> tokens = tokenizer.Tokenize();
        
        String result = (String) method.invoke(null, tokens);
        
        assertNotNull(result);
        assertTrue(result.contains("("));
        assertTrue(result.contains("+"));
    }
    
    @Test
    void testInteractiveModeSimple() {
        // Simular entrada del usuario
        String input = "(+ 2 3)\nsalir\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        MainProjectLisp.main(new String[]{});
        
        // Verificar que la salida contiene el resultado esperado
        String output = outContent.toString();
        assertTrue(output.contains("5"));
        
        // Restaurar System.in
        System.setIn(System.in);
    }
    
    @Test
    void testInteractiveModeHelp() {
        // Simular entrada del usuario pidiendo ayuda
        String input = "ayuda\nsalir\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        MainProjectLisp.main(new String[]{});
        
        // Verificar que la salida contiene ejemplos
        String output = outContent.toString();
        assertTrue(output.contains("Ejemplos"));
        assertTrue(output.contains("factorial"));
        
        // Restaurar System.in
        System.setIn(System.in);
    }
    
    @Test
    void testInteractiveModeError() {
        // Simular entrada del usuario con error
        String input = "(/ 5 0)\nsalir\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        MainProjectLisp.main(new String[]{});
        
        // Verificar que la salida contiene mensaje de error
        String output = outContent.toString();
        assertTrue(output.contains("Error"));
        
        // Restaurar System.in
        System.setIn(System.in);
    }
}