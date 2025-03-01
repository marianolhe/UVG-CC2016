package uvg;

import java.util.Scanner;

/**
 * Clase de fábrica para crear pilas según la entrada del usuario.
 */
public class StackFactory {
    /**
     * Crea una pila según la selección del usuario.
     * 
     * @param <T> el tipo de elementos que se almacenarán en la pila
     * @return una instancia de IStack<T> basada en la elección del usuario
     */

    public static <T> IStack<T> createStack() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de la pila:");

        System.out.println("1. ArrayListStack");
        System.out.println("2. VectorStack");
        System.out.println("3. ListStack (Lista Enlazada)");

        int choice = scanner.nextInt();
        scanner.close();
        
        switch (choice) {
            case 1: return new ArrayListStack<>();
            case 2: return new VectorStack<>();
            case 3: return new ListStack<>();
            default:
                System.out.println("Opción no válida. Se usará VectorStack por defecto.");
                return new VectorStack<>();
        }
    }
}
