package be6_day11.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import be6_day11.dto.Users;

public abstract class Course {

	public String filename;

	public ArrayList<Users> readDataFile() {
		try {

			ArrayList<Users> userList = new ArrayList<>();
			File be = new File(filename);
			Scanner scanInput = new Scanner(be);
			while (scanInput.hasNextLine()) {
				String data = scanInput.nextLine();
				String[] elArray = data.split(",");
				Users u = new Users(elArray[0].trim(), elArray[1].trim(), elArray[2].trim());
				// using trim() to cut down all whitespace around the string
				userList.add(u);
				return userList;

			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred when reading file. ");

		}
		return new ArrayList<Users>();

	}

	public void writeDataFile(ArrayList<Users> userList) {
		FileWriter writer; // moi lan write file thi write tu dau, cac du lieu cua file trc do deu bi
		// delete het
		try {
			writer = new FileWriter(filename);
			for (Users student : userList) {
				writer.write(student.name + "," + student.mobile + "," + student.date + System.lineSeparator());
			}
			writer.close();
			System.out.println(" Registered successfully!!! ");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static public Course courseCreation(int selectedCourse) {

		if (selectedCourse == 2) {
			return new FrontEndCourse("fe.csv");
		} else {
			return new BackEndCourse("be.txt");
		}

	}

	public abstract void welcomeMessage(int selectedCourse);

}