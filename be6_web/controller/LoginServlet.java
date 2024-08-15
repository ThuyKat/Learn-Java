package controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.time.Instant;

import db.CookieDB;
import db.CookieUtil;

import db.User;
import db.UserDatabaseUtil;

//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		User user = null;
		String username = null;
		String password = null;
		String sessionCookieValue = null;
		HttpSession session = request.getSession();
		String returnURI = request.getParameter("returnURI");

		// look for username and password in current session
		username = (String) session.getAttribute("username");
		password = (String) session.getAttribute("password");

		if (username != null && password != null) {
			System.out.println("username and password found");
			if (returnURI != null) {
				response.sendRedirect(returnURI);
			} else {
				response.sendRedirect("/be6-web/Home");
			}
			return;
		}
		if (username == null && password == null) {
			// look for session cookie in current session
			System.out.println("finding cookies");
			Cookie cookie = CookieUtil.findCookieByName(request, "sessionCookie");
			if (cookie != null) {
				System.out.println("session cookie found");
				sessionCookieValue = cookie.getValue();
				try {
					int userId = CookieUtil.getUserIdFromSessionCookie(sessionCookieValue);
					if (userId != -1) {
						User user = UserDatabaseUtil.getUserById(userId);
						if (user != null) {
							username = user.getUserName();
							session.setAttribute("username", username);
							if (returnURI != null) {
								response.sendRedirect(returnURI);
							} else {
								response.sendRedirect("/be6-web/Home");
							}
							return;
						}
					}

				} catch (Exception e) {
					System.out.println("session cookie found but error happens!");
					e.printStackTrace();

				}

				// if cookie is not found in database--> invalidate the browser cookie by
				// setting its value to null
				Cookie newCookie = new Cookie("sessionCookie", null);
				newCookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
				newCookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
				response.addCookie(newCookie);
				session.setAttribute("returnURI", returnURI);
				response.sendRedirect("loginForm.jsp");

			} else {

				System.out.println("Not found any session cookie");
				session.setAttribute("returnURI", returnURI);
				request.getRequestDispatcher("loginForm.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		// Get returnURI
		String returnURI = request.getParameter("returnURI");
		if (returnURI == null) {
			returnURI = (String) session.getAttribute("returnURI");
		}
		System.out.println("I am at login with return URI is: " + returnURI);

		if (username != null && password != null) {
			int userID = 0; // to use in sessionCookie

//			PreparedStatement psFind = null;
//			ResultSet cookieRs = null;
//			PreparedStatement ps1 = null;
			try {
				if (UserDatabaseUtil.authenticate(username, password)) {
					User user = UserDatabaseUtil.getUserByUsername(username);
					userID = user.getUserId();
					CookieDB cookieDB = CookieUtil.getCookieDBByUserId(userID);
					// VALIDATING COOKIE
					/*
					 * check for cookie's expiration time. If it is still active, calculate the
					 * maxAge and send it back
					 */
					if (cookieDB != null) {
						cookieDB = CookieUtil.updateCookieExpirationTime(cookieDB);

						// SEND BACK UPDATED COOKIE TO USER
						String cookieValue = cookieDB.getSessionID();

						Cookie sessionCookie = new Cookie("sessionCookie", cookieValue);
						// Timestamp object cannot be subtracted directly, use .getTime() to convert to
						// milliseconds

						sessionCookie.setMaxAge(
								(int) (cookieDB.getExpirationTime().getTime() - Timestamp.from(Instant.now()).getTime())
										/ 1000);
						// prevent cookie be acessed via JavaScript
						sessionCookie.setHttpOnly(true);
						// cookie will only be transmittted over HTTPS
						sessionCookie.setSecure(true);
						// make cookie availabe to all path: login, homepage, etc to maintain login
						// status
						sessionCookie.setPath("/be6-web");
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						response.addCookie(sessionCookie);

					} else {
						System.out.println("no valid cookie is found for this user, creating new cookie");

						// get the sessionID
						String sessionID = session.getId();

						// create sessionCookie and send to client
						Cookie sessionCookie = CookieUtil.createSessionCookie(sessionID);
						response.addCookie(sessionCookie);

						// save sessionCookie in database
						Instant creationTime = Instant.ofEpochMilli(session.getCreationTime());
						Instant expirationTime = Instant
								.ofEpochMilli(session.getCreationTime() + sessionCookie.getMaxAge() * 1000);
						Timestamp creationTimestamp = Timestamp.from(creationTime);
						Timestamp expirationTimestamp = Timestamp.from(expirationTime);
						CookieDB cookieToSave = new CookieDB();
						cookieToSave.setExpirationTime(expirationTimestamp);
						cookieToSave.setUserId(String.valueOf(userID));
						cookieToSave.setSessionID(sessionID);
						cookieToSave.setCreationTime(creationTimestamp);
						CookieUtil.saveCookieToDatabase(cookieToSave);
						// redirection
						session.setAttribute("username", username);
						session.setAttribute("password", password);
					}
					if (returnURI != null) {
						System.out.println("returning to ProductDetail....");
						response.sendRedirect(returnURI);
					} else {
						response.sendRedirect("/be6-web/Home");
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
			out.println("Incorrect username or password");
		}
	}
}
