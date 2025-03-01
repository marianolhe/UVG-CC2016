package uvg;
import java.util.Stack;

public class InfixtoPostfix{
    public static String convert(String infix){
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
    public static int precedence(char ch){
        return switch (ch){
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };

    }
}