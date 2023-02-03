// Sean Szumlanski
// Jackson Mazer
// COP 3503, Fall 2022

// ====================================
// ConstrainedTopoSort: TestCase07.java
// ====================================
// A small test case for ConstrainedTopoSort.

// (1)     (4)     (7)
//  |  __/  |   __/ |
// (2)/_   (5)~"    |
//  | \	"""-|-._    | 
// (3) "'~-(6)  "~-(8)  

import java.io.*;
import java.util.*;

public class TestCase07
{
	private static void failwhale(String params)
	{
		System.out.println("Test Case #7: hasConstrainedTopoSort(" + params + "): fail whale :(");
		System.exit(0);
	}

	public static void main(String [] args) throws IOException
	{
		ConstrainedTopoSort t = new ConstrainedTopoSort("input_files/g7.txt");

		if (t.hasConstrainedTopoSort(6, 2) != false) failwhale("6, 2");
		if (t.hasConstrainedTopoSort(8, 7) != false) failwhale("8, 7");
		if (t.hasConstrainedTopoSort(3, 5) != true) failwhale("3, 5");
		if (t.hasConstrainedTopoSort(8, 6) != true) failwhale("8, 6");
		if (t.hasConstrainedTopoSort(6, 8) != true) failwhale("6, 8");

		System.out.println("Test Case #7: PASS (Hooray!)");
	}
}
