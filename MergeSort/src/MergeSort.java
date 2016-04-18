import java.util.Arrays;
import java.util.Scanner;

/**
 * Implement best version of MergeSort
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-17
 */

public class MergeSort {

    /**
     * @param args the command line argument
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements : ");
        int n = sc.nextInt();
        Integer[] input = new Integer[n];
        System.out.println("Enter the elements seperated by space/enter :");
        for(int i=0;i<n;i++)
            input[i] = sc.nextInt();
        mergeSort(input, 0, n-1);
        System.out.println("The input after sorting is :");
        System.out.println(Arrays.toString(input));
    }

    /**
     * Function to implement merge sort algorithm
     * 
     * @param arr  An input array which needs to be sorted
     * @param low  The low index of the array
     * @param high The high index of the array
     * @param <T>  The generic Type which is implements the comparable interface
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int low, int high) {
        if (low >= high) return;
        // We use insertion sort when the input size is less than 11
        // as it has been proved that insertion sort is fatser for small inputs
        if (high - low <= 11) {
            insertionSort(arr, low, high);
            return;
        }
        int mid = (low + high) / 2;
        // split array into two halves and recursively solve both
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        // Merge both the arrays
        merge(arr, low, mid, high);
    }

    /**
     * A method which is used to merge two sorted arrays into one
     *
     * @param arr  The input array
     * @param low  low index of the array
     * @param mid  mid point of the array
     * @param high high index of the array
     * @param <T>  The generic Type which is implements the comparable interface
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr, int low, int mid, int high) {
        Comparable[] aux = new Comparable[high - low + 1];
        int arr1ptr, arr1end, arr2ptr, arr2end;
        arr1ptr = low;
        arr1end = mid;
        arr2ptr = mid + 1;
        arr2end = high;
        int auxPtr = 0;
        while ((arr1ptr <= arr1end) && (arr2ptr <= arr2end)) {
            if (arr[arr1ptr].compareTo(arr[arr2ptr]) < 0)
                aux[auxPtr++] = arr[arr1ptr++];
            else
                aux[auxPtr++] = arr[arr2ptr++];
        }
        // copying rest of the elements into temp list
        if (arr1ptr > arr1end) {
            for (int k = arr2ptr; k <= arr2end; k++)
                aux[auxPtr++] = arr[k];
        } else {
            for (int k = arr1ptr; k <= arr1end; k++)
                aux[auxPtr++] = arr[k];
        }
        int l = 0;
        // Copy temp list back to main array
        for (int k = low; k <= high; k++)
            arr[k] = (T) aux[l++];
    }

    /**
     * Function to implement Insertion sort algorithm
     *
     * @param arr  The input array which needs to be sorted
     * @param low  The low index of the array
     * @param high The high index of the array
     * @param <T>  The generic Type which is implements the comparable interface
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            T temp = arr[i];
            int j;
            for (j = i - 1; j >= low && temp.compareTo(arr[j]) < 0; j--)
                arr[j + 1] = arr[j];
            arr[j + 1] = temp;
        }
    }

}

/**
 * Sample Input:
 * 15
 * 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
 * 
 * Sample Output:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
 */