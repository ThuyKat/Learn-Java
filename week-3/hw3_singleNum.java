package be6_day3;

public class hw3_singleNum {
	
	// main program
	public static void main(String[] args) {
		int[] array1 = {1,4,4,1,2};
		System.out.println("The single number in the array is " + singNum(array1));
		
	}
	
	//function to find the single number
	
	public static int singNum(int[]nums) {
		int sum = 0;
		int sum2 = 0;
		
		for(int i =0; i<= nums.length -1; i++) {
			sum += nums[i];
			for(int j =i+1; j<= nums.length -1;j++) {
				if (nums[i] == nums[j]) {
					sum2 += nums[i];}
				}
			}
		int singNum = sum - 2*sum2;
		return singNum;
		}	
}

