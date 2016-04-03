import java.util.Scanner;

/**
 * To find the majority element in the given array
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-03-25
 */

public class MajorityElement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array : ");
        int n = sc.nextInt();
        int[] input = new int[n];
        System.out.println("Enter the elements seperated by Space/Enter :");
        for(int i=0;i<n;i++)
            input[i] = sc.nextInt();
        int k = findMajority(input);
        System.out.println("The majority element in the given array is : "+k);
    }

    /**
     * Function to find the majority element in the given array
     * 
     * @param input Array
     * 
     * @return Majority element
     */
    private static int findMajority(int[] input) {
        int n = input.length;
        int majority = input[0];
        int count = 1;
        for(int i=1;i<n;i++){
            if(input[i]==majority)
                count++;
            else{
                count--;
                if(count==0){
                    majority = input[i];
                    count = 1;
                }
            }
        }
        return majority;
    }

}

/**
 * Sample input:
 * 19
 * 3 3 3 3 3 1 3 1 6 2 1 1 1 9 3 3 1 1 3
 * 
 * Sample output:
 * 3
 */