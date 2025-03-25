package uvg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    
    @Test
    void testTokenCreation() {
        Token token = new Token(TokenType.NUMBER, "123");
        assertEquals(TokenType.NUMBER, token.getType());
        assertEquals("123", token.getValue());
    }
    
    @Test
    void testToString() {
        Token token = new Token(TokenType.KEYWORD, "defun");
        assertTrue(token.toString().contains("KEYWORD"));
        assertTrue(token.toString().contains("defun"));
    }
}