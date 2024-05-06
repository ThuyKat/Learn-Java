package be6_day14;

public class Feast {
	public static void main(String[] args) {
		int n = 15;
		int c = 3;
		int m = 2;
		System.out.println(solution(n,c,m));
	}

	private static int solution(int n, int c, int m) {
		int barNum = n/c; //1
		int wrapNum = barNum; //1
		int count = 0;
		while(wrapNum >m-1) {
			barNum += Math.floorDiv(wrapNum,m);
			wrapNum = Math.floorDiv(wrapNum,m) + wrapNum % m;
			count++;
		}
		System.out.println(count);
		return barNum;
	}

}
