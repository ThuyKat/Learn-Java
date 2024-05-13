package be6_day16.BST.AVL;

public class AVLNode {
int height;
int key;
AVLNode left;
AVLNode right;
public AVLNode(int key) {
	
	height = 1;
	this.key = key;
	left = null;
	right = null;
}

}
