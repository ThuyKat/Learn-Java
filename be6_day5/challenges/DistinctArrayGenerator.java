package be6_day6;

import java.util.Arrays;
import java.util.Random;

public class DistinctArrayGenerator {
	public static void main(String[] args) {
		int N = 10; // N in [1,100]
		System.out.println(Arrays.toString(solution(N)));
	}

	public static int[] solution(int N) {
		// return any array of N unique integers with sum = 0

		int[] result = new int[N];

		for (int i = 0; i < N / 2; i++) {
			result[i] = (i - (N + 1) / 2);
		}

		for (int i = N / 2; i < N; i++) {
			result[i] = -result[N - 1 - i];
		}
		// make the result look more random?
		//solution A: 
		//pick one element of array and minus or plus to a random number
		// pick another element of array and do the opposite plus or minus to that random number
		// 1- create a random object:
		Random random = new Random();
		
		// 2- generate a random index within the range of the array
		int index1 = random.nextInt(result.length);
		int index2 = random.nextInt(result.length);
		// 3- generate a random number to add and minus to two random elements of array
		int randomNum = random.nextInt();
		result[index1] = result[index1] + randomNum;
		result[index2] = result[index2] - randomNum;
		// solution B:
		//mix up element positions in array?
		return result;

	}
}
