package db;

import java.sql.Timestamp;

public class CookieDB {

	private int id;
	private String sessionID;
	private String userId;
	private Timestamp expirationTime;
	private Timestamp creationTime;

	public CookieDB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CookieDB(int id, String sessionID, String userId,Timestamp expirationTime,Timestamp creationTime) {
		super();
		this.id = id;
		this.sessionID = sessionID;
		this.userId = userId;
		this.expirationTime=expirationTime;
		this.creationTime = creationTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	
}
