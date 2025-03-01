package uvg;

import java.util.LinkedList;

/**
 * Implementación de la interfaz IStack utilizando un LinkedList.
 * 
 * @param <T> el tipo de elementos almacenados en la pila
 */

public class ListStack<T> implements IStack<T> {
    private LinkedList<T> stack = new LinkedList<>();

    @Override
    public void push(T item) {
        stack.addFirst(item);
    }
    
     


    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return stack.removeFirst();
    }
    
     


    @Override
    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return stack.getFirst();
    }
    
     


    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
     


    @Override
    public void clear() {
        stack.clear();
    }
    
     

}
