package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDatabaseUtil {
	public Cart getCartByUserId(int userId) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart cart = null;
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			String query = "SELECT * FROM carts WHERE user_id =?";
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(userId));
			rs = ps.executeQuery();
			if(rs.next()) {
				int cartId = rs.getInt("id");
				 cart = new Cart();
				ItemInCartDatabaseUtil itemInCartRepo = new ItemInCartDatabaseUtil();
				List<ItemInCart> items = itemInCartRepo.getItemsByCartId(cartId);
				cart.setItems(items);
				cart.setUserId(userId);
				cart.setId(cartId);
				
			}
		}finally {
			if(ps !=null) {
			  ps.close();
			}
			if(rs !=null) {
			  rs.close();
			}
		}
		return cart;
	}
	
	public void saveCart(Cart cart) throws SQLException {
		
		PreparedStatement ps = null;
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			//save new Cart to DB
			String query =  "INSERT INTO carts (user_id,total_price) VALUES (?,?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cart.getUserId());
			ps.setDouble(2, cart.getTotalPrice());
			int rowInserted = ps.executeUpdate();
			if(rowInserted !=0) {
				System.out.println("new cart is saved to database");
			}else {
				System.out.println(" cart is not yet saved!");
			}
		}
	
	}
	
	public void updateCart(Cart cart) throws SQLException{
		PreparedStatement ps = null;
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			//save new Cart to DB
			String query =  "UPDATE carts SET updated_at = CURRENT_TIMESTAMP, total_price = ?  WHERE id = ?";
			ps = connection.prepareStatement(query);
			ps.setDouble(1, cart.getTotalPrice());
			ps.setInt(2, cart.getId());
			int rowUpdated = ps.executeUpdate();
			if(rowUpdated!=0) {
				System.out.println("cart is updated");
			}else {
				System.out.println(" cart is not updated");
			}
		}
	}

}
