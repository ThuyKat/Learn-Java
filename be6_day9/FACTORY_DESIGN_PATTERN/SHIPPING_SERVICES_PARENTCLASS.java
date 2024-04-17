package be6_day9;

public class ShippingService {

	public void showPrice() {
		System.out.println("Price per package based on dead weight ");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "5kg", "10kg", "20kg", "30kg");
		System.out.printf("%-10s %-10s %-10s %-10s%n", "$45", "$48", "$52", "$56");
	}

	public void showShippingTime() {
		System.out.println("Shipping time by product types");
		System.out.printf("%-50s %-30s %-20s%n", "Service", "Delivery speed", "Coverage");
		System.out.printf("%-50s %-30s %-20s%n", "Parcel Post", "2-4 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Express post parcels and letters", "1-2 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Regular letters", "2-4 business days", "Aus wide");
		System.out.printf("%-50s %-30s %-20s%n", "Priority letters", "Up to 2 business day", "Aus wide");
	}

	public void showDeliveryAndPaymentGuidelines() {
		System.out.println("Guidelines on delivery and payment");
		System.out.println("You will receive a message to track your delivery");
		System.out.println("Payment should be made in advance to confirm delivery");
	}

	public ShippingService routeCreation(int selectedRoute) {

		if (selectedRoute == 1) {
			return new PlaneService();
		} else if (selectedRoute == 2) {
			return new ShipService();
		} else if (selectedRoute == 3) { // update EconomyService
			return new EconomyService();
		}
		return new ShippingService();
	}

}