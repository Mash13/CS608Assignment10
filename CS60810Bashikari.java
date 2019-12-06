import java.io.File;
import java.util.Scanner;

public class CS60810Bashikari {

	CS60810Bashikari(){}
	
	static class BSTNode {
		
		BSTNode left;
		int element;
		BSTNode right;
		
		//constructor
		public BSTNode() {}
		//constructor
		public BSTNode(final int n) {
			left = null;
			element = n;
			right = null;
		}

	}

	/* Creating a BST */

	static BSTNode root;

	static class AVLNode {
		AVLNode left;
		int element;
		AVLNode right;
		int height;

		// constructor
		public AVLNode() {
			left = null;
			element = 0;
			right = null;
			height = 0;
		}

		// constructor
		public AVLNode(final int n) {
			left = null;
			element = n;
			right = null;
			height = 0;
		}
	}

	static AVLNode AVLRoot;

	public void insertStart(final int element) {
		root = insert(root, element); // This calls the insert method - see below
	}

	public void insertStartAVL(final int element) {
		AVLRoot = insertAVL(AVLRoot, element); // This calls the insert method - see below
	}

	static BSTNode insert(BSTNode node, final int element) {
		if (node == null)
			node = new BSTNode(element);
		else {
			if (element <= node.element)
				node.left = insert(node.left, element);
			else
				node.right = insert(node.right, element);
		}
		return node;
	}

	AVLNode insertAVL(AVLNode node, final int element) {

		if (node == null)
			node = new AVLNode(element);
		else if (element <= node.element) {
			node.left = insertAVL(node.left, element);
			if (heightSubtree(node.left) - heightSubtree(node.right) == 2)
				if (element < node.left.element)
					node = singleRightRotation(node);
				else
					node = doubleRightRotation(node);
		} else if (element > node.element) {
			node.right = insertAVL(node.right, element);
			if (heightSubtree(node.right) - heightSubtree(node.left) == 2)
				if (element > node.right.element)
					node = singleLeftRotation(node);
				else
					node = doubleLeftRotation(node);
		} else
			System.out.println("Element already exists in the tree");
		node.height = larger(heightSubtree(node.left), heightSubtree(node.right)) + 1;

		return node;
	}

	private int heightSubtree(final AVLNode node) {
		if (node == null)
			return -1;
		else
			return (node.height);
	}

