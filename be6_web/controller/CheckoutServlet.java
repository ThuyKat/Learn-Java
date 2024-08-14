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
import java.util.Date;
import java.util.List;
import java.util.Random;

import db.Cart;
import db.CookieUtil;
import db.ItemInCart;
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

		//generate invoice number
		String prefix = "INV";
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        String invoiceNumber = prefix+randomNumber;
        //invoice date
        Date orderDate = new Date();
        request.setAttribute("invoiceNumber", invoiceNumber);
        request.setAttribute("orderDate",orderDate );
        request.getRequestDispatcher("successCheckout.jsp").forward(request, response);;
        
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
		
			
		
		// save order
		OrderDatabaseUtil orderRepo = new OrderDatabaseUtil();
		Order order = new Order();
		order.setStatus(OrderStatus.COMPLETED);
		order.setUserId(cart.getUserId());
		try {
			if(cart != null) {
			orderId = orderRepo.saveOrder(order);
			}
			if (cart.getItems() != null || cart.getItems().size() != 0) {

				OrderDetailDatabaseUtil orderDetailRepo = new OrderDetailDatabaseUtil();
				for (ItemInCart item : cart.getItems()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProductId(item.getProduct().getId());
					orderDetail.setProductPrice((float) item.getProduct().getPrice());
					orderDetail.setQuantity(item.getQuantity());
					orderDetail.setOrderId(orderId);
					orderDetails.add(orderDetail);
				}

				orderDetailRepo.saveOrderDetail(orderDetails);
				//set attribute for invoice generation
				float totalPaid =  (float) cart.getTotalPrice();
				request.setAttribute("totalPaid", totalPaid);
				request.setAttribute("purchasedItems",cart.getItems());
				session.setAttribute("cart", new Cart());
				
				//clear the cookieCart if any
				Cookie cookieCart = CookieUtil.findCookieByName(request, "cookieCart");
				
				if(cookieCart !=null) {
				// invalidate the cookieCart by setting its value to null
				Cookie newCookie = new Cookie("cookieCart", null);
				newCookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
				newCookie.setPath("/be6-web"); // Set the cookie path to match the original cookie
				response.addCookie(newCookie);
				}
				doGet(request, response);
			
			}else {
				response.sendRedirect("be6-web/Product");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("failCheckOut.jsp");
		}
		
	}

}
