package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Cart;
import db.Feedback;
import db.FeedbackDatabaseUtil;
import db.Product;
import db.ProductDatabaseUtil;
import db.User;
import db.UserDatabaseUtil;

/**
 * Servlet implementation class ProductDetailsServlet
 */
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		Product product = null;
		System.out.println("I am at productDetail servlet, quantity chosen is: "+request.getAttribute("quantity"));
		Boolean returningFromFeedback = (Boolean) request.getAttribute("returningFromFeedback");
		
		
		try {
			ProductDatabaseUtil productData = new ProductDatabaseUtil();			
			product = productData.getProductById(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(product != null) {
		  request.setAttribute("product", product);
        } else {
        	response.sendRedirect("error.jsp");
        }
        String URI =request.getRequestURI();
        System.out.println("current request URI: "+ URI);
        String queryString = request.getQueryString();
        String fullURI = URI+="?"+queryString;
        request.setAttribute("returnURI", fullURI);
		System.out.println("redirecting to feedback controller");
		System.out.println("returning from feedback is " + returningFromFeedback );
		
		if(returningFromFeedback == null) {
			System.out.println("going to FeedbackServlet now");
		request.getRequestDispatcher("/Feedback").forward(request, response); 
//			response.sendRedirect("be6-web/Feedback");
		}else if(returningFromFeedback ==false) {
			int selectedQuantity = (int) request.getAttribute("quantity");
			response.sendRedirect("/be6-web/Feedback?productId="+productId+"&&"+"quantity="+selectedQuantity);
		
		}else {
		request.getRequestDispatcher("productDetail.jsp").forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		int quantity = 0;
		// Get  parameters from the request
        String quantityParam = request.getParameter("quantity");
        String action = request.getParameter("action");
        
		//UDPATE QUANTITY
           //get the current quantity input
        if (quantityParam != null && !quantityParam.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityParam);
            } catch (NumberFormatException e) {
                quantity = 0; // Default to 0 if parsing fails
            }
        }
           //submit quantity
        if("+".equals(action)) {
        	quantity ++;
        }else if("-".equals(action)) {
        	quantity--;
        }
        // Ensure quantity does not go below 0
        if (quantity < 0) {
            quantity = 0;
        }
        request.setAttribute("quantity", quantity);
        //avoid dispatch post request to FeedbackServlet
        Boolean returningFromFeedback = false;
        request.setAttribute("returningFromFeedback", returningFromFeedback);
        
        //refresh the page
        
        
        doGet(request,response); //still post request here!!! be careful if forward this to Feedback, it will be post, not get method
		
	}
}
