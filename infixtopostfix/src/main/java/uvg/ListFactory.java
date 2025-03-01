package uvg;

import java.util.Scanner;

public class ListFactory {
     public static <T> IList<T> createList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de la lista:");
        System.out.println("1. Simplemente Enlazada");
        System.out.println("2. Doblemente Enlazada");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: return new SingleLinkedList<>();
            case 2: return new DoubleLinkedList<>();
            default:
                System.out.println("Opción no válida. Se usará Lista Simplemente Enlazada por defecto.");
                return new SingleLinkedList<>();
        }
    }
}
