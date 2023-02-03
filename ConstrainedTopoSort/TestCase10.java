// Sean Szumlanski
// Jackson Mazer
// COP 3503, Fall 2022

// ====================================
// ConstrainedTopoSort: TestCase10.java
// ====================================
// A test case where the the graph has no valid topological sort.


import java.io.*;
import java.util.*;

public class TestCase10
{
	private static void failwhale(String params)
	{
		System.out.println("Test Case #10: hasConstrainedTopoSort(" + params + "): fail whale :(");
		System.exit(0);
	}

	public static void main(String [] args) throws IOException
	{
		ConstrainedTopoSort t = new ConstrainedTopoSort("input_files/g10.txt");

		if (t.hasConstrainedTopoSort(1, 2) != false) failwhale("1, 2");
		if (t.hasConstrainedTopoSort(3, 4) != false) failwhale("3, 4");
		if (t.hasConstrainedTopoSort(4, 3) != false) failwhale("4, 3");
		if (t.hasConstrainedTopoSort(7, 1) != false) failwhale("7, 1");
		if (t.hasConstrainedTopoSort(1, 7) != false) failwhale("1, 7");
		if (t.hasConstrainedTopoSort(1, 6) != false) failwhale("1, 6");

		System.out.println("Test Case #10: PASS (Hooray!)");
	}
}