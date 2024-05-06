package be6_day15;

public class CalculateTheSquare {
	public static void main(String[] args) {
		int A = 10;
		int B = 20;
		
		System.out.println(solution(A,B));

	}

	private static int solution(int a, int b) {
		
		int count = 0;
		while(b>a) {
			a = (int) Math.sqrt(a);
			b = (int) Math.sqrt(b);
			
			count++;
			
		}
		
		return count -1;
	}

}
