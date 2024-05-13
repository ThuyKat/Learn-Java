package be6_day12.services;

import java.util.ArrayList;


import be6_day12.entities.Shop;

public abstract class Service <T> { // GENERIC TYPE T
public ArrayList<T>getAll(){
	System.out.println("This funtion is not implemented yet");
	return null;
}
public ArrayList<T>getAllByShop(Shop shop){
	System.out.println("This funtion is not implemented yet");
	return null;
}
public abstract T getById(int id);



}
