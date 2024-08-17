package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import db.Cart;
import db.CartDatabaseUtil;
import db.CookieUtil;
import db.DatabaseConnectionUtil;
import db.ItemInCart;
import db.ItemInCartDatabaseUtil;
import db.Order;
import db.OrderDatabaseUtil;
import db.OrderDetail;
import db.OrderDetailDatabaseUtil;
import db.OrderStatus;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// generate invoice number
		String prefix = "INV";
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		String invoiceNumber = prefix + randomNumber;
		// invoice date
		Date orderDate = new Date();
		request.setAttribute("invoiceNumber", invoiceNumber);
		request.setAttribute("orderDate", orderDate);
		request.getRequestDispatcher("successCheckout.jsp").forward(request, response);
		;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get cart from session
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart"); // cart is initiated at HomeServlet so it is not null
		List<OrderDetail> orderDetails = new ArrayList<>();
		int orderId = -1;

		Connection connection = null;
		try {
			// Get a database connection
			connection = DatabaseConnectionUtil.getDatabaseConnection(); 

			// Change from auto-commit mode to manual commit mode -> only commit once all transaction is completed
			connection.setAutoCommit(false);

			if (cart != null && cart.getItems().size() != 0) { // prevent saving empty cart

				// save order
				OrderDatabaseUtil orderRepo = new OrderDatabaseUtil();
				Order order = new Order();
				order.setStatus(OrderStatus.COMPLETED);
				order.setUserId(cart.getUserId());
				orderId = orderRepo.saveOrder(order, connection);

				// save order details
				OrderDetailDatabaseUtil orderDetailRepo = new OrderDetailDatabaseUtil();
				for (ItemInCart item : cart.getItems()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProductId(item.getProduct().getId());
					orderDetail.setProductPrice((float) item.getProduct().getPrice());
					orderDetail.setQuantity(item.getQuantity());
					orderDetail.setOrderId(orderId);
					orderDetails.add(orderDetail);
				}

				orderDetailRepo.saveOrderDetail(orderDetails, connection);

				// Commit transaction
				connection.commit();

				// set attribute for invoice generation
				float totalPaid = (float) cart.getTotalPrice();
				request.setAttribute("totalPaid", totalPaid);
				request.setAttribute("purchasedItems", cart.getItems());

				// clear cart in session
				session.setAttribute("cart", new Cart());
				session.setAttribute("countItem", 0);

				// clear the cookieCart if any
				// not only for guest user, in case user logged in and not yet have associated
				// cart, what in cookie will be saved to order
				Cookie cookieCart = CookieUtil.findCookieByName(request, "cookieCart");

				if (cookieCart != null) {
					// invalidate the cookieCart by setting its value to null
					Cookie newCookie = new Cookie("cookieCart", null);
					newCookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
					newCookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
					response.addCookie(newCookie);
				}

				if (session.getAttribute("username") != null) {
					// for logged in user ,update itemInCart and cart in database

					CartDatabaseUtil cartRepo = new CartDatabaseUtil();
					ItemInCartDatabaseUtil itemInCartRepo = new ItemInCartDatabaseUtil();
					cartRepo.updateCart(cart);
					cart.setItems(new ArrayList<>());
					itemInCartRepo.updateItemInCart(cart.getItems(), cart.getId());

				}
				doGet(request, response);

			} else {
				response.sendRedirect("viewCart.jsp");
			}
		} catch (SQLException e) {

			// Revert transaction in case of error
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			e.printStackTrace();
			response.sendRedirect("failCheckOut.jsp");
		} finally {
			try {
				// reset auto-commit mode and close the connection
				if (connection != null) {
					connection.setAutoCommit(true);
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
