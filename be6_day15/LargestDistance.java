package be6_day15;

import java.util.Arrays;

public class LargestDistance {
	public static void main(String[] args) {
		int [] A = {4,6,2,2,6,6,4};
		System.out.println(solution(A));
	}

	private static int solution(int[] a) {
		int count = 0;
		int maxCount = 0;
		int sum = 0;
		int start = 0;
		int [] b = new int[a.length -1];
		for(int i = 0; i< b.length;i++) {
			b[i] = a[i+1] -a[i];
		}
		
		for (int i = 0;i<b.length;i++) {
			sum+=b[i];
			if(sum != 0) {
				count++;
			}else{
				maxCount = Math.max(maxCount,count);
				count = 0;
				sum -=b[start];
				start++;
				
			}
		}
		return maxCount;
	}


}
