package uvg;

public class PostfixCalculatorImpl implements ICalculadora {
    private static PostfixCalculatorImpl instance;
    private IStack<Integer> stack;

    public PostfixCalculatorImpl(IStack<Integer> stack) {
        this.stack = stack;
    }

    public static PostfixCalculatorImpl getInstance(IStack<Integer> stack) {
        if (instance == null) {
            instance = new PostfixCalculatorImpl(stack);
        }
        return instance;
    }

    @Override
    public int evaluate(String expression) {
        for (String token : expression.split("\\s+")) {
            if (token.matches("-?\\d+")) { // Check if the token is a number
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
            }
        }
        return stack.pop();
    }
}