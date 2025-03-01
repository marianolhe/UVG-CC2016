package uvg;

import java.util.Scanner;

/**
 * Clase de fábrica para crear listas basadas en la entrada del usuario.
 */

public class ListFactory {
    /**
     * Crea una lista basada en la selección del usuario.
     * 
     * @param <T> el tipo de elementos que se almacenarán en la lista
     * @return una instancia de IList<T> basada en la elección del usuario

     */
    public static <T> IList<T> createList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de la lista:");
        System.out.println("1. Simplemente Enlazada");
        System.out.println("2. Doblemente Enlazada");

        int choice = scanner.nextInt();
        IList<T> list;
        switch (choice) {
            case 1:
                list = new SingleLinkedList<>();
                break;
            case 2:
                list = new DoubleLinkedList<>();
                break;
            default:
                System.out.println("Opción no válida. Se usará Lista Simplemente Enlazada por defecto.");
                list = new SingleLinkedList<>();
                break;
        }
        scanner.close();
        return list;
    }
}
