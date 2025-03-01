package uvg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class PostfixCalculatorTest {
    @Test
    public void testAddition() throws Exception {
        IStack<Integer> stack = new VectorStack<>();
        ICalculadora calculator = PostfixCalculatorImpl.getInstance(stack);
        assertEquals(5, calculator.evaluate("2 3 +"));
    }
}
