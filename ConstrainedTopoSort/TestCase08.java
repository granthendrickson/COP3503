// Sean Szumlanski
// Jackson Mazer
// COP 3503, Fall 2022

// ====================================
// ConstrainedTopoSort: TestCase08.java
// ====================================
// A small test case for ConstrainedTopoSort.

//        (2)-->(4)-->(6)
//     _,^ |     |     ^
// (1)<_   |     |     |
//      "v V     V     |
//        (3)-->(5)<--(7)

import java.io.*;
import java.util.*;

public class TestCase08
{
	private static void failwhale(String params)
	{
		System.out.println("Test Case #8: hasConstrainedTopoSort(" + params + "): fail whale :(");
		System.exit(0);
	}

	public static void main(String [] args) throws IOException
	{
		ConstrainedTopoSort t = new ConstrainedTopoSort("input_files/g8.txt");

		if (t.hasConstrainedTopoSort(1, 2) != true) failwhale("1, 2");
		if (t.hasConstrainedTopoSort(1, 3) != true) failwhale("1, 3");
		if (t.hasConstrainedTopoSort(2, 5) != true) failwhale("2, 5");
		if (t.hasConstrainedTopoSort(6, 5) != true) failwhale("6, 5");
		if (t.hasConstrainedTopoSort(1, 7) != true) failwhale("1, 7");
		if (t.hasConstrainedTopoSort(7, 1) != true) failwhale("7, 1");
		if (t.hasConstrainedTopoSort(7, 2) != true) failwhale("7, 2");
		if (t.hasConstrainedTopoSort(2, 4) != true) failwhale("2, 4");
		if (t.hasConstrainedTopoSort(4, 2) != false) failwhale("4, 2");
		if (t.hasConstrainedTopoSort(2, 1) != false) failwhale("2, 1");
		if (t.hasConstrainedTopoSort(4, 1) != false) failwhale("4, 1");

		System.out.println("Test Case #7: PASS (Hooray!)");
	}
}
