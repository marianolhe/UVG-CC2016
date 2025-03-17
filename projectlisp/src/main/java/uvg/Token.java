package uvg;

/**
 * Representa un token en el análisis léxico.
 * Un token es una unidad de significado dentro del código fuente, 
 * como palabras clave, identificadores, operadores o signos de puntuación.
 */
public class Token {
    private TokenType type; // Tipo del token
    private String value; // Valor literal del token

    /**
     * Constructor de la clase Token.
     * @param type Tipo del token según su clasificación léxica.
     * @param value Valor literal asociado al token.
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Obtiene el tipo del token.
     * @return El tipo del token como un elemento de TokenType.
     */    
    public TokenType getType() {
        return type;
    }

    /**
     * Obtiene el valor del token.
     * @return El valor del token como una cadena de texto.
     */    
    public String getValue() {
        return value;
    }

    /**
     * Devuelve una representación en cadena del token.
     * @return Una cadena con el tipo y valor del token.
     */    
    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value='" + value + '\'' + '}';
    }
}
