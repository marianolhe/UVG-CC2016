/**
 * Interface genérica para implementar una estructura de datos tipo Pila (Stack).
 * @param <T> tipo de dato a almacenar en la pila
 * @author Marian Olivares, Marcela Ordoñez
 */
public interface IStack<T> {
    /**
     * Añade un elemento al tope de la pila.
     * @param item elemento a añadir
     * @throws IllegalArgumentException si el elemento es null
     */
    void push(T item);

    /**
     * Remueve y retorna el elemento del tope de la pila.
     * @return el elemento del tope de la pila
     * @throws IllegalStateException si la pila está vacía
     */
    T pop();

    /**
     * Retorna el elemento del tope sin removerlo.
     * @return el elemento del tope de la pila
     * @throws IllegalStateException si la pila está vacía
     */
    T peek();

    /**
     * Verifica si la pila está vacía.
     * @return true si la pila está vacía, false en caso contrario
     */
    boolean isEmpty();

    /**
     * Elimina todos los elementos de la pila.
     */
    void clear();
}