package uvg;
// Main.java (mismo de la hoja2 pero con modificaciones)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase principal de la aplicación de la Calculadora Postfija.
 * Esta clase inicializa la calculadora y procesa las expresiones de entrada.
 */
public class Main {
    
    /**
     * Método principal para ejecutar la Calculadora Postfija.
     * 
     * Precondiciones: 
     * - El archivo "datos.txt" debe existir en el directorio de trabajo actual.
     * 
     * Postcondiciones:
     * - Evalúa las expresiones del archivo y muestra los resultados en la consola.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */


    public static void main(String[] args) {
        // Inciar la calculadora postfix y el stack
        IStack<Integer> stack = StackFactory.createStack();
        ICalculadora calculator = new PostfixCalculatorImpl(stack); // Calculator implementation using the stack

        InfixtoPostfix InfixtoPostfix = new InfixtoPostfix();
        
        String directorioActual = System.getProperty("user.dir"); 
        System.out.println("Directorio actual: " + directorioActual);
        
        // Intentar leer los datos del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            // Leer linea por linea
            while ((line = reader.readLine()) != null) {
                try {
                    // evaluar la expresión e imprimir el resultado
                    String postfixExpression = InfixtoPostfix.convert(line);
                    int result = calculator.evaluate(postfixExpression);
                    System.out.println("Expresión: " + line);
                    System.out.println("Resultado: " + result);
                } catch (Exception e) {
                    // evaluar errores
                    System.err.println("Error al evaluar la expresión: " + e.getMessage());
                    if (e instanceof PostfixCalculatorException) {
                        PostfixCalculatorException pce = (PostfixCalculatorException) e;
                        System.err.println("Código de error: " + pce.getCodigoError());
                    }
                }
            }
        } catch (IOException e) {
            // en caso de errores de lectura
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
