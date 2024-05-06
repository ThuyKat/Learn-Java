package be6_day15;

import java.util.Arrays;

public class EarliestTime {
	public static void main(String[] args) {
		int A = 1;
		int B = 8;
		int C = 3;
		int D = 2;
		int E = 6;
		int F = 4;
		System.out.println(solution(A, B, C, D, E, F));
	}

	private static String solution(int a, int b, int c, int d, int e, int f) { // {1,2,3,4,6,8}
		int[] arr = { a, b, c, d, e, f };
		Arrays.sort(arr);
		String hours = null;
		String minutes = null;
		String seconds = null;
		// first digit of hours = min of arr, must <=2
		if (arr[0] > 2) {
			return "Impossible";
		} else {
			hours = Integer.toString(arr[0]);
		}
		// make sure at least 3 number is <6, check how many number is actually <6
		if (arr[2] > 6) {
			return "Impossible";
		}
		int count = 3;
		for (int i = 3; i < 6; i++) {
			if (arr[i] > 6) {
				break;
			} else {
				count++;
			}
		}

		// arr[0] == 2 then arr[1] always <=4 else impossible
		// arr[0] == 2 then at least 4 number has to <= 6 ;
		if (arr[0] == 2) {
			if (arr[1] > 4 || count == 3) {
				return "Impossible";
			} else {
				hours += arr[1];
				minutes = Integer.toString(arr[2]) + arr[4];
				seconds = Integer.toString(arr[3]) + arr[5];
			}

		} else {
			if (count == 3) { // only 3 number < 6
				hours += arr[3];
				minutes = Integer.toString(arr[1]) + arr[4];
				seconds = Integer.toString(arr[2]) + arr[5];
			} else { // more than 3 number <6
				hours += arr[1];
				minutes = Integer.toString(arr[2]) + arr[4];
				seconds = Integer.toString(arr[3]) + arr[5];
			}
		}

		// check if minutes and seconds' values <=60
		if (Integer.valueOf(minutes) > 60 || Integer.valueOf(seconds) > 60) {
			return "Impossible";
		} else if (Integer.valueOf(hours) == 24 && (Integer.valueOf(minutes) > 0 || Integer.valueOf(seconds) > 0)) {
			return "Impossible";
		} else {
			return hours + " : " + minutes + " : " + seconds;
		}

	}
}
