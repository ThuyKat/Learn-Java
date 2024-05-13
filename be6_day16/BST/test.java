package be6_day16.BST;



public class test {
	public static void main(String[] args) {
		Node root = new Node(50);
		
		BST tree = new BST();
		
		//INSERT VALUES
		int[] values = {30,70,20,40,60,80};
		for (int num : values) {
			root = tree.insert(root,num);
		}
		
		/*
		 Y (50)
		/
	   X (30)
	    \
	     T2 (40)
		 */
		Node Y = root;
		Node X = Y.left;
		Node T2 = X.right; // T2 and X.right pointing to obj1
		System.out.println(T2.key);
		
		X.right = Y; // Now X.right pointing to obj2
		//Hence T2 and X.right pointing to different objects now
		
	}


}
