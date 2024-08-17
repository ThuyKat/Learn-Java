package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDatabaseUtil {
	
public int saveOrder(Order order, Connection connection) throws SQLException {
		int orderId = -1;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try{
			//save new Cart to DB
			String query =  "INSERT INTO orders (user_id,status) VALUES (?,?)";
			ps = connection.prepareStatement(query,	PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getStatus().name());
			int rowInserted = ps.executeUpdate();
			if(rowInserted !=0) {
				generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                	 orderId = generatedKeys.getInt(1);
                }
				System.out.println("new order is saved to database");
				
			}else {
				System.out.println(" order is not yet saved!");
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (ps != null) ps.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return orderId;
	
	}

}
