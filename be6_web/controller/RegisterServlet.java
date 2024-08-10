package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DatabaseConnectionUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataConnection
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String hobbies = request.getParameter("hobbies");
		String city = request.getParameter("city");
		Connection connection = null;
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println("<h1>Thanks</h1>");
		try {
			//connect to DB and create table user if not exist
			connection = DatabaseConnectionUtil.getDatabaseConnection();
			connection.createStatement()
			.executeUpdate("create table if not exists users( " + "id int auto_increment primary key,"
					+ " username varchar(50) ," + " password varchar(100) ," + " gender varchar(20),"
					+ " hobbies varchar(100)," + "city varchar(50));");
			
			// save the new user record into user table
			int rowsInserted = saveUserRecord(connection, username, password, gender, hobbies, city);
			
			//check if user record is saved, return message to browser
			if (rowsInserted > 0) {
				out.println("Register successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private int saveUserRecord(Connection connection, String username, String password, String gender, String hobbies,
			String city) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("insert into users(username,password,gender,hobbies,city) values(?,?,?,?,?);");
		statement.setString(1, username);
		statement.setString(2, password);
		statement.setString(3, gender);
		statement.setString(4, hobbies);
		statement.setString(5, city);
		// execute statement
		int rowsInserted = statement.executeUpdate();
		return rowsInserted;

	}

	
}
