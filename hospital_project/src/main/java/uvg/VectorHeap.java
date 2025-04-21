package uvg;

// --- Clase VectorHeap.java ---
import java.util.Vector;

public class VectorHeap<E extends Comparable<E>> {
    protected Vector<E> data;

    public VectorHeap() {
        data = new Vector<>();
    }

    public void add(E item) {
        data.add(item);
        percolateUp(data.size() - 1);
    }

    public E remove() {
        E min = data.firstElement();
        data.set(0, data.lastElement());
        data.remove(data.size() - 1);
        if (!data.isEmpty()) percolateDown(0);
        return min;
    }

    public E peek() {
        return data.firstElement();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    protected void percolateUp(int index) {
        E val = data.get(index);
        while (index > 0) {
            int parent = (index - 1) / 2;
            E parentVal = data.get(parent);
            if (val.compareTo(parentVal) >= 0) break;
            data.set(index, parentVal);
            index = parent;
        }
        data.set(index, val);
    }

    protected void percolateDown(int index) {
        int child;
        E val = data.get(index);
        while (index * 2 + 1 < data.size()) {
            child = index * 2 + 1;
            if (child + 1 < data.size() && data.get(child + 1).compareTo(data.get(child)) < 0)
                child++;
            if (data.get(child).compareTo(val) >= 0) break;
            data.set(index, data.get(child));
            index = child;
        }
        data.set(index, val);
    }
}