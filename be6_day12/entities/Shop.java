package be6_day12.entities;

public class Shop {
public int id; // REQUIRED PUBLIC ACCESS TO GET OBJECTS BY SHOP ID IN SERVICES
String name;
public boolean hasUserRank; // REQUIRED PUBLIC ACCESS TO CONSTRUCT MENU OBJECT
public boolean hasShippingMethods;// REQUIRED PUBLIC ACCESS TO CONSTRUCT MENU OBJECT
public int defaultShipFee;
public Shop(int id, String name, boolean hasUserRank, boolean hasShippingMethods, int defaultShipFee) {
	super();
	this.id = id;
	this.name = name;
	this.hasUserRank = hasUserRank;
	this.hasShippingMethods = hasShippingMethods;
	this.defaultShipFee = defaultShipFee;
}

}
