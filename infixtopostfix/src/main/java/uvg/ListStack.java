package uvg;

import java.util.LinkedList;

public class ListStack<T> implements IStack<T> {
    private LinkedList<T> stack = new LinkedList<>();

    @Override
    public void push(T item) {
        stack.addFirst(item);
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.removeFirst();
    }

    @Override
    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
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
