package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDatabaseUtil {

	

	public static List<Product> getAllProduct() {
		
		List<Product> products = new ArrayList<>();
		
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			
			String query = "SELECT * FROM products";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getDouble("price"));
	            product.setCategory(rs.getString("category"));
	            
	            products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
public static List<Product> getProductByCategory(String category) {
		
		List<Product> products = getAllProduct();
		List<Product>productByCategory = new ArrayList<>();
		
		
		for(Product product:products) {
        	if(product.getCategory().equalsIgnoreCase(category) ){
        		productByCategory.add(product);
        	}
        }
		
		return productByCategory;
	}
}
