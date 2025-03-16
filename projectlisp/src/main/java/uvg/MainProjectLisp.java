package uvg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MainProjectLisp {
    public static void main(String[] args) {
        if (args.length > 0) {
            // Modo de archivo
            String fileName = args[0];
            Environment env = new Environment();
            executeFile(fileName, env);
        } else {
            // Modo interactivo
            interactiveMode();
        }
    }
    
    private static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        Environment globalEnv = new Environment();
        
        System.out.println("Intérprete LISP - Java (Escriba 'salir' para terminar)");
        System.out.println("--------------------------------------------------------");
        
        while (true) {
            System.out.print("\nlisp> ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            if (input.equalsIgnoreCase("salir")) {
                break;
            }
            
            try {
                LispResult result = interpretLisp(input, globalEnv);
                
                System.out.println("\n=== Análisis y Ejecución ===");
                System.out.println("Tokens:        " + result.tokens);
                System.out.println("Expresión AST: " + result.expression);
                System.out.println("Código Java:   " + result.javaCode);
                System.out.println("Resultado:     " + result.result);
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("¡Hasta luego!");
        scanner.close();
    }
    
    private static void executeFile(String fileName, Environment env) {
        try {
            System.out.println("Ejecutando archivo: " + fileName);
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] expressions = content.split("\n");
            
            for (String expr : expressions) {
                expr = expr.trim();
                if (expr.isEmpty() || expr.startsWith(";")) {
                    continue; // Saltar líneas vacías y comentarios
                }
                
                try {
                    LispResult result = interpretLisp(expr, env);
                    System.out.println("\n=== " + expr + " ===");
                    System.out.println("Código Java: " + result.javaCode);
                    System.out.println("Resultado:   " + result.result);
                } catch (Exception e) {
                    System.out.println("Error en expresión '" + expr + "': " + e.getMessage());
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    private static LispResult interpretLisp(String code, Environment env) {
        LispResult result = new LispResult();
        
        // Paso 1: Tokenización
        Tokenizer tokenizer = new Tokenizer(code);
        result.tokens = tokenizer.Tokenize();
        
        // Paso 2: Análisis sintáctico
        Parser parser = new Parser(result.tokens);
        result.expression = parser.parse();
        
        // Paso 3: Generación de código Java equivalente
        result.javaCode = JavaCodeGenerator.generateJavaCode(result.expression);
        
        // Paso 4: Evaluación
        result.result = result.expression.evaluate(env);
        
        return result;
    }
    
    static class LispResult {
        List<Token> tokens;
        Expression expression;
        String javaCode;
        Object result;
    }
}