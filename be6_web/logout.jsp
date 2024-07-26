<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="demo.DatabaseConnectionUtil"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
	<% 
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
%>

</body>
</html>