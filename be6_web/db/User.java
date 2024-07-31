package db;
public class User {
	
	private String userName;
	private String password;
	private Integer userId;
	private String gender;
	private String hobbies;
	private String city;
	
	
	public User() {
	
	}
	public User(Integer userId,String userName, String password,  String gender, String hobbies, String city) {
		super();
		this.userName = userName;
		this.password = password;
		this.userId = userId;
		this.gender = gender;
		this.hobbies = hobbies;
		this.city = city;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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
}
