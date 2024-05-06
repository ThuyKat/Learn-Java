package be6_day13;

import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		int [] arr = {1,2,2,0,0,1,2,2,1};
		System.out.print(Arrays.toString(solution(arr)));
	}

	private static int[] solution(int[] arr) {
		int [] count = new int [3];
		for(int i = 0;i<arr.length;i++) { // count = {2,3,4}
			count[arr[i]] ++;
		}
		int k = 0;
		for(int i = 0;i<count[k];i++) {
				arr[i] = k;	
			if(i==arr.length-1) {
				break;
			}
			if(i == count[k] -1) {
				k++;
				count[k] = count[k] + count[k-1];
			}	
		}
		return arr;
	}


}
