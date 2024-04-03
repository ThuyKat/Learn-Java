package be6_day6;

public class MaxProfit {
// Array consisting N integer N in range [0,400k], elements in range [0,200k]
// P/L = A[Q]-A[P] , Q>P
// Function: return max possible profit , 0 if no profit
	public static void main(String[] args) {
		int[] A = { 23171, 21011, 21123, 21366, 21013, 21367 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		// has to maintain the index order, because Q>P
		// consider only elements from index of min onwards, find the max in those 
		
		int[] B = new int[A.length];
		int min = A[0];
		int minIndex = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] < min) {
				min = A[i];
				minIndex = i;
			}
		}
		int max = A[minIndex];
		for (int i = minIndex; i < A.length; i++) {
			if (A[i] > max) {
				max = A[i];
			}
		}
		
		// max profit = max-min
		return max - min;

	}
}
