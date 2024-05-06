package be6_day13;

public class longestSamePrefix {
	public static void main(String[] args) {
		String[] strs = {"flower","flow","flight"};
		System.out.println(solution(strs));
	}

	private static String solution(String[] strs) {
//		String prefix = "";
		int minLength = strs[0].length();
		for(int i = 0;i<strs.length;i++) {
			if (strs[i].length()< minLength) {
				minLength = strs[i].length();
			}
		}
		//OPTION 1: 
//		for(int i = 0; i< minLength;i++) {
//			if(strs[0].charAt(i) == strs[1].charAt(i) && strs[0].charAt(i) == strs[2].charAt(i)) {
//				prefix += strs[0].charAt(i);
//			} else {break;}
//		}
		//OPTION 2:
		String prefix = strs[0].substring(0, minLength-1);
		for (int i = 1; i<strs.length;i++) {
			System.out.println("i" + i);
			if(strs[i].charAt(minLength-1) != prefix.charAt(minLength-1)) {
				minLength -= 1;
				
			}
		}
		return prefix;
	}
	

}
