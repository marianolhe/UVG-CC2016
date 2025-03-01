package uvg;

import java.util.ArrayList;

public class ArrayListStack<T> implements IStack<T> {
    private ArrayList<T> stack = new ArrayList<>();

    @Override
    public void push(T item) {
        stack.add(item);
    }

    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
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