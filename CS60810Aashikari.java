import java.io.File;
import java.util.Scanner;

public class CS60810Aashikari {
	
	static class BSTNode {
		
		BSTNode left;
		int element;
		BSTNode right;
		
		//constructor
		public BSTNode() {}
		//constructor
		public BSTNode(int n) {
			left = null;
			element = n;
			right = null;
		}
		
	}
	
	/* Creating a BST */ 
         
	static BSTNode root;
	
	public void insertStart(int element) 
	{     
		root = insert(root, element); //This calls the insert method - see below  
	}

	static BSTNode insert(BSTNode node, int element)  
	{      
		if (node == null)        
			node = new BSTNode(element);    
		else      
		{       
			if (element <= node.element)      
				node.left = insert(node.left, element);     
			else            
				node.right = insert(node.right, element);   
		}      
		return node;   
	}
	
	/*
	
	static int rightMostSub() {
		
		int sub = 1; //first node. subscript is 1
		//let us keep going right
		while(2*sub+1<=L) sub = 2*sub+1;
		return sub;
		
	}
	
	static int leftMostSub() {
		
		int sub = 1;//first node. subscript is 1.
		//let us keep going right... is this supposed to say right or left???
		while(2*sub<=eleCount) sub = 2*sub;
		return sub;
		
	}
	
	static void preOrder(TreeNode node) {
		
		if(node != null) {
			
			preOrder(node.left);
			System.out.print(node.element + " ");
			preOrder(node.right);
			
		}
		
	}
	
	static void inorder(int sub) {
		
		if(sub <= subLast) {
			
			inorder(2*sub);
			System.out.print(" " + CBT[sub]);
			inorder(2*sub+1);
			
		}
		
	}
	
	static int nextInInorder(int sub) {
		
		if(sub == subMax) return subMax;
		if(2 * sub + 1 <= subLast) { //node has two child nodes
			
			sub = 2 * sub + 1;
			while (2 * sub < subLast) sub = 2 * sub;
			return sub;
			
		} else { //node does not have two children - may have one or none
			
			if (sub % 2 == 0) return sub/2;
			else {
				while (sub % 2 != 0) sub = sub/2;
				return sub/2;
			}
			
		}
		
	}

	//subMax is the subscript of the largest element in the tree (in the array).
	
	//subLast is the subscript of the last element in the tree (in the array).
	
	*/
	
    int height(BSTNode node)  
    { 
        if (node == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int leftHeight = height(node.left); 
            int rightHeight = height(node.right); 
   
            /* use the larger one */
            if (leftHeight > rightHeight) 
                return (leftHeight + 1); 
             else 
                return (rightHeight + 1); 
        } 
    }
    
    static int largest(BSTNode node){   
	    if(node==null) return -1;    
	    while(node.right!=null) {        
	    	node=node.right;}     
	    return node.element;   
	}
	
    int numOfNodes(BSTNode node) {   
   	 
  	  if (node == null) 
  		  return 0;
  	  return 1 + numOfNodes(node.left) + numOfNodes(node.right);
  	  
  }

    static boolean search(BSTNode node, int key){   
	    if(node==null) return false;    
	    if(node.element == key) return true;    
	    else{       
	    	if(key < node.element) return search(node.left, key);       
	    	else return search(node.right, key);    
	    }   
	}
    
    static int heightCBT(int lengthCount) {
    	
    	int height = 1;
    	
    	//multiply by 2 until the number is greater than the length??? I don't think this is correct.
    	//number of nodes between 4 and 7 gives a height of 2. 2 - 3 = height of 1. 8 - 15 = height of 3... 
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
    
	public static void main(String[] args) throws Exception {
	
		Integer[] dataLength = new Integer[2000];
		int lengthCount = 0;
		int j = 0;
		
		Scanner in = new Scanner(new File("inputData10.txt"));
		
		while (in.hasNextInt())
	    {
	        dataLength[j] = in.nextInt();
	        j++;
	    }
		j = 0;
		
		//if an arrayOutOfBounds error is given, dataLength may need to be filled with nulls.
		//makes lengthCount equal to the correct number of elements to be inserted
		while (dataLength[j] != null)
		{
			lengthCount++;
			j++;
		}
		
		//instantiates the array that the input data is stored in. 
		//This array has length equal to the amount of elements
		int[] data = new int[lengthCount];
				
		//transfers elements scanned into dataLength to data
		for(int i = 0; i < data.length; i++)
		{
			data[i] = dataLength[i];
		}
		
		//Height of the tree
		System.out.println("The height of the Complete Binary Tree is " + heightCBT(lengthCount));
		
		
		//Number of nodes in the tree
		//The length of the array should be equal to the number of nodes in the tree
		System.out.println("The number of nodes in this tree is " 
				+ lengthCount);
		
		//Print the largest element in the tree
		int largest = 0;
	
		for (int i = 0; i < lengthCount; i++) {			
			if (largest < data[i]) {
				largest = data[i];					
			}	
		}
		
		System.out.println("The largest number in the tree is " + largest);
		
		//Search for an integer. prints whether its been found or not.
		//search the array for the number... iterate with a for loop for the length of the array
		
		int input = 1;
		while (input != 0) {
			System.out.println("What number would you like to search for? Enter 0 to end the program.");
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			boolean search = false;
			
			for (int i = 0; i < lengthCount; i++) {
				if (input == data[i]) {	
					search = true;	
				}
			}

			if (search == true) {
				System.out.println("The element exists within the Complete Binary Tree");
			} else {
				System.out.println("The element does not exist within the Complete Binary Tree");
			}
		}
	}
}
