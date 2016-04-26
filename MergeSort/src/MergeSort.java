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
        mergeSort(input);
        System.out.println("The input after sorting is :");
        System.out.println(Arrays.toString(input));
    }

    /**
     * Function to call mergesort algorithm and return the result in arr
     * 
     * @param <T> The generic Type which implements the comparable interface
     * @param arr An input array which needs to be sorted
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        int n = arr.length;
        T[] tempArray = (T[])new Comparable[n];
        if(mergeSort(arr, tempArray, 0, n-1)==1){
            for(int i=0;i<n;i++)
                arr[i] = (T)tempArray[i];
        }
    }

    /**
     * Function to implement merge sort algorithm
     * 
     * @param arr An input array which needs to be sorted
     * @param tempArray Temporary array
     * @param low  The low index of the array
     * @param high The high index of the array
     * @param <T>  The generic Type which implements the comparable interface
     * 
     * @return 0: if data is in arr
     *         1: if data is in tempArray
     */
    public static <T extends Comparable<? super T>> int mergeSort(T[] arr, T[] tempArray, int low, int high) {
        // We use insertion sort when the input size is less than 11
        // as it has been proved that insertion sort is fatser for small inputs
        if (high - low <= 11) {
            insertionSort(arr, low, high);
            return 0;
        }
        int mid = (low + high) / 2;
        // split array into two halves and recursively solve both
        int t1 = mergeSort(arr, tempArray, low, mid);
        int t2 = mergeSort(arr, tempArray, mid + 1, high);
        // Merge both the arrays
        // if t1 == 0 data is in arr else data is in tempArr
        if(t1==0){
            merge(arr, tempArray, low, mid, high);
            return 1;
        }
        else{
            merge(tempArray, arr, low, mid, high);
            return 0;
        }
    }

    /**
     * A method which is used to merge two sorted arrays into one
     *
     * @param arr  The input array
     * @param low  low index of the array
     * @param mid  mid point of the array
     * @param high high index of the array
     * @param <T>  The generic Type which implements the comparable interface
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr1, T[] arr2, int low, int mid, int high) {
        int i = low;
        int j = mid+1;
        for(int k=low;k<=high;k++){
            if(j>high || (i<=mid && arr1[i].compareTo(arr1[j])<=0)){
                arr2[k] = arr1[i];
                i++;
            }
            else{
                arr2[k] = arr1[j];
                j++;
            }
        }
    }

    /**
     * Function to implement Insertion sort algorithm
     *
     * @param arr  The input array which needs to be sorted
     * @param low  The low index of the array
     * @param high The high index of the array
     * @param <T>  The generic Type which implements the comparable interface
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