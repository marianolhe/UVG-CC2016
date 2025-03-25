package uvg;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseNumber() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.NUMBER, "42"));
        
        Parser parser = new Parser(tokens);
        Expression result = parser.parse();
        
        assertTrue(result instanceof NumberExpression);
        assertEquals("42", result.toString());
    }
    
    @Test
    void testParseSymbol() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x"));
        
        Parser parser = new Parser(tokens);
        Expression result = parser.parse();
        
        assertTrue(result instanceof SymbolExpression);
        assertEquals("x", ((SymbolExpression)result).getName());
    }
    
    @Test
    void testParseSimpleList() {
        List<Token> tokens = Arrays.asList(
            new Token(TokenType.PUNCTUATION, "("),
            new Token(TokenType.OPERATOR, "+"),
            new Token(TokenType.NUMBER, "1"),
            new Token(TokenType.NUMBER, "2"),
            new Token(TokenType.PUNCTUATION, ")")
        );
        
        Parser parser = new Parser(tokens);
        Expression result = parser.parse();
        
        assertTrue(result instanceof ListExpression);
        ListExpression list = (ListExpression) result;
        List<Expression> elements = list.getElements();
        
        assertEquals(3, elements.size());
        assertTrue(elements.get(0) instanceof SymbolExpression);
        assertEquals("+", ((SymbolExpression)elements.get(0)).getName());
    }
    
    @Test
    void testParseNestedList() {
        List<Token> tokens = Arrays.asList(
            new Token(TokenType.PUNCTUATION, "("),
            new Token(TokenType.OPERATOR, "+"),
            new Token(TokenType.NUMBER, "1"),
            new Token(TokenType.PUNCTUATION, "("),
            new Token(TokenType.OPERATOR, "*"),
            new Token(TokenType.NUMBER, "2"),
            new Token(TokenType.NUMBER, "3"),
            new Token(TokenType.PUNCTUATION, ")"),
            new Token(TokenType.PUNCTUATION, ")")
        );
        
        Parser parser = new Parser(tokens);
        Expression result = parser.parse();
        
        assertTrue(result instanceof ListExpression);
        ListExpression list = (ListExpression) result;
        List<Expression> elements = list.getElements();
        
        assertEquals(3, elements.size());
        assertTrue(elements.get(2) instanceof ListExpression);
    }
    
    @Test
    void testParseUnexpectedToken() {
        List<Token> tokens = Arrays.asList(
            new Token(TokenType.PUNCTUATION, "("),
            new Token(TokenType.OPERATOR, "+"),
            // Falta el paréntesis de cierre
            new Token(TokenType.NUMBER, "1")
        );
        
        Parser parser = new Parser(tokens);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parser.parse();
        });
        
        assertTrue(exception.getMessage().contains("cierre"));
    }
}