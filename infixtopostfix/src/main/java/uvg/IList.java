package uvg;

public interface IList<T> {
    void add(T item);
    T remove();
    boolean isEmpty();
    void clear();
}