	int larger(final int a, final int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	private AVLNode singleRightRotation(final AVLNode A) {
		final AVLNode temp = A.left;
		A.left = temp.right;
		temp.right = A;
		A.height = larger(heightSubtree(A.left), heightSubtree(A.right)) + 1;
		temp.height = larger(heightSubtree(temp.left), temp.height) + 1;
		return temp;
	}

	private AVLNode singleLeftRotation(final AVLNode A) {
		final AVLNode temp = A.right;
		A.right = temp.left;
		temp.left = A;
		A.height = larger(heightSubtree(A.left), heightSubtree(A.right)) + 1;
		temp.height = larger(heightSubtree(temp.right), A.height) + 1;
		return temp;
	}

	AVLNode doubleRightRotation(final AVLNode A) {
		return singleRightRotation(A);
	}

	AVLNode doubleLeftRotation(final AVLNode A) {
		A.right = singleRightRotation(A.right);
		return singleLeftRotation(A);
	}

	static int heightCBT(final int lengthCount) {

		int height = 1;

		// multiply by 2 until the number is greater than the length??? I don't think
		// this is correct.
		// number of nodes between 4 and 7 gives a height of 2. 2 - 3 = height of 1. 8 -
		// 15 = height of 3...
		if (1 <= lengthCount && lengthCount <= 3) {
			height = 1;
		} else if (4 <= lengthCount && lengthCount <= 7) {
			height = 2;
		} else if (8 <= lengthCount && lengthCount <= 15) {
			height = 3;
		} else if (16 <= lengthCount && lengthCount <= 31) {
			height = 4;
		} else if (32 <= lengthCount && lengthCount <= 63) {
			height = 5;
		} else if (64 <= lengthCount && lengthCount <= 127) {
			height = 6;
		} else if (128 <= lengthCount && lengthCount <= 255) {
			height = 7;
		} else if (256 <= lengthCount && lengthCount <= 511) {
			height = 8;
		} else if (512 <= lengthCount && lengthCount <= 1023) {
			height = 9;
		} else {
			height = 10;
		}

		return height;
	}

	static int findIndex(final Integer[] CBSTree, int index) {

		int height = 0;
		height = heightCBT(CBSTree.length);
		index = 1;

		for (int i = 0; i < height - 1; i++) {

			index = (index * 2) + 1;

		}
		System.out.println("Test height: " + height + " Test Index: " + index);

		return index;
	}

	static int situationCBS(final int np, final int dp, final int index, final Integer[] CBSTree, final int last,
			final int subMax) {

		if (np < 2 * index + 1 || np == 2 * index + 1) {
			// situation 1
			return situation1CBS(np, dp, last, CBSTree, subMax);
		} else {
			// situation 2
			return situation2CBS(np, dp, last, CBSTree, subMax);
		}
	}

	static int situation1CBS(final int np, final int dp, final int last, final Integer[] CBSTree, final int subMax) {
		// moves from DP to NP

		final int[] temp = new int[10];

		while (np != dp) {

			System.out.println("np: " + np);
			System.out.println("next in inorder (situation1): " + nextInInorder(np, subMax, last));

		}

		return np;
	}

	static int situation2CBS(final int np, final int dp, final int last, final Integer[] CBSTree, final int subMax) {
		// moves from NP to DP

		final int[] temp = new int[10];

		while (np != dp) {

			System.out.println("next in inorder (situation2): " + nextInInorder(dp, subMax, last));

		}

		return np;
	}

	static void inorder(final Integer[] CBSTree, final int sub, final int subLast) {

		if (sub <= subLast) {

			inorder(CBSTree, 2 * sub, subLast);
			System.out.print("" + CBSTree[sub]);
			inorder(CBSTree, 2 * sub + 1, subLast);

		}
	}

	static int nextInInorder(int sub, final int subMax, final int subLast) {

		// sub = 2;
		// subMax = ;
		// subLast = 3;

		if (sub == subMax)
			return subMax;
		if (2 * sub + 1 <= subLast) { // node has two children nodes
			sub = 2 * sub + 1;
			while (2 * sub < subLast)
				sub = 2 * sub;
			return sub;
		} else {// node does not have two children
			if (sub % 2 == 0)
				return sub / 2;
			else {
				while (sub % 2 != 0)
					sub = sub / 2;
				return sub / 2;
			}
		}
	}

	static Integer[] constructCBS(final int[] data) {

		final Integer[] CBSTree = new Integer[data.length + 1];
		int np = 0; // new position... position before following CBS requirements
		int dp = 0; // desired position... position after following CBS requirements
		final int k = 0; // num of elements within the CBS tree array
		int index = 0; // equal to the right most node in the CBS Tree
		int last = 1; // num of elements currently in CBS Tree
		final int n = 0; // total number of elements

		// If NP <= 2 * index + 1, then NP is on the last level (situation 1).
		// Otherwise, it is on the next level.

		CBSTree[1] = data[0];

		// finds the largest number in the array data[]
		int largest = 0;
		for (int i = 0; i < data.length; i++) {
			if (largest < data[i]) {
				largest = data[i];
			}
		}

		// finds the index (furthest node to the right)
		index = findIndex(CBSTree, index);

		// find the values of np (new position) and dp (desired position)
		for (int i = 1; i < data.length; i++) {
			final int element = data[i];
			last = 1;
			while (CBSTree[last] != null) {
				if (element < CBSTree[last]) {
					last = last * 2;
				} else {
					last = (last * 2) + 1;
				}
			}
			np = last;
			while (CBSTree[last] == null) {
				last = last - 1;
			}

			dp = last + 1;
			// CBSTree[last] = element;
			situationCBS(np, dp, index, CBSTree, last, largest);
		}

		return CBSTree;
	}

	public static void main(final String[] args) throws Exception {

		final Integer[] dataLength = new Integer[2000];
		int lengthCount = 0;
		int j = 0;
		long start = 0;
		long start2 = 0;
		long elapsedTime = 0;
		long elapsedTime2 = 0;
		long BSTreeConstructTime = 0;
		long AVLTreeConstructTime = 0;
		long CBSTreeConstructTime = 0;
		long BSTreeSearchTime = 0;
		long AVLTreeSearchTime = 0;
		long CBSTreeSearchTime = 0;
		final int[] searchKeys = { 250, 2504, 2078, 2158, 3502, 7138, 6230, 9661, 1330, 6136 };

		System.out.println("Test " + dataLength.length);

		final Scanner in = new Scanner(new File("inputData10.txt"));

		while (in.hasNextInt()) {
			dataLength[j] = in.nextInt();
			j++;
		}
		j = 0;

		// if an arrayOutOfBounds error is given, dataLength may need to be filled with
		// nulls.
		// makes lengthCount equal to the correct number of elements to be inserted
		start2 = System.nanoTime();
		while (dataLength[j] != null) {
			lengthCount++;
			j++;
		}

		// instantiates the array that the input data is stored in.
		// This array has length equal to the amount of elements
		final int[] data = new int[lengthCount];
		final Integer[] CBSTree = new Integer[lengthCount + 1];

		// transfers elements scanned into dataLength to data
		for (int i = 0; i < data.length; i++) {
			data[i] = dataLength[i];
			CBSTree[i + 1] = dataLength[i];
		}

		start = System.nanoTime();
		// create a binary search tree from the data in the scanned file.
		final CS6089Bashikari BSTree = new CS6089Bashikari();

		// changed from BSTree.inputData.length to data.length... 2 lines down
		// changed from BSTree.inputData[i] to data[i]... 3 lines down
		// plugs all elements into the Binary Search Tree
		for (int i = 0; i < data.length; i++)
			BSTree.insertStart(data[i]);

		elapsedTime = System.nanoTime() - start;
		BSTreeConstructTime = elapsedTime;
		System.out.println("Time taken to construct BST in nanoseconds: " + BSTreeConstructTime);

		start = System.nanoTime();
		// create an AVL tree from the data in the file.
		final CS6089Bashikari AVLTree = new CS6089Bashikari(); // this must not be right... AVL must be made differently
		elapsedTime2 = System.nanoTime() - start2;
		CBSTreeConstructTime = elapsedTime2;

		// changed from BSTree.inputData.length to data.length... 2 lines down
		// changed from BSTree.inputData[i] to data[i]... 3 lines down
		// plugs all elements into the Binary Search Tree
		for (int i = 0; i < data.length; i++)
			AVLTree.insertStartAVL(data[i]);

		elapsedTime = System.nanoTime() - start;
		AVLTreeConstructTime = elapsedTime;
		System.out.println("Time taken to construct AVL tree in nanoseconds: " + AVLTreeConstructTime);

		// Construct CBS Tree

		// CBSTree = constructCBS(data);

		System.out.println("Time taken to construct CBS tree in nanoseconds: " + CBSTreeConstructTime);

		// Height of each tree

		// 2. Print the height of the tree... both BST and AVL

		// BST
		if (BSTree.height(BSTree.root) != 0) {
			System.out.println("The height of the Binary Search Tree is: " + (BSTree.height(BSTree.root) - 1));
		} else {
			System.out.println("The binary tree has no elements");
		}

		// AVL
		if (AVLTree.heightAVL(AVLTree.AVLRoot) != 0) {
			System.out.println("The height of the AVL tree is: " + (AVLTree.heightAVL(AVLTree.AVLRoot) - 1));
		} else {
			System.out.println("The AVL Tree has no elements");
		}

		// CBS tree height... print it

		System.out.println("The height of the Complete Binary Tree is: " + heightCBT(lengthCount));

		// print the time taken to construct each tree

		// Time taken to construct BST

		// Time taken to construct AVL Tree

		// Time taken to construct CBS Tree

		// Search each tree for the keys in the searchKeys[] array.
		// Calculate the time that is taken to search each tree for all 10 keys. Not
		// each key... three times, one for each tree.

		// Time taken by BST to search for 10 keys in searchKeys array:
		start = System.nanoTime();
		for (int i = 0; i < searchKeys.length; i++) {
			final int keyNum = i + 1;
			if (BSTree.search(BSTree.root, searchKeys[i]) == true) {
				System.out.println("key " + keyNum + ": " + searchKeys[i] + " has been found (BST)");
			} else {
				System.out.println("key " + keyNum + ": " + searchKeys[i] + " has not been found (BST)");
			}
		}
		elapsedTime = System.nanoTime() - start;
		BSTreeSearchTime = elapsedTime;
		System.out.println("Time taken to search BST in nanoseconds: " + BSTreeSearchTime);

		start = System.nanoTime();
		for (int i = 0; i < searchKeys.length; i++) {
			final int keyNum = i + 1;
			if (AVLTree.search(AVLTree.AVLRoot, searchKeys[i]) == true) {
				System.out.println("key " + keyNum + ": " + searchKeys[i] + " has been found (AVL tree)");
			} else {
				System.out.println("key " + keyNum + ": " + searchKeys[i] + " has not been found (AVL tree)");
			}
		}
		elapsedTime = System.nanoTime() - start;
		AVLTreeSearchTime = elapsedTime;
		System.out.println("Time taken to search AVL in nanoseconds: " + AVLTreeSearchTime);

		// Keys found in BST

		// Time taken by AVL Tree to search for 10 keys in searchKeys array:

		// Keys found in AVL Tree

		// Time taken by CBS Tree to search for 10 keys in searchKeys array:

		boolean search = false;
		int element = 0;

		start = System.nanoTime();
		for (int i = 0; i < searchKeys.length; i++) {
			search = false;
			element = searchKeys[i];
			for (int k = 0; k < data.length; k++) {
				if (searchKeys[i] == data[k]) {
					search = true;
				}
			}

			final int keyNum = i + 1;
					if (search == true) {
						System.out.println("key " + keyNum + ": "+ element + " has been found (CBS tree)");
					} else {
						System.out.println("key " + keyNum + ": "+ element + " has not been found (CBS tree)");
					}
			}
			elapsedTime = System.nanoTime() - start;
			CBSTreeSearchTime = elapsedTime;
			System.out.println("Time taken to search CBS Tree in nanoseconds: " + CBSTreeSearchTime);	
		
	}
}
