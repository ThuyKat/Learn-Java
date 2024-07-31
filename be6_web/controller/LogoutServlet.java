package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DatabaseConnectionUtil;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null){ 
			String username = (String)session.getAttribute("username");
		//delete the saved sessionCookie in database
					try(Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
						String findUser = "SELECT * FROM users WHERE username =?"; // username is assumed unique in Database
						PreparedStatement ps = connection.prepareStatement(findUser);
						ps.setString(1,username);
						ResultSet rs = ps.executeQuery();
						
						if(rs.next()){
							
							int userID = rs.getInt("id");
							//find all related session cookies in database
							String findCookie = "DELETE FROM session_cookies WHERE user_id =?";
							PreparedStatement ps1 = connection.prepareStatement(findCookie);
							ps1.setInt(1,userID);
							int executedRows = ps1.executeUpdate();
							System.out.println("deleted "+executedRows+" cookies where userID is " +userID);	
						}
						session.removeAttribute("username");
						session.removeAttribute("password");
					}catch(Exception e){
						e.printStackTrace();
					}
				}


			response.sendRedirect("login.jsp");
	}


}
