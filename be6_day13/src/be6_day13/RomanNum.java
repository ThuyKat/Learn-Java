package be6_day13;

public class RomanNum {
	public static void main(String[] args) {
		String s = "MCMXCIV"; 
		System.out.println(solution(s));
	}
	public static int convertToNum(char ch) {
		switch(ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		
		}
		return 0;
	}
	private static int solution(String s) {
		int result = 0; //1
		int start = convertToNum(s.charAt(0)); //1
		for(int i = 1;i<s.length();i++) { //n
			int currentValue = convertToNum(s.charAt(i)); //8
			if( currentValue <= start) { //1
				result += start;
			}else {result -=start;}
			start = currentValue;//1
		}
		result +=start;//1
		return result;//1
	}// total O = n+14 --> O(n)


}
