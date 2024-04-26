package be6_day11;
import java.util.ArrayList;
import java.util.Scanner;
import be6_day11.dto.Users;
import be6_day11.service.Course;
import be6_day11.utilities.UtilityPrint;

public class MAIN {
	public static void main(String[] args) {

		Course course = null;
		while (true) {
			// YEU CAU NGUOI DUNG CHON KHOA HOC
			int selectedCourse = selectCourse();
			course = Course.courseCreation(selectedCourse);
			course.welcomeMessage(selectedCourse); // #1- Factory design pattern applied to welcomeMessage()

			// LOAD DATA TU be.txt or fe.csv VAO CLASS USER
			// FIRST NEED TO EMPTY LIST OF USERS SO THAT WE CAN RUN THE LOOP INDEFINITELY
			ArrayList<Users> userList = course.readDataFile(); // #2 - Bring userList out to MAIN
																// #3 - Fix the input and output of readDataFile()
																// #4 - Fix the structure of try/catch function in
																// readDataFile
																// #5 - Move readDataFile and writeDataFile into Course
																// parent class, create constructor for child class
																// using super.filename, adjust the courseCreator method
			// SHOW STUDENT LIST
			UtilityPrint.printUserList(userList);

			// YEU CAU NGUOI DUNG NHAP INPUT #5 Move this into a separate class called
			// utility class
			String userName;
			String userMobile;
			String userDOB;
			System.out.println("Please fill in your details: ");
			Scanner scanInput = new Scanner(System.in);
			System.out.println("Your name: ");
			while (true) { // verify user input
				if (scanInput.hasNextLine()) {
					userName = scanInput.nextLine();
					break;
				} else {
					System.out.println("Invalid input.Please try again:");
					scanInput.next();
				}
			}

			System.out.println("Your mobile number: ");
			while (true) { // verify user input
				if (scanInput.hasNextLine()) {
					userMobile = scanInput.next();
					break;
				} else {
					System.out.println("Invalid input.Please try again:");
					scanInput.next();
				}
			}

			System.out.println("Your DOB: ");
			while (true) { // verify user input
				if (scanInput.hasNextLine()) {
					userDOB = scanInput.next();
					break;
				} else {
					System.out.println("Invalid input.Please try again:");
					scanInput.next();
				}
			}

			// VALIDATE USER INPUT XEM DA REGISTER HAY CHUA?-->CHECK TEN, DOB,MOBILE
			// NEU CHUA REGISTER THI TAO OBJECT USER MOI VOI THONG TIN DA INPUT
			if (validateUserRegistration(userList, userName, userMobile, userDOB) == true) {
				Users u = new Users(userName, userMobile, userDOB);
				userList.add(u);

				// RECORD USER DETAILS VAO FILE THONG QUA METHOD CUA COURSE
				course.writeDataFile(userList);

				// PRINT STUDENT LIST OF THE CHOSEN COURSE
				UtilityPrint.printUserList(userList);
			}
		}
	}

	public static boolean validateUserRegistration(ArrayList<Users> userList, String userName, String userMobile,
			String userDOB) {

		for (Users user : userList) {
			if (user.name.equals(userName) && user.mobile.equals(userMobile) && user.date.equals(userDOB)) {
				System.out.println("You have already registered for this course! Check the student list again");
				return false;
			}
		}
		return true;
	}

	public static int selectCourse() {
		System.out.println("Please select your course : ");
		System.out.println(" 1. Back End ");
		System.out.println(" 2. Front End ");
		Scanner userInput = new Scanner(System.in);
		int selectedCourse = 0;
		while (true) { // verify user input
			if (userInput.hasNextInt()) {
				selectedCourse = userInput.nextInt();
				while (selectedCourse < 1 || selectedCourse > 2) {
					System.out.println("Invalid input.Please try again:");
					selectedCourse = userInput.nextInt();
				}
				break;
			} else {
				System.out.println("Invalid input.Please try again:");
				userInput.next();
			}
		}
		return selectedCourse;
	}

}
