import java.io.*;
import java.util.*;

public class pathToStringTest
{
    // Function to turn the current path Stack into a string
    public static String pathToString(Stack<Character> path)
    {
        System.out.println("pathToString Called!");
        StringBuilder builder = new StringBuilder();

        // Create a new Stack to reverse the order of the current Stack
        Stack<Character> temp = new Stack<>();

        // Use temp to reverse the order of the stack
        while (!path.isEmpty())
            temp.push(path.pop());

        while (!temp.isEmpty())
        {
            builder.append(temp.pop());
            builder.append(' ');
        }

        String pathString = builder.toString();

        System.out.println(pathString);

        return pathString;
    }

    public static void main(String [] args)
    {
        Stack<Character> path = new Stack();

        path.push('l');
        path.push('l');
        path.push('l');
        path.push('u');
        path.push('d');

        System.out.println(pathToString(path));
    }
}