package be6_day12.dto;

import be6_day12.entities.Product;

public class ItemInCart {
public Product product; // public access required to be used in Cart Service- showCart
public int quantity;
public ItemInCart(Product product, int quantity) {
	this.product = product;
	this.quantity = quantity;
}

}
