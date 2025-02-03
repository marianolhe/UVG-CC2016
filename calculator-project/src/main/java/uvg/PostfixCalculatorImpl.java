package uvg;

public class PostfixCalculatorImpl implements ICalculadora {
    private IStack<Integer> stack;

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
    
    stack.clear();
    String[] tokens = expression.trim().split("\\s+");
    
    for (String token : tokens) {
        try {
            if (isOperator(token)) {
                if (stack.isEmpty()) {
                    throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                }
                int b = stack.pop();
                if (stack.isEmpty()) {
                    throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                }
                int a = stack.pop();
                stack.push(executeOperation(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        } catch (NumberFormatException e) {
            throw new PostfixCalculatorException("Carácter inválido: " + token, PostfixCalculatorException.ERROR_CARACTER_INVALIDO);
        }
    }
    
    if (stack.isEmpty()) {
        throw new PostfixCalculatorException("Expresión vacía", PostfixCalculatorException.ERROR_EXPRESION_VACIA);
    }
    
    int result = stack.pop();
    if (!stack.isEmpty()) {
        throw new PostfixCalculatorException("Expresión mal formada", PostfixCalculatorException.ERROR_EXPRESION_MALFORMADA);
    }
    
    return result;
}

private boolean isOperator(String token) {
    return token.matches("[+\\-*/%]");
}

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
}}