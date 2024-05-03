package be6_day14;

public class FindNumIndex {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 11, 44, 56, 92, 100 };
		int num = 56;
		System.out.println(solution(arr, num));
	}

	private static int solution(int[] arr,int num) {
		
		int index = (int) Math.floor(arr.length/2);
		while (arr[index]!=num) {
			
			if (arr[index] > num) {
				index = index - (int)Math.floor(index/2);
			}else if(arr[index]<num) {
				index = index + (int)Math.floor(index/2);
			}
		}
		return index;
	}
}
