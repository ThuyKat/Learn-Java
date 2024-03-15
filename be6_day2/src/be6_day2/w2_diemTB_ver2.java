package be6_day2;

import java.util.Scanner;

public class w2_diemTB_ver2 {
public static void main(String[] args) {
	//Input points for Math, Eng, Lit and grade them
		Scanner input = new Scanner(System.in);
		
		System.out.println("Math: ");
		float math = input.nextFloat();
		
		int math_rounded = (int)Math.floor(math);	
		System.out.println("-->grade " + converse(math_rounded).substring(0,1));
		
		System.out.println("English: ");
		float eng = input.nextFloat();
		
		int eng_rounded = (int)Math.floor(eng);
		System.out.println("-->grade " + converse(eng_rounded).substring(0,1));
		
		
		System.out.println("Literature: ");
		float lit = input.nextFloat();
		int lit_rounded = (int)Math.floor(lit);
		System.out.println("-->grade " + converse(lit_rounded).substring(0,1));
		
	// min of grades	
		float min = Math.min(Math.min(math, eng),lit);
		
	//Calculate GPA
		double gpa = (math+eng+lit)/3;
		double gpa_rounded = Math.round(gpa * 100.0 + 0.01)/100.0;
		System.out.println("GPA :" + gpa_rounded);
		
		  
		int x = (int)Math.floor(min); // ket qua math.floor la double type --> them (int) de sua error nay
									  // 5 --> case 5, 5.5 --> case 5 --> round down
		if (x>=5 && gpa>=x && x<10) {x ++;};
		
		System.out.println("grade is "+ converse(x).substring(0,1));
		System.out.println(converse(x).substring(1)+" student");
		
}
	// function to convert points to grade and student type
	// added "static" to fix error but not understand why?
		public static String converse(int x) {
			
		String grade = null;
		String type = null;
				
		switch (x) {
						
			case 5: //5<=grade<6
				type = "not bad";
				grade ="D";
				break;
			case 6: //6<=grade<7
				type = "average";
				grade ="C";
				break;
			case 7: // 7<=grade<8
				type = "normal";
				grade ="B";
				break;
			case 8: //8<=grade<9
				type = "good";
				grade ="A";
				break;
			case 9 :// 9<=grade
			case 10:
				type = "excellent";
				grade ="S";
				break;
			default:
				type = "fail";
				grade ="F";
			
				
		}
		return grade + type;
}
}//push to main not master
