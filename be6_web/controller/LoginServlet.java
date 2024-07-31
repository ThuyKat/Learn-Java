package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import db.DatabaseConnectionUtil;
import db.UserDatabaseUtil;

@WebServlet("/login2")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		String password = null;
		String sessionCookieValue = null;
		HttpSession session = request.getSession();

		// look for username and password in current session
		username = (String) session.getAttribute("username");
		password = (String) session.getAttribute("password");

		if (username != null && password != null) {
			System.out.println("username and password found");
			response.sendRedirect("home.jsp");
			return;
		}
		if (username == null && password == null) {
			// look for session cookie in current session
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
					PreparedStatement psCookie = connection.prepareStatement(cookieQuery);
					psCookie.setString(1, sessionCookieValue);
					ResultSet rsCookie = psCookie.executeQuery();
					if (rsCookie.next()) {

						int userID = rsCookie.getInt("user_id");
						String userQuery = "SELECT * FROM users WHERE id = ? ";
						PreparedStatement psUser = connection.prepareStatement(userQuery);
						psUser.setInt(1, userID);
						ResultSet rsUser = psUser.executeQuery();
						if (rsUser.next()) {
							username = rsUser.getString("username");
							session.setAttribute("username", username);
							response.sendRedirect("home.jsp");
							return;
						}
					}

				} catch (Exception e) {
					System.out.println("session cookie found but error happens!");
					e.printStackTrace();
				}

				// if cookie is not found in database--> invalidate the browser cookie by
				// setting its value to null
				Cookie cookie = new Cookie("sessionCookie", null);
				cookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
				cookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
				response.addCookie(cookie);
				response.sendRedirect("login.jsp");

			} else {

				System.out.println("Not found any session cookie");
			}
		}
		if (username == null && password == null && sessionCookieValue == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (username != null && password != null) {
			boolean authenticated = false;
			int userID = 0; // to use in sessionCookie
			try {
				if (UserDatabaseUtil.authenticate(username, password)) {
					String findCookieQuery = "SELECT * FROM session_cookies WHERE user_id = ?";
					try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
						PreparedStatement psFind = connection.prepareStatement(findCookieQuery);
						psFind.setInt(1, userID);
						ResultSet cookieRs = psFind.executeQuery();
						if (cookieRs.next()) {
							System.out.println("a cookie is found for this user");
							// VALIDATING COOKIE
							/*
							 * check for cookie's expiration time. If it is still active, calculate the
							 * maxAge and send it back
							 */

							Timestamp cookieExpirationTime = cookieRs.getTimestamp("expiration_time");

							if (cookieExpirationTime.before(Timestamp.from(Instant.now()))) {
								// Cookie has expired, update expiration time to extra 7 days from current time
								Timestamp newExpirationTime = Timestamp
										.from(Instant.now().plusSeconds(7 * 24 * 60 * 60));
								String updateCookieQuery = "UPDATE session_cookies SET expiration_time = ? WHERE user_id = ?";
								PreparedStatement psUpdate = connection.prepareStatement(updateCookieQuery);
								psUpdate.setTimestamp(1, newExpirationTime);
								psUpdate.setInt(2, userID);
								psUpdate.executeUpdate();

								// Send the cookie back to the user
								String cookieValue = cookieRs.getString("sessionID");
								Cookie sessionCookie = new Cookie("sessionCookie", cookieValue);
								// Timestamp object cannot be subtracted directly, use .getTime() to convert to
								// milliseconds
								sessionCookie.setMaxAge((int) (cookieRs.getTimestamp("expiration_time").getTime()
										- Timestamp.from(Instant.now()).getTime()) / 1000);
								// prevent cookie be acessed via JavaScript
								sessionCookie.setHttpOnly(true);
								// cookie will only be transmittted over HTTPS
								sessionCookie.setSecure(true);
								// make cookie availabe to all path: login, homepage, etc to maintain login
								// status
								sessionCookie.setPath("/be6-web");
								response.addCookie(sessionCookie);
								session.setAttribute("username", username);
								session.setAttribute("password", password);

							}
						} else {
							System.out.println("no valid cookie is found for this user, creating new cookie");
							// CREATE A COOKIE AND STORE SESSION ID IN THE COOKIE
							// get the sessionID
							String sessionID = session.getId();
							Cookie sessionCookie = new Cookie("sessionCookie", sessionID);
							// set the expiring time for cookies - in seconds

							int expirationDuration = 7 * 24 * 60 * 60;// 1 week in seconds
							sessionCookie.setMaxAge(expirationDuration);
							// prevent cookie be acessed via JavaScript
							sessionCookie.setHttpOnly(true);
							// cookie will only be transmittted over HTTPS
							sessionCookie.setSecure(true);
							// make cookie availabe to all path: login, homepage, etc to maintain login
							// status
							sessionCookie.setPath("/be6-web");

							response.addCookie(sessionCookie);
							// save sessionCookie in database
							// additional session info to save in database
							Instant creationTime = Instant.ofEpochMilli(session.getCreationTime());
							Instant expirationTime = Instant
									.ofEpochMilli(session.getCreationTime() + expirationDuration * 1000);
							Timestamp creationTimestamp = Timestamp.from(creationTime);
							Timestamp expirationTimestamp = Timestamp.from(expirationTime);

							// create table session_cookies if not exist
							String sql = "CREATE TABLE IF NOT EXISTS session_cookies(id INT AUTO_INCREMENT PRIMARY KEY, sessionID VARCHAR(100) NOT NULL UNIQUE, user_id VARCHAR(50) NOT NULL, creation_time TIMESTAMP NOT NULL,expiration_time TIMESTAMP NOT NULL);";
							connection.createStatement().executeUpdate(sql);

							// save sessionCookie into the table
							String sql1 = "INSERT INTO session_cookies(sessionID,user_id,creation_time,expiration_time) VALUES (?,?,?,?)";
							try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {

								ps1.setString(1, sessionID);
								ps1.setInt(2, userID);
								ps1.setTimestamp(3, creationTimestamp);
								ps1.setTimestamp(4, expirationTimestamp);
								ps1.executeUpdate();
							}
							System.out.println("Session cookie saved!");
							// redirection
							session.setAttribute("username", username);
							session.setAttribute("password", password);

						}
						response.sendRedirect("home.jsp");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					PrintWriter out = response.getWriter();
					out.println("<h3>Login Failed</h3>");
					out.println("<p>Invalid username or password.</p>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println("Missing username or password");
		}
	}
}
