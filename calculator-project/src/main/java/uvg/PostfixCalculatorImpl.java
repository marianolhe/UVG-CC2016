package uvg;

public class PostfixCalculatorImpl implements ICalculadora {
    private IStack<Integer> stack;

    // Constructor that initializes the stack
    public PostfixCalculatorImpl(IStack<Integer> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("La pila no puede ser null");
        }
        this.stack = stack;
    }

    @Override
    public int evaluate(String expression) throws PostfixCalculatorException {
        if (expression == null) {
            throw new PostfixCalculatorException("La expresión no puede ser null", PostfixCalculatorException.ERROR_EXPRESION_NULA);
        }
        
        stack.clear(); // Clear the stack for a new evaluation
        String[] tokens = expression.trim().split("\\s+"); // Split the expression into tokens
        
        for (String token : tokens) {
            try {
                if (isOperator(token)) {
                    // Ensure there are enough operands in the stack
                    if (stack.isEmpty()) {
                        throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                    }
                    int b = stack.pop();
                    if (stack.isEmpty()) {
                        throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                    }
                    int a = stack.pop();
                    stack.push(executeOperation(a, b, token)); // Execute the operation and push the result
                } else {
                    stack.push(Integer.parseInt(token)); // Push the number onto the stack
                }
            } catch (NumberFormatException e) {
                throw new PostfixCalculatorException("Carácter inválido: " + token, PostfixCalculatorException.ERROR_CARACTER_INVALIDO);
            }
        }
        
        // Final result check
        if (stack.isEmpty()) {
            throw new PostfixCalculatorException("Expresión vacía", PostfixCalculatorException.ERROR_EXPRESION_VACIA);
        }
        
        int result = stack.pop();
        if (!stack.isEmpty()) {
            throw new PostfixCalculatorException("Expresión mal formada", PostfixCalculatorException.ERROR_EXPRESION_MALFORMADA);
        }
        
        return result; // Return the final result
    }

    // Check if the token is an operator
    private boolean isOperator(String token) {
        return token.matches("[+\\-*/%]");
    }

    // Execute the operation based on the operator
    private int executeOperation(int a, int b, String operator) throws PostfixCalculatorException {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    throw new PostfixCalculatorException("División por cero", PostfixCalculatorException.ERROR_DIVISION_CERO);
                }
                return a / b;
            case "%":
                if (b == 0) {
                    throw new PostfixCalculatorException("Módulo por cero", PostfixCalculatorException.ERROR_MODULO_CERO);
                }
                return a % b;
            default:
                throw new PostfixCalculatorException("Operador inválido: " + operator, PostfixCalculatorException.ERROR_OPERADOR_INVALIDO);
        }
    }
}
