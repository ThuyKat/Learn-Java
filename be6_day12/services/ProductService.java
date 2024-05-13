package be6_day12.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import be6_day12.entities.Product;
import be6_day12.entities.Shop;


public class ProductService extends Service<Product> {
	

@Override
public Product getById(int id) {
	// TODO Auto-generated method stub
	return null;
}
public ArrayList<Product>getAllbyShop(Shop shop){
	ArrayList<Product>datas = null;
	try {
		datas = new ArrayList<Product>();
		File myObj = new File("resource/shop-"+shop.id+"/product.txt");
		Scanner myReader = new Scanner(myObj);
		while(myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitDatas = data.split(",");
			Product x = new Product(Integer.parseInt(splitDatas[0]),splitDatas[1],Integer.parseInt(splitDatas[2]));
			datas.add(x);
		}
		myReader.close();
		return datas;
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.out.println("File is not found : resource/shop-"+shop.id+"/product.txt");
	}
	return datas;
	
}


}
