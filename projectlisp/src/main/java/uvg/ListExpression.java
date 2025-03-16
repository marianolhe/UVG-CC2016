package uvg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Expresión que representa una lista (aplicación de función o forma especial).
 */
public class ListExpression extends Expression {
    private final List<Expression> elements;

    public ListExpression(List<Expression> elements) {
        this.elements = elements;
    }

    public List<Expression> getElements() {
        return elements;
    }

    @Override
    public Object evaluate(Environment env) {
        if (elements.isEmpty()) {
            return new ArrayList<>(); // Lista vacía se evalúa a sí misma
        }
        
        Expression first = elements.get(0);
        
        // Formas especiales
        if (first instanceof SymbolExpression) {
            String symbol = ((SymbolExpression) first).getName();
            
            switch (symbol) {
                case "setq":
                    return evaluateSetq(env);
                case "defun":
                    return evaluateDefun(env);
                case "if":
                    return evaluateIf(env);
                case "cond":
                    return evaluateCond(env);
                case "quote":
                    if (elements.size() < 2) {
                        throw new RuntimeException("quote requiere un argumento");
                    }
                    return elements.get(1); // Devuelve sin evaluar
            }
        }
        
        // Evaluación de aplicación de función
        return evaluateFunction(env);
    }

    private Object evaluateSetq(Environment env) {
        if (elements.size() != 3) {
            throw new RuntimeException("setq requiere exactamente 2 argumentos");
        }
        
        if (!(elements.get(1) instanceof SymbolExpression)) {
            throw new RuntimeException("El primer argumento de setq debe ser un símbolo");
        }
        
        String varName = ((SymbolExpression) elements.get(1)).getName();
        Object value = elements.get(2).evaluate(env);
        env.define(varName, value);
        return value;
    }

    private Object evaluateDefun(Environment env) {
        if (elements.size() < 4) {
            throw new RuntimeException("defun requiere al menos 3 argumentos");
        }
        
        if (!(elements.get(1) instanceof SymbolExpression)) {
            throw new RuntimeException("El nombre de la función debe ser un símbolo");
        }
        
        String funcName = ((SymbolExpression) elements.get(1)).getName();
        
        if (!(elements.get(2) instanceof ListExpression)) {
            throw new RuntimeException("La lista de parámetros debe ser una lista");
        }
        
        List<String> params = ((ListExpression) elements.get(2)).getElements().stream()
                .map(expr -> {
                    if (!(expr instanceof SymbolExpression)) {
                        throw new RuntimeException("Los parámetros deben ser símbolos");
                    }
                    return ((SymbolExpression) expr).getName();
                })
                .collect(Collectors.toList());
        
        // El cuerpo puede ser una expresión o varias
        Expression body;
        if (elements.size() == 4) {
            body = elements.get(3);
        } else {
            List<Expression> bodyExprs = elements.subList(3, elements.size());
            body = new ListExpression(bodyExprs);
        }
        
        Function func = new Function(params, body, env);
        env.define(funcName, func);
        return funcName;
    }

    private Object evaluateIf(Environment env) {
        if (elements.size() < 3 || elements.size() > 4) {
            throw new RuntimeException("if requiere 2 o 3 argumentos");
        }
        
        Object condition = elements.get(1).evaluate(env);
        if (isTruthy(condition)) {
            return elements.get(2).evaluate(env);
        } else if (elements.size() == 4) {
            return elements.get(3).evaluate(env);
        }
        return null; // nil en Lisp
    }

    private Object evaluateCond(Environment env) {
        for (int i = 1; i < elements.size(); i++) {
            Expression clause = elements.get(i);
            if (!(clause instanceof ListExpression)) {
                throw new RuntimeException("Las cláusulas de cond deben ser listas");
            }
            
            List<Expression> clauseElements = ((ListExpression) clause).getElements();
            if (clauseElements.isEmpty()) {
                continue;
            }
            
            Object test = clauseElements.get(0).evaluate(env);
            if (isTruthy(test)) {
                if (clauseElements.size() == 1) {
                    return test;
                }
                // Evaluar todos los elementos de la cláusula y devolver el último
                Object result = null;
                for (int j = 1; j < clauseElements.size(); j++) {
                    result = clauseElements.get(j).evaluate(env);
                }
                return result;
            }
        }
        return null; // nil en Lisp
    }

    private Object evaluateFunction(Environment env) {
        Object func = elements.get(0).evaluate(env);
        
        List<Object> args = new ArrayList<>();
        for (int i = 1; i < elements.size(); i++) {
            args.add(elements.get(i).evaluate(env));
        }
        
        if (func instanceof Function) {
            return ((Function) func).apply(args);
        } else if (func instanceof BiFunction) {
            @SuppressWarnings("unchecked")
            BiFunction<List<Object>, Environment, Object> builtinFunc = 
                (BiFunction<List<Object>, Environment, Object>) func;
            return builtinFunc.apply(args, env);
        }
        
        throw new RuntimeException("No se puede aplicar: " + func);
    }

    private boolean isTruthy(Object value) {
        return value != null && !(value instanceof Boolean && !((Boolean) value));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i).toString());
            if (i < elements.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}