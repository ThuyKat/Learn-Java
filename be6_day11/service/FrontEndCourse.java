package be6_day11.service;

public class FrontEndCourse extends Course {

	public FrontEndCourse(String filename) {
		super.filename = filename;
	}

	@Override
	public void welcomeMessage(int selectedCourse) {
		System.out.println("Welcome to front end course");
		
	}

}
