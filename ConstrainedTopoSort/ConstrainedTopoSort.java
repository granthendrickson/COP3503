// ConstainedTopoSort.java
// Grant Hendrickson (gr251825)
// COP3503C-00317 Fall 22

import java.util.*;
import java.io.*;

public class ConstrainedTopoSort
{
    boolean [][] matrix;

    public ConstrainedTopoSort(String filename) throws IOException
    {
        Scanner s = new Scanner(new File(filename));

        // get the number of vertices in the graph
        int n = s.nextInt();

        matrix = new boolean[n][n];

        for (int i = 0; i < n; i++)
        {
            // get the number of vertices this node points to
            int numEdges = s.nextInt();

            for (int j = 0; j < numEdges; j++)
            {
                matrix[i][s.nextInt() - 1] = true;
            }
        }
    }

    public void printMatrix()
    {
        int n = matrix.length;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                int isEdge = matrix[i][j] ? 1 : 0;
                System.out.print(isEdge + " ");
            }
            System.out.println();
        }
    }

    // this was heavily inspired by the topoSort method covered in class
    public boolean hasConstrainedTopoSort(int x, int y)
    {
        // This first section makes sure that there is a valid topological sort of the graph
        // because otherwise there is no point in checking if x can come before y
        int [] incomingEdges = new int[matrix.length];
        int count = 0;

        // count the number of incoming edges for each vertex
        for (int i = 0;  i < matrix.length; i++)
            for(int j = 0; j < matrix.length; j++)
                incomingEdges[j] += (matrix[i][j] ? 1 : 0);

        Queue<Integer> q = new ArrayDeque<Integer>();

        for (int i = 0; i < matrix.length; i++)
            if (incomingEdges[i] == 0)
                q.add(i);


        while (!q.isEmpty())
        {
            // pull from the queue and add it to the topological sort
            int node = q.remove();

            ++count;

            // decrement the incoming edge counts of all the vertices the curent vertex points to
            for (int i = 0; i < matrix.length; i++)
                if (matrix[node][i] && --incomingEdges[i] == 0)
                    q.add(i);
        }
        
        // check for a cycle in the graph
        if (count != matrix.length)
        {
            return false;
        }

        // now we begin the search to find if x can come before y
        // we can use a DFS on the y node to see if x comes afer it
        // if so we should return false
        return this.DFS(x, y);
    }

    // the following DFS methods were heavily inspired by those covered in class
    // the idea of using a DFS is that if x can be found after y
    // then y is a "prerequisite" to x and we should return false
    public boolean DFS(int x, int start)
    {
        start -=1;
        boolean [] visited = new boolean[matrix.length];
        return DFS(start, x, visited);
    }

    public boolean DFS(int node, int x, boolean [] visited)
    {
        // whenever we encounter a node, mark it as visited
        visited[node] = true;

        // check to see if we encounter x after y
        if ((node + 1) == x)
        {
            return false;
        }

        // now call DFS on all of the current node's neighbors
        for (int i = 0; i < matrix.length; i++)
            if(matrix[node][i] && !visited[i])
                if (DFS(i, x, visited) == false)
                    return false;

        return true;
    }

    public static double difficultyRating()
    {
        return 2.5;
    }

    public static double hoursSpent()
    {
        return 4.0;
    }
}