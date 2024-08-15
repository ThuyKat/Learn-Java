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
		String username = (String) session.getAttribute("username");
		if (cart != null) { //this already ensure items !=null because we initialize new item list with every new cart
				if(username !=null) { //when user login , session is changed so total price needs recalculation
				List<ItemInCart> items = cart.getItems(); 
			
				double totalPrice = items.stream().mapToDouble(od -> od.getQuantity() * od.getProduct().getPrice())
						.sum();
				float roundedTotal = Math.round(totalPrice * 100) / 100;
				cart.setTotalPrice(roundedTotal);
				session.setAttribute("cart", cart);
				}
				response.sendRedirect("viewCart.jsp");
			} else { // in case of logging in from other page than going through Home,
				// cart will not be loaded
				response.sendRedirect("/be6-web/Home?action=viewCart");

			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartDatabaseUtil cartRepo = new CartDatabaseUtil();
		ItemInCartDatabaseUtil itemInCartRepo = new ItemInCartDatabaseUtil();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Cart cart = new Cart();
		if(session.getAttribute("cart")!= null) {
		cart = (Cart) session.getAttribute("cart");
		}
		List<ItemInCart> items = new ArrayList<>();

		int cartId = 0;
		
//		if (cart.getItems() != null) { // if condition can be obmit because we initiate new item list with new cart in entity Cart
			items = cart.getItems();
			cartId = cart.getId();
//		}

		ProductDatabaseUtil ProductRepo = new ProductDatabaseUtil();
		// find product by productId
		String productId = (String) request.getParameter("productId");
		int quantity = 0;
		if(request.getParameter("quantity") != "") {
		quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		try {

			if (quantity > 0) {
				Product product = ProductRepo.getProductById(productId);
				if (product != null) {
					System.out.println(product.getName());
					// check if product exists in cart
					boolean isProductExistInCart = false;
					if (items.size() != 0) {
						for (ItemInCart item : items) {
							if (item.getProduct().getId() == product.getId()) {
								int updatedQuantity = item.getQuantity() + quantity;
								System.out.println("updatedQuantity: " + updatedQuantity);
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
						int countItem = items.size();
						session.setAttribute("countItem", countItem);
					}

					// update total price attribute
					double totalPrice = items.stream()
							.mapToDouble(od -> od.getQuantity() * od.getProduct().getPrice()).sum();
					float roundedTotal = Math.round(totalPrice * 100) / 100;
					cart.setTotalPrice(roundedTotal);

					if (username == null) {
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
						
						
						
					} else {
						// logged in user
						// if cart not exist, save new cart to the db
						if (cartId == 0) { // cart id is 0 after checkout, old cart is still in database, only item_in_cart is updated
							
							User user = UserDatabaseUtil.getUserByUsername(username);
							//find cart by userId in database
							Cart cartDB = cartRepo.getCartByUserId(user.getUserId());
							if(cartDB == null) {
								// if no cart is found in database,save cart to DB
								System.out.println("cart not exist");
								cart.setUserId(user.getUserId());
	
								if (cart.getItems().size() != 0) {
									
									cartRepo.saveCart(cart);
								}
							}
							// retrieve cartId
							cart = cartRepo.getCartByUserId(user.getUserId());
							cartId = cart.getId();

							itemInCartRepo.saveItemInCart(items, cartId);
							
						} else {
							// if cart exist, update cart and itemInCart
							System.out.println("cart is in db");
							itemInCartRepo.updateItemInCart(items, cartId);

						}


						cartRepo.updateCart(cart);
					}
					
					// update cart attribute in session
					cart.setItems(items);
					session.setAttribute("cart", cart);

				} else {
					response.sendRedirect("error.jsp");
				}
			}

			String returnURI = (String) request.getParameter("returnURI");

			System.out.println("returnURI:" + returnURI);
			response.sendRedirect(returnURI);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
