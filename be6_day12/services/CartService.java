package be6_day12.services;

import java.util.ArrayList;
import java.util.Scanner;

import be6_day12.dto.Cart;
import be6_day12.dto.ItemInCart;
import be6_day12.dto.Menu;
import be6_day12.entities.Product;

public class CartService {

	public Cart updateCart(int productID, int selection, Cart cart, Menu menu) {

		
		Scanner scan = new Scanner(System.in);
		int updatedQuantity;
		while (true) {
			System.out.println("Quantity: ");
			if (scan.hasNextInt()) {
				updatedQuantity = scan.nextInt();
				break;
			} else {
				System.out.println("Invalid input. Enter again!");
				scan.next();
			}
		}

		if (selection == 1) {
			// ADD TO CART
			ArrayList<Product> ProductList = menu.productOptions;
			for (Product product : ProductList) {
				if (product.productID == productID) {
					if (updatedQuantity > 0) {
						ItemInCart item = new ItemInCart(product,updatedQuantity);
						cart.items.add(item);
						System.out.println("Added to CART!");
						break;
					}
				}

			}

		} else if (selection == 2) {
			// REMOVE FROM CART
			ArrayList<ItemInCart>itemList = cart.items;
			for (ItemInCart item : itemList) {
				if (item.product.productID == productID) {
					if (updatedQuantity <= item.quantity) {
						item.quantity = item.quantity - updatedQuantity;
					}
					if (item.quantity == 0) {
						cart.items.remove(item);
					}
					System.out.println("Removed from CART!");
					break;
				}
			}

		}
		return cart;

	}

	public void showCart(Cart cart) {

		System.out.println("shopping CART: ");
		System.out.println();
		if (cart.items.size() == 0) {
			System.out.println("shopping cart is empty");
			System.out.println();
		} else {
			System.out.printf("%-3s %-20s %-20s %-20s %-20s%n", "", "Product", "Unit Price", "Quantity", "Total");
			for (ItemInCart item : cart.items) {
				System.out.printf("%-3s %-20s %-20s %-20s %-20s%n", (cart.items.indexOf(item) + 1) + " . ",
						item.product.productName, item.product.price + " AUD", item.quantity,
						item.quantity * item.product.price);
			}

		}
	}

}
