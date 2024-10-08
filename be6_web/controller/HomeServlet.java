package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
import db.User;
import db.UserDatabaseUtil;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// view cart
		Cart cart = new Cart();
		// get the list of items in cart
		List<ItemInCart> items = new ArrayList<>();
		

		// add to cart
		// retrieve cart from cart cookie
		// check if user is a guest

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username + " I am at HomeServlet checking if user is logged in");

		// find cookieCart
		Cookie cookieCart = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("cookieCart")) {
					cookieCart = cookie;
					System.out.println("cookieCart is found!");

					String[] serialisedItems = cookie.getValue().split("\\|");
					// To split the string using the pipe character as a literal, you need to escape
					// it in the regular expression.
					// do this by using \\|.
					for (String serialisedItem : serialisedItems) {
						try {
							System.out.println("serialisedItem: " + serialisedItem);
							ItemInCart item = ItemInCart.deserialize(serialisedItem);
							items.add(item);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					cart.setItems(items);
				}
			}
		}
		System.out.println(items.size());
		if (username != null) {
			System.out.println("username is found :" + username);
			// retrieve cart associated with user in database
			User user;
			try {
				user = UserDatabaseUtil.getUserByUsername(username);
				int userId = user.getUserId();
				// replace the guest cart with db cart
				CartDatabaseUtil cartRepo = new CartDatabaseUtil();
				Cart cartDB = cartRepo.getCartByUserId(userId);
				
				
				if(cartDB !=null) {
					cart = cartDB;
					items = cart.getItems();
				}
				
//				if(cookieCart !=null) {
//				// invalidate the cookieCart by setting its value to null
//				Cookie newCookie = new Cookie("cookieCart", null);
//				newCookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
//				newCookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
//				response.addCookie(newCookie);
//				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		cart.setItems(items);
		session.setAttribute("cart", cart);
		
		int countItem = 0;
		if (items.size() != 0) {
			countItem = items.size();
		}
		session.setAttribute("countItem", countItem);
		String action = (String)request.getParameter("action");
		System.out.println(action + "action captured at HomeServlet");
		
		if(action =="viewCart") {
			System.out.println("action == view cart, I am going to cart");
			response.sendRedirect("/be6-web/Cart");
		}else {
		response.sendRedirect("/be6-web/Product");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
