package be6_day12.dto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import be6_day12.entities.Product;
import be6_day12.entities.Rank;
import be6_day12.entities.Shop;
import be6_day12.services.ProductService;

public class Menu {
	public ArrayList<String>menuOptions = new ArrayList<String>(); // public access required to be used in MAIN
	public ArrayList<Product>productOptions = new ArrayList<Product>();
	public ArrayList<Product>shippingOptions; //** // public access required to be used in MAIN
	public ArrayList<Rank>rankOptions;
	public Menu(Shop shop){
		this.menuOptions.add("View Cart");
		if(shop.hasUserRank) {
			this.menuOptions.add("View Rank");
		}
		
		ProductService productService = new ProductService();
		this.productOptions = productService.getAllbyShop(shop);
		
		if(shop.hasShippingMethods) {
			this.shippingOptions = new ArrayList<Product>();
			Product saving = new Product(1,"Saving",5);
			Product fast = new Product(2,"Fast",15);
			Product basic = new Product(3,"Basic",10);
 			this.shippingOptions.add(saving);
			this.shippingOptions.add(fast);
			this.shippingOptions.add(basic);
			
		}
		
		if(shop.hasUserRank) {
			this.rankOptions = new ArrayList<Rank>();
			
			try {
				File myObj = new File("resource/shop-"+shop.id+"/rank.txt");
				Scanner myReader = new Scanner(myObj);
				while(myReader.hasNextLine()) {
					String data = myReader.nextLine();
					String[] splitDatas = data.split(",");
					Rank x = new Rank(splitDatas[0],Float.parseFloat(splitDatas[1]),Float.parseFloat(splitDatas[2]),Integer.parseInt(splitDatas[3]),Integer.parseInt(splitDatas[4]));
					rankOptions.add(x);
				}
				myReader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
}

