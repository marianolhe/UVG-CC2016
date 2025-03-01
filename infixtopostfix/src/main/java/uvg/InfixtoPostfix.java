package uvg;
import java.util.Stack;

/**
 * Clase para convertir expresiones infijas a notación postfix.
 */

public class InfixtoPostfix {
    
    /**
     * Convierte una expresión infija a notación postfix.
     * 
     * Precondiciones:
     * - La expresión infija debe ser válida.
     * 
     * Postcondiciones:
     * - Devuelve la expresión postfix correspondiente como una cadena.
     * 
     * @param infix La expresión infija a convertir.
     * @return La representación postfix de la expresión.

     */
    public static String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char ch : infix.toCharArray()){
            if (Character.isDigit(ch)) { 
                postfix.append(ch).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString().trim();
    }

    /**
 * Retorna la precedencia del operador dado.
 * 
 * @param ch El carácter del operador.
 * @return El nivel de precedencia del operador.
 */

    public static int precedence(char ch) {
        return switch (ch){
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
}
