package uvg;

/**
 * Enum que define los diferentes tipos de tokens que se pueden identificar durante
 * el proceso de tokenización.
 * 
 * Cada valor representa una categoría de token en el análisis léxico, como palabras clave, 
 * operadores, números, puntuación, etc.
*/

public enum TokenType {
    KEYWORD, OPERATOR, PUNCTUATION, NUMBER, WHITESPACE, UNKNOWN, STRING, IDENTIFIER, COMMENT
}