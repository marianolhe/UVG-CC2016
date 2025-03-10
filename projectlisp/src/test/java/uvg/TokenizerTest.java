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

}