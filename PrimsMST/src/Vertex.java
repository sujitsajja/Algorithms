import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a vertex
 * 
 * @author SujitS
 */

public class Vertex implements Index {

    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj; // adjacency list; use LinkedList or ArrayList
    public int rank;

    /**
     * Constructor for the vertex
     *
     * @param n : int - name of the vertex
     */
    Vertex(int n) {
        name = n;
        seen = false;
        parent = null;
        Adj = new ArrayList<>();
    }

    /**
     * Helper function to assign the rank to a vertex
     * 
     * @param index Rank of the vertex
     */
    @Override
    public void putIndex(int index) {
        this.rank = index;
    }

    /**
     * Helper function to get the rank of the vertex
     * 
     * @return Rank of the vertex
     */
    @Override
    public int getIndex() {
        return this.rank;
    }

}