import java.util.Arrays;
import java.util.Scanner;

/**
 * Implement Quick sort algorithm for generic
 * data types using two pivots
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-06
 */

public class QuickSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array : ");
        int n = sc.nextInt();
        Integer[] input = new Integer[n];
        System.out.println("Enter the elements seperated by Space/Enter :");
        for(int i=0;i<n;i++)
            input[i] = sc.nextInt();
        quickSort(input, 0, n-1);
        System.out.println("Elements of the array after sorting : "+Arrays.toString(input));
    }

    /**
     * Recursive function to sort elements of an array
     * 
     * @param <T> Data type
     * @param arr array to be sorted
     * @param left Start index to be considered
     * @param right End index to be considered
     */
    private static <T extends Comparable<? super T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            // Generate two random pivots with in left and right index
            int pivot1 = randomWithRange(left, right);
            int pivot2 = randomWithRange(left, right);
            // Place one pivot at the front and another pivot at the back
            swap(arr, left, pivot1);
            swap(arr, right, pivot2);
            // Make sure the first pivot is smaller than the second one
            if (arr[left].compareTo(arr[right]) > 0)
                swap(arr, left, right);
            T X1 = arr[left];
            T X2 = arr[right];
            int l = left + 1;       // Left pointer
            int k = left + 1;       // Middle pointer
            int g = right - 1;      // Right pointer
            while (k <= g) {
                if (arr[k].compareTo(X1) < 0) {
                    swap(arr, k, l);
                    l++;
                } else if (arr[k].compareTo(X2) >= 0) {
                    // Keep moving the right pointer till we find
                    // an element which is less than second pivot
                    while (arr[g].compareTo(X2) > 0 && k < g) 
                        g--;
                    swap(arr, k, g);
                    g--;
                    if (arr[k].compareTo(X1) < 0) {
                        swap(arr, k, l);
                        l++;
                    }
                }
                k++;
            }
            l--;
            g++;
            // Place the pivots in their positions
            swap(arr, left, l);
            swap(arr, right, g);
            // Recursively sort the elements between pivots
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, g - 1);
            quickSort(arr, g + 1, right);
        }
    }

    /**
     * Helper function to swap two elements of an array
     * 
     * @param <T> Data type
     * @param arr input array
     * @param i Index 1
     * @param r Index 2
     */
    private static <T extends Comparable<? super T>> void swap(T[] arr, int i, int r) {
        T temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
    }

    /**
     * Helper function to generate a random number between given range
     * 
     * @param min Minimum number
     * @param max Maximum number
     * 
     * @return random number between minimum and maximum
     */
    private static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

}

/**
 * Sample input:
 * 5
 * 9 7 5 3 1
 * 
 * Sample output:
 * 1 3 5 7 9
 */