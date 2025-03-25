package uvg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Tokenizer divide una cadena de texto en tokens significativos, como palabras clave,
 * operadores, identificadores, números, puntuación y espacios en blanco. 
 */

public class Tokenizer {
    private String input;
    private int currentPosition;
    private List<Token> tokens;

    /**
     * Constructor que inicializa el Tokenizer con la cadena de entrada.
     * 
     * @param input La cadena de texto a tokenizar.
     */    
    public Tokenizer(String input) {
        this.input = input;
        this.currentPosition = 0;
        this.tokens = new ArrayList<>();
    }

    /**
     * Realiza la tokenización de la cadena, eliminando comentarios y dividiendo
     * el texto en tokens según los patrones definidos.
     * 
     * @return Lista de tokens identificados en la cadena.
     */
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

    /**
     * Elimina los comentarios de la cadena de entrada (comentarios estilo LISP).
     * 
     * @param code Código de entrada con posibles comentarios.
     * @return La cadena sin comentarios.
     */
    private String removeComments(String code) {
        // Eliminar comentarios de línea estilo ;
        return code.replaceAll(";.*", "");
    }

    /**
     * Obtiene el siguiente token de la cadena, basándose en patrones predefinidos.
     * 
     * @return El siguiente token o null si se llega al final de la cadena.
     */    
    private Token nextToken() {
        if (currentPosition >= input.length()) {
            return null;
        }
        // Manejo especial directo para paréntesis y comillas
        char c = input.charAt(currentPosition);
        if (c == '(' || c == ')' || c == '\'') {
            currentPosition++;
            return new Token(TokenType.PUNCTUATION, String.valueOf(c));}

        String[] tokenPatterns = {
            "defun|if|cond|setq|quote",  // Palabras clave de LISP
            "[+\\-\\/\\*\\=\\<\\>\\!]",                // Operadores
            "[()']",                      // Puntuación
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