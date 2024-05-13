package be6_day16.BST.AVL;

import be6_day16.BST.Node;

public class AVLTree {
// balance factor = right subtree - left subtree
//INSERT 
	// RIGHT ROTATION + LEFT ROTATION + LEFT RIGHT ROTATION + RIGHT LEFT ROTATION
	// get the height of the tree
	public int height(AVLNode node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	public AVLNode rightRotate(AVLNode z) {
		AVLNode y = z.left;
		AVLNode T3 = y.right;

		// rotation
		z.left = T3;
		y.right = z;

		// update height
		z.height = Math.max(height(z.left), height(z.right));
		y.height = Math.max(height(y.left), height(y.right));

		// return new root
		return y;
	}

	public AVLNode leftRotate(AVLNode z) {
		AVLNode y = z.right;
		AVLNode T2 = y.left;

		// rotation
		z.right = T2;
		y.left = z;

		// update height
		z.height = Math.max(height(z.left), height(z.right));
		y.height = Math.max(height(y.left), height(y.right));

		// return new root
		return y;
	}

	// get the different in height between left and right
	public int getBalance(AVLNode z) {
		if (z == null) {
			return 0;
		}
		return Math.abs(height(z.left) - height(z.right));
	}


	// INSERT
	public AVLNode insert(AVLNode root, int num) {
		// normal BST insertion
		// base case
		
		if (root == null) {
			root = new AVLNode(num);
			return root;
		}
		// recursive function to go to the end of the branch
		if (num < root.key) {
			root.left = insert(root.left, num);
			
		} else if (num > root.key) {
			root.right = insert(root.right, num);
			
		} else {
			return root; // no duplicate key?! how about handling duplication??
		}
		// update node's height
		root.height = 1 + Math.max(height(root.left), height(root.right));
		// check if the node's balance factor has absolute value >1
		int balance = getBalance(root);
		
		if (balance <= 1) {
			return root;
		} else {
			try{

			// CASE 1: Left rotation
			if (balance > 1 && num < root.left.key) { // num is inserted on the left of node.left
				return rightRotate(root);
			}
			// CASE 2: Right rotation
			else if (balance > 1 && num > root.right.key) { // num is inserted on the right of node.right
				return leftRotate(root);
			}
			// CASE 3: Left Right rotation
			else if (balance > 1 && num > root.left.key) { // num is inserted on the right of node.left
				// first, perform left rotation with node left as the root
				root.left = leftRotate(root.left);
				// then perform right rotation with node as the root
				root = rightRotate(root);
				return root;
			}
			// CASE 4: Right Left rotation
			else if (balance > 1 && num < root.right.key) { // num is inserted on the left of node.right
				// first, perform right rotation with node right as the root
				root.right = rightRotate(root.right);
				// then perform left rotation with node as the root
				root = leftRotate(root);
				return root;
			}
			// None of above cases applied:
			return root;

		}
		 catch (Exception e) {
			System.out.println("stuck");
		}

	}
		return root;
	}
}
