package be6_day12.services;

import java.util.ArrayList;
import be6_day12.dto.Menu;
import be6_day12.entities.Product;

public class DeliveryService extends Service<Product> {

	public String showDeliveryTime(int selection) {

		if (selection == 1) {
			return "Saving option delivery time:  3 to 5 days";
		} else if (selection == 2) {
			return "Fast option delivery time:  1 to 2 days";
		} else {
			return "Standard option delivery time:  5 to 7 days";
		}
	}

	
	public Product getShippingBySelection(int selection,Menu menu) {
		ArrayList<Product> shippingOptions = menu.shippingOptions;
		for(Product shipping : shippingOptions) {
			if(shipping.productID == selection) {
				return shipping;
			}
		}
		return null;
	}


	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
