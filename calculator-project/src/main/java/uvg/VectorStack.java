// VectorStack.java
package uvg;
import java.util.Vector;

/**
 * Implementación de la interface IStack usando Vector.
 * @param <T> tipo de dato a almacenar en la pila
 */
public class VectorStack<T> implements IStack<T> {
    private Vector<T> vector;
    
    /**
     * Constructor que inicializa una nueva pila vacía.
     */
    public VectorStack() {
        vector = new Vector<>();
    }
    
    @Override
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("No se puede insertar un elemento null");
        }
        vector.add(item);
    }
    
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return vector.remove(vector.size() - 1);
    }
    
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return vector.get(vector.size() - 1);
    }
    
    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }
    
    @Override
    public void clear() {
        vector.clear();
    }
}