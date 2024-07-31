package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseUtil {

	private static User getUserByUsername(String username) throws SQLException {
		User user = null;
		//get DB connection
		System.out.println("connecting to DB");
		Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
		
		//we asume that username is unique and only one user matched with a particular username
		// get user by username
		System.out.println("connected to DB");
		//load query
		PreparedStatement ps = connection.prepareStatement("select * from users where users.username = ?;");
		ps.setString(1, username);
		//execute query
		ResultSet result = ps.executeQuery();
		System.out.println("executed query to get user from database ");
		if(result.next()) {
		int id = result.getInt("id");
		String password = result.getString("password");
		String gender = result.getString("gender");
		String hobbies = result.getString("hobbies");
		String city = result.getString("city");
		connection.close();
		user= new User(id,username,password,gender,hobbies,city);
		} else{
			System.out.println("user not found");
		}
		return user;
		
	}

	public static boolean authenticate(String username, String password) throws SQLException {
		//retrieve userId + password from the database
		// compare with the userId and password provided
		User userDB = getUserByUsername(username);
		String usernameDB = userDB.getUserName();
		String passwordDB = userDB.getPassword();
		
		if(usernameDB.equalsIgnoreCase(username) && passwordDB.equalsIgnoreCase(password)) {
			return true;
		}
		return false;
		
	}
}
