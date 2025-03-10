package uvg;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

///Codigo de: https://medium.com/@enzojade62/step-by-step-building-a-lexer-in-java-for-Tokenizing-source-code-ac4f1d91326f

public class Tokenizer {
    private String input;
    private int currrentPosition;
    
    public Tokenizer (String input){
        this.input = input;
        this.currrentPosition = 0;
    }

    public List<Token> Tokenize () {
        List<Token> Tokens = new ArrayList<>();

        while (currrentPosition < input.length()) {
            char currentChar = input.charAt(currrentPosition);

            if (Character.isWhitespace(currentChar)) {
                currrentPosition++;
                continue;
            }

            Token Token = nextToken();
            if (Token != null) {
                Tokens.add(Token);
            } else {
                throw new RuntimeException("Unknown character: " + currentChar);
            }
        }

        return Tokens;
    }

    private Token nextToken() {
        if (currrentPosition >= input.length()) {
            return null;
        }

        String[] TokenPatterns = {
            "defun|if|c|cond|setq",         // Palabras clave de LISP
            "[+-/*=<>!]",                // Operaciones
            "[()]",                 // Puntuacion de LISP
            "\"[^\"]*\"",               // Strings
            "\\d+",                     // Numeros
            "\\s+",                     // Espacios en blanco
            "[a-zA-Z_][a-zA-Z0-9_]*"    //Identificadores (variables/ nombres de funciones)
        };

        TokenType[] TokenTypes = {
            TokenType.KEYWORD,
            TokenType.OPERATOR,
            TokenType.PUNCTUATION,
            TokenType.NUMBER,
            TokenType.WHITESPACE,
            TokenType.STRING, 
            TokenType.IDENTIFIER
        };

        for (int i = 0; i < TokenPatterns.length; i++) {
            Pattern pattern = Pattern.compile("^" + TokenPatterns[i]);
            Matcher matcher = pattern.matcher(input.substring(currrentPosition));

            if (matcher.find()) {
                String value = matcher.group();
                currrentPosition += value.length();
                return new Token(TokenTypes[i], value);
            }
        }

        return null;
    }
}

