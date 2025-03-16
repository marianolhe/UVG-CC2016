package uvg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private String input;
    private int currentPosition;
    private List<Token> tokens;

    public Tokenizer(String input) {
        this.input = input;
        this.currentPosition = 0;
        this.tokens = new ArrayList<>();
    }

    public List<Token> Tokenize() {
        tokens.clear();
        Token token;
        
        // Eliminar comentarios si existen
        input = removeComments(input);
        
        while ((token = nextToken()) != null) {
            // Ignorar espacios en blanco
            if (token.getType() != TokenType.WHITESPACE) {
                tokens.add(token);
            }
        }
        
        return tokens;
    }

    private String removeComments(String code) {
        // Eliminar comentarios de línea estilo ;
        return code.replaceAll(";.*", "");
    }

    private Token nextToken() {
        if (currentPosition >= input.length()) {
            return null;
        }

        String[] tokenPatterns = {
            "defun|if|cond|setq|quote",  // Palabras clave de LISP
            "[+-/*=<>!]",                // Operadores
            "[()]",                      // Puntuación
            "\\d+",                      // Números
            "\\s+",                      // Espacios en blanco
            "[a-zA-Z_][a-zA-Z0-9_]*"     // Identificadores
        };

        TokenType[] tokenTypes = {
            TokenType.KEYWORD,
            TokenType.OPERATOR,
            TokenType.PUNCTUATION,
            TokenType.NUMBER,
            TokenType.WHITESPACE,
            TokenType.IDENTIFIER
        };

        for (int i = 0; i < tokenPatterns.length; i++) {
            Pattern pattern = Pattern.compile("^" + tokenPatterns[i]);
            Matcher matcher = pattern.matcher(input.substring(currentPosition));

            if (matcher.find()) {
                String value = matcher.group();
                currentPosition += value.length();
                return new Token(tokenTypes[i], value);
            }
        }

        // Si llegamos aquí, hay un token desconocido
        String unknown = input.substring(currentPosition, currentPosition + 1);
        currentPosition++;
        return new Token(TokenType.UNKNOWN, unknown);
    }
}