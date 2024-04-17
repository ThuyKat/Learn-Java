package be6_day9;

public class PlaneService extends ShippingService {
	public void showPrice() {
		System.out.println("Price for package by deadweight(kg) for service by PLANE");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "5kg", "10kg", "20kg", "30kg");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "$50", "$53", "$57", "$61");
	}

	public void showShippingTime() {
		System.out.println("shipping time table by product types for service by PLANE");
		System.out.printf("%-50s %-30s %-20s%n", "Service", "Delivery speed", "Coverage");
		System.out.printf("%-50s %-30s %-20s%n", "Parcel Post", "1-2 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Express post parcels and letters", "1 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Regular letters", "1-2 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Priority letters", "Up to 1 business day", "Aus wide");
	}

}