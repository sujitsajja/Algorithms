import java.util.Comparator;

/**
 * Class to represent a binary heap
 * 
 * @author SujitS
 * @param <T> Generic type
 */

public class BinaryHeap<T> implements PQ<T> {
    //creating an array of least common ancestor i.e Object class
    Object[] pq;
    Comparator<T> c;
    static int size;

    /**
     * Create an empty priority queue of given maximum size
     */
    BinaryHeap(int n, Comparator<T> comp) {
        pq = new Object[n];
        c = comp;
        size = 0;
    }

    /**
     * Function to add an element into the priority queue
     * 
     * @param x Element to be added to the priority queue
     */
    @Override
    public void insert(T x) {
        add(x);
    }

    /**
     * Function to remove and return the minimum priority element
     * 
     * @return Element present at the top of the priority queue
     */
    @Override
    public T deleteMin() {
        return remove();
    }

    /**
     * Function to return the minimum priority element
     * 
     * @return Element present at the top of the priority queue
     */
    @Override
    public T min() {
        return peek();
    }

    /**
     * Function to add an element into the priority queue
     * 
     * @param x Element to be added to the priority queue
     */
    @Override
    public void add(T x) {
        if (size < pq.length) {
            size++;
            pq[size] = x;
            percolateUp(size);
        }
    }

    /**
     * Function to remove and return the minimum priority element
     * 
     * @return Element present at the top of the priority queue
     */
    @Override
    public T remove() {
        T min = null;
        if (size > 0) {
            min = (T) pq[1];
            pq[1] = pq[size];
            size--;
            percolateDown(1);
        }
        return min;
    }

    /**
     * Function to check if the priority queue is empty or not
     * 
     * @return True : If the priority queue is empty
     *         False: If the priority queue is not empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Function to return the minimum priority element
     * 
     * @return Element present at the top of the priority queue
     */
    @Override
    public T peek() {
        T min = null;
        if (size > 0)
            min = (T) pq[1];
        return min;
    }

    /**
     * Function to adjust the priority when the element
     * at index i may violate heap order with parent
     * 
     * @param i Index where there is a chance of violating heap order
     */
    void percolateUp(int i) {
        pq[0] = pq[i];
        while (c.compare((T) pq[i / 2], (T) pq[0]) > 0) {
            pq[i] = pq[i / 2];
            i = i / 2;
        }
        pq[i] = pq[0];

    }

    /**
     * Function to adjust the priority when the element
     * at index i may violate heap order with its children
     * 
     * @param i Index where there is a chance of violating heap order
     */
    void percolateDown(int i) {
        T x = (T) pq[i];
        while (2 * i <= size) {
            if (2 * i == size) {
                if (c.compare(x, (T) pq[size]) > 0) {
                    pq[i] = pq[size];
                    i = size;
                } else
                    break;
            } else {
                int sChild;
                if (c.compare((T) pq[2 * i], (T) pq[2 * i + 1]) > 0)
                    sChild = 2 * i + 1;
                else
                    sChild = 2 * i;
                if (c.compare(x, (T) pq[sChild]) > 0) {
                    pq[i] = pq[sChild];
                    i = sChild;
                } else
                    break;
            }
        }
        pq[i] = x;
    }

    /**
     * Function to create a heap.
     */
    void buildHeap() {
        for (int i = size / 2; i > 0; i--)
            percolateDown(i);
    }

}