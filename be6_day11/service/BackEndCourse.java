package be6_day11.service;

public class BackEndCourse extends Course {
	

	public BackEndCourse(String filename) {
		super.filename = filename;
	}

	@Override
	public void welcomeMessage(int selectedCourse) {
		System.out.println("Welcome to back end course");
		
	}

	
}
