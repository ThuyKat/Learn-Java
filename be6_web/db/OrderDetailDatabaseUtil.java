package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDatabaseUtil {
	
public void saveOrderDetail(List<OrderDetail> orderDetails) throws SQLException {
		
		PreparedStatement ps= null;
		
		for(OrderDetail item : orderDetails) {
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			String query = "INSERT INTO order_details (quantity, order_id,product_id,product_price) VALUES (?, ?, ?,?)";

			ps = connection.prepareStatement(query);
			ps.setInt(1,item.getQuantity() );
			ps.setInt(3, item.getProductId());
			ps.setInt(2, item.getOrderId());
			ps.setFloat(4, item.getProductPrice());
			
			int rowInserted = ps.executeUpdate();
			if(rowInserted !=0) {
				System.out.println("order details are successfully  saved");
			}else {
				System.out.println("order details are not yet saved");
			}
			
		}finally {
			if(ps !=null) {
				ps.close();
			}
		}
		}
		
	}

}
