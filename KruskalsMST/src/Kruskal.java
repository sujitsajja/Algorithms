import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * A function to find minimum spanning tree 
 * for undirected graph using Kruskal's algorithm
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-28
 */

public class Kruskal {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("sampleInput.txt"));
        Graph G = Graph.readGraph(sc);
        long weightOfMST = findMSTKruskal(G);
        System.out.println(weightOfMST);
    }

    /**
     * Function to find minimum spanning tree of a given undirected graph
     * 
     * @param G undirected graph
     * 
     * @return weight of the MST
     */
    private static long findMSTKruskal(Graph G) {
        for (Vertex u : G)
            makeSet(u);
        long weightOfMST = 0;
        PriorityQueue<Edge> pq = G.pq;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            Vertex representativeU = find(e.To);
            Vertex representativeV = find(e.From);
            // If both the vertices do not belong
            // to the same component then we join them
            if(representativeU != representativeV){
                weightOfMST = weightOfMST + e.Weight;
                union(representativeU,representativeV);
            }
        }
        return weightOfMST;
    }

    /**
     * Helper function to initialize the vertex
     * 
     * @param u Vertex to be initialized
     */
    private static void makeSet(Vertex u) {
        u.parent = u;
        u.rank = 0;
    }

    /**
     * Function to find the representative of the component
     * 
     * @param u Vertex present in the component
     * 
     * @return Representative of the component
     */
    private static Vertex find(Vertex u) {
        if(u!=u.parent)
            u.parent = find(u.parent);
        return u.parent;
    }

    /**
     * Function to join two components
     * 
     * @param u Representative of first component
     * @param v Representative of second component
     */
    private static void union(Vertex u, Vertex v) {
        if(u.rank>v.rank)
            v.parent = u;
        else if(v.rank>u.rank)
            u.parent = v;
        else{
            u.parent = v;
            v.rank++;
        }
    }
    
}