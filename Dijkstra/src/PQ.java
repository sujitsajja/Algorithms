/**
 * Interface to represent priority queue
 * 
 * @author SujitS
 * 
 * @param <T> Generic type
 */

public interface PQ<T> {
    void insert(T x);
    T deleteMin();
    T min();
    void add(T x);
    T remove();
    T peek();
}