package be6_day2;

import java.util.Scanner;

public class ScannerConsoleApp {
public static void main(String[] args) {
//	// goi bien lay input vao
//	Scanner scanner = new Scanner(System.in);
//	//in ra menh lenh de user nhap du lieu vao dong tiep theo
//	System.out.println("hey, input your name");
//	// Giao part of input cho bien name
//	String name = scanner.nextLine();
//	System.out.println("Your name is "+ name);
//	
//	System.out.println("hey, input your age");
//	double age = scanner.nextInt();
//	System.out.println("Your age is" + age);
//	
////ARRAY: mang co chua duoc 3 strings
//	String[] nameArray = new String[3];
	
// tao array chua ten gia dinh va in toan bo ra
// print gia dinh co may nguoi
	String[] familyNameArray = new String[4];
	familyNameArray[0] = "mr.D";
	familyNameArray[1] = "mrs Y";
	familyNameArray[2] ="Nguyet";
	familyNameArray[3] = "Thuy";
	System.out.println(familyNameArray[0]);
	System.out.println(familyNameArray[1]);
	System.out.println(familyNameArray[2]);
	System.out.println(familyNameArray[3]);
	
	System.out.println("Gia dinh co "+ familyNameArray.length +" nguoi");
	
	
	
}
}
