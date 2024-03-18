package be6_day3;

import java.util.Arrays;

public class hw3_palindrome {
	
	// function to check if array is Palindrome
	
	public static boolean isPalindrome(int[] nums) {
		
		// note: using boolean function helps set default value of either true or false
		
		for (int i =0; i <= nums.length -1; i++) {
			if (nums[i] != nums[nums.length -1 -i]) {
				return false;}
			// break the loop when the iteration reaches to the middle of the array
			else if (i+1== nums.length-1-i|| i == nums.length-1-i){
				return true;}
			}
		return false;
		}	
	// main program
	
	public static void main(String[] args) {
		int[]nums = {1,2,1,2};
		
		System.out.println( Arrays.toString(nums) + " is Palindrome?  "+ isPalindrome(nums));
		
		
	}
}

