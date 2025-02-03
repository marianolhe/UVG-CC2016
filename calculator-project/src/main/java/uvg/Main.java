package uvg;
// Main.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Initialize the stack and calculator
        IStack<Integer> stack = new VectorStack<>();
        ICalculadora calculator = new PostfixCalculatorImpl(stack);
        
        // Attempt to read expressions from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                try {
                    // Evaluate the expression and print the result
                    int result = calculator.evaluate(line);
                    System.out.println("Expresión: " + line);
                    System.out.println("Resultado: " + result);
                } catch (Exception e) {
                    // Handle evaluation errors
                    System.err.println("Error al evaluar la expresión: " + e.getMessage());
                    if (e instanceof PostfixCalculatorException) {
                        PostfixCalculatorException pce = (PostfixCalculatorException) e;
                        System.err.println("Código de error: " + pce.getCodigoError());
                    }
                }
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
