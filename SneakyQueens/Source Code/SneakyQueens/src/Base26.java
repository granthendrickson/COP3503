import java.util.*;
import java.util.Arrays;


public class Base26 {
    public static void main(String[] args)
    {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int result = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");

        String str = sc.nextLine();

        double position = 0;

        //loop from the end of the string to the beginning
        for(int i = str.length() - 1; i >= 0; i--)
        {
            //get current letter
            char ch = str.charAt(i);

            //get numerical value of current letter and account for 0 based array
            int letter = (Arrays.binarySearch(alphabet, ch) + 1);
            
            double power = Math.pow(26, position);
            
            result += (int)power * letter;
            position++;
        }

        System.out.println(result);
        
        sc.close();
    }
}
