package be6_day3;

import java.util.Scanner;

public class hw3_primenumcheck {
	public static boolean isPrimeNumber(int num) {
		
		if (num <=1) {return false;}
		for (int i = 2; i < num;i++)
		{ if (num % i == 0) {return false;}
		 	
		}
		
		return true;
}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter an integer :");
		int num = input.nextInt();
		System.out.println(" This is a prime number? "+ isPrimeNumber(num));
	}
}