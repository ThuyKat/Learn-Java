package be6_day12.dto;

import java.util.ArrayList;

public class Cart {
public ArrayList<ItemInCart>items; // public access required for CartService 
public double total;
public Cart() {
	this.items = new ArrayList<ItemInCart>(); // if not writing this, default value is null
}
}
