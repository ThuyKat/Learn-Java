package be6_day16.BST.AVL;


public class AVLTraverse {
	 /*  AVL Tree  
				    30 
				   /  \ 
				 20    40 
				/  \     \ 
			   10  25    50 
	  */

	public static void main(String[] args) {
		
		AVLNode root = new AVLNode(10);
		
		AVLTree tree = new AVLTree();

		int[] values = { 20, 30, 40, 50, 25 };
		for (int num : values) {
			root = tree.insert(root, num);
			System.out.println(root.key);
		}
		printNode(root);

	}

	public static void printNode(AVLNode root) {
		if (root != null) {

			System.out.println("node: " + root.key);
			if (root.left != null) {
				System.out.print("left :" + root.left.key + " ");
			} else {
				System.out.print("left : null" + " ");
			}
			if (root.right != null) {
				System.out.println("right :" + root.right.key);
			} else {
				System.out.println("right :null");
			}
			System.out.println();
			printNode(root.left);
			printNode(root.right);
		}
	}
}
