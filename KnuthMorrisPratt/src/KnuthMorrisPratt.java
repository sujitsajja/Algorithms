import java.util.Scanner;

/**
 * To find if the given string contains another given string
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-04-01
 */

public class KnuthMorrisPratt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the main string : ");
        String input = sc.nextLine();
        System.out.print("Enter the string you want to search : ");
        String search = sc.next();
        int k = KMPMatcher(input,search);
        if(k!=-1)
            System.out.println("The search string is contained "
                    + "in the main string at the position of "+k);
        else
            System.out.println("The search string is not contained "
                    + "in the main string");
    }

    /**
     * Function to find the position of search string in the given string
     * 
     * @param input String which has to be searched
     * @param search Pattern to be searched for
     * 
     * @return position of the pattern in the string
     */
    private static int KMPMatcher(String input, String search) {
        int result = -1;
        int n = input.length();
        int m = search.length();
        // We first compute the prefix function
        int[] prefix = computePrefix(search);
        int k = 0;
        for(int i=0;i<n;i++){
            while(k>0 && search.charAt(k)!=input.charAt(i))
                k = prefix[k];
            if(search.charAt(k)==input.charAt(i))
                k++;
            if(k==m){
                result = i-m+1;
                break;
            }
        }
        return result;
    }

    /**
     * prefix function which compares pattern with itself
     * 
     * @param search pattern to be searched
     * 
     * @return array of prefix values
     */
    private static int[] computePrefix(String search) {
        int m = search.length();
        int[] prefix = new int[m];
        prefix[0] = 0;
        int k=0;
        for(int i=1;i<m;i++){
            while(k>0 && search.charAt(k)!=search.charAt(i))
                k = prefix[k];
            if(search.charAt(k)==search.charAt(i))
                k++;
            prefix[i] = k;
        }
        return prefix;
    }

}

/**
 * Sample input:
 * what are you doing?
 * you
 * 
 * Sample output:
 * 9
 */