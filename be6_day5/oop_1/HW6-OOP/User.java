package be6_day6;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
	String cccdNo; // int se mat so 0 o dau
	String firstName;
	String lastName;
	String mobileNum;
	String regCourse;

//Constructor
	public User(String cccdNo, String firstName, String lastName, String mobileNum, String regCourse) {
		this.cccdNo = cccdNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNum = mobileNum;
		this.regCourse = regCourse;
	}

// Function to print list of action for user
	public static int printActionList() {
		System.out.println("Please select an option below :");
		System.out.println("1. Register");
		System.out.println("2. Cancel registration");
		System.out.println("3. Return to list of courses");
		System.out.println("4. See the list of students in this course");

		Scanner input2 = new Scanner(System.in);
		System.out.println("Enter your choice:  ");
		int option = input2.nextInt();

		return option;

	}
// Function to verify if user a registered student 
	public static int validateUser(User[] userObjects, String cccd, int count0) {
		for (int i = 0; i < userObjects.length; i++) {

			if (userObjects[i].cccdNo != null && userObjects[i].cccdNo.equals(cccd)) {
				count0++;
				System.out.println(" You have already registered. Please select other option");
				break;
			}

		}
		return count0; // need to check if user registered into a specific course, not all course like this
	}
// Function to verify user's input
	public static boolean validateInput(String inputString, String pattern) {
		return Pattern.matches(pattern, inputString);
	}
}