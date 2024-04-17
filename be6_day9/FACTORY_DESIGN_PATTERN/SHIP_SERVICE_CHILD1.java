package be6_day9;

public class ShipService extends ShippingService {
	public void showPrice() {
		System.out.println("Price per package by deadweight(kg) for service by SHIP");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "5kg", "10kg", "20kg", "30kg");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "$48", "$51", "$55", "$59");
	}

	public void showShippingTime() {
		System.out.println("shipping time by product types for service by SHIP");
		System.out.printf("%-50s %-30s %-20s%n", "Service", "Delivery speed", "Coverage");
		System.out.printf("%-50s %-30s %-20s%n", "Parcel Post", "5-10 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Express post parcels and letters", "3-4 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Regular letters", "5-10 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Priority letters", "Up to 3 business day", "Aus wide");
	}

}