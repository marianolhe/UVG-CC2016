package uvg;
import static org.junit.Assert.*;


public class PostfixCalculatorTest {
    @Test
    public void testAddition() throws Exception {
        IStack<Integer> stack = new VectorStack<>();
        ICalculadora calculator = PostfixCalculatorImpl.getInstance(stack);
        assertEquals(5, calculator.evaluate("2 3 +"));
    }
}
