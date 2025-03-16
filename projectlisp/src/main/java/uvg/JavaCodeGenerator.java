package uvg;
import java.util.List;

public class JavaCodeGenerator {
    public static String generateJavaCode(Expression expr) {
        if (expr instanceof NumberExpression) {
            return generateNumberCode((NumberExpression) expr);
        } else if (expr instanceof SymbolExpression) {
            return generateSymbolCode((SymbolExpression) expr);
        } else if (expr instanceof ListExpression) {
            return generateListCode((ListExpression) expr);
        }
        return "/* Expresión no soportada */";
    }
    
    private static String generateNumberCode(NumberExpression expr) {
        return expr.toString();
    }
    
    private static String generateSymbolCode(SymbolExpression expr) {
        String name = expr.getName();
        // Mapeo de símbolos LISP a Java
        switch (name) {
            case "t": return "true";
            case "nil": return "null";
            default: return name;
        }
    }
    
    private static String generateListCode(ListExpression expr) {
        List<Expression> elements = expr.getElements();
        if (elements.isEmpty()) {
            return "new ArrayList<>()";
        }
        
        // Procesar formas especiales y funciones
        if (elements.get(0) instanceof SymbolExpression) {
            String op = ((SymbolExpression) elements.get(0)).getName();
            switch (op) {
                case "+":
                    return generateBinaryOperation(elements, "+");
                case "-":
                    return generateBinaryOperation(elements, "-");
                case "*":
                    return generateBinaryOperation(elements, "*");
                case "/":
                    return generateBinaryOperation(elements, "/");
                case "=":
                    return generateComparison(elements, "==");
                case "<":
                    return generateComparison(elements, "<");
                case ">":
                    return generateComparison(elements, ">");
                case "setq":
                    if (elements.size() == 3) {
                        String var = generateJavaCode(elements.get(1));
                        String value = generateJavaCode(elements.get(2));
                        return var + " = " + value;
                    }
                    break;
                case "if":
                    if (elements.size() >= 3) {
                        String condition = generateJavaCode(elements.get(1));
                        String thenClause = generateJavaCode(elements.get(2));
                        String elseClause = elements.size() > 3 ? 
                            generateJavaCode(elements.get(3)) : "null";
                        return "(" + condition + " ? " + thenClause + " : " + elseClause + ")";
                    }
                    break;
                case "defun":
                    return generateFunctionDefinition(elements);
            }
        }
        
        // Llamada a función
        StringBuilder sb = new StringBuilder();
        sb.append(generateJavaCode(elements.get(0))).append("(");
        
        for (int i = 1; i < elements.size(); i++) {
            sb.append(generateJavaCode(elements.get(i)));
            if (i < elements.size() - 1) {
                sb.append(", ");
            }
        }
        
        sb.append(")");
        return sb.toString();
    }
    
    private static String generateBinaryOperation(List<Expression> elements, String operator) {
        if (elements.size() <= 1) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder("(");
        sb.append(generateJavaCode(elements.get(1)));
        
        for (int i = 2; i < elements.size(); i++) {
            sb.append(" ").append(operator).append(" ");
            sb.append(generateJavaCode(elements.get(i)));
        }
        
        sb.append(")");
        return sb.toString();
    }
    
    private static String generateComparison(List<Expression> elements, String operator) {
        if (elements.size() != 3) {
            return "false";
        }
        
        String left = generateJavaCode(elements.get(1));
        String right = generateJavaCode(elements.get(2));
        
        return "(" + left + " " + operator + " " + right + ")";
    }
    
    private static String generateFunctionDefinition(List<Expression> elements) {
        if (elements.size() < 4 || !(elements.get(1) instanceof SymbolExpression) || 
            !(elements.get(2) instanceof ListExpression)) {
            return "/* Invalid function definition */";
        }
        
        String funcName = ((SymbolExpression) elements.get(1)).getName();
        List<Expression> params = ((ListExpression) elements.get(2)).getElements();
        
        StringBuilder sb = new StringBuilder();
        sb.append("public static Object ").append(funcName).append("(");
        
        for (int i = 0; i < params.size(); i++) {
            sb.append("Object ").append(generateJavaCode(params.get(i)));
            if (i < params.size() - 1) {
                sb.append(", ");
            }
        }
        
        sb.append(") {\n");
        sb.append("    return ");
        
        if (elements.size() == 4) {
            sb.append(generateJavaCode(elements.get(3)));
        } else {
            // Para múltiples expresiones en el cuerpo
            sb.append("/* Cuerpo con múltiples expresiones */");
        }
        
        sb.append(";\n}");
        return sb.toString();
    }
}