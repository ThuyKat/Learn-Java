package be6_day13;

public class SquareRoot {
	public static void main(String[] args) {
		int num = 1999999999;
		System.out.println(findSquareRoot(num));
		System.out.println(Math.sqrt(num));
		
	}

	public static double findSquareRoot(int num) {
		long numLong = (long)num*10000;
		double min = num - 1;
		double squareRoot = 0;
		for (long i = 1; i < numLong/100; i+=1) { // n*n = num exact to 0.01 --> for n to be accurate up to 2 decimal, we
											// multiply it by 100 --> target is now find square of num*10000
			double k = numLong/ i - i;
			if(k>=0 && k<min) {min = k;}
			if (k<=1) {		
				 squareRoot = (min +i)/100;
				break;}
		}
		return squareRoot;
	}
}
//time complexity: 3+ 100num(1+1+1)+1 = 4+300num --> O(num)