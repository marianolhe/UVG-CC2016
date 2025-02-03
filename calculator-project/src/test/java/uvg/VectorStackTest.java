package uvg;

// VectorStackTest.java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para VectorStack
 */
public class VectorStackTest {
    private IStack<Integer> stack;
    
    @Before
    public void setUp() {
        stack = new VectorStack<>();
    }
    
    @Test
    public void testPushPop() {
        stack.push(1);
        assertEquals(Integer.valueOf(1), stack.pop());
        assertTrue(stack.isEmpty());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testPopEmpty() {
        stack.pop();
    }
    
    @Test
    public void testMultiplePushPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertTrue(stack.isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPushNull() {
        stack.push(null);
    }
    
    @Test
    public void testPeek() {
        stack.push(1);
        assertEquals(Integer.valueOf(1), stack.peek());
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void testClear() {
        stack.push(1);
        stack.push(2);
        stack.clear();
        assertTrue(stack.isEmpty());
    }
}