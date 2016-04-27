import java.util.Comparator;
import java.util.Scanner;

/**
 * Implement heap sort algorithm
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-26
 */

public class HeapSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements : ");
        int n = sc.nextInt();
        Integer[] input = new Integer[n+1];
        System.out.println("Enter the elements seperated by Space/Enter :");
        for(int i=0;i<n;i++)
            input[i+1] = sc.nextInt();
        heapSort(input);
        System.out.println("The order of elements after sorting are :");
        for(int i=0;i<n;i++)
            System.out.print(input[i+1]+" ");
        System.out.println();
    }

    /**
     * Function to sort the given array using heap sort
     * 
     * @param input Array of integers to be sorted
     */
    private static void heapSort(Integer[] input) {
        Comparator<Integer> comparator = (Integer o1, Integer o2) -> o2 - o1;
        BinaryHeap heap = new BinaryHeap(input,comparator);
        heap.heapSort(input, comparator);
    }

}

/**
 * Sample Input:
 * 10
 * 10 9 8 7 6 5 4 3 2 1
 * 
 * Sample Output:
 * 1 2 3 4 5 6 7 8 9 10
 */