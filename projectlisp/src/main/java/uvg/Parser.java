package uvg;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    private List<Token> tokens;
    private int currentPosition;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0; // aquí comienza desde el primer token
    }

    // Método principal para iniciar el análisis
    public Expression parse() {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Error: No hay tokens para analizar.");
        }
        return parseExpression();
    }

    // Método para analizar una expresión
    private Expression parseExpression() {
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("Error: Expresión incompleta.");
        }
        
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
        else {
            throw new RuntimeException("Error: Token inesperado: " + token.getValue());
        }
    }
    
    // Método para analizar una expresión de lista
    private Expression parseListExpression() {
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("Error: Se esperaba un operador después de '('.");
        }
        
        // Obtiene el siguiente token que debe ser un operador
        Token operatorToken = tokens.get(currentPosition);
        currentPosition++;
        
        if (operatorToken.getType() != TokenType.IDENTIFIER && operatorToken.getType() != TokenType.KEYWORD) {
            throw new RuntimeException("Error: Se esperaba un operador válido, pero se encontró: " + operatorToken.getValue());
        }
        
        List<Expression> operands = new ArrayList<>();
        
        // Analiza los operandos mientras no encuentre el cierre ')'
        while (currentPosition < tokens.size() && 
               !(tokens.get(currentPosition).getType() == TokenType.PUNCTUATION && tokens.get(currentPosition).getValue().equals(")"))) {
            operands.add(parseExpression());
        }
        
        if (currentPosition >= tokens.size()) {
            throw new RuntimeException("Error: Se esperaba ')' para cerrar la expresión.");
        }
        
        currentPosition++;
        // Devuelve la expresión de lista con el operador y los operandos
        return new ListExpression(operatorToken.getValue(), operands);
    }
}
