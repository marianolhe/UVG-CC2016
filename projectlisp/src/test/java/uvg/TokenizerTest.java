package uvg;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test       
    void testTokenizeKeywords() {
        Tokenizer tokenizer = new Tokenizer("defun cond setq");
        List<Token> tokens = tokenizer.Tokenize();

        assertEquals(3, tokens.size());  // ¿Se detectaron 3 tokens?

        assertEquals(TokenType.KEYWORD, tokens.get(0).getType()); // Tipo correcto
        assertEquals("defun", tokens.get(0).getValue()); // Valor correcto

        assertEquals(TokenType.KEYWORD, tokens.get(1).getType());
        assertEquals("cond", tokens.get(1).getValue());

        assertEquals(TokenType.KEYWORD, tokens.get(2).getType());
        assertEquals("setq", tokens.get(2).getValue());
    }

    @Test
    void testTokenizeNumbers() {
        Tokenizer tokenizer = new Tokenizer("123 456 789");
        List<Token> tokens = tokenizer.Tokenize();

        assertEquals(3, tokens.size());  // ¿Se detectaron 3 tokens?

        assertEquals(TokenType.NUMBER, tokens.get(0).getType()); // Tipo correcto
        assertEquals("123", tokens.get(0).getValue()); // Valor correcto

        assertEquals(TokenType.NUMBER, tokens.get(1).getType());
        assertEquals("456", tokens.get(1).getValue());

        assertEquals(TokenType.NUMBER, tokens.get(2).getType());
        assertEquals("789", tokens.get(2).getValue());
    }

    @Test
    void testTokenizeOperators() {
        Tokenizer tokenizer = new Tokenizer("+ - * /");
        List<Token> tokens = tokenizer.Tokenize();
        
        assertEquals(4, tokens.size());
        assertEquals(TokenType.OPERATOR, tokens.get(0).getType());
        assertEquals("+", tokens.get(0).getValue());
        
        assertEquals(TokenType.OPERATOR, tokens.get(1).getType());
        assertEquals("-", tokens.get(1).getValue());
    }

    @Test
    void testTokenizePunctuation() {
        Tokenizer tokenizer = new Tokenizer("( )");
        List<Token> tokens = tokenizer.Tokenize();
        
        assertEquals(2, tokens.size());
        assertEquals(TokenType.PUNCTUATION, tokens.get(0).getType());
        assertEquals("(", tokens.get(0).getValue());
        assertEquals(")", tokens.get(1).getValue());
    }

    @Test
    void testTokenizeComplexExpression() {
        Tokenizer tokenizer = new Tokenizer("(defun factorial (n) (if (= n 0) 1 (* n (factorial (- n 1)))))");
        List<Token> tokens = tokenizer.Tokenize();
        
        assertTrue(tokens.size() > 10, "Debe detectar múltiples tokens para una expresión compleja");
        
        // Verificamos algunos tokens importantes
        assertEquals(TokenType.PUNCTUATION, tokens.get(0).getType());
        assertEquals("(", tokens.get(0).getValue());
        
        assertEquals(TokenType.KEYWORD, tokens.get(1).getType());
        assertEquals("defun", tokens.get(1).getValue());
    }

    @Test
    void testTokenizeWithComments() {
        Tokenizer tokenizer = new Tokenizer("(+ 2 3) ; esto es un comentario");
        List<Token> tokens = tokenizer.Tokenize();
        
        // El comentario debe ser eliminado, solo debemos tener 5 tokens: ( + 2 3 )
        assertEquals(5, tokens.size());
        assertEquals(")", tokens.get(tokens.size() - 1).getValue());
    }
    }