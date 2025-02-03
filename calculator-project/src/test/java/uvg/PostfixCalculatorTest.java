
// PostfixCalculatorTest.java
package uvg;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para PostfixCalculatorImpl
 */
public class PostfixCalculatorTest {
    private PostfixCalculator calculator;
    
    @Before
    public void setUp() {
        calculator = new PostfixCalculatorImpl(new VectorStack<>());
    }
    
    @Test
    public void testSimpleAddition() throws PostfixCalculatorException {
        assertEquals(3, calculator.evaluateExpression("1 2 +"));
    }
    
    @Test
    public void testComplexExpression() throws PostfixCalculatorException {
        assertEquals(15, calculator.evaluateExpression("1 2 + 4 * 3 +"));
    }
    
    @Test(expected = PostfixCalculatorException.class)
    public void testDivisionByZero() throws PostfixCalculatorException {
        calculator.evaluateExpression("1 0 /");
    }
    
    @Test(expected = PostfixCalculatorException.class)
    public void testInvalidOperator() throws PostfixCalculatorException {
        calculator.evaluateExpression("1 2 $");
    }
    
    @Test(expected = PostfixCalculatorException.class)
    public void testInsufficientOperands() throws PostfixCalculatorException {
        calculator.evaluateExpression("1 +");
    }
    
    @Test
    public void testAllOperations() throws PostfixCalculatorException {
        assertEquals(7, calculator.evaluateExpression("10 3 -"));
        assertEquals(15, calculator.evaluateExpression("3 5 *"));
        assertEquals(2, calculator.evaluateExpression("10 5 /"));
        assertEquals(1, calculator.evaluateExpression("10 3 %"));
    }
    
    @Test(expected = PostfixCalculatorException.class)
    public void testEmptyExpression() throws PostfixCalculatorException {
        calculator.evaluateExpression("");
    }
    
    @Test(expected = PostfixCalculatorException.class)
    public void testNullExpression() throws PostfixCalculatorException {
        calculator.evaluateExpression(null);
    }
}