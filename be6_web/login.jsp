<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="demo.DatabaseConnectionUtil"%>
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
<%
	String username = null;
	String password = null;
	String sessionCookieValue = null;
	
	//look for username and password in current session
	username = (String) session.getAttribute("username");
	password = (String) session.getAttribute("password");

	if (username != null && password != null) {
		System.out.println("username and password found");
		response.sendRedirect("home.jsp");
		return;
	}
	if (username == null && password == null) {
		//look for session cookie in current session 
		System.out.println("finding cookies");
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			System.out.println("some cookies found");
			for (Cookie cookie : cookies) {
				if ("sessionCookie".equals(cookie.getName())) {
					sessionCookieValue = cookie.getValue();
					break;
				}
			}
		}
		if (sessionCookieValue != null) {
			System.out.println("session cookie found");

			try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
				String cookieQuery = "SELECT * FROM session_cookies WHERE sessionID = ?";
				try (PreparedStatement psCookie = connection.prepareStatement(cookieQuery)) {
				psCookie.setString(1, sessionCookieValue);
					try (ResultSet rsCookie = psCookie.executeQuery()) {
						if (rsCookie.next()) {
			
						int userID = rsCookie.getInt("user_id");
						String userQuery = "SELECT * FROM users WHERE id = ? ";
							try (PreparedStatement psUser = connection.prepareStatement(userQuery)) {
							psUser.setInt(1, userID);
								try (ResultSet rsUser = psUser.executeQuery()) {
									if (rsUser.next()) {
										username = rsUser.getString("username");
										session.setAttribute("username", username);
										response.sendRedirect("home.jsp");
										return;
									}
								}
							}
						}
					}
				}
			}catch(Exception e){
				System.out.println("session cookie found but error happens!");
				e.printStackTrace();
			}
		
			//if cookie is not found in database--> invalidate the browser cookie by setting its value to null
			Cookie cookie = new Cookie("sessionCookie", null);
		    cookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
		    cookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
		    response.addCookie(cookie);
		    response.sendRedirect("login.jsp");
		
		
	}else{

	System.out.println("Not found any session cookie");
	}
	}
	if (username == null && password == null && sessionCookieValue == null) {
		System.out.println("printing form");
       %>
		<h2>Login</h2>
		<form action="login.jsp" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br> <br> <label
				for="password">Password:</label> <input type="password" id="password"
				name="password" required><br> <br> <input
				type="submit" value="Login">
		</form>
		<%
		username = request.getParameter("username");
		password = request.getParameter("password");
	}

		if (username != null && password != null) {
		boolean authenticated = false;
		int userID = 0; // to use in sessionCookie
	
			try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()){
				// Query to get user
				String query = "SELECT * FROM users WHERE username = ?";
				try( PreparedStatement ps = connection.prepareStatement(query)){
					ps.setString(1, username);
					try(ResultSet rs = ps.executeQuery()){
		
						if (rs.next()) {
							String storedPassword = rs.getString("password");
				
							if (storedPassword.equals(password)) {
								authenticated = true;
								userID = rs.getInt("id");
							}
						}
	
					if (authenticated) {
						
						//FIND ALL COOKIES RELATED TO THE USER 
						String findCookieQuery = "SELECT * FROM session_cookies WHERE user_id = ?";
						try(PreparedStatement psFind = connection.prepareStatement(findCookieQuery)){
							psFind.setInt(1, userID);
							try(ResultSet cookieRs = psFind.executeQuery()){
								if (cookieRs.next()) {
									System.out.println("a cookie is found for this user");
									// VALIDATING COOKIE		
									/*
									check for cookie's expiration time.
									If it is still active, calculate the maxAge and send it back
									*/
								
									Timestamp cookieExpirationTime = cookieRs.getTimestamp("expiration_time");
			
									if (cookieExpirationTime.before(Timestamp.from(Instant.now()))) {
										// Cookie has expired, update expiration time to extra 7 days from current time
										Timestamp newExpirationTime = Timestamp.from(Instant.now().plusSeconds(7 * 24 * 60 * 60));
										String updateCookieQuery = "UPDATE session_cookies SET expiration_time = ? WHERE user_id = ?";
										try(PreparedStatement psUpdate = connection.prepareStatement(updateCookieQuery)){
											psUpdate.setTimestamp(1, newExpirationTime);
											ps.setInt(2, userID);
											ps.executeUpdate();
										}catch(Exception e){
											e.printStackTrace();
										}
										// Send the cookie back to the user
										String cookieValue = cookieRs.getString("sessionID");
										Cookie sessionCookie = new Cookie("sessionCookie", cookieValue);
										//Timestamp object cannot be subtracted directly, use .getTime() to convert to milliseconds
										sessionCookie.setMaxAge((int)(cookieRs.getTimestamp("expiration_time").getTime()-Timestamp.from(Instant.now()).getTime())/1000);
										//prevent cookie be acessed via JavaScript
										sessionCookie.setHttpOnly(true);
										//cookie will only be transmittted over HTTPS
										sessionCookie.setSecure(true);
										//make cookie availabe to all path: login, homepage, etc to maintain login status
										sessionCookie.setPath("/be6-web");
										response.addCookie(sessionCookie);
										session.setAttribute("username", username);
										session.setAttribute("password", password);
										response.sendRedirect("home.jsp");
										}
									} else {
										System.out.println("no valid cookie is found for this user, creating new cookie");
										//CREATE A COOKIE AND STORE SESSION ID IN THE COOKIE
										// get the sessionID
										String sessionID = session.getId();
										Cookie sessionCookie = new Cookie("sessionCookie", sessionID);
										//set the expiring time for cookies - in seconds
							
										int expirationDuration = 7 * 24 * 60 * 60;//1 week in seconds
										sessionCookie.setMaxAge(expirationDuration);
										//prevent cookie be acessed via JavaScript
										sessionCookie.setHttpOnly(true);
										//cookie will only be transmittted over HTTPS
										sessionCookie.setSecure(true);
										//make cookie availabe to all path: login, homepage, etc to maintain login status
										sessionCookie.setPath("/be6-web");
							
										response.addCookie(sessionCookie);
										// save sessionCookie in database
										//additional session info to save in database
										Instant creationTime = Instant.ofEpochMilli(session.getCreationTime());
										Instant expirationTime = Instant.ofEpochMilli(session.getCreationTime() + expirationDuration * 1000);
										Timestamp creationTimestamp = Timestamp.from(creationTime);
										Timestamp expirationTimestamp = Timestamp.from(expirationTime);
							
										//create table session_cookies if not exist
										String sql = "CREATE TABLE IF NOT EXISTS session_cookies(id INT AUTO_INCREMENT PRIMARY KEY, sessionID VARCHAR(100) NOT NULL UNIQUE, user_id VARCHAR(50) NOT NULL, creation_time TIMESTAMP NOT NULL,expiration_time TIMESTAMP NOT NULL);";
										connection.createStatement().executeUpdate(sql);
						
										//save sessionCookie into the table
										String sql1 = "INSERT INTO session_cookies(sessionID,user_id,creation_time,expiration_time) VALUES (?,?,?,?)";
										try(PreparedStatement ps1 = connection.prepareStatement(sql1)){
						
										ps1.setString(1, sessionID);
										ps1.setInt(2, userID);
										ps1.setTimestamp(3, creationTimestamp);
										ps1.setTimestamp(4, expirationTimestamp);
										ps1.executeUpdate();
										}
										System.out.println("Session cookie saved!");
										//redirection 
										session.setAttribute("username", username);
										session.setAttribute("password", password);
										response.sendRedirect("home.jsp");
									}
								}
							}
						}else {
							out.println("<h3>Login Failed</h3>");
							out.println("<p>Invalid username or password.</p>");	
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			} 
		  }//if username and password not null
	%>
</body>
</html>

