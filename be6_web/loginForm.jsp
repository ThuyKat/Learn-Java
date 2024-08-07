<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="db.DatabaseConnectionUtil"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
// Prevent caching
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>

		<h2>Login</h2>
		<form action="/be6-web/Login" method="post">
		<label for="username">Username:</label> 
		<input type="text"id="username" name="username" required>
		<br> 
		
		<label for="password">Password:</label>
		<input type="password" id="password"name="password" required>
		<br> 
		<input type="submit" value="Login">
		</form>
	
</body>
</html>

