package uvg;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class JavaCodeGeneratorTest {

    @Test
    void testGenerateNumberCode() {
        NumberExpression expr = new NumberExpression(42);
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        
        assertEquals("42", javaCode);
    }
    
    @Test
    void testGenerateSymbolCode() {
        SymbolExpression expr = new SymbolExpression("x");
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        
        assertEquals("x", javaCode);
    }
    
    @Test
    void testGenerateArithmeticCode() {
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("+"),
            new NumberExpression(1),
            new NumberExpression(2),
            new NumberExpression(3)
        ));
        
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        assertTrue(javaCode.contains("(1 + 2 + 3)"));
    }
    
    @Test
    void testGenerateNestedArithmeticCode() {
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("+"),
            new NumberExpression(1),
            new ListExpression(Arrays.asList(
                new SymbolExpression("*"),
                new NumberExpression(2),
                new NumberExpression(3)
            ))
        ));
        
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        assertTrue(javaCode.contains("(1 + (2 * 3))"));
    }
    
    @Test
    void testGenerateComparisonCode() {
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("="),
            new NumberExpression(5),
            new NumberExpression(5)
        ));
        
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        assertTrue(javaCode.contains("(5 == 5)"));
    }
    
    @Test
    void testGenerateIfCode() {
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("if"),
            new ListExpression(Arrays.asList(
                new SymbolExpression(">"),
                new NumberExpression(5),
                new NumberExpression(3)
            )),
            new NumberExpression(1),
            new NumberExpression(0)
        ));
        
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        assertTrue(javaCode.contains("(5 > 3)"));
        assertTrue(javaCode.contains("? 1 : 0"));
    }
    
    @Test
    void testGenerateFunctionDefinitionCode() {
        ListExpression expr = new ListExpression(Arrays.asList(
            new SymbolExpression("defun"),
            new SymbolExpression("double"),
            new ListExpression(Arrays.asList(
                new SymbolExpression("n")
            )),
            new ListExpression(Arrays.asList(
                new SymbolExpression("*"),
                new SymbolExpression("n"),
                new NumberExpression(2)
            ))
        ));
        
        String javaCode = JavaCodeGenerator.generateJavaCode(expr);
        assertTrue(javaCode.contains("public static Object double"));
        assertTrue(javaCode.contains("Object n"));
        assertTrue(javaCode.contains("return (n * 2)"));
    }
}