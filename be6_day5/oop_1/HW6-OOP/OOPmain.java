package be6_day6;

import java.util.Arrays;
import java.util.Scanner;

public class OOPmain {
	public static void main(String[] args) {
		// create an array with length =4. Data type is Course
		Course[] objects = new Course[4];
		// object of Course class
		objects[0] = new Course("Be 6", "2024-02-01 -> 2024-03-09", "Dungx Nguyen", 0, 3500);
		objects[1] = new Course("Data 7", "2024-04-20 -> 2024-07-20", "Hair Tran", 0, 2000);
		objects[2] = new Course("fe 1", "2024-02-28 -> 2024-07-20", "Phucs Le", 0, 3000);
		objects[3] = new Course("Full Stack 1", "2024-03-06 -> 2024-08-06", "Dungx Nguyen and Phucs Le", 0, 3500);
		// initiate an array with starting length = 0 to store objects of User class
		int userArrayLen = 0;
		User[] userObjects = new User[userArrayLen];

		// print list of course name
		// request for user's input of course selection
		int courseIndex = -1; // this variable is to use in later functions
		while (true) {
			courseIndex = Course.displayCourseInfo(objects, courseIndex);
			if (courseIndex == -1) { // this cannot be placed in condition part of while loop because the while loops does not end here
				System.out.println("Not in the list.Please select again");
				continue;
			}
			// function to show a list of actions
			while (true) {
				int option = User.printActionList();
				// Option 1: request for user's input of CCCD, name, mobile no.
				// If CCCD of user is not in the course's data, registered number +1
				// else return a message : " this CCCD number has registered for the course"

				switch (option) {
				case 1:
					int count0 = 0;
					System.out.println("Please fill in the below information ");
					// CCCD
					Scanner cccdInput = new Scanner(System.in);
					System.out.println("CCCD no: ");
					String cccd = cccdInput.nextLine();
					while (true) {
						if (User.validateInput(cccd,"^\\d{11}$")) {
							break;
						} else {
							System.out.println("input is invalid. Please try again");
							cccd = cccdInput.next();
						}
					}
					// first name
					Scanner nameFirstInput = new Scanner(System.in);
					System.out.println("Your first name: ");
					String nameFirst;
					while(true) {
						if(nameFirstInput.hasNextLine()) {
							nameFirst = nameFirstInput.nextLine();
							break;
						}else {
							nameFirstInput.next();
							System.out.println("input is invalid. Please try again");
						}}
					// last name
					Scanner nameLastInput = new Scanner(System.in);
					System.out.println("Your last name: ");
					String nameLast;
					while(true) {
						if(nameLastInput.hasNextLine()) {
							nameLast = nameLastInput.nextLine();
							break;
						}else {
							nameLastInput.next();
							System.out.println("input is invalid. Please try again");
						}}
					// mobile number
					Scanner mobInput = new Scanner(System.in);
					System.out.println("Your mobile number: ");
					String mobile = mobInput.nextLine(); 
					while (true) {
						if (User.validateInput(mobile,"^\\d{10}$")) {
							break;
						} else {
							System.out.println("input is invalid. Please try again");
							mobile = mobInput.next();
						}
					}
					

					if (userArrayLen > 0) {
						count0 = User.validateUser(userObjects, cccd, count0);
						if (count0 == 0) { // no match is found, register the new user
							userArrayLen++;
							userObjects = Arrays.copyOf(userObjects, userArrayLen);
							userObjects[userArrayLen - 1] = new User(cccd, nameFirst, nameLast, mobile,
									objects[courseIndex].name);
							System.out.println("Welcome to" + objects[courseIndex].name
									+ ". You have been enrolled into this course");
							continue; // return to the list of option
						}

					} else { // userArrayLen = 0
						userArrayLen++;
						userObjects = Arrays.copyOf(userObjects, userArrayLen);
						userObjects[userArrayLen - 1] = new User(cccd, nameFirst, nameLast, mobile,
								objects[courseIndex].name);
						System.out.println(
								"Welcome to" + objects[courseIndex].name + ". You have been enrolled into this course");
						continue; // return to the list of actions
					}
					continue; // return to the list of actions
				case 2:
					// Option 2: request for user's input of CCCD, mobile no
					System.out.println("Please fill in the below information ");
					// CCCD
					Scanner cccdCancel = new Scanner(System.in);
					System.out.println("CCCD no: ");
					String cccdCancelInput = cccdCancel.nextLine();
					while (true) {
						if (User.validateInput(cccdCancelInput,"^\\d{11}$")) {
							break;
						} else {
							System.out.println("input is invalid. Please try again");
							cccdCancelInput = cccdCancel.next();
						}
					}
					// mobile number
					Scanner mobCancel = new Scanner(System.in);
					System.out.println("Your mobile number: ");
					String mobileCancelInput = mobCancel.nextLine();
					while (true) {
						if (User.validateInput(mobileCancelInput,"^\\d{10}$")) {
							break;
						} else {
							System.out.println("input is invalid. Please try again");
							mobileCancelInput = mobCancel.next();
						}
					}

					// check if cccd and mobile number are matched, update the number of registered
					// student
					int count = 0;
					for (int j = 0; j < userArrayLen; j++) {
						if (userObjects[j].cccdNo != null && userObjects[j].cccdNo.equals(cccdCancelInput)
								&& userObjects[j].mobileNum.equals(mobileCancelInput) && userObjects[j].regCourse.equals(objects[courseIndex].name)){
							count++;
							objects[courseIndex].regStudent--;
							userObjects[j].firstName = null;
							userObjects[j].lastName = null;
							userObjects[j].cccdNo = null;
							userObjects[j].mobileNum = null;
							userObjects[j].regCourse = null;

							// if matched, inform if the cancellation has been successful or not.
							System.out.println("Your enrollment for " + objects[courseIndex].name
									+ " has been successfully canceled");
							break;
						}

					}
					// if no match is found, print a message that user has not registered for the
					// course
					if (count == 0) {

						System.out.println("You has not registered for " + objects[courseIndex].name);
					}

					continue;// return to the list of actions
				case 3:
					// Option 3: return to the list of course
					break;
				case 4:
					// print out the list of student name with their cccdNum
					
					int count1 = 0;
					for (int i = 0; i < userObjects.length; i++) {
						if (userObjects[i].regCourse == objects[courseIndex].name) {
							count1++;
							System.out.println("List of registered students of "+ objects[courseIndex].name +":");
							System.out.printf("%-15s %-20s %-15s%n", "First Name", "Last Name", "CCCD number");
							System.out.printf("%-15s %-20s %-15s%n", userObjects[i].firstName, userObjects[i].lastName,
									userObjects[i].cccdNo);
						}
					}
					if (count1 == 0) {
						System.out
								.println("No one has yet registered for this course. You might be the first student!");
					}
					continue; // return to the list of actions
				default:
					System.out.println("Please select again");
					continue;
				} // end of switch
				break; // return to the list of course

			} 
		} 
	} 
} 
