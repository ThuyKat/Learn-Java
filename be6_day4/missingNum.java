package be6_day4;

public class missingNum {
	
public static void main(String[] args) {
	int[] A = {1,3,6,4,1,2};
	System.out.println(solution(A));
}
public static int solution(int[] A) {
	int[] B = new int[A.length +1];
	
	for(int num:A) {
		B[num]++;		
	}	
	for (int i =0; i<B.length;i++) {
		if (B[i] == 0 && i > 0)
			return i;
	} 	
	return 1;	
}
}
