// Grant Hendrickson
// COP 3503, Fall 2022

// ====================
// SneakyKnights.java
// ====================
//

import java.io.*;
import java.util.*;

class CoordinatePairs
{
  private final int column;
  private final int row;

  public CoordinatePairs(int column, int row)
  {
    this.column = column;
    this.row = row;
  }

  public int getColumn()
  {
    return column;
  }

  public int getRow()
  {
    return row;
  }

  // create an equals method to compare coordinate pairs
  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;

    if (o == null)
      return false;

    if (getClass() != o.getClass())
      return false;

    CoordinatePairs other = (CoordinatePairs) o;
    return this.column == other.column && this.row == other.row;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int code = 1;

    code = prime * code + column;
    code = prime * code + row;

    return code;
  }
}

public class SneakyKnights
{
  public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
  {
    Set<CoordinatePairs> coordinates = new HashSet<>();

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

        // check the row / column pairs that the current knight can attack
        // each of these lookups should be O(1) on the avg case
        if (coordinates.contains(new CoordinatePairs(column + 1, row + 2)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column + 1, row - 2)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column + 2, row + 1)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column + 2, row - 1)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column - 1, row + 2)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column - 1, row - 2)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column - 2, row + 1)))
        {
          return false;
        }

        if (coordinates.contains(new CoordinatePairs(column - 2, row - 1)))
        {
          return false;
        }

        // if we made it this far that means there are no collisions
        // we can add the current knight to the hashsets
        coordinates.add(new CoordinatePairs(column, row));
      }

      // if we break out of the for loop without returning
      // all the knights are safe
      return true;
    }

    public static double difficultyRating()
    {
        // doing research and figuring out the coordinate object took a bit
        // just because of a bad OOP foundation
        double diff = 2.0;

        return diff;
    }

    public static double hoursSpent()
    {
        // as said above most of this time was figuring out how to store the coordinates
        double hours = 2.0;

        return hours;
    }
}
