package model;

import java.util.Arrays;

public class User {

	

	private String username;
	private String password;
	private Integer userId;
	private String gender;
	private String hobbies;
	private String city;
	
	
	public User() {
	
	}
	public User(Integer userId,String userName, String password,  String gender, String hobbies, String city) {
		super();
		this.username = userName;
		this.password = password;
		this.userId = userId;
		this.gender = gender;
		this.hobbies = hobbies;
		this.city = city;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userId=" + userId + ", gender=" + gender
				+ ", hobbies=" + hobbies + ", city=" + city + "]";
	}
	
	
}

