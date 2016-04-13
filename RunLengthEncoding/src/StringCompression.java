import java.util.Scanner;

/**
 * To compress the given string
 * 
 * @author SujitS
 * 
 * @version 1.0
 * @since 2016-03-29
 */

public class StringCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string to be compressed : ");
        String input = sc.next();
        String output = stringCompress(input);
        System.out.println("The string after compression is : "+output);
    }

    /**
     * Function to compress the string
     * 
     * @param input String to be compressed
     * 
     * @return Compressed string
     */
    private static String stringCompress(String input) {
        int length = input.length();
        int count = 0;
        StringBuilder output = new StringBuilder();
        char curr = input.charAt(0);
        for(int i=0;i<length;i++){
            char temp = input.charAt(i);
            // If the current char is the same as previous one
            // we increment the counter and move to the next one
            if(curr == temp)
                count++;
            // If the current char is not same as previous one
            // we add the char and its frequency to output
            else{
                output.append(curr);
                output.append(count);
                // reset the counter for next char
                count = 1;
                curr = temp;
            }
        }
        // The last char and its frequency are yet to be added
        output.append(curr);
        output.append(count);
        return output.toString();
    }

}

/**
 * Sample input:
 * aaaassaaassssdsa
 * 
 * Sample output:
 * a4s2a3s4d1s1a1
 */