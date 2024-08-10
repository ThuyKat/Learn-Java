package db;

import java.sql.SQLException;

public class ItemInCart {
	
	private Product product;
	private int quantity;
	

	public ItemInCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemInCart(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String serialize() {
		
		return product.getId() + ":"+quantity;
		
	}
	public static ItemInCart deserialize(String serializedItemInCart) throws SQLException {
		String[] components = serializedItemInCart.split(":");
		String productId = components[0];
		ProductDatabaseUtil ProductRepo = new ProductDatabaseUtil();
		Product product = ProductRepo.getProductById(productId);
		int quantity = Integer.parseInt(components[1]);
		return new ItemInCart(product,quantity);
	}
	
	

}
