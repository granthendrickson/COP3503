// Sean Szumlanski
// Jackson Mazer
// COP 3503, Fall 2022

// ====================================
// ConstrainedTopoSort: TestCase09.java
// ====================================
// A small test case for ConstrainedTopoSort.


import java.io.*;
import java.util.*;

public class TestCase09
{
	private static void failwhale(String params)
	{
		System.out.println("Test Case #9: hasConstrainedTopoSort(" + params + "): fail whale :(");
		System.exit(0);
	}

	public static void main(String [] args) throws IOException
	{
		ConstrainedTopoSort t = new ConstrainedTopoSort("input_files/g9.txt");

		if (t.hasConstrainedTopoSort(2, 1) != false) failwhale("2, 1");

		System.out.println("Test Case #9: PASS (Hooray!)");
	}
}

// (1)
//  |
//  V
// (2)