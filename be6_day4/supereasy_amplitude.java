package be6_day4;

public class supereasy_amplitude {

	public static void main(String[] args) {
		int[] A = {10,2,44,15,39,20};
		System.out.println(solution(A));
		
	}
public static int solution(int[] A) {
	int min = A[0];
	int max = 0;
	
	for (int a:A) {
		if (a < min) { min = a;}
		else if (a > max) { max = a;}	
		}
	return max - min;
			
	}
	
}


