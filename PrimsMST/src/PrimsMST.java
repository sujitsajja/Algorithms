import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A function to find minimum spanning tree for undirected graph
 * using Prim's algorithm with indexed Priority Queue.
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-26
 */

public class PrimsMST {
    
    /**
     * @param args command line arguments
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("sampleInput.txt"));
        Graph G = Graph.readGraph(sc);
        System.out.println("The weight of MST : "+findMSTPrim(G));
    }

    /**
     * Function to find minimum spanning tree of a given undirected graph
     * 
     * @param G Undirected graph
     * 
     * @return Weight of the minimum spanning tree
     */
    private static long findMSTPrim(Graph G) {
        Comparator<Vertex> vertexComparator = (Vertex o1, Vertex o2) -> o1.distance - o2.distance;
        for (Vertex u : G)
            initialize(u);
        Vertex src = G.verts.get(1);
        src.distance = 0;
        long weightOfMST = 0;
        IndexedHeap pq = new IndexedHeap(G.numNodes+1,vertexComparator);
        for (Vertex u : G)
            pq.insert(u);
        while(!pq.isEmpty()){
            Vertex u = (Vertex) pq.remove();
            u.seen = true;
            weightOfMST += u.distance;
            for(Edge e:u.Adj){
                Vertex v = e.otherEnd(u);
                if(!v.seen && e.Weight<v.distance){
                    v.distance = e.Weight;
                    v.parent = u;
                    pq.percolateUp(v.rank);
                }
            }
        }
        return weightOfMST;
    }

    /**
     * Helper function to initialize the vertex
     * 
     * @param u Vertex to be initialized
     */
    private static void initialize(Vertex u) {
        u.seen = false;
        u.parent = null;
        u.distance = Integer.MAX_VALUE;
    }

}