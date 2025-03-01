package uvg;

/**
 * Interfaz genérica para una lista.
 * Define las operaciones básicas que debe soportar una lista.
 *
 * @param <T> el tipo de elementos almacenados en la lista
 */
public interface IList<T> {
    
    /**
     * Agrega un elemento a la lista.
     *
     * @param item el elemento a agregar
     */
    void add(T item);

    /**
     * Elimina y devuelve un elemento de la lista.
     *
     * @return el elemento eliminado
     */
    T remove();

    /**
     * Verifica si la lista está vacía.
     *
     * @return {@code true} si la lista está vacía, {@code false} en caso contrario
     */
    boolean isEmpty();

    /**
     * Elimina todos los elementos de la lista.
     */
    void clear();
}
