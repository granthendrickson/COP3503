// Grant Hendrickson
// COP 3503, Fall 2022

// ====================
// GenericBST.java
// ====================
// Generic binary search tree implementation that allows for any comparable values


import java.io.*;
import java.util.*;

class Node<T>
{
	T data;
	Node<T> left, right;

	Node(T data)
	{
		this.data = data;
	}
}

public class GenericBST<AnyType extends Comparable<AnyType>>
{
	private Node<AnyType> root;

	public void insert(AnyType data)
	{
		root = insert(root, data);
	}

	// inserts a new value into the BST by traversing through the BST
	// until a leaf is found to place the value in
	private Node<AnyType> insert(Node<AnyType> root, AnyType data)
	{
		// if the tree is empty return a new node
		if (root == null)
		{
			return new Node<>(data);
		}

		// if the value of the data being inserted is less than that of the root
		// move to the left node of the current root 
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}

		// if the value of the data being inserted is greater than that of the root
		// move to the right node of the current root 
		else if (data.compareTo(root.data) > 0)
		{
			root.right = insert(root.right, data);
		}

		return root;
	}

	public void delete(AnyType data)
	{
		root = delete(root, data);
	}

	// traverse the BST to find, return, and delete a given value
	private Node<AnyType> delete(Node<AnyType> root, AnyType data)
	{
		// base case to check for an empty tree
		// or when you hit an empty leaf
		if (root == null)
		{
			return null;
		}
		
		// if the value is less than the current node traverse to the left child
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}			
		
		// if the value is greater than the current node traverse to the right child
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}

		// hitting this else means that the we are in the node with the desired value
		else
		{
			// if the node has no children then we can return null
			if (root.left == null && root.right == null)
			{
				return null;
			}

			// if the node just has a right child return that child to replace the deleted node
			else if (root.left == null)
			{
				return root.right;
			}

			// if the node just has a left child return that child to replace the deleted node
			else if (root.right == null)
			{
				return root.left;
			}

			// if the node has two children:
			// find the greatest value in the left sub-tree to replace the current node
			// then set the new left child of that root to be the previous left child
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private AnyType findMax(Node<AnyType> root)
	{
		// traverse right through through the tree and the last value found will be the greatest
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// returns true if the given value is present in the BST
	// otherwise returns false
	public boolean contains(AnyType data)
	{
		return contains(root, data);
	}

	private boolean contains(Node<AnyType> root, AnyType data)
	{
		// base case to check if the tree is empty 
		// or the value is not in the tree
		if (root == null)
		{
			return false;
		}

		// if the value is less than the current node search the left sub-tree
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}

		// if the value is greater than the current node search the right sub-tree
		else if (data.compareTo(root.data) > 0)
		{
			return contains(root.right, data);
		}

		// hitting this else means we are in the node with the desired value
		else
		{
			return true;
		}
	}

	// prints the values of the BST with in-order traversal
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	// given the root traverse and print in the order:
	// left -> root -> right
	private void inorder(Node root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	// prints the values of the BST with pre-order traversal
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	// given the root traverse and print in the order:
	// root -> left -> right
	private void preorder(Node root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	// prints the values of the BST with post-order traversal
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	// given the root traverse and print in the order:
	// left -> right -> root
	private void postorder(Node root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	public static double difficultyRating()
	{
		return 1.5;
	}

	public static double hoursSpent()
	{
		// includes the time to write comments and remember how BST traversals work
		return 1.0;
	}

	public static void main(String [] args)
	{
		GenericBST<Integer> myTree = new GenericBST<Integer>();

		for (int i = 0; i < 5; i++)
		{
			int r = (int)(Math.random() * 150) + 1;
			System.out.println("Inserting " + r + "...");
			myTree.insert(r);
		}

		myTree.inorder();
		myTree.preorder();
		myTree.postorder();
	}
}
