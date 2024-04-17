package be6_day9;

import java.util.Scanner;

public class OOP_factoryDP {

// update new option: Economy Ship including following tasks: 
	// - update in Mains program in selectingRoute method to let user select the new
	// route
	// - update parent class: ShippingService- routeCreation method
	// - create a new child class named Economy Service
//EXISTING PROBLEMS: 
	// - When overriding a method of parent class, we cannot re-use one part of that
	// method's body but have to override from start
	// - Since main program has validating user input in its body, it's always
	// required to update Mains
	// - Creating new Child always requires updating the CreationRoute method in
	// Parent class
	public static void main(String[] args) {
		final int VIEW_PRICE = 1;
		final int VIEW_SHIPPING_TIME = 2;
		final int VIEW_GUIDELINE = 3;
		final int CHOOSE_OTHER_ROUTE = 4;

		ShippingService selectedService = new ShippingService();

		// After user selected the route, show the options to view route info:
		int option = CHOOSE_OTHER_ROUTE;
		while (true) {
			if (option != CHOOSE_OTHER_ROUTE) {
				option = selectingOption();
			}
			switch (option) {
			case CHOOSE_OTHER_ROUTE:
				int selectedRoute = selectingRoute();
				selectedService = selectedService.routeCreation(selectedRoute);
				option = 0;
				break;
			case VIEW_PRICE:
				selectedService.showPrice();
				break;
			case VIEW_SHIPPING_TIME:
				selectedService.showShippingTime();
				break;
			case VIEW_GUIDELINE:
				selectedService.showDeliveryAndPaymentGuidelines();
				break;

			}
		}
	}

	public static int selectingOption() {
		System.out.println("View information for your chosen route: ");
		System.out.println("1. View Price");
		System.out.println("2. View Shipping time");
		System.out.println("3. Guideline");
		System.out.println("4. Choose other route");
		Scanner userInput = new Scanner(System.in);
		int option = 0;
		while (true) { // verify user input
			if (userInput.hasNextInt()) {
				option = userInput.nextInt();
				while (option < 1 || option > 4) {
					System.out.println("Invalid input.Please try again:");
					option = userInput.nextInt();
				}
				break;
			} else {
				System.out.println("Invalid input.Please try again:");
				userInput.next();
			}
		}
		return option;
	}

	public static int selectingRoute() {
		System.out.println("Please select your shipping option: ");
		System.out.println(" 1. By Plane ");
		System.out.println(" 2. By Ship ");
		System.out.println(" 3. Economy Ship ");
		Scanner userInput = new Scanner(System.in);
		int selectedRoute = 0;
		while (true) { // verify user input
			if (userInput.hasNextInt()) {
				selectedRoute = userInput.nextInt();
				while (selectedRoute < 1 || selectedRoute > 3) { // update EconomyService, selectedRoute now has 3
																	// options
					System.out.println("Invalid input.Please try again:");
					selectedRoute = userInput.nextInt();
				}
				break;
			} else {
				System.out.println("Invalid input.Please try again:");
				userInput.next();
			}
		}
		return selectedRoute;
	}
}