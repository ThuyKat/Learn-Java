package be6_day12.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import be6_day12.entities.Shop;
import be6_day12.entities.Voucher;


public class VoucherService extends Service<Voucher> {
//read Voucher file: product name, voucher code ,discount in AUD

	public void showVoucher(Voucher voucher) {
		System.out.println("DISCOUNT CODE APPLIED:"+voucher.voucherCode +": "+ voucher.voucherValue+" AUD"); 
	}

	
	public Voucher getById(int productID, Shop shop) {
		ArrayList<Voucher>datas = getAllByShop(shop);
		for(Voucher v : datas) {
			if(v.productID==productID) {
				return v;
			}
		}
		return null;
	}
	
	public ArrayList<Voucher>getAllByShop(Shop shop){
		ArrayList<Voucher>datas = null;
		try {
			datas = new ArrayList<Voucher>();
			File myObj = new File("resource/shop-"+shop.id+"/voucher.txt");
			Scanner myReader = new Scanner(myObj);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] splitDatas = data.split(",");
				Voucher x = new Voucher (splitDatas[0],Integer.parseInt(splitDatas[1]),Double.parseDouble(splitDatas[2]));
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
	public Voucher getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
