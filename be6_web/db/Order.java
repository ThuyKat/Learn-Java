package db;

import java.util.List;

public class Order {

	
		private int id;
		private List<OrderDetail> orderDetails;
		private int userId; //generate a guest sessionID and save to cookieDB as usual
		private OrderStatus status;
		
		public Order(int id, List<OrderDetail> orderDetails, int userId, OrderStatus status) {
			super();
			this.id = id;
			this.orderDetails = orderDetails;
			this.userId = userId;
			this.status = status;
		}
		
		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public List<OrderDetail> getOrderDetails() {
			return orderDetails;
		}
		public void setOrderDetails(List<OrderDetail> orderDetails) {
			this.orderDetails = orderDetails;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public OrderStatus getStatus() {
			return status;
		}
		public void setStatus(OrderStatus status) {
			this.status = status;
		}

}