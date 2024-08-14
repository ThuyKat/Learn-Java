package db;

public class OrderDetail {
	private int productId;
	private int quantity;
	private int orderId;
	private float productPrice;
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int productId, int quantity, int orderId, float productPrice) {
		super();
		this.setProductId(productId);
		this.setQuantity(quantity);
		this.setOrderId(orderId);
		this.setProductPrice(productPrice);
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
