package uvg;

import java.util.ArrayList;
import java.util.List;

/**
 * Analizador sintáctico para expresiones LISP.
 */
public class Parser {
    private List<Token> tokens;
    private int currentPosition;

    /**
     * Constructor que inicializa el parser con una lista de tokens.
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    /**
     * Analiza la lista de tokens y devuelve una expresión.
     */
    public Expression parse() {
        if (tokens.isEmpty()) {
            return null;
        }
        return parseExpression();
    }

    /**
     * Analiza una expresión, que puede ser un número, símbolo o lista.
     */
    private Expression parseExpression() {
        if (currentPosition >= tokens.size()) {
            return null;
        }

        Token token = tokens.get(currentPosition);
        
        switch (token.getType()) {
            case NUMBER:
                currentPosition++; // Consumir el token
                try {
                    return new NumberExpression(Integer.parseInt(token.getValue()));
                } catch (NumberFormatException e) {
                    try {
                        return new NumberExpression(Integer.parseInt(token.getValue()));
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException("Número inválido: " + token.getValue());
                    }
                }
                
            case IDENTIFIER:
            case KEYWORD:
                currentPosition++; // Consumir el token
                return new SymbolExpression(token.getValue());
            case OPERATOR:  // 
                currentPosition++; // Consumir el tokenn
                return new SymbolExpression(token.getValue());
                
            case PUNCTUATION:
                if (token.getValue().equals("(")) {
                    currentPosition++; // Consumir el '('
                    return parseListExpression();
                } else if (token.getValue().equals(")")) {
                    throw new RuntimeException("Paréntesis de cierre inesperado");
                } else if (token.getValue().equals("'")) {
                    currentPosition++; // Consumir la comilla
                    Expression quoted = parseExpression();
                    return new ListExpression(List.of(
                        new SymbolExpression("quote"),
                        quoted
                    ));
                } else {
                    throw new RuntimeException("Símbolo de puntuación no reconocido: " + token.getValue());
                }
                
            default:
                throw new RuntimeException("Token inesperado: " + token);
        }
    }
    
    /**
     * Analiza una expresión de lista, que comienza con '('.
     */
    private Expression parseListExpression() {
        List<Expression> elements = new ArrayList<>();
        
        while (currentPosition < tokens.size()) {
            Token current = tokens.get(currentPosition);
            
            if (current.getType() == TokenType.PUNCTUATION && current.getValue().equals(")")) {
                currentPosition++; // Consumir el ')'
                return new ListExpression(elements);
            }
            
            elements.add(parseExpression());
        }
        
        throw new RuntimeException("Se esperaba un paréntesis de cierre ')'");
    }
}