package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDatabaseUtil {

	

	public  List<Product> getAllProduct() throws SQLException {
		
		List<Product> products = new ArrayList<>();
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
			
			String query = "SELECT * FROM products";
			
			 ps = connection.prepareStatement(query);
			 rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getDouble("price"));
	            product.setCategory(rs.getString("category"));
	            product.setDescription(rs.getString("description"));
	            
	            products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
		}
		
		return products;
	}
	
public  List<Product> getProductByCategory(String category) throws SQLException {
		
		List<Product> products = getAllProduct();
		List<Product>productByCategory = new ArrayList<>();
		
		
		for(Product product:products) {
        	if(product.getCategory().equalsIgnoreCase(category) ){
        		productByCategory.add(product);
        	}
        }
		
		return productByCategory;
	}



public  Product getProductById(String id) throws SQLException {
	
	List<Product> products = getAllProduct();
	Product product = null;
	
	
	for(Product productDB:products) {
    	if(productDB.getId()==Integer.parseInt(id) ){
    		product = productDB;
    	}
    }
	
	return product;
}
}
