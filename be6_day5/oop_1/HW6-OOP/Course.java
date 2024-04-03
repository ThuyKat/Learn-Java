package be6_day6;

import java.util.Scanner;

public class Course  {
	String name;
	String date;
	String mentor;
	int regStudent;
	float courseFee;
	
//Constructor
public Course(String name, String date, String mentor,int regStudent,float courseFee) {
	this.name = name;
	this.date = date;
	this.mentor = mentor;
	this.regStudent =regStudent;
	this.courseFee = courseFee;
}
//Funciton to display the course info
	public static int displayCourseInfo(Course[] objects, int courseIndex) {
		for (int i = 0; i < objects.length; i++) 
		{System.out.println((i + 1) + "-" + objects[i].name);}
		
		Scanner input1 = new Scanner(System.in);
		System.out.println("Enter the course number: ");
		//check if the input is an integer
		int inputNum1;
		while(true) {
			if(input1.hasNextInt()) {
				inputNum1 = input1.nextInt();
				break;
			}else {
				input1.next(); // clear the invalid input and prompt user to enter again
				System.out.println("Please enter again");
			}
		}
		

		for (int i = 0; i < objects.length; i++) {
			
			if (inputNum1 == i + 1) {
				
				courseIndex = i;
				System.out.println("name : " + objects[i].name);
				System.out.println("Start and End dates : " + objects[i].date);
				System.out.println("mentor : " + objects[i].mentor);
				System.out.println("number of registered students : " + objects[i].regStudent);
				System.out.println("course fee : " + objects[i].courseFee);
				break;
			}
		}
		return courseIndex; 
	}
}