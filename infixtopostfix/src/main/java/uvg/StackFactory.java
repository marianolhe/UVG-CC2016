package uvg;
import java.util.Scanner;

public class StackFactory {
    public static <T> IStack<T> createStack() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de la pila:");
        System.out.println("1. ArrayListStack");
        System.out.println("2. VectorStack");
        System.out.println("3. ListStack (Lista Enlazada)");

        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return new ArrayListStack<>();
            case 2: return new VectorStack<>();
            case 3: return new ListStack<>();
            default:
                System.out.println("Opción no válida. Se usará VectorStack por defecto.");
                return new VectorStack<>();
        }
    }

}
