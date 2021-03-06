import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implement Dijkstra algorithm to find the
 * shortest path for the given directed graph
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-27
 */

public class Dijkstra {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("sampleInput.txt"));
        Graph g = Graph.readGraph(sc);
        if(checkNegative(g))
            System.out.println("The graph has negative edges. "
                    + "Dijkstra's algorithm cannot be applied");
        else{
            Dijkstra(g);
            printResult(g);
        }
    }

    /**
     * Function to implement Dijkstra's Algorithm for shortest path
     *
     * @param g Directed graph
     */
    private static void Dijkstra(Graph g) {
        Vertex src = g.verts.get(1);
        initialize(g, src);
        IndexedHeap<Vertex> pq = new IndexedHeap<>(g.numNodes + 1, 
                (Vertex v1, Vertex v2) -> v1.distance - v2.distance);
        for (Vertex u : g)
            pq.add(u);
        while (!pq.isEmpty()) {
            Vertex u = pq.remove();
            u.seen = true;
            for (Edge e : u.Adj) {
                Vertex v = e.otherEnd(u);
                if (!v.seen) {
                    if (relax(u, v, e))
                        pq.decreaseKey(v);
                }
            }
        }
    }

    /**
     * Helper function to initialize the graph
     *
     * @param g   Directed graph
     * @param src Vertex
     */
    private static void initialize(Graph g, Vertex src) {
        for (Vertex u : g) {
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
            u.seen = false;
            u.count = 0;
        }
        src.distance = 0;
        src.seen = true;
    }

    /**
     * Helper function to update the distance of the vertex from source
     *
     * @param u Vertex1
     * @param v Vertex2
     * @param e Edge between vertex1 and vertex2
     * 
     * @return True : If the distances are updated
     *         False: If the distances are not updated
     */
    private static boolean relax(Vertex u, Vertex v, Edge e) {
        if (v.distance > u.distance + e.Weight) {
            v.distance = u.distance + e.Weight;
            v.parent = u;
            return true;
        }
        return false;
    }

    /**
     * Function to print according to the output specification
     * 
     * @param g Directed graph
     */
    private static void printResult(Graph g) {
        int totalDistance = 0;
        for (Vertex u : g) {
            if (u.distance != Integer.MAX_VALUE)
                totalDistance += u.distance;
        }
        System.out.println(totalDistance);
        for (Vertex u : g) {
            if (u.parent != null)
                System.out.println(u + " " + u.distance + " " + u.parent);
            else if (u.distance != Integer.MAX_VALUE)
                System.out.println(u + " " + u.distance + " -");
            else
                System.out.println(u + " INF -");
        }
    }

    /**
     * Function to check if the given graph has negative weight edges
     * 
     * @param g Directed graph
     * 
     * @return True : If the graph has negative weight edges
     *         False: If the graph does not have negative weight edges
     */
    private static boolean checkNegative(Graph g) {
        for(Vertex v:g){
            for(Edge e:v.Adj){
                if(e.Weight<0)
                    return true;
            }
        }
        return false;
    }

}