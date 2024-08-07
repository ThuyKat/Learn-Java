package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static Cookie findCookieByName(HttpServletRequest request, String name) {
		Cookie sessionCookie = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			System.out.println("some cookies found");
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					sessionCookie = cookie;

					break;
				}
			}
		}
		return sessionCookie;
	}

	public static int getUserIdFromSessionCookie(Connection connection, String sessionCookieValue) throws SQLException {
		String cookieQuery = "SELECT * FROM session_cookies WHERE sessionID = ?";
		try (PreparedStatement psCookie = connection.prepareStatement(cookieQuery)) {
			psCookie.setString(1, sessionCookieValue);
			try (ResultSet rsCookie = psCookie.executeQuery()) {
				if (rsCookie.next()) {
					return rsCookie.getInt("user_id");
				}
			}
		}
		return -1;
	}

	public static CookieDB getCookieDBByUserId(Connection connection, int userID) throws SQLException {
		CookieDB cookieDB = null;
		String findCookieQuery = "SELECT * FROM session_cookies WHERE user_id = ?";
		try (PreparedStatement psFind = connection.prepareStatement(findCookieQuery)) {

			psFind.setInt(1, userID);
			try (ResultSet cookieRs = psFind.executeQuery()) {
				if (cookieRs.next()) {
					System.out.println("a cookie is found for this user");
					cookieDB = new CookieDB();
					Timestamp cookieExpirationTime = cookieRs.getTimestamp("expiration_time");
					String cookieValue = cookieRs.getString("sessionID");
					String userId = cookieRs.getString("user_id");
					int id = cookieRs.getInt("id");
					cookieDB.setExpirationTime(cookieExpirationTime);
					cookieDB.setSessionID(cookieValue);
					cookieDB.setUserId(userId);
					cookieDB.setId(id);
				}
			}
		}
		return cookieDB;

	}

	public static CookieDB updateCookieExpirationTime(CookieDB cookieDB, Connection connection) throws SQLException {

		// VALIDATING COOKIE
		/*
		 * check for cookie's expiration time. If it is still active, calculate the
		 * maxAge and send it back
		 */

		int id = cookieDB.getId();
		Timestamp cookieExpirationTime = cookieDB.getExpirationTime();

		if (cookieExpirationTime.before(Timestamp.from(Instant.now()))) {
			// Cookie has expired, update expiration time to extra 7 days from current time
			Timestamp newExpirationTime = Timestamp.from(Instant.now().plusSeconds(7 * 24 * 60 * 60));
			String updateCookieQuery = "UPDATE session_cookies SET expiration_time = ? WHERE id = ?";
			PreparedStatement psUpdate = connection.prepareStatement(updateCookieQuery);
			psUpdate.setTimestamp(1, newExpirationTime);
			psUpdate.setInt(2, id);
			psUpdate.executeUpdate();
			cookieDB.setExpirationTime(newExpirationTime);
		}
		return cookieDB;
	}
	
	public static Cookie createSessionCookie(String sessionID) {
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
	return sessionCookie;
	}
	
	public static void saveCookieToDatabase(CookieDB sessionCookie, Connection connection) throws SQLException {
		// save sessionCookie in database
	
		// create table session_cookies if not exist
		String sql = "CREATE TABLE IF NOT EXISTS session_cookies(id INT AUTO_INCREMENT PRIMARY KEY, sessionID VARCHAR(100) NOT NULL UNIQUE, user_id VARCHAR(50) NOT NULL, creation_time TIMESTAMP NOT NULL,expiration_time TIMESTAMP NOT NULL);";
		connection.createStatement().executeUpdate(sql);

		// save sessionCookie into the table
		String sql1 = "INSERT INTO session_cookies(sessionID,user_id,creation_time,expiration_time) VALUES (?,?,?,?)";
		PreparedStatement ps1 = connection.prepareStatement(sql1);

			ps1.setString(1, sessionCookie.getSessionID());
			ps1.setString(2, sessionCookie.getUserId());
			ps1.setTimestamp(3, sessionCookie.getCreationTime());
			ps1.setTimestamp(4, sessionCookie.getExpirationTime());
			ps1.executeUpdate();
		
		System.out.println("Session cookie saved!");
	}
}
