import java.util.Comparator;

/**
 * Class to implement binary heap
 * 
 * @author SujitS
 * @param <T> Generic type
 */

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {

    /**
     * Create an empty priority queue of given maximum size
     */
    IndexedHeap(int n, Comparator<T> comp) {
        super(n, comp);
    }

    /**
     * restore heap order property after the priority of x has decreased
     */
    void decreaseKey(T x) {
        percolateUp(x.getIndex());
    }

    /**
     * Function to create a heap.
     */
    @Override
    void buildHeap() {
        super.buildHeap();
        //update indices after buildheap
        for (int j = 1; j <= size; j++) {
            T temp = (T) pq[j];
            temp.putIndex(j);
        }

    }

    /**
     * Function to adjust the priority when the element
     * at index i may violate heap order with parent
     * 
     * @param i Index where there is a chance of violating heap order
     */
    @Override
    void percolateUp(int i) {
        super.percolateUp(i);
        //update indices after percolateUp operation
        for (int j = 1; j <= size; j++) {
            T temp = (T) pq[j];
            temp.putIndex(j);
        }
    }

    /**
     * Function to adjust the priority when the element
     * at index i may violate heap order with its children
     * 
     * @param i Index where there is a chance of violating heap order
     */
    @Override
    void percolateDown(int i) {
        super.percolateDown(i);
        //update indices after percolateDown operation
        for (int j = 1; j <= size; j++) {
            T temp = (T) pq[j];
            temp.putIndex(j);
        }
    }

}