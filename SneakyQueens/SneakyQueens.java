// SneakyQueens
// Grant Hendrickson (gr251825)
// COP3503C-00317 Fall 22

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SneakyQueens
{
    // Given an ArrayList of coordinate strings representing the locations of the queens on a
    // boardSize × boardSize chess board, return true if none of the queens can attack one another. Otherwise,
    // return false.
    public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
    {
        // hashsets do not allow for duplicate values so these are a great for checking for attacking queens
        Set<Integer> columns = new HashSet<>();
        Set<Integer> rows = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        
        for (int i = 0; i < coordinateStrings.size(); i++)
        {
            int column = 0;
            int row = 0;

            // loop through each char of a given coordinate string
            // use Horner's Rule to effeciently get integer values for the columns and rows
            for (int j = 0; j < coordinateStrings.get(i).length(); j++)
            {
                if (Character.isLetter(coordinateStrings.get(i).charAt(j)))
                {
                    column = column * 26;
                    column += coordinateStrings.get(i).charAt(j) - 'a' + 1;
                }

                if (Character.isDigit(coordinateStrings.get(i).charAt(j)))
                {
                    row = row * 10;
                    row += coordinateStrings.get(i).charAt(j) - '0';
                }
            }

            // the two diagonals can be calculated with the following math
            int diagonal1 = column - row;
            int diagonal2 = column + row;
            
            // now we add each value to the accoding hashet and if we reach a duplicate we have a hit
            // NOTE: the hashset add function returns true if a value can be added 
            //       and false if it hits a duplicate
            if (columns.add(column)  == false || rows.add(row)  == false || 
                diagonals1.add(diagonal1)  == false || diagonals2.add(diagonal2) == false)
            {
                return false;
            }
        }
        return true;
    }

    public static double difficultyRating()
    {
        //it took a lot of drawing out and thinking but once I had the concept coding it up was easy
        double diff = 3.5;

        return diff;
    }

    public static double hoursSpent()
    {
        //a lot of this was spent creating an inefficient method to get the coordinates
        double hours = 7.0;

        return hours;
    }
}