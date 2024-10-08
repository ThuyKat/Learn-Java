package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Feedback;
import db.FeedbackDatabaseUtil;
import db.Product;
import db.ProductDatabaseUtil;
import db.User;
import db.UserDatabaseUtil;

/**
 * Servlet implementation class FeedbackServlet
 */
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Feedback>feedbacks=null;
		String productId = request.getParameter("productId");
		Product product = (Product) request.getAttribute("product");
		String quantity = request.getParameter("quantity");
		if(product ==null) {
			try {
				ProductDatabaseUtil productData = new ProductDatabaseUtil();			
				product = productData.getProductById(productId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("productID captured at FeedbackServlet " + productId);
		try {
			FeedbackDatabaseUtil feedbackData = new FeedbackDatabaseUtil();
			feedbacks =  feedbackData.getFeedbackByProductId(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create a map to store feedback IDs and corresponding usernames
        Map<Integer, String> feedbackUsernames = new HashMap<>();
        for (Feedback feedback : feedbacks) {
            User user = null;
			try {
				user = UserDatabaseUtil.getUserById(feedback.getUserId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String username = user.getUserName();
            feedbackUsernames.put(feedback.getId(), username);
        }
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("feedbackUsernames", feedbackUsernames);
		request.setAttribute("returningFromFeedback",true);
		request.setAttribute("quantity", quantity);
		System.out.println("I am returing back to productDetail from Feedback");
		String redirectURL ="ProductDetail?productId="+ productId;
		request.getRequestDispatcher(redirectURL).forward(request, response); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//FEEDBACK POSTING 
		System.out.println("posting feedback...");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//if user not yet logged in, redirect user to login servlet
		System.out.println(username);
		if(username == null) {
			String uriPath = request.getRequestURI(); 
			System.out.println(uriPath);
			String queryString = "productId="+request.getParameter("productId");
			System.out.println(queryString);
			String redirectURI = "/be6-web/Login?returnURI="+uriPath;
			if(queryString!=null) {
				redirectURI += "?" + queryString;
			}
			response.sendRedirect(redirectURI); // show the login URL instead of productDetail URL
		// if user has already logged in, save feedback to database
		}else {
			try {
			System.out.println("user has logged in");
			FeedbackDatabaseUtil feedbackData = new FeedbackDatabaseUtil();
			// save feedback details to database
			int productId = Integer.parseInt(request.getParameter("productId"));
			User user = UserDatabaseUtil.getUserByUsername(username);
			int userId = user.getUserId();
			String feedbackText = request.getParameter("feedback");
			String subject = request.getParameter("subject");
			feedbackData.saveFeedback( productId, userId, feedbackText, subject);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//reload the page with the new feedback added
			doGet(request,response);
		}
	}

}
