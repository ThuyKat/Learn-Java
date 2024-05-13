package be6_day16.BST;

public class BST {
// < key --> go to left; > key --> go to right; order from root to the furtherest branch (when left Node and right Node both == null)
// must be no duplicate nodes --> handling: store on the left/right??

// INSERT INTO BST : insert a number into a random node
	public Node insert(Node node, int num) {
		// base case
		if (node == null) {
			node = new Node(num);
			return node;
		}
		// recursive function to go to the end of the branch
		if (num < node.key) {
			node.left = insert(node.left, num);
		} else if (num > node.key) {
			node.right = insert(node.right, num);
		}
		return node; // node.key, and node left or right is updated with the new num
	}

// SEARCH FOR ITEM IN BST
	public Node search(Node root, int num) { // search for a num in a BST, if it's a key of a node in BST--> start from
												// root
		// base case
		if (num == root.key || root == null) {
			return root;
		}
		// recursive function to go from root to the end of the branch
		if (num < root.key) {
			return search(root.left, num);
		} else {
			return search(root.right, num);
		}

	}

//DELETE ITEM IN BST: return the Node which is null or is placed in its position
	public Node delete(Node root, int num) {
		// base cases
		if (root == null) {
			return root;
		}
		

		if (num < root.key) {
			root.left = delete(root.left, num); // keep going until root == null
			

		} else if (num > root.key) {
            
			root.right = delete(root.right, num);
			
			
		}else {
			// if the foudn Node has no child or
			// if the found Node has only left node
			if (root.right == null) {
				root = root.left;
				return root;

			}
			// if the found node has only right node
			else if (root.left == null) {
				root = root.right;
				return root;
			}
			// if the found Node has both left and right node
			// find the most left Node on the right branch
			else {
				Node successor = root.right;
				while (successor.right != null && successor.left != null) {
					successor = successor.left;
				}
				root.key = successor.key;
				// delete the successor Node
				root.right = delete(root.right, root.key);
				return root;
			}
		}
		return root;

	}
}
