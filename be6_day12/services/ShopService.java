package be6_day12.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import be6_day12.entities.Shop;


//DTO - MENU
//getAll() --> convert from data to objects
//getByID(int id) -->get object by ID, use getAll() and compare id

public class ShopService extends Service<Shop> {

	@Override
	public ArrayList<Shop>getAll(){
		ArrayList<Shop>datas = null;
		try {
			datas = new ArrayList<Shop>();
			File myObj = new File("resource/shop.txt");
			Scanner myReader = new Scanner(myObj);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] splitDatas = data.split(",");
				Shop x = new Shop(Integer.parseInt(splitDatas[0]),splitDatas[1],Boolean.parseBoolean(splitDatas[2]),Boolean.parseBoolean(splitDatas[3]),Integer.parseInt(splitDatas[4]));
				datas.add(x);
			}
			myReader.close();
			return datas;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return datas;
	}
	
	@Override
	public Shop getById(int id) {
		ArrayList<Shop>datas = getAll();
		for (Shop shop :datas) {
			if(shop.id ==id) {
				return shop;
			}
		}
		return null;
	}
//protected String productFile;
//protected String voucherFile;
//public ShopService(String productFile, String voucherFile) {
//	this.productFile = productFile;
//	this.voucherFile = voucherFile;	
//	
//}
//public abstract int showMenu(); //THIS IS TO BE MOVED TO A SEPARATE DTO


//public static ShopService classifyShop(String shop) {
//	
//	if(shop.equals("A")) {
//		return new ShopAService("productA.txt","voucherA.txt"); //THIS SHOULD BE READ IN DIFFERENT FOLDER-SHOPA-SHOPB
//	}else {
//		return new ShopBService("productB.txt","voucherB.txt");
//	}
//}
//
//public abstract DeliveryService optionToShowDelivery();

 
}
