package be6_day16.BST;

public class InorderTraverse {
	/*
	  CREATE BST 
	  			 50 
			   / 	\ 
			 30 	 70 
		 	/   \   /  \ 
		   20   40 60   80
	  
	 */
	public static void main(String[] args) {

		Node root = new Node(50);

		BST tree = new BST();

		// INSERT VALUES
		int[] values = { 30, 70, 20, 40, 60, 80 };
//	int[] values = { 20, 70, 40, 50, 25 };
		for (int num : values) {
			root = tree.insert(root, num);
		}
		printNode(root);

		Node result = tree.search(root, 70);
		System.out.println();
		System.out.println("Test search function of BST");
		printNode(result);

		tree.delete(root, 80);
		System.out.println();
		System.out.println("Test delete function of BST");
		printNode(root);
	}

	public static void printNode(Node root) {

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
