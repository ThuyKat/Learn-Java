package be6_day14;

public class MinGap {

	public static void main(String[] args) {
		int[] arr = { 7, 1, 2, 7, 0, 1, 8 }; 
		System.out.println(solution(arr));
	}

	private static int solution(int[] arr) {

		int gap = 0;
		int[] newArr = new int[arr.length - 1];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr[i] - arr[i + 1]; // newArr: {6,-1,-5,7,-1,-7} // Now target is finding smallest-size sub-array that has sum = 0
			
			if (newArr[i] == 0) { // case: two equal number next to each other, gap =1
				gap =1;
				return gap;
			}
		}
		int minGap = 0;
		int sum = 0;
		int start = 0;
		for (int i = 0; i < newArr.length; i++) {
			sum += newArr[i]; // sum up the newArr elements until a sub-array is found when sum = 0
			gap++;		// the gap = number of elements of the sub-array
			if (sum == 0) {
				if (minGap == 0) {
					minGap = gap;
				} else {
					minGap = Math.min(gap, minGap);
				}
				gap --;
				sum -= newArr[start]; // each time a sub-array is found, first element of the sub-array is excluded from the sum
				start++;
			}

		}

		return minGap;

	}
}
