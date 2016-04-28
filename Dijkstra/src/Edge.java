/**
 * Class that represents an arc in a Graph
 *
 * @author SujitS
 */

public class Edge {
    public Vertex From; // head vertex
    public Vertex To; // tail vertex
    public int Weight;// weight of the arc
    boolean isShortestPath;

    /**
     * Constructor for Edge
     *
     * @param u : Vertex - The head of the arc
     * @param v : Vertex - The tail of the arc
     * @param w : int - The weight associated with the arc
     */
    Edge(Vertex u, Vertex v, int w) {
        From = u;
        To = v;
        Weight = w;
        isShortestPath = false;
    }

    /**
     * Method to find the other end end of the arc given a vertex reference
     *
     * @param u : Vertex
     * 
     * @return Vertex on the other end of edge
     */
    public Vertex otherEnd(Vertex u) {
        // if the vertex u is the head of the arc, then return the tail else return the head
        if (From == u)
            return To;
        else
            return From;
    }

}