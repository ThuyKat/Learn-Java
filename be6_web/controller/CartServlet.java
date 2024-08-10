package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Cart;
import db.CartDatabaseUtil;
import db.ItemInCart;
import db.ItemInCartDatabaseUtil;
import db.Product;
import db.ProductDatabaseUtil;
import db.User;
import db.UserDatabaseUtil;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("viewCart ");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		List<ItemInCart>items = cart.getItems();
		if(items !=null) { // in case of logging in from other page than going through Home, cart will not be loaded
		double totalPrice = items.stream().mapToDouble(od -> od.getQuantity() * od.getProduct().getPrice())
			    .sum();
		float roundedTotal = 	Math.round(totalPrice * 100) / 100;
		cart.setTotalPrice(roundedTotal);
		session.setAttribute("cart", cart);
		response.sendRedirect("viewCart.jsp");
		}else {
			response.sendRedirect("/be6-web/Home?action=viewCart");
			
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CartDatabaseUtil cartRepo = new CartDatabaseUtil();
		ItemInCartDatabaseUtil itemInCartRepo = new ItemInCartDatabaseUtil();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Cart cart = (Cart)session.getAttribute("cart");
		List<ItemInCart>items = new ArrayList<>();
		
		int cartId = 0;
		if(cart.getItems() !=null) {
			items = cart.getItems();
			cartId = cart.getId();
			}
	
		ProductDatabaseUtil ProductRepo = new ProductDatabaseUtil();
		// find product by productId
		String productId = (String) request.getParameter("productId");
		int quantity = 1;
		try {
			Product product = ProductRepo.getProductById(productId);
			if (product != null ) {
				System.out.println(product.getName());
				// check if product exists in cart
				boolean isProductExistInCart = false;
				if(items.size() != 0) {
					System.out.println("item.size(): "+items.size());
					for (ItemInCart item : items) {
						if (item.getProduct().getId() == product.getId()) {
							int updatedQuantity = item.getQuantity() + quantity;
							System.out.println("updatedQuantity: "+updatedQuantity);
							item.setQuantity(updatedQuantity);
							isProductExistInCart = true;
							
							break;
						}
	
					}
				}
				// if product is not yet in cart
				if (isProductExistInCart == false) {
					ItemInCart newItem = new ItemInCart();
					newItem.setProduct(product);
					newItem.setQuantity(quantity);
					items.add(newItem);
				}
				// reset the itemCount in session:
				int countItem = (int) session.getAttribute("countItem");
				countItem +=quantity;
				session.setAttribute("countItem", countItem);
				
				if(username ==null) {
				// guest user: convert the list of items to string and add it to cart cookie
				String serializedItems = "";
				for (int i = 0; i < items.size(); i++) {
					serializedItems += items.get(i).serialize();

					if (i < items.size() - 1) {
						serializedItems += "|";
					}
				}

				String cookieCartName = "cookieCart";
				String cookieCartValue = serializedItems;
				Cookie cookieCart = new Cookie(cookieCartName, cookieCartValue);
				cookieCart.setMaxAge(60 * 60 * 24 * 7); // cookie will last for 7 days
				response.addCookie(cookieCart);
				
				//update cart attribute in session
				cart.setItems(items);
				session.setAttribute("cart", cart);
				}else {
				//logged in user
				//if cart not exist, save new cart to the db
					if(cartId == 0) {
						System.out.println("cart not exist");
						//save cart to DB
						User user = UserDatabaseUtil.getUserByUsername(username);
						cart.setUserId(user.getUserId());
						
						if(cart.getItems().size()!=0) {
						cartRepo.saveCart(cart);
						}
						//retrieve cartId
						cart = cartRepo.getCartByUserId(user.getUserId());
						cartId =cart.getId();
						
						itemInCartRepo.saveItemInCart(items,cartId);
						//update total price attribute
						double totalPrice = items.stream().mapToDouble(od -> od.getQuantity() * od.getProduct().getPrice())
							    .sum();
						float roundedTotal = 	Math.round(totalPrice * 100) / 100;
						cart.setTotalPrice(roundedTotal);
						
						cartRepo.updateCart(cart);
					}else {
				//if cart exist, update cart and itemInCart
					System.out.println("cart is in db");
					itemInCartRepo.updateItemInCart(items,cartId);
					//update total price attribute
					double totalPrice = items.stream().mapToDouble(od -> od.getQuantity() * od.getProduct().getPrice())
						    .sum();
					float roundedTotal = 	Math.round(totalPrice * 100) / 100;
					cart.setTotalPrice(roundedTotal);
					
					cartRepo.updateCart(cart);
					}
				}

			} else {
				response.sendRedirect("error.jsp");
			}
			
			 String returnURI = (String)request.getParameter("returnURI");

				System.out.println("returnURI:" +returnURI);
				response.sendRedirect(returnURI);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}

}
