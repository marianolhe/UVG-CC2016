package uvg;

import java.util.List;
import java.util.Scanner;


/**
 * Intérprete LISP interactivo.
 * Permite al usuario ingresar expresiones LISP, analizarlas y obtener su resultado.
 * Proporciona una interfaz interactiva donde se pueden escribir expresiones LISP y ver su evaluación.
 * También incluye ejemplos de código y una opción para salir o pedir ayuda.
 */

public class MainProjectLisp {

    /**
     * Método principal que inicia el intérprete LISP en modo interactivo.
     */
    public static void main(String[] args) {
        // Iniciar el modo interactivo
        interactiveMode();
    }

    /**
     * Modo interactivo donde el usuario puede escribir expresiones LISP, recibir
     * su evaluación y ver el análisis de los tokens y el resultado.
     */    
    private static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        Environment globalEnv = new Environment();
        
        System.out.println("======================================");
        System.out.println("   INTÉRPRETE LISP - MODO INTERACTIVO ");
        System.out.println("======================================");
        System.out.println("Escribe expresiones LISP para evaluarlas.");
        System.out.println("Escribe 'salir' para terminar.");
        System.out.println("Escribe 'ayuda' para ver ejemplos.");
        System.out.println("");
        
        while (true) {
            System.out.print("\nlisp> ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("salir")) {
                System.out.println("¡Hasta luego!");
                break;
            }
            
            if (input.equalsIgnoreCase("ayuda")) {
                showHelp();
                continue;
            }
            
            try {
                LispResult result = interpretLisp(input, globalEnv);
                
                System.out.println("\n=== Análisis y Ejecución ===");
                System.out.println("Tokens:      " + formatTokens(result.tokens));
                System.out.println("Expresión:   " + result.expression);
                if (result.javaCode != null) {
                    System.out.println("Código Java: " + result.javaCode);
                }
                System.out.println("Resultado:   " + result.result);
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // No mostrar stack trace para una interfaz más limpia
            }
        }

        scanner.close();
    }
    
    /**
     * Formatea los tokens para mostrar solo una muestra si hay demasiados.
     * 
     * @param tokens Lista de tokens a formatear.
     * @return Cadena representando los tokens.
     */    
    private static String formatTokens(List<Token> tokens) {
        if (tokens.size() > 10) {
            return tokens.subList(0, 5) + " ... " + 
                   tokens.subList(tokens.size()-5, tokens.size()) + 
                   " (" + tokens.size() + " tokens)";
        }
        return tokens.toString();
    }
    /**
     * Muestra ejemplos de código LISP para ayudar al usuario.
     */    
    private static void showHelp() {
        System.out.println("\n=== Ejemplos de código LISP ===");
        System.out.println("1. Operaciones aritméticas:");
        System.out.println("   (+ 2 3)");
        System.out.println("   (* 4 (- 7 2))");
        System.out.println("");
        System.out.println("2. Definición de variables:");
        System.out.println("   (setq x 10)");
        System.out.println("   (+ x 5)");
        System.out.println("");
        System.out.println("3. Definición de funciones:");
        System.out.println("   (defun cuadrado (n) (* n n))");
        System.out.println("   (cuadrado 5)");
        System.out.println("");
        System.out.println("4. Condicionales:");
        System.out.println("   (if (> 3 2) 'verdadero 'falso)");
        System.out.println("");
        System.out.println("5. Función recursiva:");
        System.out.println("   (defun factorial (n) (if (= n 0) 1 (* n (factorial (- n 1)))))");
        System.out.println("   (factorial 5)");
    }


    /**
     * Interpreta el código LISP proporcionado, pasando por los pasos de tokenización,
     * análisis sintáctico, generación de código Java y evaluación de la expresión.
     * 
     * @param code Código LISP a interpretar.
     * @param env Entorno en el que se evalúa la expresión.
     * @return El resultado de la interpretación, incluyendo tokens, expresión y resultado.
     * @throws Exception Si ocurre un error en cualquier paso de la interpretación.
     */    
    private static LispResult interpretLisp(String code, Environment env) {
        LispResult result = new LispResult();
        
        // Paso 1: Tokenización
        Tokenizer tokenizer = new Tokenizer(code);
        result.tokens = tokenizer.Tokenize();
        
        // Paso 2: Análisis sintáctico
        Parser parser = new Parser(result.tokens);
        result.expression = parser.parse();
        
        // Paso 3: Generación de código Java (si existe la funcionalidad)
        if (result.expression != null) {  // <-- Verificación de nulo añadida
            try {
                result.javaCode = JavaCodeGenerator.generateJavaCode(result.expression);
            } catch (Exception e) {
                System.out.println("Aviso: No se pudo generar código Java: " + e.getMessage());
                result.javaCode = null;
            }
        } else {
            result.javaCode = null;
        }
        // Paso 4: Evaluación
        if (result.expression != null) {  // <-- Verificación de nulo añadida
        result.result = result.expression.evaluate(env);}
        
        return result;
    }
}