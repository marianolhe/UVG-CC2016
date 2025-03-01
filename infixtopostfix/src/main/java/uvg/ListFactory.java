
package uvg;

import java.util.Scanner;

public class ListFactory {
    /**
     * @param <T>
     * @return
     */
    public static <T> IList<T> createList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de la lista:");
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
                System.out.println("Opción no válida. Se usará Lista Simplemente Enlazada por defecto.");
                list = new SingleLinkedList<>();
                break;
        }
        scanner.close();
        return list;
    }
}
