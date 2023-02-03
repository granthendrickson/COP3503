// RunLikeHell.java
// Grant Hendrickson (gr251825)
// COP3503 Fall 2022

public class RunLikeHell
{
    // Initial recursive solutiuon
    public static int maxGainRecursion(int [] blocks, int k)
    {
        // Base Cases
        if (k == 0)
            return 0;

        if (k == 1)
            return blocks[k - 1];

        // We either skip the block or take it and skip the next one
        return Math.max(maxGainRecursion(blocks, k - 1), 
                        maxGainRecursion(blocks, k - 2) + blocks[k - 1]);
    }

    public static int maxGain(int [] blocks)
    {
        // Because we only ever have to look back at the last two nodes
        // We can use an array of length 3 for the solution
        int [] matrix = new int[3];

        // Base Cases
        matrix[0] = 0;
        matrix[1] = blocks[0];

        for (int i = 2; i <= blocks.length; i++)
            matrix[i % 3] = Math.max(matrix[(i - 1) % 3], matrix[(i - 2) % 3] + blocks[i - 1]);

        return matrix[blocks.length % 3];
    }

    public static double difficultyRating()
    {
        return 1.5;
    }

    // Found one solution in about an hour
    // Then took time to use an array of length 3
    public static double hoursSpent()
    {
        return 1.5;
    }
}