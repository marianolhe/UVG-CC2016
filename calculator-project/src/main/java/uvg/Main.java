

package uvg;
// Main.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        IStack<Integer> stack = new VectorStack<>();
        ICalculadora calculator = new PostfixCalculatorImpl(stack);
        
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int result = calculator.evaluate(line);
                    System.out.println("Expresión: " + line);
                    System.out.println("Resultado: " + result);
                } catch (Exception e) {
                    System.err.println("Error al evaluar la expresión: " + e.getMessage());
                    if (e instanceof PostfixCalculatorException) {
                        PostfixCalculatorException pce = (PostfixCalculatorException) e;
                        System.err.println("Código de error: " + pce.getCodigoError());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}