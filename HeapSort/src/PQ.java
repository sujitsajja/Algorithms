/**
 * Interface to represent the priority queue
 * 
 * @author SujitS
 * 
 * @param <T> Generic type
 */

public interface PQ<T> {
    public void insert(T x);
    public T deleteMin();
    public T min();
    public void add(T x);
    public T remove();
    public T peek();
}