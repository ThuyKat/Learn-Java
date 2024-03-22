package be6_day4;

import java.util.Arrays;

public class SameDigitSum {
public static void main(String[] args) {
	int[] A1 = {51,71,17,42};
	int[] A2 = {42,33,60};
	int[] A3 = {51,32,43};
	System.out.println(solution(A1));
	System.out.println(solution(A2));
	System.out.println(solution(A3));
}

public static int solution(int A[]) {	
	// create sumDigit array to store sum of digits of each element of A
	int[] sumDigit = new int[A.length];
	int k = 0;
	for(int a:A) {
		
		while(a>0) { 
			sumDigit[k] = sumDigit[k]+ a%10;
			a = (int)(a /10);
		}
		k++;		
	}
	// compare element of sumDigit to find the equal pair
	//calculate the sum of relevant element in A to find the max sum
	int maxSum = -1;
	for (int i = 0; i< sumDigit.length; i++) {
		for (int j = i+1;j< sumDigit.length; j++) {
			if (sumDigit[i] == sumDigit[j] && A[i]+A[j] > maxSum) {
				maxSum = A[i] + A[j];
			} 
		}	
	}
	
	return maxSum;	
}
}
