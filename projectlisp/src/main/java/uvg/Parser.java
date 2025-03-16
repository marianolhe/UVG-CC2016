package uvg;

import java.util.ArrayList;
import java.util.List;

/**
 * Analiza los tokens y construye un árbol de expresiones.
 */
public class Parser {
    private List<Token> tokens;
    private int currentPosition;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    public Expression parse() {
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("No hay tokens para analizar");
        }
        return parseExpression();
    }

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
    
    // Método para analizar una expresión de lista
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