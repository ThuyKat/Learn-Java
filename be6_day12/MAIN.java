package be6_day12;
import java.util.Scanner;

import be6_day12.dto.Cart;
import be6_day12.dto.ItemInCart;
import be6_day12.dto.Menu;
import be6_day12.entities.Product;
import be6_day12.entities.Rank;
import be6_day12.entities.Shop;
import be6_day12.entities.User;
import be6_day12.entities.Voucher;
import be6_day12.services.CartService;
import be6_day12.services.DeliveryService;
import be6_day12.services.ProductService;
import be6_day12.services.RankService;
import be6_day12.services.ShopService;
import be6_day12.services.UserService;
import be6_day12.services.VoucherService;

public class MAIN {
	static UserService userService = new UserService();
	static ShopService shopService = new ShopService();
	static VoucherService voucherService = new VoucherService();
	static ProductService productService = new ProductService();
	static DeliveryService deliveryService = new DeliveryService();
	static CartService cartService = new CartService();
	static RankService rankService = new RankService();
	static User user;
	static Shop shop;
	static Menu menu;
	static Cart cart;
	static Voucher voucher;
	static Rank rank;

	public static void main(String[] args) {
		final int VIEW_CART = 1;
		final int VIEW_RANK = 2;
		
		// LOG-IN USER 	
		user = userService.logInUser();
		shop = shopService.getById(user.shopID);
		menu = new Menu(shop);
		cart = new Cart(); 
		
		// KHI LOG-IN THANH CONG, SHOW MENU CHO USER CHON
		while (true) {
			int option = showMainMenuAndGetSelection();
			switch (option) {
			case VIEW_CART:
				
				cartService.showCart(cart);
				for(ItemInCart item: cart.items) {
					cart.total += item.product.price * item.quantity;
				}
				System.out.println("TOTAL : " + cart.total + " AUD");
				System.out.println();
				
				// NEU CART CO ITEMS THI HOI CO CHECKOUT KO?
				while (cart.items.size() != 0) {
					System.out.println("Do you want to check out? (Y/N)");
					Scanner scan = new Scanner(System.in);
					String userAns = scan.nextLine();
					if (userAns.equals("Y")|| userAns.equals("y")) {
						// SHOW SHIPPING OPTIONS
					    int selection = showShippingOptionAndGetSelection();
					    //  TOTAL + DELIVERY FEE
					    Product delivery = deliveryService.getShippingBySelection(selection, menu);
						cart.total+= delivery.price;
						
						cartService.showCart(cart);
						//PRINT DELIVERY INFO
						System.out.println("SHIP : " + delivery.productName + " - " + delivery.price + "AUD");
						System.out.println(deliveryService.showDeliveryTime(selection));

						// KIEM TRA TUNG PRODUCT TRONG CART CO CAI NAO MATCH VOI VOUCHER HAY KO? -->
						// PRINT VOUCHER, RECALCULATE TOTAL
						for (ItemInCart item : cart.items) {
							 voucher = voucherService.getById(item.product.productID,shop);
							if(voucher != null) {
								voucherService.showVoucher(voucher);
								cart.total = cart.total + voucher.voucherValue;
								}
							}
						//SHOW RANK VA RANK DISCOUNT
						
						if(menu.rankOptions != null) {
							rank = rankService.getById(user.point, menu);
							float rankDiscount = (float) (rank.percentDiscountOnShippingFee*delivery.price + rank.percentDiscountOnTotal*cart.total);
							System.out.println( "RANK :"+ rank.name +" - " +"discount amount: "+rankDiscount);
							cart.total = cart.total - rankDiscount;
							
					     }
						// PRINT TOTAL
						
						System.out.println("TOTAL : " + (float)cart.total + " AUD");
						System.out.println();
						// NGUOI DUNG CHON TIEP TUC SHOPPING HOAC DAT HANG
						System.out.println("1. Place order");
						System.out.println("2. Continue shopping");
						Scanner scanInput = new Scanner(System.in);
						int userInput = scanInput.nextInt();
						if (userInput == 1) {
							System.out.println("Order is placed! Thank you... ");
							user.point += Math.floor(cart.total);
							System.out.println("You got extra " + Math.floor(cart.total) +" points with this purchase!");
							userService.updateUserFile(user);
						 //EMPTY CART AFTER CHECKOUT
							cart = new Cart();
						}
							break;
						} else if (userAns.equals("N")|| userAns.equals("n")) {
							break;
						} else {
						System.out.println("Please try again ");
						}
					}
					break;
				
			case VIEW_RANK:
				if(menu.rankOptions != null) {
					
					rank = rankService.getById(user.point, menu);
					System.out.println( "RANK :"+ rank.name);
					break;	
			     }
	
			default: // KHI USER CHON PRODUCT
	// show Product menu: 1.ADD TO SHOPPING CART 2.REMOVE FROM SHOPPING CART
	// cap nhat vao shopping cart
				
				int productID = option - menu.menuOptions.size();
				int selection = showProductMenuAndGetSelection();
				cart = cartService.updateCart(productID, selection, cart,menu);
			}
	}
	}

	public static int showMainMenuAndGetSelection() {
		System.out.println("Welcome " + user.name);
		System.out.println("Main menu");
		int i = 1;
		for (String menuOption : menu.menuOptions) {
			System.out.println(i + ". " + menuOption);
			i++;
		}
		for (Product p : menu.productOptions) {
			System.out.println(i + ". " + p.productName + " : " + p.price + " AUD");
			i++;
		}
		int option = userService.GetUserInput();
		return option;
	}

	public static int showShippingOptionAndGetSelection() {
		if (menu.shippingOptions != null) {
			int j = 1;
			for (Product deliveryOption : menu.shippingOptions) {
				System.out.println(j + ". " + deliveryOption.productName);
				j++;
			}
			int selection = userService.GetUserInput();
			return selection;

		} else {
			return 0;
		}

	}
	
	public static int showProductMenuAndGetSelection() {
		System.out.println("1. Add to shopping cart");
		System.out.println("2. Remove from shopping cart");
		System.out.println("Enter your selection: ");
		int selection;

		while (true) {
			Scanner scan = new Scanner(System.in);
			if (scan.hasNextInt()) {

				selection = scan.nextInt();
				if (selection > 2 || selection < 1) {
					System.out.println("Invalid Input.Please try again");

				} else {break;}

			} else {
				System.out.println("Invalid Input.Please try again");
				scan.next();
			}
		}
		return selection;

	}
}
