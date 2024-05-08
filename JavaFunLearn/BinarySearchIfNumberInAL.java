package LearnGFG;

import java.util.ArrayList;

public class BinarySearchIfNumberInAL {
public static void main(String[] args) {
	ArrayList<Integer>arr = new ArrayList<>();
	arr.add(1);
	arr.add(2);
	arr.add(3);
	arr.add(4);
	arr.add(6);
	int x = 6;
	System.out.println(solution(arr,6));
}

private static int solution(ArrayList<Integer> arr, int x) {
	int left = 0;
	int right = arr.size();
	int mid = 0;
	while(left<=right) { // when left = right, mid = left=right, mid can still be used to compare with x
		mid = (left+right)/2; 
		System.out.println("mid: " + mid);
		if(arr.get(mid)< x) { //+1 or -1 so that left can surpass right and vice versa
			left = mid +1;
			System.out.println("left:"+left);
		}else if(arr.get(mid)>x) {
			right = mid -1;
			System.out.println("right:"+right);
		}else {
			return 1;
		}
	}
	
		return -1;
	
	
}
}
