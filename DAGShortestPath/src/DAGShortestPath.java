import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Implement DAG shortest path algorithm to find the
 * shortest path for the given directed graph
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-27
 */

public class DAGShortestPath {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("sampleInput.txt"));
        Graph g = Graph.readGraph(sc);
        ArrayDeque<Vertex> topologicalOrder = new ArrayDeque<>();
        if (DFS(g, topologicalOrder))
            System.out.println("The given graph is cyclic. "
                    + "DAG shortest path algorithm cannot be applied.");
        else{
            DAGShortestPath(g, topologicalOrder);
            printResult(g);
        }
    }

    /**
     * Function to implement DFS Algorithm to check for cycles
     * and also to find the topological ordering of the vertices
     *
     * @param g Directed Graph
     * 
     * @return True : Cycle is detected
     *         False: Cycle is not detected
     */
    private static boolean DFS(Graph g, ArrayDeque<Vertex> topologicalOrder) {
        initialize(g, null);
        Vertex u = g.verts.get(1);
        u.seen = true;
        u.color = Vertex.Color.grey;
        return DFSVisit(u, topologicalOrder);
    }

    /**
     * Recursive helper function that implements DFS algorithm
     *
     * @param u                : Current vertex
     * @param topologicalOrder : Stack that is used to store the topological order
     * 
     * @return True : Cycle is detected
     *         False: Cycle is not detected
     */
    private static boolean DFSVisit(Vertex u, ArrayDeque<Vertex> topologicalOrder) {
        boolean cycle;
        for (Edge e : u.Adj) {
            Vertex v = e.otherEnd(u);
            if (!v.seen) {
                v.seen = true;
                v.color = Vertex.Color.grey;
                cycle = DFSVisit(v, topologicalOrder);
                if (cycle)
                    return true;
            } else if (v.color == Vertex.Color.grey)
                return true;
        }
        u.color = Vertex.Color.black;
        topologicalOrder.push(u);
        return false;
    }

    /**
     * Function to implement DAG shortest path Algorithm
     *
     * @param g Directed graph
     */
    private static void DAGShortestPath(Graph g, ArrayDeque<Vertex> topologicalOrder) {
        Vertex src = g.verts.get(1);
        initialize(g, src);
        while (!topologicalOrder.isEmpty()) {
            Vertex u = topologicalOrder.pop();
            for (Edge e : u.Adj) {
                Vertex v = e.otherEnd(u);
                relax(u, v, e);
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
            u.color = Vertex.Color.white;
        }
        if (src != null) {
            src.distance = 0;
            src.seen = true;
        }
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

}