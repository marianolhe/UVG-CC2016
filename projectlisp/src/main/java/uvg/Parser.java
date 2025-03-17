package uvg;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de analizar una lista de tokens y construir una estructura de expresiones.
 */
public class Parser {
    private List<Token> tokens;
    private int currentPosition;

    /**
     * Constructor de la clase Parser.
     * Inicializa el analizador con una lista de tokens.
     * @param tokens Lista de tokens generada por el Tokenizer.
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0; // Inicializa en la primera posición
    }

    /**
     * Inicia el proceso de análisis sintáctico de los tokens.
     * Se espera que los tokens formen una expresión válida.
     * @return Una expresión representada como un objeto de tipo Expression.
     * @throws RuntimeException si no hay tokens disponibles para analizar.
     */      
    public Expression parse() {
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("No hay tokens para analizar");
        }
        return parseExpression();
    }

    /**
     * Método privado que analiza una única expresión a partir de los tokens.
     * Identifica si se trata de un número, un símbolo o una expresión en paréntesis.
     * @return Una instancia de Expression que representa la expresión analizada.
     * @throws RuntimeException si se encuentra un token inesperado o inválido.
     */    
    private Expression parseExpression() {
        // Obtiene el token actual y avanza a la siguiente posición
        Token token = tokens.get(currentPosition);
        currentPosition++;
        
        // (), se analiza una expresión de lista
        if (token.getType() == TokenType.PUNCTUATION && token.getValue().equals("(")) {
            return parseListExpression();
        } 
        // número, se crea una expresión numérica
        else if (token.getType() == TokenType.NUMBER) {
            return new NumberExpression(Integer.parseInt(token.getValue()));
        } 
        // identificador, se crea una expresión de símbolo
        else if (token.getType() == TokenType.IDENTIFIER || token.getType() == TokenType.KEYWORD || 
                token.getType() == TokenType.OPERATOR) {
            return new SymbolExpression(token.getValue());
        }
        else {
            throw new RuntimeException("Error: Token inesperado: " + token.getValue());
        }
    }
    
    /**
     * Método privado que analiza una expresión de lista, que comienza con '('.
     * Se encarga de procesar múltiples expresiones dentro de los paréntesis.
     * @return Una instancia de ListExpression que representa la lista de expresiones.
     * @throws RuntimeException si no se encuentra el paréntesis de cierre ')'.
     */
    private Expression parseListExpression() {
        List<Expression> elements = new ArrayList<>();
        
        // Leer expresiones hasta encontrar el paréntesis de cierre
        while (currentPosition < tokens.size()) {
            Token current = tokens.get(currentPosition);
            
            // Ignorar espacios en blanco
            if (current.getType() == TokenType.WHITESPACE) {
                currentPosition++;
                continue;
            }
            
            // Si se encuentra un paréntesis de cierre, finalizar la lista
            if (current.getType() == TokenType.PUNCTUATION && current.getValue().equals(")")) {
                currentPosition++; // Consumir el paréntesis
                return new ListExpression(elements);
            }
            
            // Analizar la siguiente expresión y añadirla a la lista
            elements.add(parseExpression());
        }
        
        throw new RuntimeException("Error: Se esperaba un paréntesis de cierre ')'");
    }
}