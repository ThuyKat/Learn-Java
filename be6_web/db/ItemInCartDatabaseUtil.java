package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemInCartDatabaseUtil {
	public List<ItemInCart> getItemsByCartId(int cartId) throws SQLException {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ItemInCart> items = new ArrayList<>();
		
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			String query = "SELECT * FROM item_in_carts WHERE cart_id =?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			if(rs.next()) {
				ItemInCart item = new ItemInCart();
				String productId = rs.getString("product_id");
				int quantity = rs.getInt("quantity");
				ProductDatabaseUtil productRepo = new ProductDatabaseUtil();
				Product product = productRepo.getProductById(productId);
				item.setProduct(product);
				item.setQuantity(quantity);	
				items.add(item);
			}
		}finally {
			if(ps !=null) {
				ps.close();
			}
			if(rs !=null) {
				rs.close();
			}
		}
		
		return items;
	}

	public void saveItemInCart(List<ItemInCart> items,int cartId) throws SQLException {
		
		PreparedStatement ps= null;
		
		for(ItemInCart item : items) {
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			String query = "INSERT INTO item_in_carts (quantity, product_id, cart_id) VALUES (?, ?, ?)";

			ps = connection.prepareStatement(query);
			ps.setInt(1,item.getQuantity() );
			ps.setInt(2, item.getProduct().getId());
			ps.setInt(3, cartId);
			
			int rowInserted = ps.executeUpdate();
			if(rowInserted !=0) {
				System.out.println("itemInCart successfully  saved");
			}else {
				System.out.println("item is not yet saved");
			}
			
		}finally {
			if(ps !=null) {
				ps.close();
			}
		}
		}
		
	}

	public void updateItemInCart(List<ItemInCart> items,int cartId) throws SQLException {
		PreparedStatement ps= null;
		//delete item in cart with cardId
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			String query = "DELETE FROM item_in_carts WHERE cart_id=?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cartId);
			
			int rowDeleted = ps.executeUpdate();
			if(rowDeleted !=0) {
				System.out.println(rowDeleted+" items in cart with cartId : "+cartId+" are deleted");
			}else {
				System.out.println("delete not successfull");
			}
			
		}finally {
			if(ps !=null) {
				ps.close();
			}
		}
		saveItemInCart(items,cartId);
		
	}

}
