package uvg;
// Main.java (mismo de la hoja2 pero con modificaciones)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Inciar la calculadora postfix y el stack
        IStack<Integer> stack = new VectorStack<>();
        ICalculadora calculator = new PostfixCalculatorImpl(stack);
        InfixtoPostfix InfixToPostfix = new InfixtoPostfix();
        
        String directorioActual = System.getProperty("user.dir"); System.out.println("Directorio actual: " + directorioActual);
        
        // Intentar leer los datos del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            // Leer linea por linea
            while ((line = reader.readLine()) != null) {
                try {
                    // evaluar la expresión e imprimir el resultado
                    String postfixExpression = InfixToPostfix.convert(line);
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
