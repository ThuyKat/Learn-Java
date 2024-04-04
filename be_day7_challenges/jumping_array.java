package be6_day7;

public class jumping_array {
public static void main(String[] args) {
	int []  A1 = {2,3,-1,1,4};
	int []  A2 = {1,1,-1,1};
System.out.println(solution(A1));
System.out.println(solution(A2));


}

public static int solution(int []A) {
	int [] B = new int[A.length]; // Array B to count the frequency of A[i]+i
	int step = 0; 
	for(int i = 0; i<A.length;i=i+A[i]) { // [i=0,A[0]+0], [i=2,A[2]+2], [i=1,A[1]+1]
		if (A[i]+ i <A.length) {
			B[A[i]+i]++; 
			step++;
			if (B[A[i]+i]>1) { //the jump is infinite when frequency of A[i]+i is more than 1 time
				step = -1;
				break;
			}
		} else {
			step++;
			break;
		}
	}
	
	return step;
	
}
}
