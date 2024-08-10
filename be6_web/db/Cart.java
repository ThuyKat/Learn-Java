package db;

import java.util.List;

public class Cart {
	private int id;
	private List<ItemInCart> items;
	private int userId; //generate a guest sessionID and save to cookieDB as usual
	private double totalPrice;
	
	
	public Cart(int id, List<ItemInCart> items, int userId, double totalPrice) {
		super();
		this.id = id;
		this.items = items;
		this.userId = userId;
		this.totalPrice = totalPrice;
	}
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ItemInCart> getItems() {
		return items;
	}
	public void setItems(List<ItemInCart> items) {
		this.items = items;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

}
