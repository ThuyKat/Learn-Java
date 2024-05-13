package be6_day12.entities;

public class User {
public String ID;
public String password;
public String name;
public int shopID;
public int point;

public User(String ID, String password,  String name,int shopID,int point) {
	this.ID = ID;
	this.password = password;
	this.name = name;
	this.shopID = shopID;
	this.point = point; // to rank users
	
}

}

