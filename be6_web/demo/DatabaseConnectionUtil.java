package demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {
	
	public static Connection getDatabaseConnection() {
		Connection connection = null;
		try {
			
			String dbName = "JDBC_be6";
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			System.out.println("DB is connected");
			connection.createStatement().executeUpdate("create database if not exists " + dbName + ";");
			System.out.println("record is created");
			connection.close();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "root");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
